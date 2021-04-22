package com.project.GPrint3D.controller.cliente.carrinho;

import com.project.GPrint3D.util.CarrinhoUtil;
import com.project.GPrint3D.util.GeradorCodigoUtil;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.project.GPrint3D.configuration.SecurityConfig;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.CuponsPromocoesModel;
import com.project.GPrint3D.model.CuponsTrocasModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.PedCartoesModel;
import com.project.GPrint3D.model.PedComFretesModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.UsuariosModel;

import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.CartoesPadroesRepository;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.CuponsPromocoesRepository;
import com.project.GPrint3D.repository.CuponsTrocasRepository;
import com.project.GPrint3D.repository.EndEntregasPadroesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CarrinhosService;
import com.project.GPrint3D.service.PedCartoesService;
import com.project.GPrint3D.service.PedComFretesService;
import com.project.GPrint3D.service.PedidosComprasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente/carrinho")
public class CartCliController extends CarrinhoUtil
{
    private Boolean primeiraExecucao = true;
    private UsuariosModel usuarioMod = new UsuariosModel();
    private ClientesModel clienteMod = new ClientesModel();
    private CarrinhosModel carrinhoMod = new CarrinhosModel();
    private EnderecosModel enderecoMod = new EnderecosModel();
    private PedComFretesModel freteMod = new PedComFretesModel();
    private List<CuponsTrocasModel> listaCuponsTrocaDisponiveisMod = new ArrayList<CuponsTrocasModel>();
    private List<CuponsTrocasModel> listaCuponsTrocaUtilizadosMod = new ArrayList<CuponsTrocasModel>();
    private CuponsPromocoesModel cupomPromocoesMod = new CuponsPromocoesModel();
    private List<PedCartoesModel> listaPedCartoes = new ArrayList<PedCartoesModel>();
    private HashMap<Integer, String> cartaoValidador = new HashMap<Integer, String>();
    private Double descontoCupomPromocional = 0D;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private EndEntregasPadroesRepository enderecosEntregaPadroesRepository;

    @Autowired
    private CartoesPadroesRepository cartoesPadroesRepository;

    @Autowired
    private CartoesRepository cartoesRepository;
    
    @Autowired
    private CarrinhosRepository carrinhosRepository;

    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private CuponsTrocasRepository cuponsTrocasRepository;

    @Autowired
    private CuponsPromocoesRepository cuponsPromocoesRepository;

    @Autowired
    private PedidosComprasService pedidosComprasService;

    @Autowired
    private PedComFretesService pedComFretesService;

    @Autowired
    private PedCartoesService pedCartoesService;

    @Autowired
    private CarrinhosService carrinhosService;

    @Autowired
    private SecurityConfig securityConfig;
    
    //Tela dos endereços do cliente
    @RequestMapping("/escolherEndereco")
    public ModelAndView escolherEndereco(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/escolherEndereco");
        
        usuarioMod = usuariosRepository.findByEmail(principal.getName());
        clienteMod = usuarioMod.getCliente();

        if (enderecoMod.getEndId() == 0)
        {
            enderecoMod = enderecosEntregaPadroesRepository.findByClienteId(clienteMod.getCliId()).getEndereco();
        }

        //Resetar as escolhas do pagamento
        listaPedCartoes = new ArrayList<PedCartoesModel>();
        listaCuponsTrocaDisponiveisMod = new ArrayList<CuponsTrocasModel>();
        listaCuponsTrocaUtilizadosMod = new ArrayList<CuponsTrocasModel>();
        cupomPromocoesMod = new CuponsPromocoesModel();
        descontoCupomPromocional = 0D;
        primeiraExecucao = true;

        mv.addObject("cliente", clienteMod);
        mv.addObject("endereco", enderecoMod);

        return mv;
    }

    //Alterar endereco de entrega
    @PostMapping("/alterarEndereco")
    public ModelAndView alterarEnderecoEntrega(@RequestParam(name = "id") Integer enderecoId)
    {
        enderecoMod = enderecosRepository.findOneById(enderecoId);

        return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
    }

    //Grava os dados do endereço do cliente
    @PostMapping("/confirmarEndereco")
    public ModelAndView confirmarEndereco(@RequestParam(name = "frete", defaultValue = "") String freteParam, @Valid EnderecosModel endereco)
    {
        if (freteParam.equals(""))
        {
            String response = "Frete não selecionado, por favor selecione o frete.";

            return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
        }
        
        freteParam = freteParam.substring(1, freteParam.length()-1);
        String[] keyValuePairs = freteParam.split(",");
        Map<String,String> freteMap = new HashMap<String,String>();               

        for(String pair : keyValuePairs)
        {
            String[] entry = pair.split(":");
            freteMap.put(entry[0].trim(), entry[1].trim());
        }

        freteMod.setPcfEmpresa(freteMap.get("\"empresa\"").replace("\"", ""));
        freteMod.setPcfModalidade(freteMap.get("\"modalidade\"").replace("\"", ""));
        freteMod.setPcfPrazo(freteMap.get("\"prazo\"").replace("\"", ""));
        freteMod.setPcfValor(Double.parseDouble(freteMap.get("\"valor\"").replace("\"", "")));

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }
    
    //Tela dos enderços do clientes
    @RequestMapping("/escolherPagamento")
    public ModelAndView escolherPagamento(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/escolherPagamento");

        if (freteMod.getPcfModalidade() == null || freteMod.getPcfModalidade() == "")
        {
            String response = "Frete inválido, recomece o processo de compra.";

            return new ModelAndView("redirect:/cliente/carrinho/escolherEndereço");
        }

        usuarioMod = usuariosRepository.findByEmail(principal.getName());
        clienteMod = usuarioMod.getCliente();
        carrinhoMod = carrinhosRepository.findByClienteId(clienteMod.getCliId());

        if (primeiraExecucao == true)
        {
            if (listaPedCartoes.size() == 0)
            {
                PedCartoesModel pedCartao = new PedCartoesModel();

                pedCartao.setCartao(cartoesPadroesRepository.findByClienteId(clienteMod.getCliId()).getCartao());
                listaPedCartoes.add(pedCartao);
            }

            if (listaCuponsTrocaDisponiveisMod.size() == 0)
            {
                listaCuponsTrocaDisponiveisMod = cuponsTrocasRepository.findAllByCliente(clienteMod.getCliId());
            }

            primeiraExecucao = false;
        }

        mv.addObject("cliente", clienteMod);
        mv.addObject("carrinho", carrinhoMod);
        mv.addObject("frete", freteMod);
        mv.addObject("listaPedCartoes", listaPedCartoes);
        mv.addObject("totalCartoes", valorTotalCartoes(listaPedCartoes));
        mv.addObject("cuponsTrocasDisponiveis", listaCuponsTrocaDisponiveisMod);
        mv.addObject("cuponsTrocasUtilizados", listaCuponsTrocaUtilizadosMod);
        mv.addObject("totalCuponsTroca", valorTotalCupons(listaCuponsTrocaUtilizadosMod));
        mv.addObject("cupomPromocaoUtilizado", cupomPromocoesMod);
        mv.addObject("descontoCupomPromocional", descontoCupomPromocional);

        cartaoValidador = new HashMap<Integer, String>();

        return mv;
    }

    //Aplica um cupom de troca
    @PostMapping("/aplicaCupomTroca")
    public ModelAndView aplicarCupomTroca(@RequestParam(name = "codigo") String codigo)
    {
        CuponsTrocasModel cupom = new CuponsTrocasModel();

        cupom = cuponsTrocasRepository.findByCodigo(codigo);

        listaCuponsTrocaUtilizadosMod.add(cupom);
        listaCuponsTrocaDisponiveisMod.remove(cupom);

        BigDecimal valorPendenteBD = ((BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor()))).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes)))).subtract(BigDecimal.valueOf(valorTotalCupons(listaCuponsTrocaUtilizadosMod)));

        validarCupons(listaCuponsTrocaDisponiveisMod, listaCuponsTrocaUtilizadosMod, valorPendenteBD.doubleValue());
        
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Aplica um cupom de troca
    @PostMapping("/removeCupomTroca")
    public ModelAndView removerCupom(@RequestParam(name = "codigo") String codigo)
    {
        CuponsTrocasModel cupom = new CuponsTrocasModel();

        cupom = cuponsTrocasRepository.findByCodigo(codigo);

        listaCuponsTrocaDisponiveisMod.add(cupom);
        listaCuponsTrocaUtilizadosMod.remove(cupom);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Aplica um cupom promocional
    @PostMapping("/aplicaCupomPromocional")
    public ModelAndView aplicarCupomPromocional(@RequestParam (name = "codigo", defaultValue = "") String codigo)
    {
        cupomPromocoesMod = cuponsPromocoesRepository.findByCodigo(codigo);

        if (cupomPromocoesMod == null)
        {
            cupomPromocoesMod = new CuponsPromocoesModel();

            String response = "Codigo inválido";

            return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
        }

        descontoCupomPromocional = valorDescontoCupomPromocional(carrinhoMod.getCarId(), cupomPromocoesMod);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Remove um cupom promocional
    @PostMapping("/removeCupomPromocional")
    public ModelAndView removerCupomPromocional()
    {
        cupomPromocoesMod = new CuponsPromocoesModel();

        descontoCupomPromocional = 0D;

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Inclui cartão para pagamento
    @PostMapping("/inlcuiCartao")
    public ModelAndView incluirCartao(@RequestParam(name = "id") Integer cartaoId)
    {
        CartoesModel cartaoIncluir = cartoesRepository.findOneById(cartaoId);
        PedCartoesModel pedCartaoInlcuir = new PedCartoesModel();

        for (PedCartoesModel cartaoPed : listaPedCartoes)
        {
            if (cartaoPed.getCartao().getCrtId() == cartaoIncluir.getCrtId())
            {
                String response = "Cartão já incluso, inclua outro cartão.";

                return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
            }
        }
        pedCartaoInlcuir.setCartao(cartaoIncluir);
        
        listaPedCartoes.add(pedCartaoInlcuir);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Remove cartão para pagamento
    @PostMapping("/removeCartao")
    public ModelAndView removeCartao(@RequestParam(name = "index") int index)
    {
        listaPedCartoes.remove(index);

        if (cartaoValidador.get(index) != null)
        {
            cartaoValidador.remove(index);
        }
        
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Insere o valor a ser pago no cartão
    @PostMapping("/atualizarCartao")
    public ModelAndView atualizarCartao(@RequestParam(name = "index") Integer index, @RequestParam(name = "valor") String valor)
    {
        //É necessário calcular e confirmar os valores (compras + frete) - (cupons + pagamento) = 0
        //Falta colocar o cupom na fórmula

        if (valor == "")
        {
            listaPedCartoes.get(index).setPctValor(0.0);

            return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
        }
        
        Double valorCrt = Double.parseDouble(valor.replace("R$ ", "").replace(".", "").replace(",", "."));

        BigDecimal valorPendenteBD = (((BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor()))).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes)))).subtract(BigDecimal.valueOf(valorTotalCupons(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)));

        Double valorInserir = 0.0;

        if (valorCrt <= 0.0)
        {
            listaPedCartoes.get(index).setPctValor(valorInserir);

            return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
        }

        if (listaPedCartoes.get(index).getPctValor() > 0.0);
        {
            listaPedCartoes.get(index).setPctValor(valorInserir);
            valorPendenteBD = (((BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor()))).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes)))).subtract(BigDecimal.valueOf(valorTotalCupons(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)));

            if (valorCrt > 0 && valorCrt < 10)
            {
                if (carrinhoMod.getValorTotal() > 10 && listaPedCartoes.size() > 1)
                {
                    String response = "Valor menor que o mínimo permitido";

                    valorInserir = 0.0;
                }
                else
                {
                    if (valorPendenteBD.doubleValue() > 0 && valorPendenteBD.doubleValue() < 10)
                    {
                        valorInserir = valorCrt;
                    }
                    else
                    {
                        String response = "Valor menor que o mínimo permitido";
    
                        valorInserir = 0.0;
                    }
                }
            }
            else if (valorCrt > valorPendenteBD.doubleValue())
            {
                if (valorPendenteBD.doubleValue() > 0)
                {
                    String response = "Não é necessário efetuar pagamento com o cartão";

                    valorInserir = valorPendenteBD.doubleValue();
                }
            }
            else
            {
                valorInserir = valorCrt;
            }
        }

        listaPedCartoes.get(index).setPctValor(valorInserir);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Confirmar pagamento
    @PostMapping("/confirmarPagamento")
    public ModelAndView confirmarPagamento()
    {
        BigDecimal valorPendenteBD = ((BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor()))).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes)))).subtract(BigDecimal.valueOf(valorTotalCupons(listaCuponsTrocaUtilizadosMod)));

        if (valorPendenteBD.doubleValue() != 0)
        {
            String response = "Você precisa pagar todo o valor pendente. Valor pendente de: R$ " + valorPendenteBD.toString().replace(".", ",");

            return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
        }

        return new ModelAndView("redirect:/cliente/carrinho/confirmarPedido");
    }

    //Tela de confirmação do pedido
    @RequestMapping("/confirmarPedido")
    public ModelAndView confirmarPedido()
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/confirmarPedido");

        mv.addObject("cliente", clienteMod);
        mv.addObject("endereco", enderecoMod);
        mv.addObject("carrinho", carrinhoMod);
        mv.addObject("frete", freteMod);
        mv.addObject("listaPedCartoes", listaPedCartoes);
        mv.addObject("totalCartoes", valorTotalCartoes(listaPedCartoes));
        mv.addObject("cartaoValidador", cartaoValidador);

        return mv;
    }

    //Confirmar CVV
    @PostMapping("/validarCvv")
    public ModelAndView validarCvv(@RequestParam(name = "index") Integer index, @RequestParam(name = "cvv") String cvv)
    {
        if (securityConfig.passwordEncoder().matches(cvv, listaPedCartoes.get(index).getCartao().getCrtCvv()))
        {
            if (cartaoValidador.get(index) == null)
            {
                cartaoValidador.put(index, "Confirmado");
            }
        }

        return new ModelAndView("redirect:/cliente/carrinho/confirmarPedido");
    }

    @PostMapping("/cadastrarPedido")
    public ModelAndView cadastrarPedido()
    {
        if (cartaoValidador.size() != listaPedCartoes.size())
        {
            String response = "É necessário validar todos os cartões";
            return new ModelAndView("redirect:/cliente/carrinho/confirmarPedido");
        }

        PedidosComprasModel pedidoCompra = new PedidosComprasModel();
        
        GeradorCodigoUtil codigo = new GeradorCodigoUtil();
        String pedidoCompraNumero = null;

        do
        {
            pedidoCompraNumero = codigo.getGerarNumeroPedido();
        } while (pedidosComprasRepository.findByNumeroPedido(pedidoCompraNumero) != null);

        pedidoCompra.setPdcNumero(pedidoCompraNumero);
        pedidoCompra.setCliente(clienteMod);
        pedidoCompra.setEndereco(enderecoMod);
        pedidosComprasService.cadastrar(pedidoCompra);
        
        pedidoCompra = pedidosComprasRepository.findByNumeroPedido(pedidoCompraNumero);

        freteMod.setPedidoCompra(pedidoCompra);
        pedComFretesService.cadastrar(freteMod);

        converterProdutosCarrinhoParaPedidos(carrinhoMod, pedidoCompra);

        for (PedCartoesModel pedCartoes : listaPedCartoes)
        {
            pedCartoes.setPedidoCompra(pedidoCompra);
            pedCartoesService.cadastrar(pedCartoes);
        }

        carrinhosService.excluir(carrinhoMod.getCarId());

        return new ModelAndView("redirect:/cliente/meusPedidos/meusPedidosCompras");
    }
}
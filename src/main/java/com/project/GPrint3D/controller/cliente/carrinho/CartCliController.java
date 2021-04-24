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
    private List<CuponsTrocasModel> listaCuponsTrocaDisponiveisMod = new ArrayList<>();
    private List<CuponsTrocasModel> listaCuponsTrocaUtilizadosMod = new ArrayList<>();
    private CuponsPromocoesModel cupomPromocoesMod = new CuponsPromocoesModel();
    private List<PedCartoesModel> listaPedCartoes = new ArrayList<>();
    private HashMap<Integer, String> cartaoValidador = new HashMap<>();
    private Double descontoCupomPromocional = 0D;
    private Double valorPendenteCupons = 0D;
    private Double valorPendenteTotal = 0D;

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
        carrinhoMod = carrinhosRepository.findByClienteId(clienteMod.getCliId());

        if (enderecoMod.getEndId() == 0)
        {
            enderecoMod = enderecosEntregaPadroesRepository.findByClienteId(clienteMod.getCliId()).getEndereco();
        }

        //Resetar as escolhas do pagamento
        listaPedCartoes = new ArrayList<>();
        listaCuponsTrocaDisponiveisMod = new ArrayList<>();
        listaCuponsTrocaUtilizadosMod = new ArrayList<>();
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
        Map<String,String> freteMap = new HashMap<>();               

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

        //Validar se o CEP tá carregado, serve para prevenir erro ao recarregar a página.
        //Caso não encontre nenhum frete selecionado volta para a página de escolher endereço.
        if (freteMod.getPcfModalidade() == null || freteMod.getPcfModalidade().equals(""))
        {
            String response = "Frete inválido, recomece o processo de compra.";

            return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
        }

        //Reinstancia para previnir o erro: "failed to lazily initialize a collection".
        usuarioMod = usuariosRepository.findByEmail(principal.getName());
        clienteMod = usuarioMod.getCliente();
        carrinhoMod = carrinhosRepository.findByClienteId(clienteMod.getCliId());

        //Primeira execução do código, seleciona o cartão padrão e gera a lista de cupons disponíveis.
        if (primeiraExecucao == true)
        {
            if (listaPedCartoes.isEmpty())
            {
                PedCartoesModel pedCartao = new PedCartoesModel();

                pedCartao.setCartao(cartoesPadroesRepository.findByClienteId(clienteMod.getCliId()).getCartao());
                listaPedCartoes.add(pedCartao);
            }

            //Reseta as lista de cupons para previnir lixo da memória em caso de falha ao recarregar a página.
            listaCuponsTrocaDisponiveisMod = new ArrayList<>();
            listaCuponsTrocaUtilizadosMod = new ArrayList<>();
            listaCuponsTrocaDisponiveisMod = cuponsTrocasRepository.findAllByCliente(clienteMod.getCliId());

            primeiraExecucao = false;
        }

        mv.addObject("cliente", clienteMod);
        mv.addObject("carrinho", carrinhoMod);
        mv.addObject("frete", freteMod);
        mv.addObject("listaPedCartoes", listaPedCartoes);
        mv.addObject("totalCartoes", valorTotalCartoes(listaPedCartoes));
        mv.addObject("cuponsTrocasDisponiveis", listaCuponsTrocaDisponiveisMod);
        mv.addObject("cuponsTrocasUtilizados", listaCuponsTrocaUtilizadosMod);
        mv.addObject("totalCuponsTroca", valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod));
        mv.addObject("cupomPromocaoUtilizado", cupomPromocoesMod);
        mv.addObject("descontoCupomPromocional", descontoCupomPromocional);

        cartaoValidador = new HashMap<>();

        return mv;
    }

    //Aplica um cupom de troca
    @PostMapping("/aplicaCupomTroca")
    public ModelAndView aplicarCupomTroca(@RequestParam(name = "codigo") String codigo)
    {
        CuponsTrocasModel cupom = cuponsTrocasRepository.findByCodigo(codigo);

        listaCuponsTrocaUtilizadosMod.add(cupom);
        listaCuponsTrocaDisponiveisMod.remove(cupom);

        //Registra o valor pendente para pagamento (subtrai o valor do cupom de troca adicionado).
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCupons(listaCuponsTrocaDisponiveisMod, listaCuponsTrocaUtilizadosMod, valorPendenteCupons);
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCartoes(listaPedCartoes, valorPendenteCupons, valorPendenteTotal);
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();
        
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Aplica um cupom de troca
    @PostMapping("/removeCupomTroca")
    public ModelAndView removerCupom(@RequestParam(name = "codigo") String codigo)
    {
        CuponsTrocasModel cupom = cuponsTrocasRepository.findByCodigo(codigo);

        listaCuponsTrocaDisponiveisMod.add(cupom);
        listaCuponsTrocaUtilizadosMod.remove(cupom);

        //Registra o valor pendente para pagamento (soma o valor do cupom de troca removido).
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCupons(listaCuponsTrocaDisponiveisMod, listaCuponsTrocaUtilizadosMod, valorPendenteCupons);
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCartoes(listaPedCartoes, valorPendenteCupons, valorPendenteTotal);
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

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

        //Registra o valor pendente para pagamento (subtrai o valor do cupom promocional).
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();
        
        validarCupons(listaCuponsTrocaDisponiveisMod, listaCuponsTrocaUtilizadosMod, valorPendenteCupons);
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCartoes(listaPedCartoes, valorPendenteCupons, valorPendenteTotal);
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Remove um cupom promocional
    @PostMapping("/removeCupomPromocional")
    public ModelAndView removerCupomPromocional()
    {
        cupomPromocoesMod = new CuponsPromocoesModel();
        
        descontoCupomPromocional = 0D;

        //Registra o valor pendente para pagamento (soma o valor do cupom promocional).
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCupons(listaCuponsTrocaDisponiveisMod, listaCuponsTrocaUtilizadosMod, valorPendenteCupons);
        valorPendenteCupons = BigDecimal.valueOf(carrinhoMod.getValorTotal()).add(BigDecimal.valueOf(freteMod.getPcfValor())).subtract(BigDecimal.valueOf(valorTotalCuponsTroca(listaCuponsTrocaUtilizadosMod))).subtract(BigDecimal.valueOf(descontoCupomPromocional)).doubleValue();
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();
        
        validarCartoes(listaPedCartoes, valorPendenteCupons, valorPendenteTotal);
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

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
        
        if (valorPendenteTotal < 0 || (valorPendenteTotal < 10 && !listaPedCartoes.isEmpty()))
        {
            String response = "Valor insuficiente para incluir um cartão.";
        }
        else
        {
            pedCartaoInlcuir.setCartao(cartaoIncluir);
            listaPedCartoes.add(pedCartaoInlcuir);
        }

        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCartoes(listaPedCartoes, valorPendenteCupons, valorPendenteTotal);
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

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
        
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCartoes(listaPedCartoes, valorPendenteCupons, valorPendenteTotal);
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();
        
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Insere o valor a ser pago no cartão
    @PostMapping("/atualizarCartao")
    public ModelAndView atualizarCartao(@RequestParam(name = "index") Integer index, @RequestParam(name = "valor") String valor)
    {
        //É necessário calcular e confirmar os valores (compras + frete) - (cupons + pagamento) = 0

        //Verifica se possui algum valor digitado no campo do cartão.
        //Essa validação é necessária para depois converter a String em Double.
        if (valor.equals(""))
        {
            valor = "R$ 0,00";
        }
        
        Double valorDigitado = Double.parseDouble(valor.replace("R$ ", "").replace(".", "").replace(",", "."));
        Double valorNoCartao = listaPedCartoes.get(index).getPctValor();
        Double valorInserir = 0D;

        //Verifica se é maior que 0.
        if (valorDigitado > 0)
        {
            //Verifica se já existe algum valor cadastrado para aquele cartão.
            if (valorNoCartao > 0)
            {
                valorPendenteTotal += valorNoCartao;
                
                if (valorDigitado >= valorPendenteTotal)
                {
                    //Verifica se o valor pendente é maior que 0.
                    if (valorPendenteTotal > 0 && valorPendenteTotal < 10 && listaPedCartoes.size() == 1)
                    {
                        //Cadastra o valor pendente, independente do valor digitado.
                        valorInserir = valorPendenteTotal;
                    }
                    else if (valorPendenteTotal > 10)
                    {
                        valorInserir = valorDigitado;
                    }
                }
                //Verifica se o valor digitado é maior que 0 e menor que 10.
                else if (valorDigitado >= 0 && valorDigitado < 10)
                {
                    String response = "Valor menor que o mínimo permitido.";
                }
                else
                {
                    //Cadastra o valor digitado.
                    valorInserir = valorDigitado;
                }
            }
            //Verifica, se não houver valor cadastrado no cartão, se o valor inserido é maior que o valor pendente.
            else if (valorDigitado >= valorPendenteTotal)
            {
                //Verifica se o valor pendente é maior que 0.
                if (valorPendenteTotal > 0 && valorPendenteTotal < 10 && listaPedCartoes.size() == 1)
                {
                    //Cadastra o valor pendente, independente do valor digitado.
                    valorInserir = valorPendenteTotal;
                }
                else if (valorPendenteTotal > 10)
                {
                    valorInserir = valorDigitado;
                }
            }
            //Verifica se o valor digitado é maior que 0 e menor que 10.
            else if (valorDigitado >= 0 && valorDigitado < 10)
            {
                String response = "Valor menor que o mínimo permitido.";
            }
            //Caso seja outra condição.
            else
            {
                //Cadastra o valor digitado.
                valorInserir = valorDigitado;
            }
        }

        listaPedCartoes.get(index).setPctValor(valorInserir);

        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        validarCartoes(listaPedCartoes, valorPendenteCupons, valorPendenteTotal);
        valorPendenteTotal = BigDecimal.valueOf(valorPendenteCupons).subtract(BigDecimal.valueOf(valorTotalCartoes(listaPedCartoes))).doubleValue();

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Confirmar pagamento
    @PostMapping("/confirmarPagamento")
    public ModelAndView confirmarPagamento()
    {
        if (valorPendenteTotal != 0)
        {
            String response = "Você precisa pagar todo o valor pendente. Valor pendente de: R$ " + valorPendenteTotal.toString().replace(".", ",");

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

        //A ideia era garantir que o número do pedido gerado seja único.
        //Deve validar a informação via BD com a Constraint Unique. -- Verificar esse código do.
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
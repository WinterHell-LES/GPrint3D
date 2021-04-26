package com.project.GPrint3D.controller.cliente.carrinho;

import com.project.GPrint3D.util.CarrinhoUtil;
import com.project.GPrint3D.util.GeradorCodigoUtil;

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
import com.project.GPrint3D.model.PedCuponsPromocoesModel;
import com.project.GPrint3D.model.PedCuponsTrocasModel;
import com.project.GPrint3D.model.PedProdutosModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.CartoesPadroesRepository;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.CuponsPromocoesRepository;
import com.project.GPrint3D.repository.CuponsTrocasRepository;
import com.project.GPrint3D.repository.EndEntregasPadroesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CarrinhosService;
import com.project.GPrint3D.service.CuponsTrocasService;
import com.project.GPrint3D.service.PedCartoesService;
import com.project.GPrint3D.service.PedComFretesService;
import com.project.GPrint3D.service.PedCuponsPromocoesService;
import com.project.GPrint3D.service.PedCuponsTrocasService;
import com.project.GPrint3D.service.PedProdutosService;
import com.project.GPrint3D.service.PedidosComprasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente/carrinho")
public class CartCliController extends CarrinhoUtil
{
    private boolean primeiraExecucao = true;
    private UsuariosModel usuario = new UsuariosModel();
    private ClientesModel cliente = new ClientesModel();
    private PedidosComprasModel pedidoCompra = new PedidosComprasModel();
    private CarrinhosModel carrinho = new CarrinhosModel();
    private PedComFretesModel frete = new PedComFretesModel();
    private List<CuponsTrocasModel> listaCuponsTrocasCliente = new ArrayList<>();
    private HashMap<Integer, String> cartaoValidador = new HashMap<>();

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

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
    private CuponsTrocasService cuponsTrocasService;

    @Autowired
    private PedCuponsPromocoesService pedCuponsPromocoesService;

    @Autowired
    private PedCuponsTrocasService pedCuponsTrocasService;

    @Autowired
    private PedProdutosService pedProdutosService;

    @Autowired
    private SecurityConfig securityConfig;

    // Tela dos endereços do cliente
    @RequestMapping("/escolherEndereco")
    public ModelAndView escolherEndereco (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/escolherEndereco");

        usuario = usuariosRepository.findByEmail(principal.getName());
        cliente = clientesRepository.findByUsuarioId(usuario.getUsuId());
        pedidoCompra.setCliente(cliente);
        carrinho = carrinhosRepository.findByClienteId(pedidoCompra.getCliente().getCliId());

        if ((pedidoCompra.getEndereco() == null))
        {
            pedidoCompra.setEndereco(enderecosEntregaPadroesRepository
                    .findByClienteId(pedidoCompra.getCliente().getCliId()).getEndereco());
        }

        // Resetar as escolhas do pagamento
        listaCuponsTrocasCliente = new ArrayList<>();
        listaCuponsTrocasCliente.addAll(cuponsTrocasRepository.findAllAtivoByCliente(pedidoCompra.getCliente().getCliId()));
        pedidoCompra.setListPedCartoes(new ArrayList<>());
        pedidoCompra.setListPedCuponsPromocoes(new ArrayList<>());
        pedidoCompra.setListPedCuponsTrocas(new ArrayList<>());
        pedidoCompra.setListPedProdutos(new ArrayList<>());
        primeiraExecucao = true;
        converterProdutosCarrinhoParaPedidos(carrinho, pedidoCompra);

        mv.addObject("pedidoCompra", pedidoCompra);
        mv.addObject("cliente", cliente);

        return mv;
    }

    // Alterar endereco de entrega
    @PostMapping("/alterarEndereco")
    public ModelAndView alterarEnderecoEntrega (@RequestParam(name = "id") Integer enderecoId)
    {
        pedidoCompra.setEndereco(enderecosRepository.findOneById(enderecoId));

        return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
    }

    // Grava os dados do endereço do cliente
    @PostMapping("/confirmarEndereco")
    public ModelAndView confirmarEndereco (@RequestParam(name = "frete", defaultValue = "") String freteParam,
            @Valid EnderecosModel endereco)
    {
        if (freteParam.equals(""))
        {
            String response = "Frete não selecionado, por favor selecione o frete.";

            return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
        }

        freteParam = freteParam.substring(1, freteParam.length() - 1);
        String[] keyValuePairs = freteParam.split(",");
        Map<String, String> freteMap = new HashMap<>();

        for (String pair : keyValuePairs)
        {
            String[] entry = pair.split(":");
            freteMap.put(entry[0].trim(), entry[1].trim());
        }

        frete.setPcfEmpresa(freteMap.get("\"empresa\"").replace("\"", ""));
        frete.setPcfModalidade(freteMap.get("\"modalidade\"").replace("\"", ""));
        frete.setPcfPrazo(freteMap.get("\"prazo\"").replace("\"", ""));
        frete.setPcfValor(Double.parseDouble(freteMap.get("\"valor\"").replace("\"", "")));

        pedidoCompra.setFrete(frete);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Tela dos enderços do clientes
    @RequestMapping("/escolherPagamento")
    public ModelAndView escolherPagamento (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/escolherPagamento");

        // Validar se o CEP tá carregado, serve para prevenir erro ao recarregar a
        // página. Caso não encontre nenhum frete selecionado volta para a página de
        // escolher endereço.
        if (frete.getPcfModalidade() == null || frete.getPcfModalidade().equals(""))
        {
            String response = "Frete inválido, recomece o processo de compra.";

            return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
        }

        // Reinstancia para previnir o erro: "failed to lazily initialize a collection".
        usuario = usuariosRepository.findByEmail(principal.getName());
        cliente = clientesRepository.findByUsuarioId(usuario.getUsuId());

        // Primeira execução do código, seleciona o cartão padrão e gera a lista de
        // cupons disponíveis.
        if (primeiraExecucao)
        {
            if (pedidoCompra.getListPedCartoes().isEmpty())
            {
                PedCartoesModel pedCartao = new PedCartoesModel();

                pedCartao.setCartao(
                        cartoesPadroesRepository.findByClienteId(pedidoCompra.getCliente().getCliId()).getCartao());

                pedidoCompra.removeAllFromListPedCartoes();
                pedidoCompra.getListPedCartoes().add(pedCartao);

            }

            primeiraExecucao = false;
        }

        // Orderna os cupons de forma descendente
        pedidoCompra.getListPedCuponsTrocas()
                .sort( (a, b) -> b.getCupom().getCptSaldo().compareTo(a.getCupom().getCptSaldo()));
        listaCuponsTrocasCliente.sort( (a, b) -> b.getCptSaldo().compareTo(a.getCptSaldo()));

        mv.addObject("cliente", cliente);
        mv.addObject("pedidoCompra", pedidoCompra);
        mv.addObject("carrinho", carrinho);
        mv.addObject("frete", frete);
        mv.addObject("cuponsTrocasCliente", listaCuponsTrocasCliente);

        cartaoValidador = new HashMap<>();

        return mv;
    }

    // Aplica um cupom de troca
    @PostMapping("/aplicaCupomTroca")
    public ModelAndView aplicarCupomTroca (@RequestParam(name = "codigo") String codigo)
    {
        CuponsTrocasModel cupom = cuponsTrocasRepository.findByCodigo(codigo);
        PedCuponsTrocasModel cupomPedido = new PedCuponsTrocasModel();

        cupomPedido.setCupom(cupom);

        pedidoCompra.getListPedCuponsTrocas().add(cupomPedido);
        listaCuponsTrocasCliente.remove(cupom);

        // Registra o valor pendente para pagamento (subtrai o valor do cupom de troca
        // adicionado).

        validarCupons(pedidoCompra, listaCuponsTrocasCliente);
        validarCartoes(pedidoCompra);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Aplica um cupom de troca
    @PostMapping("/removeCupomTroca")
    public ModelAndView removerCupom (@RequestParam(name = "codigo") String codigo)
    {
        // Pelo for ternario (each), assim como no aplicarcupom, o objeto cupom não era
        // reconhecido, fazendo com que fosse excluido o primeiro cupom da lista,
        // acredito que o problema ocorra devido ao caminho CupomTrocasModel >
        // PedCuponsTrocas > PedidoCompras.
        for (int index = 0 ; index < pedidoCompra.getListPedCuponsTrocas().size() ; index++)
        {
            if (pedidoCompra.getListPedCuponsTrocas().get(index).getCupom().getCptCodigo().equals(codigo))
            {
                listaCuponsTrocasCliente.add(pedidoCompra.getListPedCuponsTrocas().get(index).getCupom());
                pedidoCompra.getListPedCuponsTrocas().remove(index);
                break;
            }
        }

        // Registra o valor pendente para pagamento (soma o valor do cupom de troca
        // removido).
        validarCupons(pedidoCompra, listaCuponsTrocasCliente);
        validarCartoes(pedidoCompra);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Aplica um cupom promocional
    @PostMapping("/aplicaCupomPromocional")
    public ModelAndView aplicarCupomPromocional (@RequestParam(name = "codigo", defaultValue = "") String codigo,
            RedirectAttributes attributes)
    {
        CuponsPromocoesModel cupom = cuponsPromocoesRepository.findByCodigo(codigo);

        if (cupom == null)
        {
            String response = "Codigo inválido";

            attributes.addFlashAttribute("error", response);

            return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
        }
        else
        {
            PedCuponsPromocoesModel cupomPedidoPromocao = new PedCuponsPromocoesModel();

            cupomPedidoPromocao.setCupom(cupom);

            pedidoCompra.getListPedCuponsPromocoes().add(cupomPedidoPromocao);
        }

        // Registra o valor pendente para pagamento (subtrai o valor do cupom
        // promocional).
        validarCupons(pedidoCompra, listaCuponsTrocasCliente);
        validarCartoes(pedidoCompra);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Remove um cupom promocional
    @PostMapping("/removeCupomPromocional")
    public ModelAndView removerCupomPromocional ()
    {
        pedidoCompra.removeAllFromListPedCuponsPromocoes();

        // Registra o valor pendente para pagamento (soma o valor do cupom promocional).
        validarCupons(pedidoCompra, listaCuponsTrocasCliente);
        validarCartoes(pedidoCompra);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Inclui cartão para pagamento
    @PostMapping("/incluirCartao")
    public ModelAndView incluirCartao (@RequestParam(name = "id") Integer cartaoId, RedirectAttributes attributes)
    {
        CartoesModel cartaoIncluir = cartoesRepository.findOneById(cartaoId);

        for (PedCartoesModel cartaoPed : pedidoCompra.getListPedCartoes())
        {
            if (cartaoPed.getCartao().getCrtId() == cartaoIncluir.getCrtId())
            {
                String response = "Cartão já incluso, inclua outro cartão.";

                attributes.addFlashAttribute("error", response);

                return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
            }
        }

        if (pedidoCompra.getValorPendenteTotal() < 0
                || (pedidoCompra.getValorPendenteTotal() < 10 && !pedidoCompra.getListPedCartoes().isEmpty()))
        {
            String response = "Valor insuficiente para incluir um cartão.";

            attributes.addFlashAttribute("error", response);
        }
        else
        {
            PedCartoesModel pedCartaoIncluir = new PedCartoesModel();

            pedCartaoIncluir.setCartao(cartaoIncluir);

            pedidoCompra.getListPedCartoes().add(pedCartaoIncluir);
        }

        validarCartoes(pedidoCompra);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Remove cartão para pagamento
    @PostMapping("/removeCartao")
    public ModelAndView removeCartao (@RequestParam(name = "index") int index, RedirectAttributes attributes)
    {
        pedidoCompra.getListPedCartoes().remove(index);

        if (cartaoValidador.get(index) != null)
        {
            cartaoValidador.remove(index);
        }

        validarCartoes(pedidoCompra);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Insere o valor a ser pago no cartão
    @PostMapping("/atualizarCartao")
    public ModelAndView atualizarCartao (@RequestParam(name = "index") Integer index,
            @RequestParam(name = "valor") String valor, RedirectAttributes attributes)
    {
        // É necessário calcular e confirmar os valores (compras + frete) - (cupons +
        // pagamento) = 0

        // Verifica se possui algum valor digitado no campo do cartão.
        // Essa validação é necessária para depois converter a String em Double.
        if (valor.equals(""))
        {
            valor = "R$ 0,00";
        }

        double valorDigitado = Double.parseDouble(valor.replace("R$ ", "").replace(".", "").replace(",", "."));
        double valorNoCartao = pedidoCompra.getListPedCartoes().get(index).getPctValor();
        double valorInserir = 0.0;

        // Verifica se é maior que 0.
        if (valorDigitado > 0)
        {
            // Verifica se já existe algum valor cadastrado para aquele cartão.
            if (valorNoCartao > 0)
            {
                double valorPendenteTotal = pedidoCompra.getValorPendenteTotal() + valorNoCartao;

                if (valorDigitado >= valorPendenteTotal)
                {
                    // Verifica se o valor pendente é maior que 0.
                    if (valorPendenteTotal > 0 && valorPendenteTotal < 10
                            && pedidoCompra.getListPedCartoes().size() == 1)
                    {
                        // Cadastra o valor pendente, independente do valor digitado.
                        valorInserir = valorPendenteTotal;
                    }
                    else if (valorPendenteTotal > 10)
                    {
                        if (valorDigitado > valorPendenteTotal)
                        {
                            valorInserir = valorPendenteTotal;
                        }
                        else
                        {
                            valorInserir = valorDigitado;
                        }
                    }
                }
                // Verifica se o valor digitado é maior que 0 e menor que 10.
                else if (valorDigitado >= 0 && valorDigitado < 10)
                {
                    String response = "Valor menor que o mínimo permitido.";

                    attributes.addFlashAttribute("error", response);
                }
                else
                {
                    // Cadastra o valor digitado.
                    valorInserir = valorDigitado;
                }
            }
            // Verifica, se não houver valor cadastrado no cartão, se o valor inserido é
            // maior que o valor pendente.
            else if (valorDigitado >= pedidoCompra.getValorPendenteTotal())
            {
                // Verifica se o valor pendente é maior que 0.
                if (pedidoCompra.getValorPendenteTotal() > 0 && pedidoCompra.getValorPendenteTotal() < 10
                        && pedidoCompra.getListPedCartoes().size() == 1)
                {
                    // Cadastra o valor pendente, independente do valor digitado.
                    valorInserir = pedidoCompra.getValorPendenteTotal();
                }
                else if (pedidoCompra.getValorPendenteTotal() > 10)
                {
                    if (valorDigitado > pedidoCompra.getValorPendenteTotal())
                    {
                        valorInserir = pedidoCompra.getValorPendenteTotal();
                    }
                    else
                    {
                        valorInserir = valorDigitado;
                    }
                }
            }
            // Verifica se o valor digitado é maior que 0 e menor que 10.
            else if (valorDigitado >= 0 && valorDigitado < 10)
            {
                String response = "Valor menor que o mínimo permitido.";
            }
            // Caso seja outra condição.
            else
            {
                // Cadastra o valor digitado.
                valorInserir = valorDigitado;
            }
        }

        pedidoCompra.getListPedCartoes().get(index).setPctValor(valorInserir);

        validarCartoes(pedidoCompra);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    // Confirmar pagamento
    @PostMapping("/confirmarPagamento")
    public ModelAndView confirmarPagamento ()
    {
        // Validar se o CEP tá carregado, serve para prevenir erro ao recarregar a
        // página. Caso não encontre nenhum frete selecionado volta para a página de
        // escolher endereço.
        if (frete.getPcfModalidade() == null || frete.getPcfModalidade().equals(""))
        {
            String response = "Frete inválido, recomece o processo de compra.";

            return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
        }

        if (pedidoCompra.getValorPendenteTotal() > 0.009)
        {
            String response = "Você precisa pagar todo o valor pendente. Valor pendente de: R$ "
                    + pedidoCompra.getValorPendenteTotal().toString().replace(".", ",");

            System.out.println("Valor pendente: " + pedidoCompra.getValorPendenteTotal());

            return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
        }

        return new ModelAndView("redirect:/cliente/carrinho/confirmarPedido");
    }

    // Tela de confirmação do pedido
    @RequestMapping("/confirmarPedido")
    public ModelAndView confirmarPedido ()
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/confirmarPedido");

        mv.addObject("pedidoCompra", pedidoCompra);
        mv.addObject("carrinho", carrinho);
        mv.addObject("frete", frete);
        mv.addObject("cartaoValidador", cartaoValidador);

        return mv;
    }

    // Confirmar CVV
    @PostMapping("/validarCvv")
    public ModelAndView validarCvv (@RequestParam(name = "index") Integer index, @RequestParam(name = "cvv") String cvv)
    {
        if (securityConfig.passwordEncoder().matches(cvv,
                pedidoCompra.getListPedCartoes().get(index).getCartao().getCrtCvv()))
        {
            if (cartaoValidador.get(index) == null)
            {
                cartaoValidador.put(index, "Confirmado");
            }
        }

        return new ModelAndView("redirect:/cliente/carrinho/confirmarPedido");
    }

    @PostMapping("/cadastrarPedido")
    public ModelAndView cadastrarPedido ()
    {
        if (cartaoValidador.size() != pedidoCompra.getListPedCartoes().size())
        {
            String response = "É necessário validar todos os cartões";
            return new ModelAndView("redirect:/cliente/carrinho/confirmarPedido");
        }

        GeradorCodigoUtil codigo = new GeradorCodigoUtil();
        String pedidoCompraNumero = null;

        // A ideia é garantir que o número do pedido gerado seja único.
        // Deve validar a informação via BD com a Constraint Unique.
        pedidoCompraNumero = codigo.getGerarNumeroPedido();

        // Seta o frete como nulo para não ocorre o erro do OneToOne onde exige o id do
        // Frete
        pedidoCompra.setFrete(null);

        pedidoCompra.setPdcNumero(pedidoCompraNumero);
        pedidosComprasService.cadastrar(pedidoCompra);

        PedidosComprasModel pedidoCompraRegistrado = pedidosComprasRepository.findByNumeroPedido(pedidoCompraNumero);

        frete.setPedidoCompra(pedidoCompraRegistrado);
        pedComFretesService.cadastrar(frete);

        // Insere o frete para ser utilizado em formulas mais a frente
        pedidoCompra.setFrete(frete);

        for (PedCartoesModel pedCartoes : pedidoCompra.getListPedCartoes())
        {
            pedCartoes.setPedidoCompra(pedidoCompraRegistrado);
            pedCartoesService.cadastrar(pedCartoes);
        }

        for (PedCuponsPromocoesModel cupomPromocoes : pedidoCompra.getListPedCuponsPromocoes())
        {
            cupomPromocoes.setPedidoCompra(pedidoCompraRegistrado);
            pedCuponsPromocoesService.cadastrar(cupomPromocoes);
        }

        boolean validadorSaldo = true;

        for (PedCuponsTrocasModel cupomTroca : pedidoCompra.getListPedCuponsTrocas())
        {
            if (validadorSaldo && pedidoCompra.getValorPendenteTotal() < 0)
            {
                cupomTroca.getCupom().setCptSaldo(Math.abs(pedidoCompra.getValorPendenteTotal()));
                cuponsTrocasService.atualizar(cupomTroca.getCupom());
                validadorSaldo = false;
            }
            else
            {
                cupomTroca.getCupom().setCptSaldo(0.0);
                cupomTroca.getCupom().setCptStatus(false);
                cuponsTrocasService.atualizar(cupomTroca.getCupom());
            }

            cupomTroca.setPedidoCompra(pedidoCompraRegistrado);
            pedCuponsTrocasService.cadastrar(cupomTroca);
        }


        for (PedProdutosModel produto : pedidoCompra.getListPedProdutos())
        {
            produto.setPedidoCompra(pedidoCompraRegistrado);
            pedProdutosService.cadastrar(produto);
        }

        carrinhosService.excluir(carrinho.getCarId());

        return new ModelAndView("redirect:/cliente/meusPedidos/meusPedidosCompras");
    }
}
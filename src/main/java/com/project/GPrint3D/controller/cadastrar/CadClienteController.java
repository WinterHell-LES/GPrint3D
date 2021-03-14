package com.project.GPrint3D.controller.cadastrar;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.CartoesPadroesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.EndCobrancasPadroesModel;
import com.project.GPrint3D.model.EndEntregasPadroesModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CartoesPadroesService;
import com.project.GPrint3D.service.CartoesService;
import com.project.GPrint3D.service.ClientesService;
import com.project.GPrint3D.service.DocumentosService;
import com.project.GPrint3D.service.EndCobrancasPadroesService;
import com.project.GPrint3D.service.EndEntregasPadroesService;
import com.project.GPrint3D.service.EnderecosService;
import com.project.GPrint3D.service.TelefonesService;
import com.project.GPrint3D.service.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastro")
public class CadClienteController 
{ 
    private UsuariosModel usuariosMod;
    private CartoesPadroesModel cartaoPadraoMod;
    private ClientesModel clientesMod;
    private DocumentosModel documentosMod;
    private TelefonesModel telefonesMod; 
    private EnderecosModel enderecosMod;
    private EndCobrancasPadroesModel endCobrancasPadroesMod;
    private EndEntregasPadroesModel endEntregasPadroesMod;

    @Autowired
    private CartoesService cartoesService;

    @Autowired
    private CartoesPadroesService cartoesPadroesService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private DocumentosService documentosService;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private EndCobrancasPadroesService endCobrancasPadroesService;

    @Autowired
    private EndEntregasPadroesService endEntregasPadroesService;

    @Autowired
    private TelefonesService telefonesService;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private CartoesRepository cartoes;

    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private EnderecosRepository enderecos;

    @Autowired
    private UsuariosRepository usuarios;
    
    // Controle de cadastro de dados pessoais
    @RequestMapping("/cadastroDadosPessoais")
    public ModelAndView cadastroDadosPessoais(ClientesModel cliente, TelefonesModel telefone, DocumentosModel documento, UsuariosModel usuario)
    {
        ModelAndView mv = new ModelAndView("/cadastro/cadDadosPessoais");

        return mv;
    }
    
    @PostMapping("/cadastroDadosPessoais")
    public ModelAndView cadastrarDadosPessoais(@RequestParam(name = "confirm_password") String confirmPass, @Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento, @Valid UsuariosModel usuario, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroDadosPessoais(cliente, telefone, documento, usuario);
        }

        //Valida a senha e a confimação
        String newUserPass = usuario.getUsuSenha();

        if (!confirmPass.equals(newUserPass))
        {
            return cadastroDadosPessoais(cliente, telefone, documento, usuario);
        }

        //Atribui os valores padrões
        usuario.setUsuRegra("ROLE_CLI");
        cliente.setCliRanking("0");

        //Atribui as informações do html às variaveis
        clientesMod = cliente;
        telefonesMod = telefone;
        documentosMod = documento;
        usuariosMod = usuario;

        return new ModelAndView("redirect:/cadastro/cadastroEndereco");
    }

    @RequestMapping("/cadastroEndereco")
    public ModelAndView cadastroEndereco(EnderecosModel endereco)
    {
        ModelAndView mv = new ModelAndView("/cadastro/cadEndereco");

        return mv;
    }

    @PostMapping("/cadastroEndereco")
    public ModelAndView cadastrarEndereco(@Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroEndereco(endereco);
        }

        //Atribui as informações do html à variável
        enderecosMod = endereco;

        return new ModelAndView("redirect:/cadastro/cadastroCartao");
    }

    @RequestMapping("/cadastroCartao")
    public ModelAndView cadastroCartao(CartoesModel cartao)
    {
        ModelAndView mv = new ModelAndView("/cadastro/cadCartao");

        return mv;
    }

    @PostMapping("/cadastroCartao")
    public ModelAndView cadastrarCartao(@Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroCartao(cartao);
        }

        //Registra o novo usuário
        usuariosService.cadastrar(usuariosMod);

        //Busca o novo usuário pelo emaill
        UsuariosModel usu = usuarios.findByEmail(usuariosMod.getUsuEmail());

        //Define e cadastra o novo cliente com o usuário já registrado
        clientesMod.setUsuario(usu);
        clientesService.cadastrar(clientesMod);

        //Busca o novo cliente cadastrado
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        //Define o novo cliente nos dados vinculados
        telefonesMod.setCliente(cli);
        documentosMod.setCliente(cli);
        enderecosMod.setCliente(cli);
        cartao.setCliente(cli);

        //Cadastra os dados vinculados ao novo cliente
        telefonesService.cadastrar(telefonesMod);
        enderecosService.cadastrar(enderecosMod);
        documentosService.cadastrar(documentosMod);
        cartoesService.cadastrar(cartao);

        //Busca o novo endereço cadastrado
        EnderecosModel end = enderecos.findByClienteId(cli.getCliId());

        //Verifica se o endereço é de cobrança
        if (end.isEndCobranca())
        {
            //Configura o novo cliente e novo endereço no endereço de cobrança padrao
            endCobrancasPadroesMod.setCliente(cli);
            endCobrancasPadroesMod.setEndereco(end);
            endCobrancasPadroesService.cadastrar(endCobrancasPadroesMod);
        }

        //Verifica se o endereço é de entrega
        if (end.isEndEntrega())
        {
            //Configura o novo cliente e novo endereço no endereço de entrega padrao
            endEntregasPadroesMod.setCliente(cli);
            endEntregasPadroesMod.setEndereco(end);
            endEntregasPadroesService.cadastrar(endEntregasPadroesMod);
        }

        //Busca o novo cartão cadastrado
        CartoesModel crt = cartoes.findByClienteId(cli.getCliId());

        //Configura e cadastra o novo cartão padrão com o cliente e o cartão cadastrados
        cartaoPadraoMod.setCartao(crt);
        cartaoPadraoMod.setCliente(cli);
        cartoesPadroesService.cadastrar(cartaoPadraoMod);

        return new ModelAndView("redirect:/index");
    }
}
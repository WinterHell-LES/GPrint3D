package com.project.GPrint3D.controller.cadastrar;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.BandeirasRepository;
import com.project.GPrint3D.service.DefaultFacadeService;

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
    private UsuariosModel usuariosMod = new UsuariosModel();
    private ClientesModel clientesMod = new ClientesModel();
    private DocumentosModel documentosMod = new DocumentosModel();
    private TelefonesModel telefonesMod = new TelefonesModel();
    private EnderecosModel enderecosMod = new EnderecosModel();

    @Autowired
    private BandeirasRepository bandeirasRepository;

    @Autowired 
    private DefaultFacadeService defaultFacadeService;

    // Controle de cadastro de dados pessoais
    @RequestMapping("/cadastroDadosPessoais")
    public ModelAndView cadastroDadosPessoais (ClientesModel cliente, TelefonesModel telefone,
            DocumentosModel documento, UsuariosModel usuario)
    {
        return new ModelAndView("/cadastro/cadDadosPessoais");
    }

    @PostMapping("/cadastroDadosPessoais")
    public ModelAndView cadastrarDadosPessoais (@RequestParam(name = "confirm_password") String confirmPass,
            @Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento,
            UsuariosModel usuario, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            System.out.println(result.toString());
            return cadastroDadosPessoais(cliente, telefone, documento, usuario);
        }

        // Valida a senha e a confimação
        String newUserPass = usuario.getUsuSenha();

        if (!confirmPass.equals(newUserPass))
        {
            return cadastroDadosPessoais(cliente, telefone, documento, usuario);
        }

        // Atribui os valores padrões
        usuario.setUsuRegra("ROLE_CLI");
        cliente.setCliRanking(0);

        // Atribui as informações do html às variaveis
        clientesMod = cliente;
        telefonesMod = telefone;
        documentosMod = documento;
        usuariosMod = usuario;

        return new ModelAndView("redirect:/cadastro/cadastroEndereco");
    }

    @RequestMapping("/cadastroEndereco")
    public ModelAndView cadastroEndereco (EnderecosModel endereco)
    {
        ModelAndView mv = new ModelAndView("/cadastro/cadEndereco");

        // Define endereço de entrega como escolha inicial do tipo de endereço
        endereco.setEndEntrega(true);

        return mv;
    }

    @PostMapping("/cadastroEndereco")
    public ModelAndView cadastrarEndereco (@Valid EnderecosModel endereco, BindingResult result,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroEndereco(endereco);
        }

        // Atribui as informações do html à variável
        enderecosMod = endereco;

        endereco.setEndEntrega(true);
        endereco.setEndCobranca(true);

        return new ModelAndView("redirect:/cadastro/cadastroCartao");
    }

    @RequestMapping("/cadastroCartao")
    public ModelAndView cadastroCartao (CartoesModel cartao)
    {
        ModelAndView mv = new ModelAndView("/cadastro/cadCartao");

        mv.addObject("bandeiras", bandeirasRepository.findAll());

        return mv;
    }

    @PostMapping("/cadastroCartao")
    public ModelAndView cadastrarCartao (@Valid CartoesModel cartao, BindingResult result,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroCartao(cartao);
        }

        String[] mensagem = defaultFacadeService.cadastrarCliente(usuariosMod, clientesMod, telefonesMod, documentosMod, enderecosMod, cartao);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/index");
    }
}
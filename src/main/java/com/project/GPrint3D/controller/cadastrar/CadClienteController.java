package com.project.GPrint3D.controller.cadastrar;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CartoesService;
import com.project.GPrint3D.service.ClientesService;
import com.project.GPrint3D.service.DocumentosService;
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
    private ClientesModel clientesMod;
    private DocumentosModel documentosMod;
    private TelefonesModel telefonesMod; 
    private EnderecosModel enderecosMod;

    @Autowired
    private CartoesService cartoesService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private DocumentosService documentosService;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private TelefonesService telefonesService;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private ClientesRepository clientes;

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
    public ModelAndView cadastrarDadosPessoais(@Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento, @Valid UsuariosModel usuario, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroDadosPessoais(cliente, telefone, documento, usuario);
        }

        clientesMod = cliente;
        telefonesMod = telefone;
        documentosMod = documento;

        usuario.setUsuRegra("ROLE_CLI");

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
    public ModelAndView cadastrarEndereco(@RequestParam(name = "endPadrao", defaultValue = "false") boolean endPadrao, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroEndereco(endereco);
        }

        enderecosMod = endereco;

        if (endPadrao == true)
        {
            if (endereco.isEndCobranca())
            {
                endereco.setEndCobrancaPadrao(true);
            }
            else if (endereco.isEndEntrega()) 
            {
                endereco.setEndEntregaPadrao(true);
            }
        }else
        {
            endereco.setEndEntregaPadrao(false);
            endereco.setEndCobrancaPadrao(false);
        }

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

        usuariosService.cadastrar(usuariosMod);
        UsuariosModel usu = usuarios.findByEmail(usuariosMod.getUsuEmail());
        clientesMod.setUsuario(usu);

        clientesService.cadastrar(clientesMod);
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        telefonesMod.setCliente(cli);
        documentosMod.setCliente(cli);
        enderecosMod.setCliente(cli);
        cartao.setCliente(cli);

        telefonesService.cadastrar(telefonesMod);
        enderecosService.cadastrar(enderecosMod);
        documentosService.cadastrar(documentosMod);
        cartoesService.cadastrar(cartao);

        return new ModelAndView("redirect:/index");
    }
}
package com.project.GPrint3D.controller.cliente;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

<<<<<<< HEAD
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
=======

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.DocumentosRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.repository.TelefonesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CartoesService;
import com.project.GPrint3D.service.ClientesService;
import com.project.GPrint3D.service.DocumentosService;
import com.project.GPrint3D.service.EnderecosService;
import com.project.GPrint3D.service.TelefonesService;
import com.project.GPrint3D.service.UsuariosService;
>>>>>>> 538a84e705470907635345aa0d97b1bf90894a4a

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class DadosClienteController 
{
    @Autowired
<<<<<<< HEAD
    private UsuariosRepository usuarios;
=======
    private CartoesRepository cartoes;
>>>>>>> 538a84e705470907635345aa0d97b1bf90894a4a

    @Autowired
    private ClientesRepository clientes;

<<<<<<< HEAD
=======
    @Autowired
    private DocumentosRepository documentos;

    @Autowired
    private EnderecosRepository enderecos;

    @Autowired
    private TelefonesRepository telefones;

    @Autowired
    private UsuariosRepository usuarios;
    
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

    //Alterar senha do usuario
>>>>>>> 538a84e705470907635345aa0d97b1bf90894a4a
    @RequestMapping("/alterarSenha")
    public ModelAndView alterarSenha(UsuariosModel usuario, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/alterarSenha");

        return mv;
    }

    @PostMapping("/alterarSenha")
    public ModelAndView alteracaoSenha(@Valid UsuariosModel usuario, RedirectAttributes attributes)
    {
        String[] mensagem = usuariosService.atualizarPass(usuario);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/alterarSenha");
    }

    @RequestMapping("/meuCadastro")
    public ModelAndView meuCadastro(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meuCadastro");

        UsuariosModel usu = usuarios.findByEmail("cliente1@gprint3d.com");
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());
        TelefonesModel tel = telefones.findByClienteId(cli.getCliId());
        DocumentosModel doc = documentos.findByClienteId(cli.getCliId());
        
        mv.addObject("usuario", usu);
        mv.addObject("cliente", cli);
        mv.addObject("telefone", tel);
        mv.addObject("documento", doc);

        return mv;
    }

    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusCartoes");

        return mv;
    }

    @RequestMapping("/cadastrarCartao")
    public ModelAndView cadastrarCartao(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadCartao2");

        return mv;
    }

    @RequestMapping("/meusEnderecos")
    public ModelAndView meusEnderecos(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusEnderecos");

        return mv;
    }

    @RequestMapping("/cadastrarEndereco")
    public ModelAndView cadastrarEndereco(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadEndereco2");

        return mv;
    }
}
package com.project.GPrint3D.controller.cliente;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class DadosClienteController 
{
    @Autowired
    private CartoesRepository cartoes;

    @Autowired
    private ClientesRepository clientes;

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
    @RequestMapping("/alterarSenha")
    public ModelAndView alterarSenha(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/alterarSenha");

        UsuariosModel usu = usuarios.findByEmail(principal.getName());        
        
        mv.addObject("usuario", usu);

        return mv;
    }

    @PostMapping("/alterarSenha")
    public ModelAndView alteracaoSenha(@Valid UsuariosModel Usuario, RedirectAttributes attributes)
    {
        /*if (oldUsuario.getUsuSenha() == checkOldUsuario.getUsuSenha())
        {
            //usuariosService.atualizarPass(newUsuario);
            System.out.println(oldUsuario.getUsuSenha());
            System.out.println(checkOldUsuario.getUsuSenha());
            
            return new ModelAndView("redirect:/cliente/alterarSenha");
        }
        
        else
        {
            return new ModelAndView("redirect:/cliente/index");
        }*/

        usuariosService.atualizarPass(Usuario);

        return new ModelAndView("redirect:/cliente/index");

    }

    @RequestMapping("/meuCadastro")
    public ModelAndView meuCadastro(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meuCadastro");

        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());
        TelefonesModel tel = telefones.findByClienteId(cli.getCliId());
        DocumentosModel doc = documentos.findByClienteId(cli.getCliId());
        
        mv.addObject("usuario", usu);
        mv.addObject("cliente", cli);
        mv.addObject("telefone", tel);
        mv.addObject("documento", doc);

        return mv;
    }

    @PostMapping("/meuCadastro")
    public ModelAndView atualizarCadastro(@Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento, @Valid UsuariosModel usuario, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/meuCadastro");
        }

        clientesService.atualizar(cliente);
        telefonesService.atualizar(telefone);
        documentosService.atualizar(documento);

        
        /*String[] mensagem = clientesService.atualizarSituacao(matricula);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);*/

        return new ModelAndView("redirect:/cliente/meuCadastro");
    }

    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusCartoes");
        
        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        mv.addObject("cliente", cli);

        return mv;
    }

    @RequestMapping("/cadastrarCartao/{id}")
    public ModelAndView cadastroCartao(@PathVariable("id") Integer id, CartoesModel cartao, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadCartao");
        
        mv.addObject("cliente", String.valueOf(id));

        return mv;
    }

    @PostMapping("/cadastrarCartao/{id}")
    public ModelAndView cadastrarCartao(@PathVariable("id") Integer id, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/cadastrarCartao/" + id);
        }

        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());
        
        cartao.setCliente(cli);

        cartoesService.cadastrar(cartao);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }

    @RequestMapping("/alterarCartao/{id}")
    public ModelAndView alterarCartao(@PathVariable("id") Integer id, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/altCartao");

        mv.addObject("cartao", cartoes.findOneById(id));

        return mv;
    }

    @PostMapping("/alterarCartao/{id}")
    public ModelAndView alteraCartao(@PathVariable("id") Integer id, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/alterarCartao");
        }
        
        cartoesService.atualizar(cartao);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }

    @PostMapping("/deletarCartao")
    public ModelAndView deletarCartao(@RequestParam(name = "id") Integer id, RedirectAttributes attributes) 
    {
        cartoesService.excluir(id);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }

    @RequestMapping("/meusEnderecos")
    public ModelAndView meusEnderecos(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusEnderecos");
        
        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        mv.addObject("cliente", cli);

        return mv;
    }

    @RequestMapping("/cadastrarEndereco/{id}/{check}")
    public ModelAndView cadastroEndereco(@PathVariable("id") Integer id, @PathVariable("check") Integer check, EnderecosModel endereco, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadEndereco");
        
        mv.addObject("cliente", String.valueOf(id));
        mv.addObject("check", check);

        return mv;
    }

    @PostMapping("/cadastrarEndereco/{id}/{check}")
    public ModelAndView cadastrarEndereco(@PathVariable("id") Integer id, @PathVariable("check") Integer check, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/cadastrarEndereco/" + id);
        }

        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());
        
        endereco.setCliente(cli);

        enderecosService.cadastrar(endereco);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }

    @RequestMapping("/alterarEndereco/{id}")
    public ModelAndView alterarEndereco(@PathVariable("id") Integer id, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/altEndereco");

        mv.addObject("endereco", enderecos.findOneById(id));

        return mv;
    }

    @PostMapping("/alterarEndereco/{id}")
    public ModelAndView alteraEndereco(@PathVariable("id") Integer id, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/alterarEndereco");
        }
        
       enderecosService.atualizar(endereco);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }

    @PostMapping("/deletarEndereco")
    public ModelAndView deletarEndereco(@RequestParam(name = "id") Integer id, RedirectAttributes attributes) 
    {
        enderecosService.excluir(id);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }

}

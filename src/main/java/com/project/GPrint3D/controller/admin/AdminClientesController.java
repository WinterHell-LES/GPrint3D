package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.DocumentosRepository;
import com.project.GPrint3D.repository.TelefonesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminClientesController 
{
    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private DocumentosRepository documentos;

    @Autowired
    private TelefonesRepository telefones;

    @Autowired
    private EnderecosRepository enderecos;

    @Autowired
    private CartoesRepository cartoes;

    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private UsuariosService usuariosService;

    @RequestMapping("/clientes")
    public ModelAndView listagemAlunos(ClientesModel cliente) 
    {
        ModelAndView mv = new ModelAndView("admin/clientes");

        mv.addObject("clientes", clientes.findAll());

        return mv;
    }

    @PostMapping("/clientesAtiva")
    public ModelAndView ativacaoCliente(@RequestParam(name = "id") Integer id, RedirectAttributes attributes) 
    {
        UsuariosModel usu = usuarios.findOneById(id);

        String[] mensagem;

        if (usu.getUsuAtivo())
        {
            mensagem = usuariosService.ativar(false, id);
        }
        else
        {
            mensagem = usuariosService.ativar(true, id);
        }    

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/clientes");
    }

    @GetMapping(value = "/cliente/{id}/dados")
    public ModelAndView listagemDadosCliente(@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/infoClientesDados");

        mv.addObject("cliente", clientes.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/cliente/{id}/documentos")
    public ModelAndView listagemDocumentosCliente(@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/infoClientesDocumentos");

        mv.addObject("cliente", clientes.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/cliente/{id}/telefones")
    public ModelAndView listagemTelefonesCliente(@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/infoClientesTelefones");

        mv.addObject("cliente", clientes.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/cliente/{id}/enderecos")
    public ModelAndView listagemEnderecosCliente(@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/infoClientesEnderecos");

        mv.addObject("cliente", clientes.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/cliente/{id}/cartoes")
    public ModelAndView listagemCartoesCliente(@PathVariable("id") Integer id, UsuariosModel usuario)
    {
        ModelAndView mv = new ModelAndView("/admin/infoClienteCartoes");

        mv.addObject("cliente", clientes.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/cliente/{id}/usuario")
    public ModelAndView listagemUsuariosCliente(@PathVariable("id") Integer id, UsuariosModel usuario)
    {
        ModelAndView mv = new ModelAndView("/admin/infoClientesUsuario");

        mv.addObject("cliente", clientes.findOneById(id));

        return mv;
    }
}

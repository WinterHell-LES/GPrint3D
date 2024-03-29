package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.AdminFacadeService;

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
    private ClientesRepository clientesRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private AdminFacadeService adminFacadeService;

    @RequestMapping("/listarClientes")
    public ModelAndView listarClientes (ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("admin/clientes/listarClientes");

        mv.addObject("clientes", clientesRepository.findAll());

        return mv;
    }

    @PostMapping("/clientesAtiva")
    public ModelAndView ativacaoCliente (@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {
        UsuariosModel usu = usuariosRepository.findOneById(id);

        String[] mensagem;

        mensagem = adminFacadeService.ativarCliente(!usu.getUsuAtivo(), id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarClientes");
    }

    @GetMapping(value = "/infoCliente/{id}/dados")
    public ModelAndView listagemDadosCliente (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/clientes/info/infoClientesDados");

        mv.addObject("cliente", clientesRepository.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/infoCliente/{id}/documentos")
    public ModelAndView listagemDocumentosCliente (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/clientes/info/infoClientesDocumentos");

        mv.addObject("cliente", clientesRepository.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/infoCliente/{id}/telefones")
    public ModelAndView listagemTelefonesCliente (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/clientes/info/infoClientesTelefones");

        mv.addObject("cliente", clientesRepository.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/infoCliente/{id}/enderecos")
    public ModelAndView listagemEnderecosCliente (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/clientes/info/infoClientesEnderecos");

        mv.addObject("cliente", clientesRepository.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/infoCliente/{id}/cartoes")
    public ModelAndView listagemCartoesCliente (@PathVariable("id") Integer id, UsuariosModel usuario)
    {
        ModelAndView mv = new ModelAndView("/admin/clientes/info/infoClienteCartoes");

        mv.addObject("cliente", clientesRepository.findOneById(id));

        return mv;
    }

    @GetMapping(value = "/infoCliente/{id}/usuario")
    public ModelAndView listagemUsuariosCliente (@PathVariable("id") Integer id, UsuariosModel usuario)
    {
        ModelAndView mv = new ModelAndView("/admin/clientes/info/infoClientesUsuario");

        mv.addObject("cliente", clientesRepository.findOneById(id));

        return mv;
    }
}

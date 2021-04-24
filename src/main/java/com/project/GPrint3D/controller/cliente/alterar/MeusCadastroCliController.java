package com.project.GPrint3D.controller.cliente.alterar;

import java.security.Principal;

import javax.validation.Valid;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.DocumentosRepository;
import com.project.GPrint3D.repository.TelefonesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.ClientesService;
import com.project.GPrint3D.service.DocumentosService;
import com.project.GPrint3D.service.TelefonesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class MeusCadastroCliController
{
    @Autowired
    private DocumentosRepository documentosRepository;

    @Autowired
    private TelefonesRepository telefonesRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private DocumentosService documentosService;

    @Autowired
    private TelefonesService telefonesService;

    //Alterar senha do usuario
    @RequestMapping("/meuCadastro")
    public ModelAndView meuCadastro(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/meuCadastro");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());
        TelefonesModel tel = telefonesRepository.findByClienteId(usu.getCliente().getCliId());
        DocumentosModel doc = documentosRepository.findByClienteId(usu.getCliente().getCliId());
        
        mv.addObject("usuario", usu);
        mv.addObject("cliente", usu.getCliente());
        mv.addObject("telefone", tel);
        mv.addObject("documento", doc);

        return mv;
    }

    @PostMapping("/meuCadastro")
    public ModelAndView atualizarCadastro(@Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento, @Valid UsuariosModel usuario, BindingResult result, RedirectAttributes attributes, Principal principal)
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
}
package com.project.GPrint3D.controller.cliente.alterar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class AltPassCliController 
{
    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private UsuariosService usuariosService;

    //Tela de alteração de senha do usuario
    @RequestMapping("/alterarSenha")
    public ModelAndView alterarSenha(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarSenha");

        UsuariosModel usu = usuarios.findByEmail(principal.getName());        
        
        mv.addObject("usuario", usu);

        return mv;
    }

    //Alterar a senha do cliente
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
}

package com.project.GPrint3D.controller.cliente.alterar;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.project.GPrint3D.configuration.SecurityConfig;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.UsuariosRepository;
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
@RequestMapping("/cliente")
public class AltPassCliController 
{
    private UsuariosModel oldUser;

    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private SecurityConfig securityConfig;

    //Tela de alteração de senha do usuario
    @RequestMapping("/alterarSenha")
    public ModelAndView alteracaoSenha(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarSenha");

        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        
        oldUser = usu;
        
        mv.addObject("usuario", usu);

        return mv;
    }

    //Alterar a senha do cliente
    @PostMapping("/alterarSenha")
    public ModelAndView alterarSenha(@RequestParam(name = "oldPassword") String oldPass, @RequestParam(name = "confirmNewPassword") String confirmNewPass, @Valid UsuariosModel Usuario, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {

        if (result.hasErrors())
        {
            return alteracaoSenha(auth, principal);
        }

        String oldUserPass = oldUser.getUsuSenha();
        String newUserPass = Usuario.getUsuSenha();

        if (securityConfig.passwordEncoder().matches(oldPass, oldUserPass))
        {
            if (confirmNewPass.equals(newUserPass))
            {
                String[] msgDeErro = usuariosService.atualizarPass(Usuario);

                attributes.addFlashAttribute(msgDeErro[0], msgDeErro[1]);

                return new ModelAndView("redirect:/cliente/index");
            }
            else
            {
                //String msgError = "Senha nova diferente da confirmação de senha, favor digitar corretamente.";
            }
        }
        else
        {
            //String msgError = "Senha antiga não confere, favor digitar corretamente.";
        }

        return alteracaoSenha(auth, principal);
    }
}

package com.project.GPrint3D.controller.cliente;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CliFacadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class CliSenhaController
{
    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private CliFacadeService cliFacadeService;

    // Tela de alteração de senha do usuario
    @RequestMapping("/alterarSenha")
    public ModelAndView alteracaoSenha (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarSenha");

        UsuariosModel usu = usuarios.findByEmail(principal.getName());

        mv.addObject("usuario", usu);

        return mv;
    }

    // Alterar a senha do cliente
    @PostMapping("/alterarSenha")
    public ModelAndView alterarSenha (@RequestParam(name = "usuarioId") Integer usuarioId,
            @RequestParam(name = "oldPassword") String oldPassword,
            @RequestParam(name = "newPassword") String newPassword,
            @RequestParam(name = "confirmNewPassword") String confirmNewPassword, RedirectAttributes attributes,
            Principal principal)
    {
        List<String> listValidarSenha = cliFacadeService.validarSenhaAtualizacao(usuarioId, oldPassword, newPassword,
                confirmNewPassword);

        if (listValidarSenha.size() > 1)
        {
            List<String> auxList = new ArrayList<>();

            for (int i = 1 ; i < listValidarSenha.size() ; i++)
            {
                auxList.add(listValidarSenha.get(i));
            }

            attributes.addFlashAttribute(listValidarSenha.get(0), auxList);

            return new ModelAndView("redirect:/cliente/alterarSenha");
        }

        String[] mensagem2 = cliFacadeService.alterarSenha(usuarioId, newPassword);

        attributes.addFlashAttribute(mensagem2[0], mensagem2[1]);

        return new ModelAndView("redirect:/cliente/alterarSenha");
    }
}

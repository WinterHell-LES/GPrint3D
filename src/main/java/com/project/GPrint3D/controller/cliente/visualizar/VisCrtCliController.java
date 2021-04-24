package com.project.GPrint3D.controller.cliente.visualizar;

import java.security.Principal;

import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CartoesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class VisCrtCliController
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private CartoesService cartoesService;

    // Tela de cartões do cliente
    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/visualizar/meusCartoes");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());

        mv.addObject("cliente", usu.getCliente());

        return mv;
    }

    // Deletar cartão do cliente
    @PostMapping("/excluirCartao")
    public ModelAndView excluirCartao (@RequestParam(name = "id") Integer id)
    {
        cartoesService.excluir(id);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }

    @PostMapping("/editarCartao")
    public ModelAndView editarCartao (@RequestParam(name = "id") Integer id)
    {
        return new ModelAndView("redirect:/cliente/alterarCartao/" + id);
    }
}
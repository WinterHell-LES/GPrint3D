package com.project.GPrint3D.controller.admin;

import java.security.Principal;

import javax.validation.Valid;

import com.project.GPrint3D.model.EntradasModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.EntradasRepository;
import com.project.GPrint3D.service.AdminFacadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminEntrController
{
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private EntradasRepository entradasRepository;

    @Autowired
    private AdminFacadeService adminFacadeService;

    @RequestMapping("listarEntradas")
    public ModelAndView listarEntradas ()
    {
        ModelAndView mv = new ModelAndView("/admin/entradas/listarEntradas");

        mv.addObject("entradas", entradasRepository.findAll());

        return mv;
    }

    @RequestMapping("cadastrarEntradas")
    public ModelAndView cadastrarEntradas (EntradasModel entrada)
    {
        ModelAndView mv = new ModelAndView("/admin/entradas/cadastrarEntradas");

        mv.addObject("categorias", categoriasRepository.findAll());

        return mv;
    }

    @PostMapping("cadastrarEntradas")
    public ModelAndView cadastroEntradas (@Valid EntradasModel entrada, Principal principal, BindingResult result,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastrarEntradas(entrada);
        }

        String[] mensagem = adminFacadeService.cadastrarEntrada(entrada, principal);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastrarEntradas");
    }
}

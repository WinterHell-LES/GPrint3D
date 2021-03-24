package com.project.GPrint3D.controller.admin;

import java.security.Principal;

import javax.validation.Valid;

import com.project.GPrint3D.model.EntradasModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.EntradasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.EntradasService;

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
    private CategoriasRepository categorias;

    @Autowired
    private EntradasRepository entradas;

    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private EntradasService entradasService;

    @RequestMapping("listarEntradas")
    public ModelAndView listarEntradas()
    {
        ModelAndView mv = new ModelAndView("/admin/entradas/listarEntradas");

        mv.addObject("entradas", entradas.findAll());

        return mv;
    }

    @RequestMapping("cadastrarEntradas")
    public ModelAndView cadastrarEntradas(EntradasModel entrada)
    {
        ModelAndView mv = new ModelAndView("/admin/entradas/cadastrarEntradas");

        mv.addObject("categorias", categorias.findAll());

        return mv;
    }
    @PostMapping("cadastrarEntradas")
    public ModelAndView cadastroEntradas(@Valid EntradasModel entrada, Principal principal, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors() || (entrada.getEntQuantidade().intValue() <= 0))
        {
            return cadastrarEntradas(entrada);
        }

        entrada.setUsuario(usuarios.findByEmail(principal.getName()));

        String[] mensagem = entradasService.cadastrar(entrada);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastrarEntradas");
    }
}

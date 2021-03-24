package com.project.GPrint3D.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.repository.CategoriasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController 
{
    @Autowired
    private CategoriasRepository categorias;

    @RequestMapping({"/", "/index"})
    public ModelAndView index(@AuthenticationPrincipal User user, HttpServletRequest auth, Principal principal, CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/index");

        mv.addObject("categorias", categorias.findAll());
        
        return mv;
    }
}

package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.repository.CuponsTrocasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminCuponsTrcController
{
    @Autowired
    private CuponsTrocasRepository cuponsTrocasRepository;

    @RequestMapping("cupons/listarCuponsTrocas")
    public ModelAndView listarCuponsTrocas ()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/trocas/listarCuponsTrocas");

        mv.addObject("cupons", cuponsTrocasRepository.findAll());

        return mv;
    }
}

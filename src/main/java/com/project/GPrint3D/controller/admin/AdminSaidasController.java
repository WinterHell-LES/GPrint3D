package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.repository.SaidasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminSaidasController 
{
    @Autowired
    private SaidasRepository saidasRepository;

    @RequestMapping("listarSaidas")
    public ModelAndView listarSaidas()
    {
        ModelAndView mv = new ModelAndView("/admin/saidas/listarSaidas");

        mv.addObject("saidas", saidasRepository.findAll());

        return mv;
    }
}

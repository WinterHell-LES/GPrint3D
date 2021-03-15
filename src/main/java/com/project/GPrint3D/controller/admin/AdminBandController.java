package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminBandController 
{
    @RequestMapping("listarBandeiras")
    public ModelAndView listarBandeiras()
    {
        ModelAndView mv = new ModelAndView("/admin/bandeiras/listarBandeiras");

        return mv;
    }

    @RequestMapping("cadastrarBandeiras")
    public ModelAndView cadastrarBandeiras()
    {
        ModelAndView mv = new ModelAndView("/admin/bandeiras/cadastrarBandeiras");

        return mv;
    }
}

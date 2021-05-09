package com.project.GPrint3D.controller.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class CliIndexController 
{
    @RequestMapping({"/", "/index"})
    public ModelAndView index()
    {
        return new ModelAndView("/cliente/index");
    }
}

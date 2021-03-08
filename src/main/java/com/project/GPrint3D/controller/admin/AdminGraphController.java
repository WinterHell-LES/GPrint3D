package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminGraphController 
{
    @RequestMapping("visualizarVendas")
    public ModelAndView graficos()
    {
        ModelAndView mv = new ModelAndView("/admin/graficos/visualizarVendas");

        return mv;
    }
}

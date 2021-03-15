package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminCuponsTrcController 
{
    @RequestMapping("cupons/listarCuponsTrocas")
    public ModelAndView listarCuponsTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/trocas/listarCuponsTrocas");

        return mv;
    }
}

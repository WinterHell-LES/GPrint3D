package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminLogTransController 
{
    @RequestMapping("listarLogsTransacoes")
    public ModelAndView listarLogsTransacoes()
    {
        ModelAndView mv = new ModelAndView("/admin/logsTransacoes/listarLogsTransacoes");

        return mv;
    }
}

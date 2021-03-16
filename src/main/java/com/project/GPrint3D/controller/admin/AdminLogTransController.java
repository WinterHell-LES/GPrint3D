package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.model.LogTransacoesModel;
import com.project.GPrint3D.repository.LogTransacoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminLogTransController 
{
    @Autowired
    private LogTransacoesRepository logs;

    @RequestMapping("listarLogsTransacoes")
    public ModelAndView listarLogsTransacoes(LogTransacoesModel log)
    {
        ModelAndView mv = new ModelAndView("/admin/logsTransacoes/listarLogsTransacoes");

        mv.addObject("logs", logs.findAll(Sort.by("logId").descending()));

        return mv;
    }
}

package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.repository.ClientesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminClientesController 
{
    @Autowired
    private ClientesRepository clientes;

    @RequestMapping("clientes")
    public ModelAndView listagemAlunos(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("admin/clientes");

        mv.addObject("clientes", clientes.findAll());

        return mv;
    }
}

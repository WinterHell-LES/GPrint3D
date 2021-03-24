package com.project.GPrint3D.controller;

import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.repository.CategoriasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAtributesController 
{
    @Autowired
    private CategoriasRepository categorias;

    @ModelAttribute
    public void globalCategorias(CategoriasModel categoria, Model model) 
    {
        model.addAttribute("ctgs", categorias.findAll());
    }
}

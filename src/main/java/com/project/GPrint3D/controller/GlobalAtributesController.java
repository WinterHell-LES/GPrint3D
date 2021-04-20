package com.project.GPrint3D.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.util.CarrinhoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAtributesController 
{
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @ModelAttribute
    public void globalDefault(HttpServletRequest hRequest, Model model, @AuthenticationPrincipal User user) 
    {
        model.addAttribute("ctgs", categoriasRepository.findAll());

        if (user != null)
        {
            UsuariosModel usu = usuariosRepository.findByEmail(user.getUsername());

            if (usu.getCliente() == null)
            {
                model.addAttribute("usuario", "Administrador ");
            }
            else
            {
                model.addAttribute("usuario", usu.getCliente().getCliNome());
            }            
        }
    }

    @ModelAttribute
    public void globalAdmin(HttpServletRequest hRequest, Model model)
    {
        
    }

    @ModelAttribute
    public void globalCliente(HttpServletRequest hRequest, Model model)
    {
        
    }
}

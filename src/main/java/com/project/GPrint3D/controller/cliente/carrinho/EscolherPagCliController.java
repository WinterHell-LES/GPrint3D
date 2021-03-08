package com.project.GPrint3D.controller.cliente.carrinho;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class EscolherPagCliController
{
    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private UsuariosRepository usuarios;
    
    //Tela dos enderços do clientes
    @RequestMapping("/carrinho/escolherPagamento")
    public ModelAndView escolherEndereco(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/escolherPagamento");
        
        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        mv.addObject("cliente", cli);

        return mv;
    }
}

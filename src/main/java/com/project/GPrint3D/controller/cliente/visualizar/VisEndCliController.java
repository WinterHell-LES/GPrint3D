package com.project.GPrint3D.controller.cliente.visualizar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.EnderecosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class VisEndCliController 
{
    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private EnderecosService enderecosService;

    //Tela dos enderços do clientes
    @RequestMapping("/meusEnderecos")
    public ModelAndView meusEnderecos(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/visualizar/meusEnderecos");
        
        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        mv.addObject("cliente", cli);

        return mv;
    }

    //Deletar endereço do cliente
    @PostMapping("/deletarEndereco")
    public ModelAndView deletarEndereco(@RequestParam(name = "id") Integer id) 
    {
        enderecosService.excluir(id);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }

}

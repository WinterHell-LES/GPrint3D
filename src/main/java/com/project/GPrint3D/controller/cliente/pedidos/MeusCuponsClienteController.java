package com.project.GPrint3D.controller.cliente.pedidos;

import java.security.Principal;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.CuponsTrocasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class MeusCuponsClienteController 
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private CuponsTrocasRepository cuponsTrocasRepository;
    
    @RequestMapping("/meusCupons")
    public ModelAndView meusPedidos(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/pedidos/meusCupons");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());
        ClientesModel cli = clientesRepository.findByUsuarioId(usu.getUsuId());
        
        mv.addObject("cupons", cuponsTrocasRepository.findAllByCliente(cli.getCliId()));

        return mv;
    }
}
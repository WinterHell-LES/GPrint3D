package com.project.GPrint3D.controller.cliente;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.PedidosTrocasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class CliIndexController
{
    @Autowired
    private PedidosTrocasRepository pedidosTrocasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @RequestMapping({"/", "/index"})
    public ModelAndView index(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/index");

        UsuariosModel usuario = usuariosRepository.findByEmail(principal.getName());

        List<PedidosTrocasModel> listPedTroca = new ArrayList<>();

        for (PedidosTrocasModel aux : pedidosTrocasRepository.findAllByCliente(usuario.getCliente().getCliId())) 
        {
            if (aux.getPdtEscolha().equals(0) && aux.getPdtStatusPedido().equals(3))
            {
                listPedTroca.add(aux);
            }
        }

        mv.addObject("pedidos", listPedTroca);

        return mv;
    }
}

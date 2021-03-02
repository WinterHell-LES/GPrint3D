package com.project.GPrint3D.controller.cliente.visualizar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CartoesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class VisCrtCliController 
{

    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private UsuariosRepository usuarios;
    
    @Autowired
    private CartoesService cartoesService;

    //Tela de cartões do cliente
    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/visualizar/meusCartoes");
        
        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        mv.addObject("cliente", cli);

        return mv;
    }

    //Deletar cartão do cliente
    @PostMapping("/deletarCartao")
    public ModelAndView deletarCartao(@RequestParam(name = "id") Integer id, RedirectAttributes attributes) 
    {
        cartoesService.excluir(id);

        return new ModelAndView("redirect:/cliente/visualizar/meusCartoes");
    }
}

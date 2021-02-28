package com.project.GPrint3D.controller.cliente;

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
public class DadosClienteController 
{
    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private ClientesRepository clientes;

    @RequestMapping("/alterarSenha")
    public ModelAndView alterarSenha(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/alterarSenha");

        return mv;
    }

    @RequestMapping("/meuCadastro")
    public ModelAndView meuCadastro(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meuCadastro");

        UsuariosModel usu = usuarios.findByEmail("email@email.com");
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());
        
        mv.addObject("usuario", usu);
        mv.addObject("cliente", cli);

        return mv;
    }

    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusCartoes");

        return mv;
    }

    @RequestMapping("/meusEnderecos")
    public ModelAndView meusEnderecos(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusEnderecos");

        return mv;
    }
}
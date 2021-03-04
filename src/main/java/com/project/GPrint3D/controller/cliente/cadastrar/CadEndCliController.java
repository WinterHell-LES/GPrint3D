package com.project.GPrint3D.controller.cliente.cadastrar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.EnderecosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class CadEndCliController 
{
    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private EnderecosService enderecosService;

    //Tela de cadastro de novo endereço do cliente
    @RequestMapping("/cadastrarEndereco/{id}/{check}")
    public ModelAndView cadastroEndereco(@PathVariable("id") Integer id, @PathVariable("check") Integer check, EnderecosModel endereco, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastrar/cadastrarEndereco");
        
        mv.addObject("cliente", String.valueOf(id));
        mv.addObject("check", check);

        return mv;
    }

    //Cadastrar novo endereço do cliente
    @PostMapping("/cadastrarEndereco/{id}/{check}")
    public ModelAndView cadastrarEndereco(@PathVariable("id") Integer id, @PathVariable("check") Integer check, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/cadastrarEndereco/" + id);
        }

        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());
        
        endereco.setCliente(cli);

        enderecosService.cadastrar(endereco);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }
}

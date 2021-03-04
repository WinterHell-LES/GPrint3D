package com.project.GPrint3D.controller.cliente.cadastrar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CartoesService;

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
public class CadCrtCliController 
{
    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private UsuariosRepository usuarios;
    
    @Autowired
    private CartoesService cartoesService;

    //Tela de cadastro de novo cartão do cliente
    @RequestMapping("/cadastrarCartao/{id}")
    public ModelAndView cadastroCartao(@PathVariable("id") Integer id, CartoesModel cartao, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastrar/cadastrarCartao");
        
        mv.addObject("cliente", String.valueOf(id));

        return mv;
    }

    //Cadastrar novo cartão do cliente
    @PostMapping("/cadastrarCartao/{id}")
    public ModelAndView cadastrarCartao(@PathVariable("id") Integer id, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/cadastrarCartao/" + id);
        }

        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());
        
        cartao.setCliente(cli);

        cartoesService.cadastrar(cartao);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }
}
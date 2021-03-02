package com.project.GPrint3D.controller.cliente.alterar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.repository.EnderecosRepository;
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
public class AltEndCliController 
{
    @Autowired
    private EnderecosRepository enderecos;

    @Autowired
    private EnderecosService enderecosService;

    //Tela de alteração de dados do endereço do cliente
    @RequestMapping("/alterarEndereco/{id}")
    public ModelAndView alterarEndereco(@PathVariable("id") Integer id, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarEndereco");

        mv.addObject("endereco", enderecos.findOneById(id));

        return mv;
    }

    //Alterar os dados do endereço do cliente
    @PostMapping("/alterarEndereco/{id}")
    public ModelAndView alteraEndereco(@PathVariable("id") Integer id, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/alterarEndereco");
        }
        
       enderecosService.atualizar(endereco);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }
}

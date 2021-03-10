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
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView alterarEndereco(@PathVariable("id") Integer id, boolean endPadrao, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarEndereco");

        EnderecosModel endereco = enderecos.findOneById(id);

        
        if (endereco.isEndCobrancaPadrao())
        {
            endPadrao = true;
        }
        else if (endereco.isEndEntregaPadrao()) 
        {
            endPadrao = true;
        }

        mv.addObject("endereco", endereco);
        mv.addObject("endPadrao", endPadrao);

        return mv;
    }

    //Alterar os dados do endereço do cliente
    @PostMapping("/alterarEndereco/{id}")
    public ModelAndView alteraEndereco(@PathVariable("id") Integer id, @RequestParam(name = "endPadrao", defaultValue = "false") boolean endPadrao, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/alterarEndereco");
        }
        
        if (endPadrao == true)
        {
            if (endereco.isEndCobranca())
            {
                endereco.setEndCobrancaPadrao(true);
            } else
            {
                endereco.setEndCobrancaPadrao(false);
            }
            
            if (endereco.isEndEntrega()) 
            {
                endereco.setEndEntregaPadrao(true);
            } else
            {
                endereco.setEndEntregaPadrao(false);
            }
        }else
        {
            endereco.setEndEntregaPadrao(false);
            endereco.setEndCobrancaPadrao(false);
        }
        
       enderecosService.atualizar(endereco);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }
}

package com.project.GPrint3D.controller.cliente.alterar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.repository.CartoesRepository;
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
public class AltCrtCliController 
{
    @Autowired
    private CartoesRepository cartoes;
    
    @Autowired
    private CartoesService cartoesService;

    //Tela de alteração de dados do cartão do cliente
    @RequestMapping("/alterarCartao/{id}")
    public ModelAndView alterarCartao(@PathVariable("id") Integer id, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastrar/alterarCartao");

        mv.addObject("cartao", cartoes.findOneById(id));

        return mv;
    }

    //Alterar os dados do cartão do cliente
    @PostMapping("/alterarCartao/{id}")
    public ModelAndView alteraCartao(@PathVariable("id") Integer id, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/alterarCartao");
        }
        
        cartoesService.atualizar(cartao);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }
}

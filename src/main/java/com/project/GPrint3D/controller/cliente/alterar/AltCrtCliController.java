package com.project.GPrint3D.controller.cliente.alterar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.CartoesPadroesModel;
import com.project.GPrint3D.repository.CartoesPadroesRepository;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.service.CartoesPadroesService;
import com.project.GPrint3D.service.CartoesService;

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
public class AltCrtCliController 
{
    @Autowired
    private CartoesRepository cartoesRepository;
    
    @Autowired
    private CartoesPadroesRepository cartoesPadroesRepository;
    
    @Autowired
    private CartoesService cartoesService;
    
    @Autowired
    private CartoesPadroesService cartoesPadroesService;

    //Tela de alteração de dados do cartão do cliente
    @RequestMapping("/alterarCartao/{id}")
    public ModelAndView alterarCartao(@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarCartao");

        CartoesModel cartao = cartoesRepository.findOneById(id);
        CartoesPadroesModel cartaoPadrao = cartoesPadroesRepository.findByCartaoId(cartao.getCrtId());

        mv.addObject("cartao", cartao);

        if (cartaoPadrao != null)
        {
            mv.addObject("cartaoPadrao", cartaoPadrao);
        }

        return mv;
    }

    //Alterar os dados do cartão do cliente
    @PostMapping("/alterarCartao/{id}")
    public ModelAndView alteraCartao(@PathVariable("id") Integer id, @RequestParam(name = "crtPadrao", defaultValue = "false") Boolean crtPadrao, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes)
    {
        CartoesPadroesModel cartaoPadrao = cartoesPadroesRepository.findByClienteId(cartao.getCliente().getCliId());

        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/alterarCartao");
        }

        if (crtPadrao == true)
        {
            cartaoPadrao.setCartao(cartao);
            
            cartoesPadroesService.atualizar(cartaoPadrao);
        }
        
        cartoesService.atualizar(cartao);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }
}

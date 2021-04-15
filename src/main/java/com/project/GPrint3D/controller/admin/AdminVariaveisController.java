package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.service.VariaveisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminVariaveisController 
{
    @Autowired
    private VariaveisRepository variaveisRepository;

    @Autowired
    private VariaveisService variaveisService;

    @RequestMapping("listarVariaveis")
    public ModelAndView listarVariaveis(VariaveisModel variavel)
    {
        ModelAndView mv = new ModelAndView("/admin/configuracoes/listarVariaveis");

        mv.addObject("variaveis", variaveisRepository.findOneById(1));

        return mv;
    }

    @RequestMapping("cadastrarVariaveis")
    public ModelAndView cadastrarVariaveis(VariaveisModel variavel)
    {
        ModelAndView mv = new ModelAndView("/admin/configuracoes/cadastrarVariaveis");

        return mv;
    }
    @PostMapping("cadastrarVariaveis")
    public ModelAndView cadastroVariaveis(@Valid VariaveisModel variavel, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastrarVariaveis(variavel);
        }

        String[] mensagem = variaveisService.cadastrar(variavel);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastrarVariaveis");
    }

    @PostMapping("/alterarVariaveis")
    public ModelAndView alterarCategoria(@Valid VariaveisModel variavel, RedirectAttributes attributes)
    {
        String[] mensagem = variaveisService.atualizar(variavel);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarVariaveis");
    }

    @PostMapping("/deletaVariaveis")
    public ModelAndView deletarBandeiras(@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {        
        String[] mensagem = variaveisService.excluir(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarVariaveis");
    }
}

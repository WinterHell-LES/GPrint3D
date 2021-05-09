package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.PrecificacoesModel;
import com.project.GPrint3D.repository.PrecificacoesRepository;
import com.project.GPrint3D.service.AdminFacadeService;

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
public class AdminPrecifiController
{
    @Autowired
    private PrecificacoesRepository precificacoesRepository;

    @Autowired
    private AdminFacadeService adminFacadeService;

    @RequestMapping("listarPrecificacoes")
    public ModelAndView listarPrecificacoes (PrecificacoesModel precificacao)
    {
        ModelAndView mv = new ModelAndView("/admin/precificacoes/listarPrecificacoes");

        mv.addObject("precificacoes", precificacoesRepository.findAll());

        return mv;
    }

    @RequestMapping("cadastrarPrecificacoes")
    public ModelAndView cadastrarPrecificacoes (PrecificacoesModel precificacao)
    {
        ModelAndView mv = new ModelAndView("/admin/precificacoes/cadastrarPrecificacoes");

        return mv;
    }

    @PostMapping("cadastroPrecificacoes")
    public ModelAndView cadastroPrecificacoes (@Valid PrecificacoesModel precificacao, BindingResult result,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastrarPrecificacoes(precificacao);
        }

        String[] mensagem = adminFacadeService.cadastrarPrecificacao(precificacao);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastrarPrecificacoes");
    }

    @PostMapping("alterarPrecificacoes")
    public ModelAndView alterarPrecificacoes (@RequestParam(name = "id") Integer id, PrecificacoesModel precificacao)
    {
        ModelAndView mv = new ModelAndView("/admin/precificacoes/alterarPrecificacoes");

        mv.addObject("precificacao", precificacoesRepository.findOneById(id));

        return mv;
    }

    @PostMapping("alterarPrecificacao")
    public ModelAndView alterarPrecificacao (@Valid PrecificacoesModel precificacao, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.atualizarPrecificacao(precificacao);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarPrecificacoes");
    }

    @PostMapping("/deletaPrecificacoes")
    public ModelAndView deletaPrecificacoes (@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.excluirPrecificacao(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarPrecificacoes");
    }
}

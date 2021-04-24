package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.service.CategoriasService;

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
public class AdminCategController
{
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private CategoriasService categoriasService;

    @RequestMapping("listarCategorias")
    public ModelAndView listarCategorias (CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/admin/categorias/listarCategorias");

        mv.addObject("categorias", categoriasRepository.findAll());

        return mv;
    }

    @RequestMapping("cadastrarCategorias")
    public ModelAndView cadastrarCategorias (CategoriasModel categoria)
    {
        return new ModelAndView("/admin/categorias/cadastrarCategorias");
    }

    @PostMapping("cadastrarCategorias")
    public ModelAndView cadastroCategorias (@Valid CategoriasModel categoria, BindingResult result,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastrarCategorias(categoria);
        }

        String[] mensagem = categoriasService.cadastrar(categoria);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastrarCategorias");
    }

    @PostMapping("/alterarCategorias")
    public ModelAndView alterarCategorias (@RequestParam(name = "id") Integer id, CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/admin/categorias/alterarCategorias");

        mv.addObject("categoria", categoriasRepository.findOneById(id));

        return mv;
    }

    @PostMapping("/alterarCategoria")
    public ModelAndView alterarCategoria (@Valid CategoriasModel categoria, RedirectAttributes attributes)
    {
        String[] mensagem = categoriasService.atualizar(categoria);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarCategorias");
    }

    @PostMapping("/ativaCategorias")
    public ModelAndView ativacaoBandeiras (@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {
        CategoriasModel ctg = categoriasRepository.findOneById(id);

        String[] mensagem = categoriasService.ativar(!ctg.getCtgAtivo(), id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarCategorias");
    }
}

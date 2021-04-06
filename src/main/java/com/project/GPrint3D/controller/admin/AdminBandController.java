package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.BandeirasModel;
import com.project.GPrint3D.repository.BandeirasRepository;
import com.project.GPrint3D.service.BandeirasService;

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
public class AdminBandController 
{
    @Autowired
    private BandeirasRepository bandeirasRepository;

    @Autowired
    private BandeirasService bandeirasService;

    @RequestMapping("listarBandeiras")
    public ModelAndView listarBandeiras(BandeirasModel bandeira)
    {
        ModelAndView mv = new ModelAndView("/admin/bandeiras/listarBandeiras");

        mv.addObject("bandeiras", bandeirasRepository.findAll());

        return mv;
    }

    @RequestMapping("cadastrarBandeiras")
    public ModelAndView cadastrarBandeiras(BandeirasModel bandeira)
    {
        ModelAndView mv = new ModelAndView("/admin/bandeiras/cadastrarBandeiras");

        return mv;
    }
    @PostMapping("cadastrarBandeiras")
    public ModelAndView cadastroBandeiras(@Valid BandeirasModel bandeira, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastrarBandeiras(bandeira);
        }

        String[] mensagem = bandeirasService.cadastrar(bandeira);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastrarBandeiras");
    }

    @PostMapping("/ativaBandeiras")
    public ModelAndView ativacaoBandeiras(@RequestParam(name = "id") Integer id, RedirectAttributes attributes) 
    {
        BandeirasModel band = bandeirasRepository.findOneById(id);

        String[] mensagem = bandeirasService.ativar(!band.getBanAtivo(), id); 

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarBandeiras");
    }

    // Controle de exclusão
    @PostMapping("/deletaBandeiras")
    public ModelAndView deletarBandeiras(@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {        
        String[] mensagem = bandeirasService.excluir(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarBandeiras");
    }
}

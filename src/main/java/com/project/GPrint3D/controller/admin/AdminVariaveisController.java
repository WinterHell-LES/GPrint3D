package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.service.AdminFacadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminVariaveisController
{
    @Autowired
    private VariaveisRepository variaveisRepository;

    @Autowired
    private AdminFacadeService adminFacadeService;

    @RequestMapping("listarVariaveis")
    public ModelAndView listarVariaveis (VariaveisModel variavel)
    {
        ModelAndView mv = new ModelAndView("/admin/configuracoes/listarVariaveis");

        mv.addObject("variaveis", variaveisRepository.findOneById(1));

        return mv;
    }

    @PostMapping("/alterarVariaveis")
    public ModelAndView alterarCategoria (@Valid VariaveisModel variavel, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.atualizarVariaveis(variavel);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarVariaveis");
    }
}

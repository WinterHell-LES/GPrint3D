package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.CuponsPromocoesModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.CuponsPromocoesRepository;
import com.project.GPrint3D.service.CuponsPromocoesService;
import com.project.GPrint3D.util.GeradorCodigoUtil;

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
public class AdminCuponsProController
{
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private CuponsPromocoesRepository cuponsPromocoesRepository;

    @Autowired
    private CuponsPromocoesService cuponsPromocoesService;

    @RequestMapping("cupons/listarCuponsPromocionais")
    public ModelAndView listarCuponsPromocionais ()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/promocionais/listarCuponsPromocionais");

        mv.addObject("cupons", cuponsPromocoesRepository.findAll());

        return mv;
    }

    @RequestMapping("cupons/cadastrarCuponsPromocionais")
    public ModelAndView cadastrarCuponsPromocionais (CuponsPromocoesModel cuponsPromocao)
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/promocionais/cadastrarCuponsPromocionais");

        mv.addObject("categorias", categoriasRepository.findAll());

        return mv;
    }

    @PostMapping("cupons/cadastrarCuponsPromocionais")
    public ModelAndView cadastroCuponsPromocionais (@Valid CuponsPromocoesModel cuponsPromocao, BindingResult result,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastrarCuponsPromocionais(cuponsPromocao);
        }

        GeradorCodigoUtil codigo = new GeradorCodigoUtil();

        cuponsPromocao.setCppCodigo(codigo.getGerarCodigoPromocional());

        String[] mensagem = cuponsPromocoesService.cadastrar(cuponsPromocao);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cupons/cadastrarCuponsPromocionais");
    }

    @PostMapping("cupons/alterarCuponsPromocionais")
    public ModelAndView alterarCuponsPromocionais (@RequestParam(name = "id") Integer id,
            CuponsPromocoesModel cuponsPromocao)
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/promocionais/alterarCuponsPromocionais");

        mv.addObject("cupom", cuponsPromocoesRepository.findOneById(id));
        mv.addObject("categorias", categoriasRepository.findAll());

        return mv;
    }

    @PostMapping("cupons/alterarCuponsPromocional")
    public ModelAndView alterarCuponsPromocionais (@Valid CuponsPromocoesModel cuponsPromocao, BindingResult result,
            RedirectAttributes attributes)
    {
        String[] mensagem = cuponsPromocoesService.atualizar(cuponsPromocao);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cupons/listarCuponsPromocionais");
    }

    @PostMapping("cupons/deletaCuponsPromocionais")
    public ModelAndView deletaCuponsPromocionais (@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {
        String[] mensagem = cuponsPromocoesService.excluir(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cupons/listarCuponsPromocionais");
    }
}

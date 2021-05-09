package com.project.GPrint3D.controller.cliente;

import java.security.Principal;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.CartoesPadroesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.BandeirasRepository;
import com.project.GPrint3D.repository.CartoesPadroesRepository;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CliFacadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class CliCartaoController
{
    @Autowired
    private BandeirasRepository bandeirasRepository;

    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private CartoesPadroesRepository cartoesPadroesRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private CliFacadeService cliFacadeService;

    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/visualizar/meusCartoes");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());

        mv.addObject("cliente", usu.getCliente());

        return mv;
    }

    @PostMapping("/cadastrarCartao")
    public ModelAndView cadastrarCartao (@RequestParam(name = "id") Integer id, CartoesModel cartao)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastrar/cadastrarCartao");

        mv.addObject("cliente", clientesRepository.findOneById(id));
        mv.addObject("bandeiras", bandeirasRepository.findAll());

        return mv;
    }

    @PostMapping("/cadastraCartao")
    public ModelAndView cadastraCartao (@RequestParam(name = "crtPadrao", defaultValue = "false") boolean crtPadrao,
            @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes,
            Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/meusCartoes/");
        }

        String[] mensagem = cliFacadeService.cadastrarCartao(cartao, crtPadrao, principal);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }

    @PostMapping("/alterarCartao")
    public ModelAndView alterarCartao (@RequestParam(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarCartao");

        CartoesModel cartao = cartoesRepository.findOneById(id);
        CartoesPadroesModel cartaoPadrao = cartoesPadroesRepository.findByCartaoId(cartao.getCrtId());

        mv.addObject("cartao", cartao);
        mv.addObject("bandeiras", bandeirasRepository.findAll());

        if (cartaoPadrao != null)
        {
            mv.addObject("cartaoPadrao", cartaoPadrao);
        }

        return mv;
    }

    @PostMapping("/alteraCartao")
    public ModelAndView alteraCartao (@RequestParam(name = "crtPadrao", defaultValue = "false") boolean crtPadrao, 
            @RequestParam(name = "cvv") String cvv, @Valid CartoesModel cartao,
            BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/meusCartoes");
        }

        String[] mensagem = cliFacadeService.atualizarCartao(cartao, crtPadrao, cvv);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }

    @PostMapping("/excluirCartao")
    public ModelAndView excluirCartao (@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {
        String[] mensagem = cliFacadeService.excluirCartao(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }
}

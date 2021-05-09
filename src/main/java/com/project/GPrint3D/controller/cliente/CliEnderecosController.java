package com.project.GPrint3D.controller.cliente;

import java.security.Principal;

import javax.validation.Valid;

import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
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
public class CliEnderecosController
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private CliFacadeService cliFacadeService;

    // Tela dos enderços do clientes
    @RequestMapping("/meusEnderecos")
    public ModelAndView meusEnderecos (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/visualizar/meusEnderecos");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());

        mv.addObject("cliente", usu.getCliente());

        return mv;
    }

    @PostMapping("/cadastrarEndereco")
    public ModelAndView cadastrarEndereco (@RequestParam(name = "id") Integer id,
            @RequestParam(name = "check") Integer check, EnderecosModel endereco)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastrar/cadastrarEndereco");

        if (check == 0)
        {
            endereco.setEndEntrega(true);
        }
        else
        {
            endereco.setEndCobranca(true);
        }

        mv.addObject("cliente", clientesRepository.findOneById(id));

        return mv;
    }

    @PostMapping("/cadastraEndereco")
    public ModelAndView cadastraEndereco (@RequestParam(name = "endPadrao", defaultValue = "false") boolean endPadrao,
            @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/meusEnderecos");
        }

        String[] mensagem = cliFacadeService.cadastrarEndereco(endereco, endPadrao, principal);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }

    @PostMapping("/alterarEndereco")
    public ModelAndView alterarEndereco (@RequestParam(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarEndereco");

        EnderecosModel endereco = enderecosRepository.findOneById(id);

        if (endereco.getEndId().equals(endereco.getCliente().getEndCobrancaPadrao().getEndereco().getEndId()))
        {
            mv.addObject("endPadrao", endereco.getEndCobrancaPadrao());
        }

        if (endereco.getEndId().equals(endereco.getCliente().getEndEntregaPadrao().getEndereco().getEndId()))
        {
            mv.addObject("endPadrao", endereco.getEndEntregaPadrao());
        }

        mv.addObject("endereco", endereco);

        return mv;
    }

    @PostMapping("/alteraEndereco")
    public ModelAndView alteraEndereco (@RequestParam(name = "endPadrao", defaultValue = "false") boolean endPadrao,
            @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/meusEnderecos");
        }

        String[] mensagem = cliFacadeService.atualizarEndereco(endereco, endPadrao);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }

    @PostMapping("/excluirEndereco")
    public ModelAndView excluirEndereco (@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {
        String[] mensagem = cliFacadeService.excluirEndereco(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }
}

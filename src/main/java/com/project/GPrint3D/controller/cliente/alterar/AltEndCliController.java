package com.project.GPrint3D.controller.cliente.alterar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import com.project.GPrint3D.model.EndCobrancasPadroesModel;
import com.project.GPrint3D.model.EndEntregasPadroesModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.repository.EndCobrancasPadroesRepository;
import com.project.GPrint3D.repository.EndEntregasPadroesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.service.EndCobrancasPadroesService;
import com.project.GPrint3D.service.EndEntregasPadroesService;
import com.project.GPrint3D.service.EnderecosService;

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
public class AltEndCliController 
{
    @Autowired
    private EnderecosRepository enderecos;

    @Autowired
    private EndCobrancasPadroesRepository endCobrancasPadroes;

    @Autowired
    private EndEntregasPadroesRepository endEntregasPadroes;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private EndCobrancasPadroesService endCobrancasPadroesService;

    @Autowired
    private EndEntregasPadroesService endEntregasPadroesService;

    //Tela de alteração de dados do endereço do cliente
    @RequestMapping("/alterarEndereco/{id}")
    public ModelAndView alterarEndereco(@PathVariable("id") Integer id, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/alterar/alterarEndereco");

        EnderecosModel endereco = enderecos.findOneById(id);

        if (endereco.getEndId() == endereco.getCliente().getEndCobrancaPadrao().getEndereco().getEndId())
        {
            mv.addObject("endPadrao", endereco.getEndCobrancaPadrao());
        }

        if (endereco.getEndId() == endereco.getCliente().getEndEntregaPadrao().getEndereco().getEndId())
        {
            mv.addObject("endPadrao", endereco.getEndEntregaPadrao());
        }

        mv.addObject("endereco", endereco);

        return mv;
    }

    //Alterar os dados do endereço do cliente
    @PostMapping("/alterarEndereco/{id}")
    public ModelAndView alteraEndereco(@PathVariable("id") Integer id, @RequestParam(name = "endPadrao", defaultValue = "false") boolean endPadrao, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, Principal principal)
    {
        EndCobrancasPadroesModel endCobrancaPadrao = endCobrancasPadroes.findByClienteId(endereco.getCliente().getCliId());
        EndEntregasPadroesModel endEntregaPadrao = endEntregasPadroes.findByClienteId(endereco.getCliente().getCliId());

        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/alterarEndereco");
        }

        if (endPadrao == true)
        {
            if (endereco.isEndCobranca())
            {
                endCobrancaPadrao.setEndereco(endereco);

                endCobrancasPadroesService.atualizar(endCobrancaPadrao);
            }

            if (endereco.isEndEntrega())
            {
                endEntregaPadrao.setEndereco(endereco);

                endEntregasPadroesService.atualizar(endEntregaPadrao);
            }
        }

        enderecosService.atualizar(endereco);

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }
}

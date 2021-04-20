package com.project.GPrint3D.controller.cliente.cadastrar;

import java.security.Principal;

import javax.validation.Valid;

import com.project.GPrint3D.model.EndCobrancasPadroesModel;
import com.project.GPrint3D.model.EndEntregasPadroesModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.EndCobrancasPadroesRepository;
import com.project.GPrint3D.repository.EndEntregasPadroesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
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
public class CadEndCliController 
{
    @Autowired
    private UsuariosRepository usuariosRepository;

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

    //Tela de cadastro de novo endereço do cliente
    @RequestMapping("/cadastrarEndereco/{id}/{check}")
    public ModelAndView cadastroEndereco(@PathVariable("id") Integer id, @PathVariable("check") Integer check, EnderecosModel endereco)
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
        
        mv.addObject("cliente", id);

        return mv;
    }

    //Cadastrar novo endereço do cliente
    @PostMapping("/cadastrarEndereco/{id}/{check}")
    public ModelAndView cadastrarEndereco(@PathVariable("id") Integer id, @RequestParam(name = "endPadrao", defaultValue = "false") boolean endPadrao, @Valid EnderecosModel endereco, BindingResult result, RedirectAttributes attributes, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/cadastrarEndereco/" + id);
        }

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());
        EndCobrancasPadroesModel endCobrancaPadrao = endCobrancasPadroes.findByClienteId(endereco.getCliente().getCliId());
        EndEntregasPadroesModel endEntregaPadrao = endEntregasPadroes.findByClienteId(endereco.getCliente().getCliId());

        endereco.setCliente(usu.getCliente());

        enderecosService.cadastrar(endereco);

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

        return new ModelAndView("redirect:/cliente/meusEnderecos");
    }
}
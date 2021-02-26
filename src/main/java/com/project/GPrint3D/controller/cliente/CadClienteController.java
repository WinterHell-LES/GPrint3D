package com.project.GPrint3D.controller.cliente;

import javax.validation.Valid;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.service.CartoesService;
import com.project.GPrint3D.service.ClientesService;
import com.project.GPrint3D.service.DocumentosService;
import com.project.GPrint3D.service.EnderecosService;
import com.project.GPrint3D.service.TelefonesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class CadClienteController 
{    
    @Autowired
    private CartoesService cartoesService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private DocumentosService documentosService;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private TelefonesService telefonesService;
    
    // Controle de cadastro de dados pessoais
    @RequestMapping("/cadastroDadosPessoais")
    public ModelAndView cadastroDadosPessoais(ClientesModel cliente, TelefonesModel telefone, DocumentosModel documento, UsuariosModel usuario, EnderecosModel endereco, CartoesModel cartao)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadDadosPessoais");

        return mv;
    }
    @PostMapping("/cadastroDadosPessoais")
    public ModelAndView cadastrarDadosPessoais(@Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento, @Valid UsuariosModel usuario, @Valid EnderecosModel endereco, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroDadosPessoais(cliente, telefone, documento, usuario, endereco, cartao);
        }

        return cadastroEndereco(cliente, telefone, documento, usuario, endereco, cartao);
    }

    @RequestMapping("/cadastroEndereco")
    public ModelAndView cadastroEndereco(ClientesModel cliente, TelefonesModel telefone, DocumentosModel documento, UsuariosModel usuario, EnderecosModel endereco, CartoesModel cartao)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadEndereco");

        return mv;
    }
    @PostMapping("/cadastroEndereco")
    public ModelAndView cadastrarEndereco(@Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento, @Valid UsuariosModel usuario, @Valid EnderecosModel endereco, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroEndereco(cliente, telefone, documento, usuario, endereco, cartao);
        }

        return cadastroCartao(cliente, telefone, documento, usuario, endereco, cartao);
    }

    @RequestMapping("/cadastroCartao")
    public ModelAndView cadastroCartao(ClientesModel cliente, TelefonesModel telefone, DocumentosModel documento, UsuariosModel usuario, EnderecosModel endereco, CartoesModel cartao)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadCartao");

        return mv;
    }
    @PostMapping("/cadastroCartao")
    public ModelAndView cadastrarCartao(@Valid ClientesModel cliente, @Valid TelefonesModel telefone, @Valid DocumentosModel documento, @Valid UsuariosModel usuario, @Valid EnderecosModel endereco, @Valid CartoesModel cartao, BindingResult result, RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return cadastroCartao(cliente, telefone, documento, usuario, endereco, cartao);
        }

        String[] mensagem1 = clientesService.cadastrar(cliente);
        telefonesService.cadastrar(telefone);
        documentosService.cadastrar(documento);
        enderecosService.cadastrar(endereco);
        cartoesService.cadastrar(cartao);
  
        attributes.addFlashAttribute(mensagem1[0], mensagem1[1]);

        return new ModelAndView("redirect:/index");
    }

    @RequestMapping("/cadastroEndereco2")
    public ModelAndView cadEndereco2(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadEndereco2");

        return mv;
    }

    @RequestMapping("/cadastroCartao2")
    public ModelAndView cadCartao2(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadCartao2");

        return mv;
    }
}
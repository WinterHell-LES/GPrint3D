package com.project.GPrint3D.controller.cliente.visualizar;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CartoesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class VisCrtCliController 
{

    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private UsuariosRepository usuarios;
    
    @Autowired
    private CartoesService cartoesService;

    //Tela de cartões do cliente
    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/visualizar/meusCartoes");
        
        UsuariosModel usu = usuarios.findByEmail(principal.getName());
        ClientesModel cli = clientes.findByUsuarioId(usu.getUsuId());

        mv.addObject("cliente", cli);

        return mv;
    }

    //Deletar cartão do cliente
    @PostMapping("/excluirCartao")
    public ModelAndView excluirCartao(@RequestParam(name = "id") Integer id) 
    {
        cartoesService.excluir(id);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }

    @PostMapping("/editarCartao")
    public ModelAndView editarCartao(@RequestParam(name = "id") Integer id)
    {
        return new ModelAndView("redirect:/cliente/alterarCartao/" + id);
    }
}

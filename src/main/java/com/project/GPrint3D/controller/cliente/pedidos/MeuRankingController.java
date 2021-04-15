package com.project.GPrint3D.controller.cliente.pedidos;

import java.security.Principal;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class MeuRankingController 
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private VariaveisRepository variaveisRepository;

    @RequestMapping("/meuRanking")
    public ModelAndView meuRanking(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/Pedidos/meuRanking");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());
        ClientesModel cli = clientesRepository.findByUsuarioId(usu.getUsuId());

        VariaveisModel var = variaveisRepository.findOneById(1);

        int pontosRank = verificaRank(cli.getCliRanking(), var);
        
        mv.addObject("cliente", cli);
        mv.addObject("variaveis", var); 
        mv.addObject("ponto", (int) Math.ceil(cli.getCliPontos() / (pontosRank / 100)));
        mv.addObject("pontosRank", pontosRank);       

        return mv;
    }

    private int verificaRank (int rank, VariaveisModel variavel)
    {
        switch (rank)
        {
            case 0:
                return variavel.getVarRank1();
            case 1:
                return variavel.getVarRank2();
            case 2:
                return variavel.getVarRank3();
            case 3:
                return variavel.getVarRank4();
            case 4:
                return variavel.getVarRank4();
            default:
                return 0;
        }
    }
}
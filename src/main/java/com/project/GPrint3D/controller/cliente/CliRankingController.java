package com.project.GPrint3D.controller.cliente;

import java.security.Principal;

import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class CliRankingController
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private VariaveisRepository variaveisRepository;

    @RequestMapping("/meuRanking")
    public ModelAndView meuRanking (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/Pedidos/meuRanking");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());

        VariaveisModel var = variaveisRepository.findOneById(1);

        int pontosRank = verificaRank(usu.getCliente().getCliRanking(), var);

        mv.addObject("cliente", usu.getCliente());
        mv.addObject("variaveis", var);
        mv.addObject("ponto", (int) Math.ceil(usu.getCliente().getCliPontos() / (pontosRank / 100)));
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

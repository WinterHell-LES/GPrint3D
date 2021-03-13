package com.project.GPrint3D.util;

import com.project.GPrint3D.model.EndCobrancasPadroesModel;
import com.project.GPrint3D.model.EndEntregasPadroesModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.service.EndCobrancasPadroesService;
import com.project.GPrint3D.service.EndEntregasPadroesService;

import org.springframework.beans.factory.annotation.Autowired;

public class configurarEndPadrao
{
    @Autowired
    private EndCobrancasPadroesService endCobrancasPadroesService;

    @Autowired
    private EndEntregasPadroesService endEntregasPadroesService;
    
    public void setEndPadrao(EnderecosModel endereco, EndCobrancasPadroesModel endCobrancaPadrao, EndEntregasPadroesModel endEntregaPadrao)
    {
        if (endereco.isEndCobranca())
        {
            endCobrancaPadrao.setEndereco(endereco);
            System.out.println("EcpId: " + endCobrancaPadrao.getEcpId());
            System.out.println("CliId: " + endCobrancaPadrao.getCliente().getCliId());
            System.out.println("EndId: " + endCobrancaPadrao.getEndereco().getEndId());

            endCobrancasPadroesService.atualizar(endCobrancaPadrao);
        }

        if (endereco.isEndEntrega())
        {
            endEntregaPadrao.setEndereco(endereco);

            endEntregasPadroesService.atualizar(endEntregaPadrao);
        }
    }
}

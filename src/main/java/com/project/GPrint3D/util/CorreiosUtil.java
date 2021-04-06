package com.project.GPrint3D.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import com.project.GPrint3D.model.ProdutosModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CorreiosUtil 
{
    public HashMap<String, String> getValidarCEP(String cep)
    {
        HashMap<String, String> resp = new HashMap<>();

        String url = "https://viacep.com.br/ws/" + cep + "/xml/";

        try 
        {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();
            //System.out.println("Response code: " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }

            in.close();

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));

            NodeList errNodes = doc.getElementsByTagName("xmlcep");

            if (errNodes.getLength() > 0)
            {
                Element err = (Element) errNodes.item(0);

                if (err.getElementsByTagName("cep").item(0).getTextContent().length() > 0)
                {
                    resp.put("erro", "false");
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e);

            resp.put("erro", "true");
        }

        return resp;
    }

    public List<HashMap<String, String>> getValorPrazo(String cep, ProdutosModel produto)
    {
        // Código  Serviço
        // 04014   SEDEX à vista
        // 04510   PAC à vista
        // 04782   SEDEX 12 ( à vista)
        // 04790   SEDEX 10 (à vista)
        // 04804   SEDEX Hoje à vista

        List<HashMap<String, String>> listHash = new ArrayList<>();
        
        String cepRemetente = "08745290";
        String[] formEnvioNome = {"SEDEX", "PAC", "SEDEX 12", "SEDEX 10", "SEDEX Hoje"};
        String[] formEnvioCod = {"04014", "04510", "04782", "04790", "04804"};

        for (int i = 0 ; i < formEnvioNome.length ; i++)
        {
            try 
            {
                String url = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx" + 
                                "?nCdEmpresa=" +
                                "&sDsSenha=" +
                                "&sCepOrigem=" + cepRemetente +
                                "&sCepDestino=" + cep +
                                "&nVlPeso=" + (produto.getPrdDimEmbPe() / 10) +
                                "&nCdFormato=1" +
                                "&nVlComprimento=" + (produto.getPrdDimEmbPr() / 10) +
                                "&nVlAltura=" + (produto.getPrdDimEmbAl() / 10) +
                                "&nVlLargura=" + (produto.getPrdDimEmbLa() / 10) +
                                "&sCdMaoPropria=N" +
                                "&nVlValorDeclarado=0" +
                                "&sCdAvisoRecebimento=N" +
                                "&nCdServico=" + formEnvioCod[i] +
                                "&nVlDiametro=0" +
                                "&StrRetorno=xml" +
                                "&nIndicaCalculo=3";

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                int responseCode = con.getResponseCode();
                //System.out.println("Response code: " + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }

                in.close();

                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));

                NodeList errNodes = doc.getElementsByTagName("cServico");

                if (errNodes.getLength() > 0)
                {
                    HashMap<String, String> resp = new HashMap<>();

                    Element err = (Element) errNodes.item(0);

                    resp.put("Nome", formEnvioNome[i]);
                    resp.put("Valor", err.getElementsByTagName("Valor").item(0).getTextContent());
                    resp.put("PrazoEntrega", err.getElementsByTagName("PrazoEntrega").item(0).getTextContent());

                    listHash.add(resp);
                }
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
        }

        return listHash;
    }
}

package com.project.GPrint3D.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import com.project.GPrint3D.model.PrdCarrinhosModel;
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

            //int responseCode = con.getResponseCode();
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

    public List<HashMap<String, String>> getValorPrazo(String cepRemetente, String cepDestinatario, ProdutosModel produto)
    {
        // Código  Serviço
        // 04014   SEDEX à vista
        // 04510   PAC à vista
        // 04782   SEDEX 12 (à vista)
        // 04790   SEDEX 10 (à vista)
        // 04804   SEDEX Hoje à vista

        List<HashMap<String, String>> listHash = new ArrayList<>();

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
                                "&sCepDestino=" + cepDestinatario +
                                "&nVlPeso=" + Math.ceil(produto.getPrdDimEmbPe()) +
                                "&nCdFormato=1" +
                                "&nVlComprimento=" + Math.ceil((produto.getPrdDimEmbPr() / 10)) +
                                "&nVlAltura=" + Math.ceil((produto.getPrdDimEmbAl() / 10)) +
                                "&nVlLargura=" + Math.ceil((produto.getPrdDimEmbLa() / 10)) +
                                "&sCdMaoPropria=N" +
                                "&nVlValorDeclarado=0" +
                                "&sCdAvisoRecebimento=N" +
                                "&nCdServico=" + formEnvioCod[i] +
                                "&nVlDiametro=0" +
                                "&StrRetorno=xml" +
                                "&nIndicaCalculo=3";

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //int responseCode = con.getResponseCode();
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
                    resp.put("Codigo", err.getElementsByTagName("Codigo").item(0).getTextContent());
                    resp.put("Valor", err.getElementsByTagName("Valor").item(0).getTextContent());
                    resp.put("PrazoEntrega", err.getElementsByTagName("PrazoEntrega").item(0).getTextContent());
                    resp.put("ValorSemAdicionais", err.getElementsByTagName("ValorSemAdicionais").item(0).getTextContent());
                    resp.put("ValorMaoPropria", err.getElementsByTagName("ValorMaoPropria").item(0).getTextContent());
                    resp.put("ValorAvisoRecebimento", err.getElementsByTagName("ValorAvisoRecebimento").item(0).getTextContent());
                    resp.put("ValorValorDeclarado", err.getElementsByTagName("ValorValorDeclarado").item(0).getTextContent());
                    resp.put("EntregaDomiciliar", err.getElementsByTagName("EntregaDomiciliar").item(0).getTextContent());
                    resp.put("EntregaSabado", err.getElementsByTagName("EntregaSabado").item(0).getTextContent());

                    listHash.add(resp);
                }
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
        }

        listHash.sort(Comparator.comparing(o -> String.valueOf(o.get("Valor"))));

        return listHash;
    }
    
    public List<HashMap<String, String>> getValorPrazoLista(String cepRemetente, String cep, List<PrdCarrinhosModel> listaProdutos)
    {
        List<HashMap<String, String>> listHash = new ArrayList<>();

        if (listaProdutos.size() == 0)
        {
            List<HashMap<String, String>> listHashTemp = new ArrayList<>();

            HashMap<String, String> hashTemp = new HashMap<>();

            hashTemp.put("Error 505", "Produto não encontrado");

            listHashTemp.add(hashTemp);

            return listHashTemp;
        }

        for (PrdCarrinhosModel produtoCarrinho : listaProdutos)
        {
            List<HashMap<String, String>> listHashTemp = new ArrayList<>();

            listHashTemp = getValorPrazo(cepRemetente, cep, produtoCarrinho.getProduto());

            for (int i = 0; i < listHashTemp.size(); i++)
            {
                HashMap<String, String> hashTemp = new HashMap<>();

                hashTemp.put("Nome", listHashTemp.get(i).get("Nome"));

                BigDecimal valorBD = new BigDecimal(listHashTemp.get(i).get("Valor").replace(",", ".")).multiply(BigDecimal.valueOf(produtoCarrinho.getPcrQuantidade())).setScale(2, RoundingMode.HALF_EVEN);
                String valorS = valorBD.toString().replace(".", ",");

                hashTemp.put("Valor", valorS);
                hashTemp.put("PrazoEntrega", listHashTemp.get(i).get("PrazoEntrega"));

                if (listHash.size() < listHashTemp.size())
                {
                    listHash.add(hashTemp);
                }else
                {
                    HashMap<String, String> hashMergeTemp = new HashMap<>();

                    valorBD = new BigDecimal(listHash.get(i).get("Valor").replace(",", ".")).add(new BigDecimal(listHashTemp.get(i).get("Valor").replace(",", ".")).multiply(BigDecimal.valueOf(produtoCarrinho.getPcrQuantidade()))).setScale(2, RoundingMode.HALF_EVEN);
                    valorS = valorBD.toString().replace(".", ",");

                    Integer prazoMaior = 0;

                    if (Integer.parseInt(listHashTemp.get(i).get("PrazoEntrega")) >= Integer.parseInt(listHash.get(i).get("PrazoEntrega")))
                    {
                        prazoMaior = Integer.parseInt(listHashTemp.get(i).get("PrazoEntrega"));
                    }
                    else
                    {
                        prazoMaior = Integer.parseInt(listHash.get(i).get("PrazoEntrega"));
                    }

                    hashMergeTemp.put("Nome", listHash.get(i).get("Nome"));
                    hashMergeTemp.put("Valor", valorS);
                    hashMergeTemp.put("PrazoEntrega", prazoMaior.toString());

                    listHash.set(i, hashMergeTemp);
                }
            }
        }

        return listHash;
    }
}

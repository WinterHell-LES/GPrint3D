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
import com.project.GPrint3D.model.VariaveisModel;

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

    public List<HashMap<String, String>> getValorPrazo(String cepRemetente, String cepDestinatario, ProdutosModel produto, VariaveisModel variaveis)
    {
        // Código  Serviço
        // 04014   SEDEX à vista
        // 04510   PAC à vista
        // 04782   SEDEX 12 (à vista)
        // 04790   SEDEX 10 (à vista)
        // 04804   SEDEX Hoje à vista

        try
        {
            produto = enquadrarCorreios(produto, variaveis);
        }
        catch (Exception e)
        {
            return null;
        }

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
                                "&nVlPeso=" + (int) produto.getPrdDimEmbPe() +
                                "&nCdFormato=1" +
                                "&nVlComprimento=" + (int) Math.ceil((produto.getPrdDimEmbPr() / 10)) +
                                "&nVlAltura=" + (int) Math.ceil((produto.getPrdDimEmbAl() / 10)) +
                                "&nVlLargura=" + (int) Math.ceil((produto.getPrdDimEmbLa() / 10)) +
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

        //listHash.sort(Comparator.comparing(o -> String.valueOf(o.get("Valor"))));
        listHash.sort(Comparator.comparing(o -> String.valueOf(o.get("Nome"))));

        return listHash;
    }
    
    public List<HashMap<String, String>> getValorPrazoLista(String cepRemetente, String cep, List<PrdCarrinhosModel> listaProdutos, VariaveisModel variaveis) throws Exception
    {
        if (listaProdutos.size() == 0)
        {
            throw new Exception("Nenhum produto encontrado");
        }

        List<HashMap<String, String>> listHash = new ArrayList<>();

        for (PrdCarrinhosModel produtoCarrinho : listaProdutos)
        {
            List<HashMap<String, String>> listHashTemp = getValorPrazo(cepRemetente, cep, produtoCarrinho.getProduto(), variaveis);
            int cont = 0;

            if (listHashTemp == null)
            {
                continue;
            }

            for (HashMap<String, String> aux : listHashTemp)
            {
                HashMap<String, String> hashTemp = new HashMap<>();

                hashTemp.put("Nome", aux.get("Nome"));

                BigDecimal valorBD = new BigDecimal(aux.get("Valor").replace(".", "").replace(",", ".")).multiply(BigDecimal.valueOf(produtoCarrinho.getPcrQuantidade())).setScale(2, RoundingMode.HALF_EVEN);

                hashTemp.put("Valor", valorBD.toString().replace(".", ","));
                hashTemp.put("PrazoEntrega", aux.get("PrazoEntrega"));

                if (listHash.size() < 5)
                {
                    listHash.add(hashTemp);
                }
                else
                {
                    HashMap<String, String> hashMergeTemp = listHash.get(cont);

                    valorBD = new BigDecimal(hashMergeTemp.get("Valor").replace(",", ".")).add(valorBD).setScale(2, RoundingMode.HALF_EVEN);

                    hashTemp.put("Valor", valorBD.toString().replace(".", ","));

                    if (Integer.parseInt(aux.get("PrazoEntrega")) > Integer.parseInt(hashMergeTemp.get("PrazoEntrega")))
                    {
                        hashTemp.put("PrazoEntrega", aux.get("PrazoEntrega"));
                    }

                    listHash.set(cont, hashTemp);

                    cont++;                  
                }
            }
        }

        return listHash;
    }

    public ProdutosModel enquadrarCorreios(ProdutosModel produto, VariaveisModel variaveis) throws Exception
    {
        Double pesoMin = variaveis.getVarCorPeMin();
        Double pesoMax = variaveis.getVarCorPeMax();
        Double profundidadeMin = variaveis.getVarCorPrMin();
        Double profundidadeMax = variaveis.getVarCorPrMax();
        Double larguraMin = variaveis.getVarCorLaMin();
        Double larguraMax = variaveis.getVarCorLaMax();
        Double alturaMin = variaveis.getVarCorAlMin();
        Double alturaMax = variaveis.getVarCorAlMax();
        Double somaMin = variaveis.getVarCorSomDimMin(); // Garantindo que cada dimensão seja seu mínimo, não há necessidade de verificar a somaMin
        Double somaMax = variaveis.getVarCorSomDimMax();

        Double pesoPrd = Math.ceil(produto.getPrdDimEmbPe());
        Double profundidadePrd = Math.ceil(produto.getPrdDimEmbPr());
        Double larguraPrd =  Math.ceil(produto.getPrdDimEmbLa());
        Double alturaPrd =  Math.ceil(produto.getPrdDimEmbAl());

        if (pesoPrd < pesoMin)
        {
            produto.setPrdDimEmbPe(pesoMin);
        }

        if (profundidadePrd < profundidadeMin)
        {
            produto.setPrdDimEmbPr(profundidadeMin);
        }

        if (larguraPrd < larguraMin)
        {
            produto.setPrdDimEmbLa(larguraMin);
        }

        if (alturaPrd < alturaMin)
        {
            produto.setPrdDimEmbAl(alturaMin);
        }

        if (pesoPrd > pesoMax || profundidadePrd > profundidadeMax || larguraPrd > larguraMax || alturaPrd > alturaMax || (profundidadePrd + larguraPrd + alturaPrd) > somaMax)
        {
            throw new Exception("Peso maior que o permitido");
        }

        return produto;
    }
}

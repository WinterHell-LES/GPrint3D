package com.project.GPrint3D.controller.admin;

import java.io.IOException;
import java.sql.Date;

import javax.validation.Valid;

import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.model.CategoriasProdutosModel;
import com.project.GPrint3D.model.FotosModel;
import com.project.GPrint3D.model.ProdutosJustificativasModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.ProdutosJustificativasRepository;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.service.CategoriasProdutosService;
import com.project.GPrint3D.service.FotosService;
import com.project.GPrint3D.service.ProdutosJustificativasService;
import com.project.GPrint3D.service.ProdutosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminProdController 
{
    @Autowired
    private ProdutosRepository produtos;

    @Autowired
    private CategoriasRepository categorias;
    
    @Autowired
    private ProdutosJustificativasRepository produtosJustificativas;

    @Autowired
    private ProdutosService produtosService;

    @Autowired
    private CategoriasProdutosService categoriasProdutosService;

    @Autowired
    private ProdutosJustificativasService produtosJustificativasService;

    @Autowired
    private FotosService fotosService;

    @RequestMapping("listarProdutos")
    public ModelAndView listagemProdutos(ProdutosModel produto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/listarProdutos");

        mv.addObject("produtos", produtos.findAll());

        return mv;
    }

    @RequestMapping("cadastrarProdutos")
    public ModelAndView cadastrarProdutos(ProdutosModel produto, FotosModel foto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarProdutos");

        mv.addObject("categorias", categorias.findAll());

        return mv;
    }
    @PostMapping("cadastrarProdutos")
    public ModelAndView cadastroProdutos(@Valid ProdutosModel produto, @RequestParam("categoriaProduto") Integer ctgId, @RequestParam("foto") MultipartFile multipartFile, BindingResult result, RedirectAttributes attributes) throws IOException
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/admin/cadastrarProdutos");
        }

        produtosService.cadastrar(produto);

        ProdutosModel prod = produtos.findOneByNome(produto.getPrdNome());
        CategoriasModel ctg = categorias.findOneById(ctgId);
        
        CategoriasProdutosModel ctgPrd = new CategoriasProdutosModel();

        ctgPrd.setProduto(prod);
        ctgPrd.setCategoria(ctg);

        categoriasProdutosService.cadastrar(ctgPrd);

        String fotoNome = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        FotosModel foto = new FotosModel();
        foto.setFtoNome(fotoNome);
        foto.setFtoContent(multipartFile.getBytes());
        foto.setFtoData(new Date(new java.util.Date().getTime()));
        foto.setProduto(prod);

        fotosService.cadastrar(foto);

        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarProdutos");

        return mv;
    }

    @PostMapping("/alterarProdutos")
    public ModelAndView alterarProdutos(@RequestParam(name = "id") Integer id, ProdutosModel produto, CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/alterarProdutos");

        mv.addObject("produto", produtos.findOneById(id));
        mv.addObject("categorias", categorias.findAll());

        return mv;
    }
    @PostMapping("/alterarProduto")
    public ModelAndView alterarProduto(@Valid ProdutosModel produto, RedirectAttributes attributes)
    {
        String[] mensagem = produtosService.atualizar(produto);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarProdutos");
    }

    @PostMapping("/ativarProdutos")
    public ModelAndView ativarProdutos(@RequestParam(name = "id") Integer id, ProdutosJustificativasModel produtosJustificativa)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/justificarProdutos");

        ProdutosModel prd = produtos.findOneById(id);
        String titulo = "";
        String botao = "";

        if (prd.getPrdAtivo())
        {
            titulo = "inativação";
            botao = "Inativar";
        }
        else
        {
            titulo = "ativação";
            botao = "Ativar";
        }

        mv.addObject("produto", prd);
        mv.addObject("titulo", titulo);
        mv.addObject("botao", botao);

        return mv;
    }
    @PostMapping("/ativaProdutos")
    public ModelAndView ativaProdutos(@RequestParam(name = "id") Integer id, @Valid ProdutosJustificativasModel produtosJustificativa, RedirectAttributes attributes)
    {
        ProdutosModel prd = produtos.findOneById(id);
        
        produtosJustificativa.setPjuProduto(prd.getPrdNome());

        for (int i = 0 ; i < prd.getListCategoriasProdutos().size() ; i++)
        {
            produtosJustificativa.setPjuCategorias(prd.getListCategoriasProdutos().get(i).getCategoria().getCtgNome());

            if (i < prd.getListCategoriasProdutos().size() - 1)
            {
                produtosJustificativa.setPjuCategorias(", ");
            }
        }

        if (prd.getPrdAtivo())
        {
            produtosJustificativa.setPjuAcao("DESATIVAR");
        }
        else
        {
            produtosJustificativa.setPjuAcao("ATIVAR");
        }

        String[] mensagem1 = produtosJustificativasService.cadastrar(produtosJustificativa);
        String[] mensagem2 = produtosService.ativar(!prd.getPrdAtivo(), id);
  
        attributes.addFlashAttribute(mensagem2[0], mensagem2[1]);

        return new ModelAndView("redirect:/admin/listarProdutos");
    }

    @RequestMapping("justificativasProdutos")
    public ModelAndView justificativasProdutos(ProdutosJustificativasModel produtosJustificativa)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/justificativasProdutos");

        mv.addObject("justificativas", produtosJustificativas.findAll());

        return mv;
    }

    @GetMapping("cadastroFotos/{id}")
    public ModelAndView cadastroFotos(@PathVariable(value = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarFotos");

        ProdutosModel prd = produtos.findOneById(id);

        mv.addObject("produto", prd);
        mv.addObject("fotos", prd.getListFotos());

        return mv;
    }
    @PostMapping("addFotos")
    public ModelAndView addFotos(@RequestParam("foto") MultipartFile multipartFile, @Valid ProdutosModel produto, BindingResult result, RedirectAttributes attributes) throws IOException
    {
        String fotoNome = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        FotosModel foto = new FotosModel();
        foto.setFtoNome(fotoNome);
        foto.setFtoContent(multipartFile.getBytes());
        foto.setFtoData(new Date(new java.util.Date().getTime()));
        foto.setProduto(produto);

        String[] mensagem = fotosService.cadastrar(foto);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroFotos/" + produto.getPrdId());
    }
    @PostMapping("deleteFotos")
    public ModelAndView deleteFotos(@RequestParam(name = "id") Integer id, @Valid ProdutosModel produto, RedirectAttributes attributes)
    {
        String[] mensagem = fotosService.excluir(id);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroFotos/" + produto.getPrdId());
    }
}
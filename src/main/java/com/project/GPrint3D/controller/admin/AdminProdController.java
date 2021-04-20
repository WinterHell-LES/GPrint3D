package com.project.GPrint3D.controller.admin;

import java.io.IOException;
import java.sql.Date;

import javax.validation.Valid;

import com.project.GPrint3D.configuration.SecurityConfig;
import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.model.CategoriasProdutosModel;
import com.project.GPrint3D.model.EntradasModel;
import com.project.GPrint3D.model.FotosModel;
import com.project.GPrint3D.model.ProdutosJustificativasModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.EntradasRepository;
import com.project.GPrint3D.repository.PrecificacoesRepository;
import com.project.GPrint3D.repository.ProdutosJustificativasRepository;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
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
    private ProdutosRepository produtosRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private PrecificacoesRepository precificacoesRepository;

    @Autowired
    private EntradasRepository entradasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;
    
    @Autowired
    private ProdutosJustificativasRepository produtosJustificativasRepository;

    @Autowired
    private ProdutosService produtosService;

    @Autowired
    private CategoriasProdutosService categoriasProdutosService;

    @Autowired
    private ProdutosJustificativasService produtosJustificativasService;

    @Autowired
    private FotosService fotosService;

    @Autowired
    private SecurityConfig securityConfig;

    @RequestMapping("listarProdutos")
    public ModelAndView listagemProdutos(ProdutosModel produto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/listarProdutos");

        mv.addObject("produtos", produtosRepository.findAll());

        return mv;
    }

    @RequestMapping("cadastrarProdutos")
    public ModelAndView cadastrarProdutos(ProdutosModel produto, FotosModel foto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarProdutos");

        mv.addObject("precificacoes", precificacoesRepository.findAll());
        mv.addObject("categorias", categoriasRepository.findAll());

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

        ProdutosModel prod = produtosRepository.findOneByNome(produto.getPrdNome());
        CategoriasModel ctg = categoriasRepository.findOneById(ctgId);
        
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

        mv.addObject("produto", produtosRepository.findOneById(id));
        mv.addObject("categorias", categoriasRepository.findAll());
        mv.addObject("precificacoes", precificacoesRepository.findAll());

        return mv;
    }
    @PostMapping("/alterarProduto")
    public ModelAndView alterarProduto(@Valid ProdutosModel produto, RedirectAttributes attributes)
    {
        EntradasModel ent = entradasRepository.findByProduto(produto.getPrdId());

        if (produto.getPrdPreco() < ((ent.getEntPrecoCusto() / ent.getEntQuantidade()) * (1 - (produto.getPrecificacao().getPrcMargLuc() / 100))))
        {
            return alterarProdutoML(produto);
        }

        String[] mensagem = produtosService.atualizar(produto);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarProdutos");
    }
    public ModelAndView alterarProdutoML(ProdutosModel produto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/confirmAlterarProdutos");

        mv.addObject("produto", produto);

        return mv;
    }
    @PostMapping("/alterarProdutoConfirm")
    public ModelAndView alterarProdutoConfirm(@RequestParam(name = "password") String senha, @Valid ProdutosModel produto, RedirectAttributes attributes)
    {
        UsuariosModel usu = usuariosRepository.findByEmail("admin@gprint3d.com");

        if (!securityConfig.passwordEncoder().matches(senha, usu.getUsuSenha()))
        {
            System.out.println(senha);
            System.out.println(usu.getUsuSenha());
            System.out.println(securityConfig.passwordEncoder().encode(senha));
            return alterarProdutoML(produto);
        }

        String[] mensagem = produtosService.atualizar(produto);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarProdutos");
    }

    @PostMapping("/ativarProdutos")
    public ModelAndView ativarProdutos(@RequestParam(name = "id") Integer id, ProdutosJustificativasModel produtosJustificativa)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/justificarProdutos");

        ProdutosModel prd = produtosRepository.findOneById(id);
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
        ProdutosModel prd = produtosRepository.findOneById(id);
        
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

        mv.addObject("justificativas", produtosJustificativasRepository.findAll());

        return mv;
    }

    @GetMapping("cadastroFotos/{id}")
    public ModelAndView cadastroFotos(@PathVariable(value = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarFotos");

        ProdutosModel prd = produtosRepository.findOneById(id);

        mv.addObject("produto", prd);
        mv.addObject("fotos", prd.getListFotos());

        return mv;
    }
    @PostMapping("addFotos")
    public ModelAndView addFotos(@RequestParam("foto") MultipartFile[] multipartFile, @Valid ProdutosModel produto, BindingResult result, RedirectAttributes attributes) throws IOException
    {
        String[] mensagem = new String[2];

        for (MultipartFile aux : multipartFile)
        {
            String fotoNome = StringUtils.cleanPath(aux.getOriginalFilename());

            FotosModel foto = new FotosModel();
            foto.setFtoNome(fotoNome);
            foto.setFtoContent(aux.getBytes());
            foto.setFtoData(new Date(new java.util.Date().getTime()));
            foto.setProduto(produto);

            mensagem = fotosService.cadastrar(foto);
        }

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

    @GetMapping("cadastroCategorias/{id}")
    public ModelAndView cadastroCategorias(@PathVariable(value = "id") Integer id, CategoriasProdutosModel categoriaProduto, CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarCategorias");

        ProdutosModel prd = produtosRepository.findOneById(id);

        mv.addObject("produto", prd);
        mv.addObject("categoriasProdutos", prd.getListCategoriasProdutos());
        mv.addObject("categorias", categoriasRepository.findAll());

        return mv;
    }
    @PostMapping("addCategoria")
    public ModelAndView addCategoria(@Valid CategoriasModel categoria, @Valid ProdutosModel produto, BindingResult result, RedirectAttributes attributes) throws IOException
    {
        CategoriasProdutosModel cpr = new CategoriasProdutosModel();
        cpr.setCategoria(categoria);
        cpr.setProduto(produto);

        String[] mensagem = categoriasProdutosService.cadastrar(cpr);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroCategorias/" + produto.getPrdId());
    }
    @PostMapping("deleteCategoria")
    public ModelAndView deleteCategoria(@RequestParam(name = "id") Integer id, @Valid ProdutosModel produto, RedirectAttributes attributes)
    {
        String[] mensagem = categoriasProdutosService.excluir(id);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroCategorias/" + produto.getPrdId());
    }
}
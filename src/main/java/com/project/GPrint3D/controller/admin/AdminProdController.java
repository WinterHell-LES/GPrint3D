package com.project.GPrint3D.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

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
import com.project.GPrint3D.service.AdminFacadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private AdminFacadeService adminFacadeService;

    @RequestMapping("listarProdutos")
    public ModelAndView listagemProdutos (ProdutosModel produto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/listarProdutos");

        mv.addObject("produtos", produtosRepository.findAll());

        return mv;
    }

    @RequestMapping("cadastrarProdutos")
    public ModelAndView cadastrarProdutos (ProdutosModel produto, FotosModel foto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarProdutos");

        mv.addObject("precificacoes", precificacoesRepository.findAll());
        mv.addObject("categorias", categoriasRepository.findAll());

        return mv;
    }

    @PostMapping("cadastrarProdutos")
    public ModelAndView cadastroProdutos (@Valid ProdutosModel produto, @RequestParam("categoriaProduto") Integer ctgId,
            @RequestParam("foto") MultipartFile multipartFile, BindingResult result, RedirectAttributes attributes) throws IOException
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/admin/cadastrarProdutos");
        }

        String[] mensagem = adminFacadeService.cadastrarProduto(produto, ctgId, multipartFile);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastrarProdutos");
    }

    @PostMapping("/alterarProdutos")
    public ModelAndView alterarProdutos (@RequestParam(name = "id") Integer id, ProdutosModel produto,
            CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/alterarProdutos");

        mv.addObject("produto", produtosRepository.findOneById(id));
        mv.addObject("categorias", categoriasRepository.findAll());
        mv.addObject("precificacoes", precificacoesRepository.findAll());

        return mv;
    }

    @PostMapping("/alterarProduto")
    public ModelAndView alterarProduto (@Valid ProdutosModel produto, RedirectAttributes attributes)
    {
        EntradasModel entrada = entradasRepository.findByProduto(produto.getPrdId());

        if (adminFacadeService.verificaPrecificacao(produto, entrada))
        {
            return alterarProdutoML(produto);
        }

        String[] mensagem = adminFacadeService.atualizarProduto(produto);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarProdutos");
    }

    public ModelAndView alterarProdutoML (ProdutosModel produto)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/confirmAlterarProdutos");

        mv.addObject("produto", produto);

        return mv;
    }

    @PostMapping("/alterarProdutoConfirm")
    public ModelAndView alterarProdutoConfirm (@RequestParam(name = "password") String senha,
            @Valid ProdutosModel produto, RedirectAttributes attributes)
    {
        UsuariosModel usuario = usuariosRepository.findByEmail("admin@gprint3d.com");

        if (!adminFacadeService.verificaUsuario(senha, usuario))
        {
            attributes.addFlashAttribute("alteracaoError", "Senha inválida");

            return alterarProdutoML(produto);
        }

        String[] mensagem = adminFacadeService.atualizarProduto(produto);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarProdutos");
    }

    @PostMapping("/ativarProdutos")
    public ModelAndView ativarProdutos (@RequestParam(name = "id") Integer id,
            ProdutosJustificativasModel produtosJustificativa)
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
    public ModelAndView ativaProdutos (@RequestParam(name = "id") Integer id,
            @Valid ProdutosJustificativasModel produtosJustificativa, RedirectAttributes attributes)
    {
        String[] mensagem1 = adminFacadeService.ativarProduto(id, produtosJustificativa);

        attributes.addFlashAttribute(mensagem1[0], mensagem1[1]);

        return new ModelAndView("redirect:/admin/listarProdutos");
    }

    @RequestMapping("justificativasProdutos")
    public ModelAndView justificativasProdutos (ProdutosJustificativasModel produtosJustificativa)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/justificativasProdutos");

        mv.addObject("justificativas", produtosJustificativasRepository.findAll());

        return mv;
    }

    @GetMapping("cadastroFotos/{id}")
    public ModelAndView cadastroFotos (@PathVariable(value = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarFotos");

        mv.addObject("produto", produtosRepository.findOneById(id));

        return mv;
    }

    @PostMapping("addFotos")
    public ModelAndView addFotos (@RequestParam("foto") MultipartFile[] multipartFile, @Valid ProdutosModel produto,
            BindingResult result, RedirectAttributes attributes) throws IOException
    {
        String[] mensagem = adminFacadeService.cadastrarProdutoFoto(multipartFile, produto);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroFotos/" + produto.getPrdId());
    }

    @PostMapping("deleteFotos")
    public ModelAndView deleteFotos (@RequestParam(name = "id") Integer id, @Valid ProdutosModel produto,
            RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.excluirProdutoFoto(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroFotos/" + produto.getPrdId());
    }

    @GetMapping("cadastroCategorias/{id}")
    public ModelAndView cadastroCategorias (@PathVariable(value = "id") Integer id,
            CategoriasProdutosModel categoriaProduto, CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarCategorias");

        ProdutosModel prd = produtosRepository.findOneById(id);

        mv.addObject("produto", prd);
        mv.addObject("categoriasProdutos", prd.getListCategoriasProdutos());
        mv.addObject("categorias", categoriasRepository.findAll());

        return mv;
    }

    @PostMapping("addCategoria")
    public ModelAndView addCategoria (@Valid CategoriasModel categoria, @Valid ProdutosModel produto,
            BindingResult result, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.cadatrarProdutoCategoria(categoria, produto);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroCategorias/" + produto.getPrdId());
    }

    @PostMapping("deleteCategoria")
    public ModelAndView deleteCategoria (@RequestParam(name = "id") Integer id, @Valid ProdutosModel produto,
            RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.excluirProdutoCategoria(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/cadastroCategorias/" + produto.getPrdId());
    }
}
package com.project.GPrint3D.controller.admin;

import java.io.IOException;
import java.sql.Date;

import javax.validation.Valid;

import com.project.GPrint3D.model.FotosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.service.FotosService;
import com.project.GPrint3D.service.ProdutosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
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
    private ProdutosService produtosService;

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
    public ModelAndView cadastroProdutos(@Valid ProdutosModel produto, @RequestParam("foto") MultipartFile multipartFile, BindingResult result, RedirectAttributes attributes) throws IOException
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/admin/cadastrarProdutos");
        }

        produtosService.cadastrar(produto);

        ProdutosModel prod = produtos.findOneByNome(produto.getPrdNome());

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

    @RequestMapping("alterarProdutos")
    public ModelAndView alterarProdutos()
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/alterarProdutos");

        return mv;
    }

    @RequestMapping("justificarProdutos")
    public ModelAndView justificarProdutos()
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/justificarProdutos");

        return mv;
    }
}

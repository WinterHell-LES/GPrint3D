import cadastrarCliente
import index
import comprar
import admin
import usuario

from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0

#Chromium path
CHROMIUM_PATH = 'automationTest/chromium/chromedriver.exe'
GECKODRIVER_PATH = 'automationTest/geckodriver/geckodriver.exe'

#Carrega o webdriver
#driver = webdriver.Chrome(executable_path=CHROMIUM_PATH)
driver = webdriver.Firefox(executable_path=GECKODRIVER_PATH)
driver.get('http://127.0.0.1:8080')

#Cadastrar usuário
index.clicarLogin(driver)
index.clicarCadastrarNovoUsuario(driver)

cadastrarCliente.cadastrarInformacoes(driver)
cadastrarCliente.cadastrarEndereco(driver)
cadastrarCliente.cadastrarPagamento(driver)

#Ativar login com ADM
index.clicarLogin(driver)
index.logarUsuario(driver, "admin@gprint3d.com", "admin")

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.listarCliente(driver)
admin.ativarCliente(driver, '26')
admin.deslogarAdm(driver)

#Logar com o usuário e realizar compra
index.clicarLogin(driver)
index.logarUsuario(driver, "cliente10@gprint3d.com", "Cliente10@")

#Menu do usuário
index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

#Cadastrar novo endereço
usuario.meusEnderecos(driver)
usuario.cadastrarNovoEndereco(driver)

#Cadastrar novo cartão
usuario.meusCartoes(driver)
usuario.cadastrarNovoCartao(driver)

#Volta para a home da loja
usuario.home(driver)

#Incluir produto no carrinho
comprar.abrirCategorias(driver)
comprar.selecionaCategoria(driver)
comprar.entrarProduto(driver)
comprar.adicionarProdutoCarrinho(driver)

#Comprar carrinho
index.entrarCarrinho(driver)

comprar.comprarCarrinho(driver)
comprar.confirmarEndereco(driver)
comprar.confirmarPagamento(driver)
comprar.confirmarPedido(driver)

#Trocar pedido
usuario.visualizarPedido(driver)
usuario.trocarProdutoPedido(driver)

#Visualizar as outras telas do usuario
usuario.meuCadastro(driver)
usuario.alterarSenha(driver)
usuario.meusCupons(driver)
usuario.meuRanking(driver)

#Desloga o usuario
usuario.deslogarUsuario(driver)

#Loga e acessa menu do Admin
index.clicarLogin(driver)
index.logarUsuario(driver, "admin@gprint3d.com", "admin")
index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

#Navega nas outras telas
admin.cadastrarBandeiras(driver)
admin.listarBandeiras(driver)
admin.cadastrarCategoria(driver)
admin.listarCategorias(driver)
admin.cadastrarProduto(driver)
admin.listarProdutos(driver)
admin.justificativaProdutos(driver)
admin.cadastroEntrada(driver)
admin.listarEntradas(driver)
admin.cadastrarCuponPromocional(driver)
admin.listarCupomPromocional(driver)
admin.listarCupomTroca(driver)
admin.listarPedidos(driver)
admin.listarPedidosTroca(driver)
admin.logisticaPedidos(driver)
admin.logisticaTrocas(driver)
admin.visualizarGrafico(driver)
admin.logTransacao(driver)
admin.deslogarAdm(driver)

driver.quit()
import index
import admin

from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support.ui import Select
# available since 2.26.0
from selenium.webdriver.support import expected_conditions as EC

#Paths
CHROMIUM_PATH = 'automationTest/chromium/chromedriver.exe'
GECKODRIVER_PATH = 'automationTest/geckodriver/geckodriver.exe'

#Carrega o webdriver
#driver = webdriver.Chrome(executable_path=CHROMIUM_PATH)
driver = webdriver.Firefox(executable_path=GECKODRIVER_PATH)
driver.get('http://localhost:8080')

# ------- Dados de acesso -------
cliLogin = "cliente1@gprint3d.com"
cliPass = "cliente"
adminLogin = "admin@gprint3d.com"
adminPass = "admin"

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

# ------- Menu ADMIN -------
## ------- Bandeiras -------
admin.menuBandeira(driver)
admin.cadastrarBandeiras(driver)
admin.listarBandeiras(driver)

## ------- Precificação -------
admin.menuPrecificacao(driver)
admin.cadastroPrecificacao(driver)
admin.listarPrecificacoes(driver)

## ------- Categorias -------
admin.menuCategoria(driver)
admin.cadastrarCategoria(driver)
admin.listarCategorias(driver)

## ------- Produtos -------
admin.menuProduto(driver)
admin.cadastrarProduto(driver)
admin.listarProdutos(driver)
admin.justificativaProdutos(driver)

## ------- Estoque -------
admin.menuEstoque(driver)

### ------- Entradas -------
admin.menuEntradas(driver)
admin.cadastroEntrada(driver)
admin.listarEntradas(driver)

### ------- Saídas -------
admin.menuSaidas(driver)
admin.listarSaidas(driver)

## ------- Cupons -------
admin.menuCupons(driver)

### ------- Cupons promocionais -------
admin.menuCuponPromocional(driver)
admin.cadastrarCuponPromocional(driver)
admin.listarCupomPromocional(driver)

### ------- Cupons de troca -------
admin.menuCupomTroca(driver)
admin.listarCupomTroca(driver)

## ------- Pedido -------
admin.menuPedido(driver)
admin.listarPedidoCompra(driver)
admin.listarPedidoTroca(driver)

## ------- Logística -------
admin.menuLogistica(driver)
admin.logisticaPedidos(driver)
admin.logisticaTrocas(driver)

## ------- Precificação -------
admin.menuClientes(driver)
admin.listarCliente(driver)

## ------- Gráficos -------
admin.menuGrafico(driver)
admin.visualizarGraficoPedidos(driver)
admin.visualizarGraficoProdutos(driver)
admin.visualizarGraficoCategorias(driver)

## ------- Configurações -------
admin.menuConfiguracoes(driver)
admin.acessarVariaveis(driver)

## ------- Transações -------
admin.logTransacao(driver)

## ------- Deslogar ADMIN -------
admin.deslogarAdm(driver)
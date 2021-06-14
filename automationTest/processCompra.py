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

# ------- Seleção do produto 1 -------
#Selecionar um produto no index
comprar.abrirCategorias(driver)
comprar.selecionaCategoria(driver)
comprar.entrarProduto1(driver)

# #Inicia compra do produto
comprar.adicionarProdutoCarrinho(driver)
index.entrarCarrinho(driver)

# # ------- Seleção do produto 2 -------
comprar.abrirCategorias(driver)
comprar.selecionaCategoria(driver)
comprar.entrarProduto2(driver)

# # #Inicia compra do produto
comprar.adicionarProdutoCarrinho(driver)
index.entrarCarrinho(driver)

# # # ------- Seleção do produto 3 -------
comprar.abrirCategorias(driver)
comprar.selecionaCategoria(driver)
comprar.entrarProduto3(driver)

# # #Inicia compra do produto
comprar.adicionarProdutoCarrinho(driver)
index.entrarCarrinho(driver)

# # ------- Altera a quantidade do produto -------
comprar.aumentar_produto(driver)

# # ------- Comprar carrinho -------
#index.entrarCarrinho(driver) # desativar essa função
comprar.comprarCarrinho(driver)
comprar.login_carrinho(driver, cliLogin, cliPass)

# # ------- Selecionar endereço -------
comprar.listar_enderecos(driver)
comprar.selecionar_frete(driver)
comprar.confirmar(driver)

# # ------- Selecionar pagamento -------
comprar.listar_produtos(driver)
comprar.listar_cartoes(driver)
comprar.add_cartao(driver)

# # ------- Cadastrar novo cartão -------
cadastrarCliente.cadastrarPagamento(driver)

# # ------- Acessa o carrinho -------
index.entrarCarrinho(driver)

# # ------- Comprar carrinho -------
comprar.comprarCarrinho(driver)

# # ------- Selecionar endereço -------
comprar.listar_enderecos(driver)
comprar.selecionar_frete(driver)
comprar.confirmar(driver)

# # ------- Selecionar pagamento -------
comprar.listar_produtos(driver)

comprar.listar_cupons(driver)
comprar.add_cupom_troca(driver)

comprar.listar_cupons(driver)
comprar.add_cupom_troca(driver)

comprar.listar_cupons(driver)
comprar.add_cupom_troca(driver)

comprar.listar_cupons(driver)
comprar.add_cupom_promocao(driver, "GP3DCP210425173316")

comprar.listar_cupons(driver)
comprar.listar_cupons(driver)

comprar.listar_cartoes(driver)
comprar.add_cartao_pagamento(driver, 2)

comprar.listar_cartoes(driver)
comprar.add_cartao_pagamento(driver, 3)

comprar.add_valor(driver, 1, 150000)
comprar.add_valor(driver, 2, 999)
comprar.add_valor(driver, 2, 250000)
comprar.add_valor(driver, 3, 2000000)

comprar.confirmar(driver)

# # ------- Confirmar pagamento -------
comprar.add_cvv(driver, 1, 123)
comprar.add_cvv(driver, 2, 123)
comprar.add_cvv(driver, 3, 123)
comprar.confirmar(driver)

index.deslogarUsuario(driver)

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.menuPedido(driver)
admin.listarPedidoCompra(driver)
admin.acessar_pedido_compra(driver)
admin.aprovar_pagamento(driver)

index.deslogarUsuario(driver)

# ------- Acessar CLIENTE -------
index.clicarLogin(driver)
index.logarUsuario(driver, cliLogin, cliPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

usuario.meusPedidos(driver)
usuario.detalhe_pedidos(driver)

index.deslogarUsuario(driver)

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.menuPedido(driver)
admin.listarPedidoCompra(driver)
admin.acessar_pedido_compra(driver)
admin.gerar_nota(driver)

index.deslogarUsuario(driver)

# ------- Acessar CLIENTE -------
index.clicarLogin(driver)
index.logarUsuario(driver, cliLogin, cliPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

usuario.meusPedidos(driver)
usuario.detalhe_pedidos(driver)

index.deslogarUsuario(driver)

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.menuLogistica(driver)
admin.logisticaPedidos(driver)
admin.acessar_logistica_compra(driver)
admin.avancar_logistica(driver) # Separar pedido

index.deslogarUsuario(driver)

# ------- Acessar CLIENTE -------
index.clicarLogin(driver)
index.logarUsuario(driver, cliLogin, cliPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

usuario.meusPedidos(driver)
usuario.detalhe_pedidos(driver)

index.deslogarUsuario(driver)

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.menuLogistica(driver)
admin.logisticaPedidos(driver)
admin.acessar_logistica_compra(driver)
admin.avancar_logistica(driver)  # Embalar pedido

index.deslogarUsuario(driver)

# ------- Acessar CLIENTE -------
index.clicarLogin(driver)
index.logarUsuario(driver, cliLogin, cliPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

usuario.meusPedidos(driver)
usuario.detalhe_pedidos(driver)

index.deslogarUsuario(driver)

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.menuLogistica(driver)
admin.logisticaPedidos(driver)
admin.acessar_logistica_compra(driver)
admin.avancar_logistica(driver)  # Enviar para a transportadora

index.deslogarUsuario(driver)

# ------- Acessar CLIENTE -------
index.clicarLogin(driver)
index.logarUsuario(driver, cliLogin, cliPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

usuario.meusPedidos(driver)
usuario.detalhe_pedidos(driver)

index.deslogarUsuario(driver)

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.menuLogistica(driver)
admin.logisticaPedidos(driver)
admin.acessar_logistica_compra(driver)
admin.avancar_logistica(driver)  # Enviar para transporte

index.deslogarUsuario(driver)

# ------- Acessar CLIENTE -------
index.clicarLogin(driver)
index.logarUsuario(driver, cliLogin, cliPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

usuario.meusPedidos(driver)
usuario.detalhe_pedidos(driver)

index.deslogarUsuario(driver)

# ------- Acessar ADMIN -------
index.clicarLogin(driver)
index.logarUsuario(driver, adminLogin, adminPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

admin.menuLogistica(driver)
admin.logisticaPedidos(driver)
admin.acessar_logistica_compra(driver)
admin.avancar_logistica(driver)  # Entregar pedido

index.deslogarUsuario(driver)

# ------- Acessar CLIENTE -------
index.clicarLogin(driver)
index.logarUsuario(driver, cliLogin, cliPass)

index.clicarLogin(driver)
index.entrarMenuUsuario(driver)

usuario.meusPedidos(driver)
usuario.detalhe_pedidos(driver)

index.deslogarUsuario(driver)

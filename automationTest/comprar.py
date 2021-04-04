from time import sleep

from tools.button_click import button_click

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait

def abrirCategorias(driver):
    #Clica no botão de categorias
    button_click(driver, by_id='navbarDropdownMenuLink')
    sleep(1)

def selecionaCategoria(driver):
    #Seleciona a categoria
    button_click(driver, by_css_selector='a[href="/c/Impressoras_3D"]')
    sleep(1)

def entrarProduto(driver):
    #escolhe o produto
    button_click(driver, by_css_selector='a[href="/c/Impressoras_3D/p/Impressora_3D_Creality_Ender-3"]')
    sleep(1)

def adicionarProdutoCarrinho(driver):
    #Adiciona o produto no carrinho
    button_click(driver, by_css_selector='button[name="id"]')
    sleep(1)

def comprarCarrinho(driver):
    #Compra os produtos no carrinho
    button_click(driver, by_id="comprar")
    sleep(1)

def confirmarEndereco(driver):
    #Escolher o endereço
    button_click(driver, by_id="confirmar")
    sleep(1)

def confirmarPagamento(driver):
    #Confirmar pagamento

    #Listar produtos
    button_click(driver, by_css_selector='div[class="row valores"]')
    sleep(0.5)

    #Listar cupons
    button_click(driver, by_css_selector='div[class="row cupons"]')
    sleep(0.5)

    #Listar meus cartões
    button_click(driver, by_css_selector='div[class="row cartoes"]')
    sleep(0.5)
    driver.find_element_by_css_selector('body').send_keys(Keys.PAGE_DOWN)

    #Confirmar pagamento
    button_click(driver, by_id="confirmar")
    sleep(0.5)

def confirmarPedido(driver):
    #Confirmar pedido

    #Listar produtos
    button_click(driver, by_css_selector='div[class="row valores"]')
    sleep(0.5)

    #Listar endereço de entrega
    button_click(driver, by_css_selector='div[class="row enderecos"]')
    sleep(0.5)

    #Listar cupons
    button_click(driver, by_css_selector='div[class="row cupons"]')
    driver.find_element_by_css_selector('body').send_keys(Keys.PAGE_DOWN)
    sleep(0.5)

    #Confirmar pedido
    button_click(driver, by_id="confirmar")
    sleep(1)
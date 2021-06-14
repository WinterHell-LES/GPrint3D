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


def entrarProduto1(driver):
    #escolhe o produto
    button_click(driver, by_css_selector='a[href="/c/Impressoras_3D/p/Impressora_3D_Creality_Ender-3"]')
    sleep(1)

def entrarProduto2(driver):
    #escolhe o produto
    button_click(
        driver, by_css_selector='a[href="/c/Impressoras_3D/p/Impressora_3D_Anycubic_I3_Mega_S"]')
    sleep(1)


def entrarProduto3(driver):
    #escolhe o produto
    button_click(
        driver, by_css_selector='a[href="/c/Impressoras_3D/p/Impressora_3D_Creality_LD-002R"]')
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

def aumentar_produto(driver):
    #Clicar no produto 2
    button_click(
        driver, by_xpath='/html/body/section/div/div[3]/div[2]/div/div[2]/div[3]/div/div/form[3]/button')
    sleep(1)

    #Clicar no produto 3
    button_click(
        driver, by_xpath='/html/body/section/div/div[3]/div[2]/div/div[3]/div[3]/div/div/form[3]/button')
    sleep(1)

    #Clicar no produto 3
    button_click(
        driver, by_xpath='/html/body/section/div/div[3]/div[2]/div/div[3]/div[3]/div/div/form[3]/button')
    sleep(1)

def login_carrinho(driver, email, senha):
    driver.find_element_by_id('inputUsuario').send_keys(email)
    sleep(0.5)

    driver.find_element_by_id('inputSenha').send_keys(senha)
    sleep(0.5)

    button_click(
        driver, by_xpath='/html/body/section/div/form/div/button')
    sleep(1)

def listar_enderecos(driver):
    button_click(driver, by_xpath='/html/body/section/div[4]/div[1]/div[1]')
    sleep(6)

def selecionar_frete(driver):
    button_click(
        driver, by_xpath='/html/body/section/div[3]/form/div[2]/div[2]/div[2]/div/div[1]/input')

def confirmar(driver):
    button_click(driver, by_xpath='//*[@id="confirmar"]')
    sleep(1)

def listar_produtos(driver):
    button_click(driver, by_xpath='/html/body/section/div[3]/div[1]/div[1]')
    sleep(1)
    button_click(driver, by_xpath='/html/body/section/div[3]/div[1]/div[1]')
    sleep(1)

def listar_cupons(driver):
    button_click(driver, by_xpath='/html/body/section/div[4]/div[1]/div[1]')
    sleep(1)

def add_cupom_troca(driver):
    button_click(driver, by_xpath='/html/body/section/div[4]/div[2]/div/div[1]/div/div[2]/div/form/button/span[2]')
    sleep(1)

def add_cupom_promocao(driver, codigo):
    driver.find_element_by_id('cupom').send_keys(codigo)
    sleep(0.2)
    button_click(driver, by_xpath='/html/body/section/div[4]/div[2]/div/div[2]/div/form/div/div[2]/button')
    sleep(1)

def listar_cartoes(driver):
    button_click(driver, by_xpath='/html/body/section/div[5]/div[1]/div[1]')
    sleep(1)

def add_cartao(driver):
    button_click(driver, by_xpath='//*[@id="novoCartao"]')
    sleep(1)
    #driver.switch_to.alert.accept()

def add_cartao_pagamento(driver, posicao):
    button_click(
        driver, by_xpath='/html/body/section/div[5]/div[2]/div/div/div[' + str(int(posicao + 1)) + ']/div/div/div[3]/div/form/button')
    sleep(1)

def add_valor(driver, posicao, valor):
    driver.find_element_by_xpath('/html/body/section/div[6]/div[2]/div/table/tbody/tr[' + str(int(posicao + 1)) + ']/td[4]/form/input[3]').send_keys(valor)
    sleep(0.2)
    driver.find_element_by_xpath('/html/body/section/div[6]/div[2]/div/table/tbody/tr[' + str(int(posicao + 1)) + ']/td[4]/form/input[3]').send_keys(Keys.ENTER)
    sleep(1)

def add_cvv(driver, posicao, cvv):
    driver.find_element_by_id("cvv" + str(posicao)).send_keys(cvv)
    sleep(0.2)
    driver.find_element_by_id("cvv" + str(posicao)).send_keys(Keys.ENTER)
    sleep(1)

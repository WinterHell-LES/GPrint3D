import cadastrarCliente

from time import sleep

from tools.button_click import button_click

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0

def home(driver):
    #Acessa a página inicial da loja virtual
    button_click(driver, by_css_selector='a[href="/index"]')
    sleep(0.5)

def meuCadastro(driver):
    #Acessa meu cadastro
    button_click(driver, by_css_selector='a[href="/cliente/meuCadastro"]')
    sleep(0.5)

def meusEnderecos(driver):
    #Acessa meus endereços
    button_click(driver, by_css_selector='a[href="/cliente/meusEnderecos"]')
    sleep(0.5)

def cadastrarNovoEndereco(driver):
    #Cadastra novo endereço
    button_click(driver, by_xpath='/html/body/main/section/div[1]/div[2]/div/div[1]/div/div/a')
    sleep(0.5)

    #Reaproveita o código do cadastro inicial
    cadastrarCliente.cadastrarEndereco(driver)

def meusCartoes(driver):
    #Acessa meus cartões
    button_click(driver, by_css_selector='a[href="/cliente/meusCartoes"]')
    sleep(0.5)

def cadastrarNovoCartao(driver):
    #Cadastra um novo cartão
    button_click(driver, by_xpath='/html/body/main/section/div/div[2]/div/div[1]/div/div/a')
    sleep(0.5)

    #Reaproveita o código do cadastro inicial
    cadastrarCliente.cadastrarPagamento(driver)

def alterarSenha(driver):
    #Acessa alteração de senha
    button_click(driver, by_css_selector='a[href="/cliente/alterarSenha"]')
    sleep(0.5)

def meusPedidos(driver):
    #Acessa meus pedidos
    button_click(driver, by_xpath='//*[@id="bt_pedidos"]')
    sleep(0.5)

    button_click(
        driver, by_xpath='/html/body/div[3]/div/div/nav/div/ul/li[4]/div/ul[1]/li/a')
    sleep(0.5)

def detalhe_pedidos(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[2]/div/div[2]/div/form/div/button')
    sleep(0.5)

def visualizarPedido(driver):
    #Visualiza o pedido
    button_click(driver, by_css_selector='a[href="/cliente/meusPedidos/detalhesPedido"]')
    sleep(0.5)

def trocarProdutoPedido(driver):
    #Solicita a troca de produto
    button_click(driver, by_css_selector='button[data-bs-target="#trocarPedidoModal"]')
    sleep(0.5)

    driver.find_element_by_xpath('//*[@id="iQuantidade"]').clear()
    driver.find_element_by_xpath('//*[@id="iQuantidade"]').send_keys('2')
    driver.find_element_by_xpath('//*[@id="descricao"]').send_keys('Defeito')
    
    sleep(0.2)

    button_click(driver, by_id="confirmarTroca")
    #button_click(driver, by_xpath='//*[@id="trocarPedidoModal"]/div/div/div[1]/button')
    sleep(0.5)


def visualizarPedidoTroca(driver):
    #Visualiza o pedido
    button_click(
        driver, by_css_selector='a[href="/cliente/meusPedidos/detalhesPedido"]')
    sleep(0.5)

def meusCupons(driver):
    #Acessa meus cupons
    button_click(driver, by_css_selector='a[href="/cliente/meusCupons"]')
    sleep(0.5)

def meuRanking(driver):
    #Acessa meu ranking
    button_click(driver, by_css_selector='a[href="/cliente/meuRanking"]')
    sleep(0.5)

    button_click(driver, by_css_selector='i[class="fs-2 bi bi-arrow-right-circle"]')
    sleep(0.5)

    button_click(driver, by_css_selector='i[class="fs-2 bi bi-arrow-right-circle"]')
    sleep(0.5)

def deslogarUsuario(driver):
    button_click(driver, by_css_selector='a[href="/logout"]')
    sleep(1)

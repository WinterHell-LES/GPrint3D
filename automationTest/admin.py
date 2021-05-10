from time import sleep

from tools.button_click import button_click

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0

def home(driver):
    #Volta para o home da loja
    sleep(0.5)

def cadastrarBandeiras(driver):
    #Cadastra uma nova bandeira de cartão
    button_click(driver, by_css_selector='button[data-bs-target="#bandeiras_cartao"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/cadastrarBandeiras"]')
    sleep(0.5)

def listarBandeiras(driver):
    #Lista todas as bandeiras de cartões cadastradas
    button_click(driver, by_css_selector='a[href="/admin/listarBandeiras"]')
    sleep(0.5)

def cadastrarCategoria(driver):
    #Cadastrar uma nova categoria de produto
    button_click(driver, by_css_selector='button[data-bs-target="#categorias"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/cadastrarCategorias"]')
    sleep(0.5)

def listarCategorias(driver):
    #Lista todas as categorias de produtos cadastradas
    button_click(driver, by_css_selector='a[href="/admin/listarCategorias"]')
    sleep(0.5)

def cadastrarProduto(driver):
    #Cadastra um novo produto
    button_click(driver, by_css_selector='button[data-bs-target="#produtos"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/cadastrarProdutos"]')
    sleep(0.5)

def listarProdutos(driver):
    #Lista todos os produtos cadastrados
    button_click(driver, by_css_selector='a[href="/admin/listarProdutos"]')
    sleep(0.5)

def justificativaProdutos(driver):
    #Visualiza todas as justificativas de produtos cadastradas
    button_click(driver, by_css_selector='a[href="/admin/justificativasProdutos"]')
    sleep(0.5)

def cadastroEntrada(driver):
    #Cadastra uma nova entrda de produto
    button_click(driver, by_css_selector='button[data-bs-target="#entradas"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/cadastrarEntradas"]')
    sleep(0.5)

def listarEntradas(driver):
    #Lista todas as entradas cadastradas
    button_click(driver, by_css_selector='a[href="/admin/listarEntradas"]')
    sleep(0.5)

def cadastrarCuponPromocional(driver):
    #Cadastra um novo cupom promocional
    button_click(driver, by_css_selector='button[data-bs-target="#cupons"]')
    sleep(0.5)

    button_click(driver, by_css_selector='button[data-bs-target="#cupons_promocionais"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/cupons/cadastrarCuponsPromocionais"]')
    sleep(0.5)

def listarCupomPromocional(driver):
    #Lista todos os cupons promocionais cadastrados
    button_click(driver, by_css_selector='a[href="/admin/cupons/listarCuponsPromocionais"]')
    sleep(0.5)

def listarCupomTroca(driver):
    #Lista todos os cupons de troca cadastrados
    button_click(driver, by_css_selector='button[data-bs-target="#cupons_trocas"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/cupons/listarCuponsTrocas"]')
    sleep(0.5)

def listarPedidos(driver):
    #Lista todos os pedidos cadastrados
    button_click(driver, by_css_selector='button[data-bs-target="#pedidos"]')
    sleep(0.5)

    button_click(
        driver, by_xpath='/html/body/div[3]/div/div/div/nav/div/ul/li[9]/div/ul/li[1]/a')
    sleep(0.5)

def acessar_pedido_compra(driver):
    button_click(
        driver, by_xpath='//*[@id="example"]/tbody/tr[3]/td[5]/div/div[2]/form/button')
    sleep(0.5)

def aprovar_pagamento(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[3]/div[3]/form/div/button')
    sleep(0.5)

def gerar_nota(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[3]/div[2]/form/div/button')
    sleep(0.5)

def listarPedidosTroca(driver):
    #lista todos os pedidos de troca cadastrados
    button_click(driver, by_css_selector='a[href="/admin/listarPedidosTrocas"]')
    sleep(0.5)

def acessar_pedido_troca(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[3]/table/tbody/tr[4]/td[5]/div/div[2]/form/button')
    sleep(0.5)

def avancar_troca(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[3]/div[2]/form/div/button')
    /html/body/main/section/div[3]/div[3]/form/div/button
    sleep(0.5)

def aprovar_troca(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[3]/div[3]/form/div/button')
    sleep(0.5)

def recusar_troca(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[3]/div[2]/form/div/button')
    sleep(0.5)

def logisticaPedidos(driver):
    #Lista toda a logistica de pedidos
    button_click(driver, by_css_selector='button[data-bs-target="#logistica"]')
    sleep(0.5)

    button_click(
        driver, by_xpath='/html/body/div[3]/div/div/div/nav/div/ul/li[10]/div/ul/li[1]/a')
    sleep(0.5)

def acessar_logistica_compra(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/div[3]/table/tbody/tr[1]/td[5]/div/form/button')
    sleep(0.5)

def avancar_logistica(driver):
    button_click(
        driver, by_xpath='/html/body/main/section/form/div/button')
    sleep(0.5)

def logisticaTrocas(driver):
    #Lista toda a logistica de troca
    button_click(driver, by_css_selector='a[href="/admin/logistica/listarLogisticaTrocas"]')
    sleep(0.5)

def listarCliente(driver):
    #Abre a listagem de clientes
    button_click(driver, by_css_selector='button[data-bs-target="#clientes"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/listarClientes"]')
    sleep(0.5)

def ativarCliente(driver, id):
    #Ativa o cliente com o id selecionado
    button_click(driver, by_css_selector='button[value="' + id + '"]')
    sleep(0.5)

def visualizarGrafico(driver):
    #Visualiza os gráficos de relatórios
    button_click(driver, by_css_selector='button[data-bs-target="#graficos"]')
    sleep(0.5)

    button_click(driver, by_css_selector='a[href="/admin/visualizarVendas"]')
    sleep(0.5)

def logTransacao(driver):
    #Visuliza todos os logs de transações
    button_click(driver, by_css_selector='a[href="/admin/listarLogsTransacoes"]')
    sleep(0.5)

def deslogarAdm(driver):
    #Desloga o usuário na tela do administrador
    button_click(driver, by_css_selector='a[href="/logout"]')
    WebDriverWait(driver, 2).until(EC.element_to_be_clickable((By.XPATH, '/html/body/div[2]/div/div/nav/div/div[2]/div/button')))
    sleep(0.5)

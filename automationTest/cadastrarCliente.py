from tools.button_click import button_click

from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0


def cadastrarInformacoes(driver):
    #Registra as informações pessoais
    WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.ID, 'confirm_password')))

    driver.find_element_by_id('nome').send_keys('João da Silva')
    sleep(0.2)

    driver.find_element_by_id('dataNascimento').send_keys('01/01/2000')
    sleep(0.2)

    select = Select(driver.find_element_by_id('tipoTelefone'))
    select.select_by_value('Celular')
    sleep(0.2)

    driver.find_element_by_id('DDD').send_keys('11')
    sleep(0.2)

    driver.find_element_by_id('telefone').send_keys('5555-54444')
    sleep(0.2)

    driver.find_element_by_id('sexoMasculino').click()
    sleep(0.2)

    select = Select(driver.find_element_by_id('documento'))
    select.select_by_value('CPF')
    sleep(0.2)

    driver.find_element_by_id('numeroDocumento').send_keys('111.111.111-11')
    sleep(0.2)

    driver.find_element_by_id('email').send_keys('cliente10@gprint3d.com')
    sleep(0.2)

    driver.find_element_by_id('password').send_keys('Cliente10@')
    sleep(0.2)

    driver.find_element_by_id('confirm_password').send_keys('Cliente10@')
    sleep(0.2)

    button_click(driver, by_css_selector='button[class="btn border shadow-sm"]')
    sleep(0.5)

def cadastrarEndereco(driver):
    #Registra as informações de endereço
    WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'textarea[id="observacao"]')))

    button_click(driver, by_id="endCobranca")
    sleep(0.2)

    driver.find_element_by_id('descricaoImovel').send_keys('Casa')
    sleep(0.2)

    select = Select(driver.find_element_by_id('tipoLogradouro'))
    select.select_by_value('Avenida')
    sleep(0.2)

    driver.find_element_by_id('logradouro').send_keys('Voluntário Pinheiro Franco')
    sleep(0.2)

    driver.find_element_by_id('enderecoNum').send_keys('1234')
    sleep(0.2)

    driver.find_element_by_id('complemento').send_keys('Casa 1')
    sleep(0.2)

    driver.find_element_by_id('CEP').send_keys('08756-652')
    sleep(0.2)

    driver.find_element_by_id('cidade').send_keys('Mogi das Cruzes')
    sleep(0.2)

    select = Select(driver.find_element_by_id('estado'))
    select.select_by_value('SP')
    sleep(0.2)

    driver.find_element_by_id('pais').send_keys('Brasil')
    sleep(0.2)

    button_click(driver, by_id='confirmar')
    sleep(0.5)

def cadastrarPagamento(driver):
    #Registra as informações de pagamento
    WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.ID, 'cvvCartao')))

    driver.find_element_by_id('numCartao').send_keys('1234 1234 1234 1234')
    sleep(0.2)

    driver.find_element_by_id('nomeCartao').send_keys('João da Silva')
    sleep(0.2)

    select = Select(driver.find_element_by_id('bandeiraCartao'))
    select.select_by_value('1')
    sleep(0.2)

    driver.find_element_by_id('validadedCartao').send_keys('12/25')
    sleep(0.2)

    driver.find_element_by_id('cvvCartao').send_keys('123')
    sleep(0.2)

    button_click(driver, by_id='confirmar')
    sleep(0.5)
from time import sleep

from tools.button_click import button_click

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0
from selenium.common.exceptions import ElementClickInterceptedException

def clicarLogin(driver):
    #Clica no login
    button_click(
        driver, by_xpath='/html/body/div[2]/div/header/div/div[3]/div/a[2]')
    try:
        WebDriverWait(driver, 2).until(EC.element_to_be_clickable((By.CSS_SELECTOR, 'a[href="/cadastro/cadastroDadosPessoais"]')))
    except:
        WebDriverWait(driver, 2).until(EC.element_to_be_clickable((By.CSS_SELECTOR, 'a[href="/logout"]')))
    sleep(0.5)

def clicarCadastrarNovoUsuario(driver):
    #Clica no cadastro de novo usuário
    button_click(driver, by_css_selector='a[href="/cadastro/cadastroDadosPessoais"]')
    sleep(0.5)

def logarUsuario(driver, email, senha):
    #Loga o usuário no index
    driver.find_element_by_id('inputEmail').send_keys(email)
    sleep(0.5)

    driver.find_element_by_id('inputPassword').send_keys(senha)
    sleep(0.5)

    button_click(
        driver, by_xpath='/html/body/div[2]/div/header/div/div[3]/div/div/div/form/div/button')
    sleep(1)

def entrarMenuUsuario(driver):
    #Entra no menu do usuário
    try:
        button_click(driver, by_css_selector='a[href="/admin/index"]')
    except:
        button_click(driver, by_css_selector='a[href="/cliente/index"]')
    sleep(0.5)

def deslogarUsuario(driver):
    #Desloga o usuário no index
    button_click(driver, by_css_selector='a[href="/logout"]')
    sleep(0.5)

def entrarCarrinho(driver):
    #Entra no carrinho
    button_click(driver, by_css_selector='a[href="/carrinho/meuCarrinho"]')
    sleep(0.5)

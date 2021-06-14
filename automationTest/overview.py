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
admin.listarBandeiras(driver)

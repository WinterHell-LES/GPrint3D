from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0
from selenium.common.exceptions import ElementClickInterceptedException

def button_click(driver, by_id=None, by_css_selector=None, by_xpath=None, by_element=None, idx=None):
    if by_id != None:
        WebDriverWait(driver, 5).until(EC.element_to_be_clickable((By.ID, by_id)))
        button = driver.find_element_by_id(by_id)
        try:
            button.click()
        except ElementClickInterceptedException:
            driver.execute_script("arguments[0].click();", button)
        
    elif by_css_selector != None:
        WebDriverWait(driver, 5).until(EC.element_to_be_clickable((By.CSS_SELECTOR, by_css_selector)))
        button = driver.find_element_by_css_selector(by_css_selector)
        try:
            button.click()
        except ElementClickInterceptedException:
            driver.execute_script("arguments[0].click();", button)
        
    elif by_xpath != None:
        WebDriverWait(driver, 5).until(EC.element_to_be_clickable((By.XPATH, by_xpath)))
        button = driver.find_element_by_xpath(by_xpath)
        try:
            button.click()
        except ElementClickInterceptedException:
            driver.execute_script("arguments[0].click();", button)

    elif by_element != None:
        try:
            by_element.click()
        except ElementClickInterceptedException:
            driver.execute_script("arguments[0].click();", by_element)

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import com.tamarind.utils.GeneralServiceNew;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Wilson
 */
@Named(value = "ftlCarrierController")
@ViewAccessScoped
public class FtlCarrierController implements Serializable {

    private WebDriver driver;
    private long lowTime = GeneralServiceNew.getLOWTIME();
    private String browser;

    private String user;
    private String password;
    private boolean abailableButtonCarrier = true;
    private String environment = "https://dev.dexfreight.io/#/";

    private String messageAccepShipment;
    private String messageCrearDriver;

    private String shipmetAccepted;

    //login
    private WebElement loginManagerElement;
    private WebElement createManagerElement;

    public FtlCarrierController() {
    }

    @PostConstruct
    public void init() {

    }

    public void createDriver() {

    }

    public void acceptShipment() {
        try {
            if (shipmetAccepted == null || shipmetAccepted.isEmpty()) {
                messageAccepShipment = "Shipment field is required";
            } else {
                login();

                createManagerElement = driver.findElement(By.xpath("//input[@id='mat-input-2']"));
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                createManagerElement.sendKeys(shipmetAccepted);
                Thread.sleep(lowTime);
                //lista de sujerencias
                List<WebElement> generalSearchList = new ArrayList();
                generalSearchList = driver.findElements(By.tagName("mat-option"));
                generalSearchList.get(0).click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                createManagerElement = driver.findElement(By.xpath("//button[@id='acceptBidBtn']"));
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                createManagerElement.click();
                Thread.sleep(lowTime);
                createManagerElement = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
                createManagerElement.click();
                Thread.sleep(lowTime);
                createManagerElement = driver.findElement(By.xpath("//div[@id='swal2-content']"));
                
                if(createManagerElement.getText().equalsIgnoreCase("Offer sent successfully")){
                    messageAccepShipment = GeneralServiceNew.getSUCCESSTEST();
                }else{
                    messageAccepShipment = GeneralServiceNew.getERRORTEST();
                }
                createManagerElement = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
                createManagerElement.click();
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageAccepShipment = e.getMessage();
        }

    }

    public void login() {
        try {
            if(browser.equals("Chrome")){
                System.setProperty(GeneralServiceNew.getCHROMEDRIVER(), GeneralServiceNew.getCHROMEDRIVER_PATH());
                driver = new ChromeDriver();
            }else if(browser.equals("Mozilla")){
                System.setProperty(GeneralServiceNew.getMOZILLADRIVER(), GeneralServiceNew.getMOZILLADRIVER_PATH());
                driver = new FirefoxDriver();
            }else if(browser.equals("Edge")){
                System.setProperty(GeneralServiceNew.getEDGEDRIVER(), GeneralServiceNew.getEDGE_PATH());
                driver = new EdgeDriver();
            }else if(browser.equals("Safari")){
            
            }else{
            
            }
            
            driver.manage().window().maximize(); //maximizar pantalla del navegador
            driver.get(environment);
            Thread.sleep(GeneralServiceNew.getFASTTIME());

            loginManagerElement = driver.findElement(By.xpath("//input[@id='username']"));//user name field
            loginManagerElement.clear();
            loginManagerElement.sendKeys(user);
            loginManagerElement = driver.findElement(By.xpath("//input[@id='pswd']"));//passwrod field
            loginManagerElement.clear();
            loginManagerElement.sendKeys(password);
            loginManagerElement = driver.findElement(By.xpath("//button[@id='btnLogin']"));//singin button
            loginManagerElement.click();
            Thread.sleep(lowTime);

        } catch (Exception e) {
            e.printStackTrace();
            messageAccepShipment = e.getMessage();
            messageCrearDriver = e.getMessage();

        }
    }

    public void clear() {
        messageAccepShipment = "";
        messageCrearDriver = "";
    }

    public void saveInfoCarrier() {
        if ((user == null || user.isEmpty()) || (password == null || password.isEmpty())) {
            //mensaje
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info", "User and password field are required."));
        } else {
            abailableButtonCarrier = false;
        }
    }

    public long getLowTime() {
        return lowTime;
    }

    public void setLowTime(long lowTime) {
        this.lowTime = lowTime;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAbailableButtonCarrier() {
        return abailableButtonCarrier;
    }

    public void setAbailableButtonCarrier(boolean abailableButtonCarrier) {
        this.abailableButtonCarrier = abailableButtonCarrier;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getMessageAccepShipment() {
        return messageAccepShipment;
    }

    public void setMessageAccepShipment(String messageAccepShipment) {
        this.messageAccepShipment = messageAccepShipment;
    }

    public String getMessageCrearDriver() {
        return messageCrearDriver;
    }

    public void setMessageCrearDriver(String messageCrearDriver) {
        this.messageCrearDriver = messageCrearDriver;
    }

    public String getShipmetAccepted() {
        return shipmetAccepted;
    }

    public void setShipmetAccepted(String shipmetAccepted) {
        this.shipmetAccepted = shipmetAccepted;
    }

}

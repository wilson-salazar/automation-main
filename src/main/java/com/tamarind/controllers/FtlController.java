/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import com.tamarind.utils.GeneralServiceNew;
import com.tamarind.utils.GeneralService_1;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Developer
 */
@Named(value = "ftlController")
@ViewAccessScoped
public class FtlController implements Serializable {

    private WebDriver driver;

    private long lowTime = GeneralServiceNew.getLOWTIME();
    private String browser;

    private String messageCreateShip;
    private String messageInvite;
    private String messageOnboard;
    private String messageAcceptBit;

    private String user;
    private String password;
    private boolean abailableButtonBroker = true;
    private String environment = GeneralServiceNew.getEnvironment();

    //login
    private WebElement loginManageElement;

    //Create Shipment
    private boolean accesorials;
    private String brokerReference;
    private String carrierReference;
    private boolean negotiable;
    private String maxrate = "1500";
    private String cityCodeOrig = "58436";
    private String cityCodeDest = "59218";
    private String locationType;
    private String handlingUnits = "30";
    private String pakageType = "Piece";
    private String pieces = "100";
    private String weight = "800";
    private String description = "This a short description test, dexFreight.";
    private String length = "40";
    private String width = "20";
    private String height = "30";
    private String uom = "Cm";
    private String rate = "1000";
    private String equipmentType;
    private String paymentMethod = "ACH";

    //WebElements Create Shipment
    WebElement createShipmentElemet;
    List<WebElement> zidCodeOriginList;
    Select locationTypeList;
    List<WebElement> zidCodeDestinationListElemets;
    Select locationTypeDes;
    Select pakageTypeSelect;
    Select uomSelect;
    Select paymentMethodSelect;

    //Invite 
    private String shipmentInvite;
    private String emailInvite;
    private boolean bidInvite;
    //Inivte Elements
    WebElement inviteManageElement;
    WebElement rateValueElement;

    //Onboard
    private String shipmentOnboard;
    private String emailCarrierOnboard;
    private String mcNumber;
    private String dotNumber;
    private String companyNameOnboard;
    private String fullNameOnboard;
    private String phoneOnboard;
    private boolean bitOnboard;
    //Onboard Element
    WebElement onboardElement;

    //Accept Bid
    WebElement acceptBidElement;
    private String shipmentBid;
    private String companyNameOriginAb = "ban";

    public FtlController() {

    }

    @PostConstruct
    public void init() {

        //onBoard
        generateOnBoardInfo();

    }

    public void acceptBid() {
        try {
            if (shipmentBid == null || shipmentBid.isEmpty()) {
                messageAcceptBit = "Shipment bid field is required";
            } else {
                login();

                acceptBidElement = driver.findElement(By.xpath("//input[@id='mat-input-2']"));
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                acceptBidElement.sendKeys(shipmentBid);
                Thread.sleep(lowTime);
                //lista de sujerencias
                List<WebElement> generalSearchList = new ArrayList();
                generalSearchList = driver.findElements(By.tagName("mat-option"));
                generalSearchList.get(0).click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                acceptBidElement = driver.findElement(By.xpath("//div[@id='" + shipmentBid + "']"));
                acceptBidElement.click();
                Thread.sleep(lowTime);
                acceptBidElement = driver.findElement(By.xpath("//button[@id='buttonAddOrEdit']"));
                if (acceptBidElement.getText().equalsIgnoreCase("Add Information")) {
                    acceptBidElement.click();
                    Thread.sleep(GeneralServiceNew.getFASTTIME());
                    acceptBidElement = driver.findElement(By.xpath("//input[@id='companyNameOrigin']"));
                    Thread.sleep(GeneralServiceNew.getFASTTIME());
                    acceptBidElement.sendKeys(companyNameOriginAb);
                    Thread.sleep(GeneralServiceNew.getFASTTIME());
                    acceptBidElement.sendKeys("n");
                    Thread.sleep(GeneralServiceNew.getFASTTIME());

                    List<WebElement> companyNameOriginList = new ArrayList<>();
                    companyNameOriginList = driver.findElements(By.className("dropdown-item ng-star-inserted"));
                    System.out.println("Tamaño lista: >>>>>>>> " + companyNameOriginList.size());
//                    companyNameOriginList.get(0).click();

                    if (companyNameOriginList.size() > 0) {
                        companyNameOriginList.get(0).click();
                    } else {
                        int value = 0;
                        while (companyNameOriginList.size() <= 0 && value <= 3) {
                            acceptBidElement.clear();
                            acceptBidElement.sendKeys(companyNameOriginAb);
                            Thread.sleep(GeneralServiceNew.getFASTTIME());
                            companyNameOriginList = driver.findElements(By.tagName("button"));
                            value++;
                        }
                        companyNameOriginList.get(0).click();
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            messageAcceptBit = e.getMessage();
        }

    }

    public void generateOnBoardInfo() {
        String[] numberAndName = GeneralServiceNew.getRandomEmailAndName();
        emailCarrierOnboard = numberAndName[1];
        companyNameOnboard = numberAndName[0];
        mcNumber = GeneralServiceNew.getRandomNumber();
        dotNumber = GeneralServiceNew.getRandomNumber();
        fullNameOnboard = companyNameOnboard;
        phoneOnboard = GeneralServiceNew.getRandomPhone();

    }

    public void clear() {
        messageCreateShip = "";
        messageInvite = "";
        messageOnboard = "";
    }

    public void login() {
        try {

            if (browser.equals("Chrome")) {
                System.setProperty(GeneralService_1.getCHROMEDRIVER(), GeneralService_1.getCHROMEDRIVER_PATH());
                driver = new ChromeDriver();               
            } else if (browser.equals("Mozilla")) {
                System.setProperty(GeneralService_1.getMOZILLADRIVER(), GeneralService_1.getMOZILLADRIVER_PATH());
                driver = new FirefoxDriver();
            } else if (browser.equals("Edge")) {
                System.setProperty(GeneralService_1.getEDGEDRIVER(), GeneralService_1.getEDGE_PATH());
                driver = new EdgeDriver();
            } else {
                System.setProperty(GeneralService_1.getIEDRIVER(), GeneralService_1.getIE_PATH());
                driver = new InternetExplorerDriver();
            }

            driver.manage().window().maximize(); //maximizar pantalla del navegador
            driver.get(environment);
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            loginManageElement = driver.findElement(By.xpath("//input[@id='username']"));//user name field
            loginManageElement.clear();
            loginManageElement.sendKeys(user);
            loginManageElement = driver.findElement(By.xpath("//input[@id='pswd']"));//passwrod field
            loginManageElement.clear();
            loginManageElement.sendKeys(password);
            loginManageElement = driver.findElement(By.xpath("//button[@id='btnLogin']"));//singin button
            loginManageElement.click();
            Thread.sleep(lowTime);

        } catch (Exception e) {
            e.printStackTrace();
            messageCreateShip = e.getMessage();
            messageInvite = e.getMessage();
            messageOnboard = e.getMessage();
        }
    }

    public void onBoard() {

        messageOnboard = "";
        try {

            if ((shipmentOnboard == null || shipmentOnboard.isEmpty())
                    || (emailCarrierOnboard == null || emailCarrierOnboard.isEmpty())) {
                messageOnboard = "====== The shipment and email fields are required ======";
                System.out.println("Mensaje: " + messageOnboard);
            } else {

                login();
                Thread.sleep(lowTime);
                System.out.println("Tiempo en la aplicacion: >>>>>>>>>>>>>>>>>" + lowTime);
                onboardElement = driver.findElement(By.xpath("//div[@id='" + shipmentOnboard + "']"));
                onboardElement.click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                onboardElement = driver.findElement(By.xpath("//i[@id='detailsOptions']"));//details option, thre points 
                onboardElement.click();

                onboardElement = driver.findElement(By.linkText("Onboard new carrier"));//option new carrier
                onboardElement.click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());

                //bid
                if (bitOnboard) {
                    onboardElement = driver.findElement(By.xpath("//p[@id='onboardRate']"));//Rate
                    String valor = onboardElement.getText();
                    onboardElement = driver.findElement(By.xpath("//input[@id='onboardRateField']"));//rate field
                    onboardElement.sendKeys(valor);
                }

                onboardElement = driver.findElement(By.xpath("//input[@id='onboardMCNumber']"));//mc number field
                onboardElement.sendKeys(mcNumber);
                onboardElement = driver.findElement(By.xpath("//input[@id='onboardDOT']"));//dot number field
                onboardElement.sendKeys(dotNumber);
                onboardElement = driver.findElement(By.xpath("//input[@id='onboardCompanyName']"));//company name field
                onboardElement.sendKeys(companyNameOnboard);
                onboardElement = driver.findElement(By.xpath("//input[@id='onboardFullName']"));//full name field
                onboardElement.sendKeys(fullNameOnboard);
                onboardElement = driver.findElement(By.xpath("//input[@id='onboardEmail']"));//email field
                onboardElement.sendKeys(emailCarrierOnboard);
                onboardElement = driver.findElement(By.xpath("//input[@id='onboardPhone']"));//phone field
                onboardElement.sendKeys(phoneOnboard);
                onboardElement = driver.findElement(By.xpath("//button[@id='onboardSendBtn']"));//send button 
                onboardElement.click();
                Thread.sleep(lowTime);
                Thread.sleep(GeneralServiceNew.getFASTTIME());

                WebElement title = new WebDriverWait(driver, lowTime).until(driver -> driver.findElement(By.id("swal2-title")));

                if (title.getText().equalsIgnoreCase("Success")) {
                    messageOnboard = GeneralServiceNew.getSUCCESSTEST();
                } else {
                    messageOnboard = GeneralServiceNew.getERRORTEST();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            messageOnboard = e.getMessage();
        }

    }

    public void createShipmentFtl() {
        try {
            messageCreateShip = "";
            login();

            createShipmentElemet = driver.findElement(By.xpath("//a[@id='shipments']"));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            createShipmentElemet.click();
            Thread.sleep(GeneralServiceNew.getFASTTIME());

            createShipmentElemet = driver.findElement(By.xpath("//a[contains(text(),'Create Shipment')]"));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            createShipmentElemet.click();
            Thread.sleep(GeneralServiceNew.getFASTTIME());

            // ==================== Origin ====================
            createShipmentElemet = driver.findElement(By.xpath("//input[@id='origin']"));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            createShipmentElemet.sendKeys(cityCodeOrig);
            Thread.sleep(GeneralServiceNew.getFASTTIME());

            zidCodeOriginList = new ArrayList<WebElement>();
            zidCodeOriginList = driver.findElements(By.xpath("//typeahead-container/button[1]"));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            if (zidCodeOriginList.size() > 0) {
                WebElement option = zidCodeOriginList.get(0);
                option.click();
            } else {
                int flag = 0;
                while (zidCodeOriginList.size() <= 0 && flag <= 3) {
                    flag++;
                    createShipmentElemet.clear();
                    createShipmentElemet.sendKeys(cityCodeOrig);
                    Thread.sleep(GeneralServiceNew.getFASTTIME());
                    zidCodeOriginList = driver.findElements(By.xpath("//typeahead-container/button[1]"));
                }
                WebElement option = zidCodeOriginList.get(0);
                option.click();
            }
            locationTypeList = new Select(driver.findElement(By.xpath("//select[@id='originLocationType']")));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            locationTypeList.selectByVisibleText(locationType);

            // ==================== origin accesorial ====================
            if (accesorials) {
                createShipmentElemet = driver.findElement(By.id("pickupAccesorials"));
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                //Actions action = new Actions(driver);
                //action.moveToElement(createShipmentElemet);
                //action.perform();

                createShipmentElemet.click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                createShipmentElemet = driver.findElement(By.xpath("//div[@class='pickupAccessorials']//li[2]"));
                createShipmentElemet.click();
            }

            // ==================== Destinaton ====================
            createShipmentElemet = driver.findElement(By.xpath("//input[@id='destination']"));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            createShipmentElemet.sendKeys(cityCodeDest);
            Thread.sleep(GeneralServiceNew.getFASTTIME());

            zidCodeDestinationListElemets = new ArrayList<WebElement>();
            zidCodeDestinationListElemets = driver.findElements(By.xpath("//typeahead-container[@class='dropdown open dropdown-menu']"));
            if (zidCodeDestinationListElemets.size() > 0) {
                WebElement option = zidCodeDestinationListElemets.get(0);
                option.click();
            }
            locationTypeDes = new Select(driver.findElement(By.xpath("//select[@id='destinationLocationType']")));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            locationTypeDes.selectByVisibleText(locationType);

            // ==================== destination accesorial ====================
            if (accesorials) {
                WebElement originAccesorial = driver.findElement(By.id("deliveryAccesorials"));
                originAccesorial.click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                WebElement optionAccesorial = driver.findElement(By.xpath("//div[@class='deliveryAccessorials']//li[4]"));
                optionAccesorial.click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
            }

            // ==================== Cargo detail ====================
            createShipmentElemet = driver.findElement(By.id("addRowBtn"));
            createShipmentElemet.click();

            createShipmentElemet = driver.findElement(By.xpath("//input[@id='handling']"));
            createShipmentElemet.sendKeys(handlingUnits);

            pakageTypeSelect = new Select(driver.findElement(By.xpath("//select[@class='form-control ng-untouched ng-pristine ng-invalid']")));
            pakageTypeSelect.selectByVisibleText(pakageType);

            createShipmentElemet = driver.findElement(By.xpath("//input[@id='pieces']"));
            createShipmentElemet.sendKeys(pieces);

            createShipmentElemet = driver.findElement(By.xpath("//input[@id='weight']"));
            createShipmentElemet.sendKeys(weight);

            createShipmentElemet = driver.findElement(By.xpath("//input[@id='loadDescription']"));
            createShipmentElemet.sendKeys(description);

            createShipmentElemet = driver.findElement(By.xpath("//input[@id='length']"));
            createShipmentElemet.sendKeys(length);

            createShipmentElemet = driver.findElement(By.xpath("//input[@id='width']"));
            createShipmentElemet.sendKeys(width);

            createShipmentElemet = driver.findElement(By.xpath("//input[@id='height']"));
            createShipmentElemet.sendKeys(height);

            uomSelect = new Select(driver.findElement(By.xpath("//select[@id='uom']")));
            uomSelect.selectByVisibleText(uom);

            createShipmentElemet = driver.findElement(By.xpath("//button[@id='addDetailBtn']"));
            createShipmentElemet.click();
            Thread.sleep(GeneralServiceNew.getFASTTIME());

            // ==================== Payment seccion ====================
            createShipmentElemet = driver.findElement(By.id("content-equipment-type"));
            createShipmentElemet.click();
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            createShipmentElemet = driver.findElement(By.xpath("//div[contains(text(),'" + equipmentType + "')]"));
            createShipmentElemet.click();
            //==============    References id ==============
            if (brokerReference != null && !brokerReference.isEmpty()) {
                createShipmentElemet.findElement(By.xpath("//input[@id='brokerRefId']"));
                createShipmentElemet.sendKeys(brokerReference);
            }
            if (carrierReference != null && !carrierReference.isEmpty()) {
                createShipmentElemet.findElement(By.xpath("//input[@id='carrierRefId']"));
                createShipmentElemet.sendKeys(carrierReference);
            }

            // ==================== Rate* ====================
            createShipmentElemet = driver.findElement(By.xpath("//input[@id='rate']"));
            createShipmentElemet.click();
            createShipmentElemet.sendKeys(rate);

            if (negotiable) {
                createShipmentElemet = driver.findElement(By.xpath("//label[@class='switch']//i"));
                createShipmentElemet.click();
                createShipmentElemet = driver.findElement(By.xpath("//input[@id='limit']"));
                createShipmentElemet.sendKeys(maxrate);
            }

            //Payment method
            paymentMethodSelect = new Select(driver.findElement(By.xpath("//select[@id='paymentMethod']")));
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            createShipmentElemet = driver.findElement(By.xpath("//button[@id='post']"));
            createShipmentElemet.click();
            Thread.sleep(GeneralServiceNew.getFASTTIME());
            Thread.sleep(lowTime);

            WebElement title = new WebDriverWait(driver, lowTime).until(driver -> driver.findElement(By.id("swal2-title")));

            String result = title.getText();
            if (!result.isEmpty() && result.equals("Shipment Posted")) {
                messageCreateShip = GeneralServiceNew.getSUCCESSTEST();
            } else {
                messageCreateShip = GeneralServiceNew.getERRORTEST();
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageCreateShip = e.getMessage();

        }

    }

    public void invite() {
        if (shipmentInvite == null || shipmentInvite.isEmpty()) {
            messageInvite = "====== The invite shipment and invite email fields are required ======";
        } else {
            try {

                messageInvite = "";
                login();

                inviteManageElement = driver.findElement(By.xpath("//div[@id='" + shipmentInvite + "']"));//shipment find
                inviteManageElement.click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                inviteManageElement = driver.findElement(By.xpath("//i[@id='detailsOptions']"));//detail otion
                inviteManageElement.click();
                inviteManageElement = driver.findElement(By.linkText("Invite a carrier"));//option
                inviteManageElement.click();
                Thread.sleep(GeneralServiceNew.getFASTTIME());
                inviteManageElement = driver.findElement(By.xpath("//input[@id='emailInvite']"));//email invite 
                inviteManageElement.sendKeys(emailInvite);
                if (bidInvite) {
                    rateValueElement = driver.findElement(By.xpath("//p[@id='rateOpen']"));//rate output
                    inviteManageElement = driver.findElement(By.xpath("//input[@id='rateInvite']"));//rate field
                    inviteManageElement.sendKeys(rateValueElement.getText());
                }
                inviteManageElement = driver.findElement(By.xpath("//button[@id='sendInviteBtn']"));//send button
                inviteManageElement.click();
                Thread.sleep(lowTime);
                Thread.sleep(GeneralServiceNew.getFASTTIME());

                WebElement title = new WebDriverWait(driver, lowTime).until(driver -> driver.findElement(By.id("swal2-title")));

                if (title.getText().equalsIgnoreCase("Success")) {
                    messageInvite = GeneralServiceNew.getSUCCESSTEST();
                } else {
                    messageInvite = GeneralServiceNew.getERRORTEST();
                }

            } catch (Exception e) {
                e.printStackTrace();
                messageInvite = e.getMessage();
            }
        }
    }

    public String getMessageInvite() {
        return messageInvite;
    }

    public void setMessageInvite(String messageInvite) {
        this.messageInvite = messageInvite;
    }

    public String getMessageOnboard() {
        return messageOnboard;
    }

    public void setMessageOnboard(String messageOnboard) {
        this.messageOnboard = messageOnboard;
    }

    public void saveInfoBroker() {
        if ((user == null || user.isEmpty()) || (password == null || password.isEmpty())) {
            //mensaje
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info", "User and password field are required."));
        } else {
            abailableButtonBroker = false;
        }

    }

    public String getMessageCreateShip() {
        return messageCreateShip;
    }

    public void setMessageCreateShip(String messageCreateShip) {
        this.messageCreateShip = messageCreateShip;
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

    public boolean isAbailableButtonBroker() {
        return abailableButtonBroker;
    }

    public void setAbailableButtonBroker(boolean abailableButtonBroker) {
        this.abailableButtonBroker = abailableButtonBroker;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getShipmentInvite() {
        return shipmentInvite;
    }

    public void setShipmentInvite(String shipmentInvite) {
        this.shipmentInvite = shipmentInvite;
    }

    public String getEmailInvite() {
        return emailInvite;
    }

    public void setEmailInvite(String emailInvite) {
        this.emailInvite = emailInvite;
    }

    public boolean isBidInvite() {
        return bidInvite;
    }

    public void setBidInvite(boolean bidInvite) {
        this.bidInvite = bidInvite;
    }

    public String getShipmentOnboard() {
        return shipmentOnboard;
    }

    public void setShipmentOnboard(String shipmentOnboard) {
        this.shipmentOnboard = shipmentOnboard;
    }

    public String getEmailCarrierOnboard() {
        return emailCarrierOnboard;
    }

    public void setEmailCarrierOnboard(String emailCarrierOnboard) {
        this.emailCarrierOnboard = emailCarrierOnboard;
    }

    public String getMcNumber() {
        return mcNumber;
    }

    public void setMcNumber(String mcNumber) {
        this.mcNumber = mcNumber;
    }

    public String getDotNumber() {
        return dotNumber;
    }

    public void setDotNumber(String dotNumber) {
        this.dotNumber = dotNumber;
    }

    public String getCompanyNameOnboard() {
        return companyNameOnboard;
    }

    public void setCompanyNameOnboard(String companyNameOnboard) {
        this.companyNameOnboard = companyNameOnboard;
    }

    public String getFullNameOnboard() {
        return fullNameOnboard;
    }

    public void setFullNameOnboard(String fullNameOnboard) {
        this.fullNameOnboard = fullNameOnboard;
    }

    public String getPhoneOnboard() {
        return phoneOnboard;
    }

    public void setPhoneOnboard(String phoneOnboard) {
        this.phoneOnboard = phoneOnboard;
    }

    public boolean isBitOnboard() {
        return bitOnboard;
    }

    public void setBitOnboard(boolean bitOnboard) {
        this.bitOnboard = bitOnboard;
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

    public String getMessageAcceptBit() {
        return messageAcceptBit;
    }

    public void setMessageAcceptBit(String messageAcceptBit) {
        this.messageAcceptBit = messageAcceptBit;
    }

    public String getShipmentBid() {
        return shipmentBid;
    }

    public void setShipmentBid(String shipmentBid) {
        this.shipmentBid = shipmentBid;
    }

    public String getCompanyNameOriginAb() {
        return companyNameOriginAb;
    }

    public void setCompanyNameOriginAb(String companyNameOriginAb) {
        this.companyNameOriginAb = companyNameOriginAb;
    }

    public String getBrokerReference() {
        return brokerReference;
    }

    public void setBrokerReference(String brokerReference) {
        this.brokerReference = brokerReference;
    }

    public String getCarrierReference() {
        return carrierReference;
    }

    public void setCarrierReference(String carrierReference) {
        this.carrierReference = carrierReference;
    }

    public boolean isAccesorials() {
        return accesorials;
    }

    public void setAccesorials(boolean accesorials) {
        this.accesorials = accesorials;
    }

    public boolean isNegotiable() {
        return negotiable;
    }

    public void setNegotiable(boolean negotiable) {
        this.negotiable = negotiable;
    }

    public String getMaxrate() {
        return maxrate;
    }

    public void setMaxrate(String maxrate) {
        this.maxrate = maxrate;
    }

    public String getCityCodeOrig() {
        return cityCodeOrig;
    }

    public void setCityCodeOrig(String cityCodeOrig) {
        this.cityCodeOrig = cityCodeOrig;
    }

    public String getCityCodeDest() {
        return cityCodeDest;
    }

    public void setCityCodeDest(String cityCodeDest) {
        this.cityCodeDest = cityCodeDest;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

}

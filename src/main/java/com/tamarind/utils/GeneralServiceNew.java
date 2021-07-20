/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.utils;

import java.util.Random;

/**
 *
 * @author Wilson
 */
public class GeneralServiceNew {
    
    static final String CHROMEDRIVER = "webdriver.chrome.driver";
    static final String MOZILLADRIVER = "webdriver.gecko.driver";
    //static final String CHROMEDRIVER_PATH = "webDrivers/ch88.0/chromedriver.exe";
    static final String CHROMEDRIVER_PATH = "webDrivers/ch98.0/chromedriver";
    //static final String MOZILLADRIVER_PATH = "webDrivers/mfox/geckodriver.exe";
    static final String MOZILLADRIVER_PATH = "webDrivers/mfox/geckodriver";
    static final String EDGEDRIVER = "webdriver.edge.driver";
    static final String EDGE_PATH = "webDrivers/edge87.0.66/msedgedriver.exe";
    static final String IEDRIVER = "webdriver.ie.driver";
    static final String IE_PATH = "webDrivers/ie3.1.50/IEDriverServer.exe";
    static final String environment = "https://dev.dexfreight.io/#/";
    
    static final long FASTTIME = 2000;
    static final long LOWTIME = 3000;
    static final String SUCCESSTEST = "RESULT: ======== Success test... ========";
    static final String ERRORTEST = "RESULT: ======== The end of the test is not as expected ========";

    
    public static String getRandomPhone(){
        Random number = new Random();
        
        return "3185"+number.nextInt(1000)+""+number.nextInt(1000);
    }
    
    public static String[] getRandomEmailAndName(){
        String[] values = new String[2];
        
        Random number = new Random();
        int value = number.nextInt(1000);
        int value2 = number.nextInt(500);
        String totalValue = value+""+value2;
        
        String nameComany = "Company"+totalValue;
        String emailComapny = "Company"+totalValue+"@mail.com";
        
        values[0] = nameComany;
        values[1] = emailComapny;
        
        return values;
    }
    
    public static String getRandomNumber(){
        Random number = new Random();
        int value = number.nextInt(1000);
        int value2 = number.nextInt(1000);
        String totalValue = value+""+value2;
        
        return totalValue;
    }

    public static String getEnvironment() {
        return environment;
    }
    
    public static String getERRORTEST() {
        return ERRORTEST;
    }

    public static String getSUCCESSTEST() {
        return SUCCESSTEST;
    }

    public static String getCHROMEDRIVER() {
        return CHROMEDRIVER;
    }

    public static String getCHROMEDRIVER_PATH() {
        return CHROMEDRIVER_PATH;
    }

    public static long getFASTTIME() {
        return FASTTIME;
    }

    public static long getLOWTIME() {
        return LOWTIME;
    }

    public static String getMOZILLADRIVER() {
        return MOZILLADRIVER;
    }

    public static String getMOZILLADRIVER_PATH() {
        return MOZILLADRIVER_PATH;
    }

    public static String getEDGEDRIVER() {
        return EDGEDRIVER;
    }

    public static String getEDGE_PATH() {
        return EDGE_PATH;
    }

    public static String getIEDRIVER() {
        return IEDRIVER;
    }

    public static String getIE_PATH() {
        return IE_PATH;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Random;

/**
 *
 * @author Wilson
 */
public class GeneralService_1 {

    static final String CHROMEDRIVER = "webdriver.chrome.driver";
    static final String MOZILLADRIVER = "webdriver.gecko.driver";
    static final String EDGEDRIVER = "webdriver.edge.driver";
    static final String IEDRIVER = "webdriver.ie.driver";
    
    //Browsers
    static String CHROMEDRIVER_PATH;
    static String MOZILLADRIVER_PATH;
    static String EDGE_PATH;
    static String IE_PATH;
    
    static final String environment = "https://dev.dexfreight.io/#/";

    static {
        try {
            CHROMEDRIVER_PATH = getPath("com/tamarind/drivers/window/chromedriver.exe");
            MOZILLADRIVER_PATH = getPath("com/tamarind/drivers/window/chromedriver.exe");
            EDGE_PATH = getPath("com/tamarind/drivers/window/msedgedriver.exe");
            IE_PATH = getPath("com/tamarind/drivers/window/IEDriverServer.exe");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static final long FASTTIME = 2000;
    static final long LOWTIME = 3000;
    static final String SUCCESSTEST = "RESULT: ======== Success test... ========";
    static final String ERRORTEST = "RESULT: ======== The end of the test is not as expected ========";

    private static String getPath(String resourceName) throws URISyntaxException {
        return new File(GeneralService_1.class.getClassLoader().getResource(resourceName).toURI())
                .getAbsolutePath();
    }

    public static String getRandomPhone() {
        Random number = new Random();

        return "3185" + number.nextInt(1000) + "" + number.nextInt(1000);
    }

    public static String[] getRandomEmailAndName() {
        String[] values = new String[2];

        Random number = new Random();
        int value = number.nextInt(1000);
        int value2 = number.nextInt(500);
        String totalValue = value + "" + value2;

        String nameComany = "Company" + totalValue;
        String emailComapny = "Company" + totalValue + "@mail.com";

        values[0] = nameComany;
        values[1] = emailComapny;

        return values;
    }

    public static String getRandomNumber() {
        Random number = new Random();
        int value = number.nextInt(1000);
        int value2 = number.nextInt(1000);
        String totalValue = value + "" + value2;

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

package com.cydeo.tests;

import com.cydeo.utils.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;

public class SauceLabTestLocal {
    @Test
    public void LoginTest(){
        AppiumDriver driver = Driver.getDriver();

        System.out.println(driver.getDeviceTime());

        Driver.closeDriver();
    }
}

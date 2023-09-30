package com.cydeo.tests;

import com.cydeo.utils.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;

public class SauceLabTestLocal {
    @Test
    public void LoginTest() throws InterruptedException {
        AppiumDriver<MobileElement> driver = Driver.getDriver();

        System.out.println(driver.getDeviceTime());

        // userName Box element and send input
        driver.findElement(MobileBy.AccessibilityId("test-Username")).sendKeys("standard_user");
        // password
        driver.findElement(MobileBy.AccessibilityId("test-Password")).sendKeys("secret_sauce");
        // submit
        driver.findElement(MobileBy.AccessibilityId("test-LOGIN")).click();

        Driver.closeDriver();
    }
}

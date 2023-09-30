package com.cydeo.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Driver {
    private static AppiumDriver<MobileElement> driver;
    private static URL url;

    private Driver() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        String platform = ConfigurationReader.getProperty("platform");
        if (Objects.isNull(driver)) {
            switch (platform) {
                case "android-calculator":
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
                    desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/calculator.apk");
                    try {
                        url = new URL("http://localhost:4723/");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new AndroidDriver<>(url, desiredCapabilities);
                    break;
                case "android-remote":
                    DesiredCapabilities caps = new DesiredCapabilities();

                    // Set your access credentials
                    caps.setCapability("browserstack.user", "testuser_1PhU8f");
                    caps.setCapability("browserstack.key", "qxU7LUK78o8BK1ki799f");

                    // Set URL of the application under test
                    caps.setCapability("app", "bs://e0ce6dfd61f8f7d9fd9c4fb11c746b65fd1d79f1");

                    // Specify device and os_version for testing
                    caps.setCapability("device", "OnePlus 8");
                    caps.setCapability("os_version", "10.0");
                    caps.setCapability("realMobile", "true");

                    // Set other BrowserStack capabilities
                    caps.setCapability("project", "My test appium automation");
                    caps.setCapability("build", "Java Android");
                    caps.setCapability("name", "Regression");

                    // Initialise the remote Webdriver using BrowserStack remote URL
                    // and desired capabilities defined above
                    try {
                        driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"), caps);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "android-saucelab":
                    DesiredCapabilities saucelabLocal = new DesiredCapabilities();
                    saucelabLocal.setCapability("appium:automationName","UiAutomator2");
                    saucelabLocal.setCapability(MobileCapabilityType.PLATFORM_NAME,Platform.ANDROID);
                    saucelabLocal.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10");
                    saucelabLocal.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 3");
                    saucelabLocal.setCapability(MobileCapabilityType.APP,"/Users/oscar/IdeaProjects/B29_AppiumTests/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    saucelabLocal.setCapability("appPackage","com.swaglabsmobileapp");
                    saucelabLocal.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
                    try {
                        url = new URL("http://localhost:4723/"); // if you are using Appium 1 add "wd/hub/" as path paramater to your URL
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new AndroidDriver<>(url,saucelabLocal);
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (Objects.nonNull(driver)) {
            driver.closeApp();
            driver = null;
        }
    }
}
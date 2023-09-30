package com.cydeo.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
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
                case "android-saucelab-remote":
                    MutableCapabilities capss = new MutableCapabilities();
                    capss.setCapability("platformName", "Android");
                    capss.setCapability("appium:deviceName", "Samsung Galaxy S10 WQHD GoogleAPI Emulator");
                    capss.setCapability("appium:deviceOrientation", "portrait");
                    capss.setCapability("appium:platformVersion", "current_major");
                    capss.setCapability("appium:automationName", "UiAutomator2");
                    capss.setCapability("appium:app","https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    capss.setCapability("appPackage","com.swaglabsmobileapp");
                    capss.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
                    MutableCapabilities sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("username", "oauth-sdetoscar-2ca62");
                    sauceOptions.setCapability("accessKey", "personal access key");
                    sauceOptions.setCapability("build", "Cydeo123");
                    sauceOptions.setCapability("name", "B29_Native_App_Test");
                    capss.setCapability("sauce:options", sauceOptions);


                    try {
                        url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new AndroidDriver(url, capss);
                    break;
                case "iphone-saucelab-remote":
                    MutableCapabilities capsI = new MutableCapabilities();
                    capsI.setCapability("platformName", "iOS");
                    capsI.setCapability("appium:deviceName", "iPhone Simulator");
                    capsI.setCapability("appium:deviceOrientation", "portrait");
                    capsI.setCapability("appium:platformVersion", "current_major");
                    capsI.setCapability("appium:automationName", "XCUITest");
                    capsI.setCapability("appium:app","https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");
                    capsI.setCapability("appPackage","com.swaglabsmobileapp");
                    capsI.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
                    MutableCapabilities sauceOptionsI = new MutableCapabilities();
                    sauceOptionsI.setCapability("username", "oauth-sdetoscar-2ca62");
                    sauceOptionsI.setCapability("accessKey", "Your personal access code");
                    sauceOptionsI.setCapability("build", "CydeoIphone123");
                    sauceOptionsI.setCapability("name", "Saucelab Iphone Test");
                    capsI.setCapability("sauce:options", sauceOptionsI);


                    try {
                        url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new IOSDriver(url, capsI);
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
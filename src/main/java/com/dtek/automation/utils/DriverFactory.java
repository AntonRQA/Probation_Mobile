package com.dtek.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileBrowserType.CHROME;

public class DriverFactory {

    public static AppiumDriver getDriver(){
        String param = System.getProperties().getProperty("os");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        AppiumDriver driver = null;

        if (param.equalsIgnoreCase("android_chrome")) {
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, CHROME);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);

            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");
            desiredCapabilities.setCapability("appium:forceAppLaunch", true);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"300");
            desiredCapabilities.setCapability("unicodeKeyboard", "true");
            desiredCapabilities.setCapability("resetKeyboard", "true");

            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                e.getMessage();
            }

        } else if (param.equalsIgnoreCase("ios_safari")) {
            desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "17.5");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 15");

            try {
                driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                e.getMessage();
            }
        };

        return driver;
    }
}

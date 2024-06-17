package com.dtek.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    @FindAll(
        @FindBy(xpath = "//*[@name='q']")
    )
    public WebElement searchBox;

    @FindAll(
        @FindBy(xpath = "//*[@name='btnK']")
    )
    public WebElement searchButton;

    @FindAll(
            {
            @FindBy(xpath = "//a[]")}
    )
    public WebElement resultsContainer;

    public SearchPage(AppiumDriver driver) {
        super(driver);
        driver.get("http://www.google.com");
        waitForElement(searchBox);
    }

    public void searchText(String searchString) {
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(searchString);
        if (getCurrentDriverInstance().getClass().getName().contains("Android")) {
            searchBox.sendKeys(Keys.ENTER);
        } else if (getCurrentDriverInstance().getClass().getName().contains("IOS")) {
            String webViewContextName = ((IOSDriver) getCurrentDriverInstance()).getContext();
            ((IOSDriver) getCurrentDriverInstance()).context("NATIVE_APP");
            ((IOSDriver) getCurrentDriverInstance()).findElement(By.xpath("//XCUIElementTypeButton[@name='Search']")).click();
            ((IOSDriver) getCurrentDriverInstance()).context(webViewContextName);
        }
        //waitForElement(resultsContainer);
    }

    public int linksContains(String str) {
        Document doc = Jsoup.parse(getCurrentDriverInstance().getPageSource());
        Elements results = doc.select("a");
        List<String> resList = new ArrayList<>();

        for (Element result : results) {
            String title = result.text();
            String url = result.absUrl("href");

            if (!url.startsWith("http")) {
                continue;
            }

            if (url.contains(str)) {
                System.out.println("Title: " + title);
                System.out.println("URL: " + url);
                resList.add(url);
            }
        }
        return resList.size();
    }

    public int titlesContains(String str) {
        Document doc = Jsoup.parse(getCurrentDriverInstance().getPageSource());
        Elements results = doc.select("a");
        List<String> resList = new ArrayList<>();

        for (Element result : results) {
            String title = result.text();
            String url = result.absUrl("href");

            if (!url.startsWith("http")) {
                continue;
            }

            if (title.contains(str)) {
                System.out.println("Title: " + title);
                System.out.println("URL: " + url);
                resList.add(title);
            }
        }
        return resList.size();
    }
}

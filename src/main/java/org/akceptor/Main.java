package org.akceptor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Akceptor on 22.06.2014.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String... args) throws IOException, InterruptedException {
        //User Agents List
        List<String> userAgentsList = new ArrayList<String>();
        LOGGER.info("Generating User Agents");
        //Add user agent strings here:
        userAgentsList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0");
        userAgentsList.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0)");
        userAgentsList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7) AppleWebKit/534.48.3 (KHTML, like Gecko) Version/5.1 Safari/534.48.3");
        //Init
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", StrUtils.randomString(userAgentsList));

        //TOR
        LOGGER.info("Starting TOR");
        Process torProcess = Runtime.getRuntime().exec("c:\\Program Files (x86)\\Vidalia Bridge Bundle\\Tor\\tor.exe");
        //Wait TOR Network
        LOGGER.info("Waiting for TOR");
        Thread.sleep(10 * 1000);
        LOGGER.info("Waiting for TOR ... ");
        Thread.sleep(20 * 1000);
        LOGGER.info("Waiting for TOR ... ... ");
        Thread.sleep(20 * 1000);
        LOGGER.info("Waiting for TOR ... ... ... ");
        Thread.sleep(10 * 1000);
        LOGGER.info("Waiting more than 1 minute already, let's go!");

        //Proxy
        LOGGER.info("Setting proxy");
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.socks", "localhost");
        profile.setPreference("network.proxy.socks_port", 9050);
        LOGGER.info("Creating profile with no cookies");
        WebDriver driver = new FirefoxDriver(profile);
        driver.manage().deleteAllCookies();

        //Open site
        LOGGER.info("Opening IP check site");
        driver.get("http://www.whatismyip.com");

        LOGGER.info("Opening target site");
        driver.get("http://akceptor.org");
        System.out.println("Cookies: " + driver.manage().getCookies());

        //Browse
        LOGGER.info("Browsing");
        driver.findElement(By.partialLinkText("(частина 5)")).click();
        driver.findElement(By.partialLinkText("Думки вголос")).click();
        driver.findElement(By.partialLinkText("(частина 4)")).click();
        driver.findElement(By.partialLinkText("Думки вголос")).click();
        //Search
        LOGGER.info("Searching");
        driver.findElement(By.name("s")).sendKeys("selenium");
        driver.findElement(By.name("s")).sendKeys(Keys.RETURN);
        //Featured posts
        driver.findElement(By.className("featured-thumb")).click();
        //Close browser
        LOGGER.info("Stopping browser");
        driver.close();
        LOGGER.info("Stoping TOR");
        torProcess.destroy();
        LOGGER.info("DONE!");
    }

}

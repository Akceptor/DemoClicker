import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Akceptor on 22.06.2014.
 */
public class Main {
    private static Random randomGenerator = new Random();

    public static void main(String... args) {
        //User Agents List
        List<String> userAgentsList = new ArrayList<String>();
        //Add user agent strings here:
        userAgentsList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0");
        userAgentsList.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0)");
        userAgentsList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7) AppleWebKit/534.48.3 (KHTML, like Gecko) Version/5.1 Safari/534.48.3");
        //Init
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", randomString(userAgentsList));
        WebDriver driver = new FirefoxDriver(profile);
        driver.manage().deleteAllCookies();

        //Open site
        driver.get("http://akceptor.org");
        System.out.println("Cookies: " + driver.manage().getCookies());

        //Browse
        driver.findElement(By.partialLinkText("(частина 1)")).click();
        driver.findElement(By.partialLinkText("Думки вголос")).click();
        driver.findElement(By.partialLinkText("(частина 2)")).click();
        driver.findElement(By.partialLinkText("Думки вголос")).click();
        //Search
        driver.findElement(By.name("s")).sendKeys("selenium");
        driver.findElement(By.name("s")).sendKeys(Keys.RETURN);
        //Featured posts
        driver.findElement(By.className("featured-thumb")).click();
        //Close browser
        driver.close();
    }

    /**
     * Returns random element from list
     *
     * @param list of elements
     * @return random element
     */
    private static String randomString(List<String> list) {
        int index = randomGenerator.nextInt(list.size());
        return list.get(index);
    }
}

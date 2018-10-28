package org.schematch_team.parsing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumParser {

    //https://chromedriver.storage.googleapis.com/index.html?path=2.43/
    //качайте отсюда selenium driver

    private WebDriver driver;

    public SeleniumParser() {
        //проставьте путь до selenium driver
        System.setProperty("webdriver.chrome.driver",
                "/Users/vitalijmonastyrev/Проекты/VkPeople/chromedriver");
        driver = new ChromeDriver();
    }

    void parse() {
        driver.get("https://www.vk.com");
        driver.findElement(By.id("index_email")).sendKeys("PHONE"); //логин и пароль проставить
        driver.findElement(By.id("index_pass")).sendKeys("PASS");
        driver.findElement(By.id("index_login_button")).click();
        driver.get("https://www.vk.com/id513142927");

        //Тут перебирайте все айдишники
        for (int i = 513142927; i <= 513142927; i++) {
            try {
                //GET PROFILE INFO
                driver.get("https://www.vk.com/id" + i);
                Thread.sleep(1000);
                System.out.println(driver.findElement(By.className("page_name")).getText());

                try {
                    System.out.println(driver.findElement(By.className("profile_info")).getText());
                } catch (Exception e) {

                }

                try {
                    driver.findElement(By.className("profile_more_info_link")).click();
                } catch (Exception e) {

                }

                try {
                    driver.findElement(By.id("profile_groups_link")).click();
                } catch (Exception e) {

                }
                try {
                    System.out.println(driver.findElement(By.className("profile_info_full")).getText());
                } catch (Exception e) {

                }

                //GET ALL FRIENDS
                driver.get("https://www.vk.com/friends?id=" + i + "&section=all");

                while (true) {
                    try {
                        driver.findElement(By.id("show_more")).click();
                    } catch (Exception exc) {
                        break;
                    }
                }


                try {
                    List<WebElement> webElements = driver.findElements(By.className("friends_field_title"));

                    for (WebElement we : webElements) {
                        List<WebElement> refList = we.findElements(By.tagName("a"));
                        for(WebElement we2 : refList) {
                            System.out.println(we2.getAttribute("href"));
                        }
                    }

                } catch (Exception e) {

                }


                driver.get("https://www.vk.com/friends?id=" + i + "&section=all");

                while (true) {
                    try {
                        driver.findElement(By.id("show_more")).click();
                    } catch (Exception exc) {
                        break;
                    }
                }

                //PAGES
                driver.get("https://m.vk.com/id" + i + "?act=idols");
                scrollToEnd(driver);
                try {
                    List<WebElement> webElements = driver.findElements(By.id("id_search_items"));

                    for (WebElement we : webElements) {
                        List<WebElement> refList = we.findElements(By.tagName("a"));
                        for (WebElement we2 : refList) {
                            System.out.println(we2.getAttribute("href"));
                        }
                    }

                } catch (Exception e) {

                }

                //GROUPS
                driver.get("https://m.vk.com/groups?id=" + i);
                scrollToEnd(driver);
                try {
                    List<WebElement> webElements = driver.findElements(By.id("gr_search_items"));

                    for (WebElement we : webElements) {
                        List<WebElement> refList = we.findElements(By.tagName("a"));
                        for (WebElement we2 : refList) {
                            System.out.println(we2.getAttribute("href"));
                        }
                    }

                } catch (Exception e) {

                }

            } catch (Exception e) {

            }
        }
    }

    public void scrollToEnd(WebDriver driver) {
        try {
            long lastHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(100);

                long newHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

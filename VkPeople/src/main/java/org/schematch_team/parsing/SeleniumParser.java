package org.schematch_team.parsing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        driver.findElement(By.id("index_email")).sendKeys("phone"); //логин и пароль проставить
        driver.findElement(By.id("index_pass")).sendKeys("pass");
        driver.findElement(By.id("index_login_button")).click();
        driver.get("https://www.vk.com/id513142927");

        //Тут перебирайте все айдишники
        for (int i = 513142927; i <= 513142927; i++) {
            try {
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

                try {
                    System.out.println(driver.findElement(By.id("profile_all_groups")).getText());
                } catch (Exception e) {

                }
            } catch (Exception e) {

            }
        }
    }
}

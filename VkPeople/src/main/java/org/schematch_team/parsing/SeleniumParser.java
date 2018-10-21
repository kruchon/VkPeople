package org.schematch_team.parsing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumParser {

    //https://chromedriver.storage.googleapis.com/index.html?path=2.43/
    //качайте отсюда selenium driver

    private WebDriver driver;

    public SeleniumParser(){
        //проставьте путь до selenium driver
        System.setProperty("webdriver.chrome.driver","C:\\111\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    void parse(){
        driver.get("https://www.vk.com");
        driver.findElement(By.id("index_email")).sendKeys("login"); //логин и пароль проставить
        driver.findElement(By.id("index_pass")).sendKeys("password");
        driver.findElement(By.id("index_login_button")).click();
        driver.get("https://www.vk.com/id10387980");

        //Тут перебирайте все айдишники
    }
}

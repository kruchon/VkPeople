package org.schematch_team.parsing;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class SeleniumParser {

    //https://chromedriver.storage.googleapis.com/index.html?path=2.43/
    //качайте отсюда selenium driver

    private WebDriver driver;

    public SeleniumParser() {
        //проставьте путь до selenium driver
        System.setProperty("webdriver.chrome.driver",
                "C:\\111\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    void parse() {
        driver.get("https://www.vk.com");
        driver.findElement(By.id("index_email")).sendKeys("ЛОГИН"); //логин и пароль проставить
        driver.findElement(By.id("index_pass")).sendKeys("ДА БЛИН ДА ТУТ НЕ ПАРОЛЬ ТУТ ПРОСТО БУКВЫ");
        driver.findElement(By.id("index_login_button")).click();

        //Тут перебирайте все айдишники
        for (int i = 513142927; i <= 513142927; i++) {

            try {
                //GET PROFILE INFO
                driver.get("https://www.vk.com/id" + i);
                Thread.sleep(1000);

                ProfileInfo profileInfo = new ProfileInfo();
                profileInfo.setId(String.valueOf(i));

                String name = driver.findElement(By.className("page_name")).getText();
                profileInfo.setName(name);

                try {
                    driver.findElement(By.className("profile_more_info_link")).click();
                } catch (Exception e) {

                }

                try {
                    driver.findElement(By.id("profile_groups_link")).click();
                } catch (Exception e) {

                }

                Collection<WebElement> infoContainers = driver.findElements(By.className("profile_info"));
                for (WebElement infoContainer : infoContainers) {
                    parseInfoContainer(infoContainer, profileInfo);
                }
                int x = 0;
               /* try {
                    String fullProhileInfo = driver.findElement(By.className("profile_info_full")).getText();
                    String[] lines = fullProhileInfo.split("\n");
                    for (int count = 0; count < lines.length; ++count) {
                        if (lines[count].equals("Родной город")){

                        }
                    }
                } catch (Exception e) {

                }*/

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
                    ArrayList<String> friends = new ArrayList<String>();
                    List<WebElement> webElements = driver.findElements(By.className("friends_field_title"));

                    for (WebElement we : webElements) {
                        List<WebElement> refList = we.findElements(By.tagName("a"));
                        for (WebElement we2 : refList) {
                            friends.add(we2.getAttribute("href"));
                        }
                    }
                    profileInfo.setFriends(friends);
                } catch (Exception e) {

                }

                //PAGES
                driver.get("https://m.vk.com/id" + i + "?act=idols");
                scrollToEnd(driver);
                try {
                    ArrayList<String> pages = new ArrayList<String>();
                    List<WebElement> webElements = driver.findElements(By.id("id_search_items"));

                    for (WebElement we : webElements) {
                        List<WebElement> refList = we.findElements(By.tagName("a"));
                        for (WebElement we2 : refList) {
                            pages.add(we2.getAttribute("href"));
                        }
                    }
                    profileInfo.setPages(pages);
                } catch (Exception e) {

                }

                //GROUPS
                driver.get("https://m.vk.com/groups?id=" + i);
                scrollToEnd(driver);
                try {
                    ArrayList<String> groups = new ArrayList<String>();
                    List<WebElement> webElements = driver.findElements(By.id("gr_search_items"));

                    for (WebElement we : webElements) {
                        List<WebElement> refList = we.findElements(By.tagName("a"));
                        for (WebElement we2 : refList) {
                            groups.add(we2.getAttribute("href"));
                        }
                    }
                    profileInfo.setGroups(groups);

                } catch (Exception e) {

                }
                System.out.println(profileInfo.toString());


            } catch (Exception e) {

            }
        }
    }

    private void parseInfoContainer(WebElement infoContainer, ProfileInfo profileInfo) {
        String lines[] = infoContainer.getText().split("\n");
        for (int count = 0; count < lines.length; ++count) {
            String line = lines[count];
            if (count == lines.length - 1){
                continue;
            }
            String value = lines[count + 1];
            if (line.equals("День рождения:")) {
                profileInfo.setBirthDate(lines[count + 1]);
            }
            if (line.equals("Город:")) {
                profileInfo.setCity(lines[count + 1]);
            }
            if (line.equals("Семейное положение:")) {
                profileInfo.setFamilyStatus(lines[count + 1]);
            }
            if (line.equals("Образование:")) {
                profileInfo.setMainEducation(lines[count + 1]);
            }
            if (line.equals("Веб-сайт:")) {
                profileInfo.setSite(lines[count + 1]);
            }
            if (line.equals("Веб-сайт:")) {
                profileInfo.setSite(lines[count + 1]);
            }
            if (line.equals("Полит. предпочтения:")) {
                profileInfo.setPoliticPreferences(lines[count + 1]);
            }
            if (line.equals("Мировоззрение:")){
                profileInfo.setWorldView(lines[count + 1]);
            }
            if (line.equals("Главное в жизни:")){
                profileInfo.setMainInLife(value);
            }
            if (line.equals("Главное в людях:")){
                profileInfo.setMainInPeople(value);
            }
            if (line.equals("Отн. к курению:")){
                profileInfo.setRelationToSmoke(value);
            }
            if (line.equals("Отн. к алкоголю:")){
                profileInfo.setRelationToDrink(value);
            }
            if (line.equals("Вдохновляют:")){
                profileInfo.setInspire(value);
            }
            if (line.equals("Деятельность:")){
                ArrayList<String> activities = Lists.newArrayList(value.split(","));
                profileInfo.setActivities(activities);
            }

            if (line.equals("Интересы:")){
                ArrayList<String> interests = Lists.newArrayList(value.split(","));
                profileInfo.setInterests(interests);
            }

            if (line.equals("Любимая музыка:")){
                ArrayList<String> favouriteMusic = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteMusic(favouriteMusic);
            }

            if (line.equals("Любимые фильмы:")){
                ArrayList<String> favouriteFilms = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteFilms(favouriteFilms);
            }

            if (line.equals("Любимые телешоу:")){
                ArrayList<String> favouriteShows = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteShows(favouriteShows);
            }

            if (line.equals("Любимые книги:")){
                ArrayList<String> favouriteBooks = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteBooks(favouriteBooks);
            }

            if (line.equals("Люимые игры:")){
                ArrayList<String> favouriteGames = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteGames(favouriteGames);
            }

            final Set<String> brothersSistersColumn = Sets.newHashSet("Сестры:","Братья:","Сестра:", "Брат:", "Братья, сестры:");
            if (brothersSistersColumn.contains(line)) {
                ArrayList<String> brothersSisters = Lists.newArrayList(lines[count + 1].split(","));
                profileInfo.setBrothersSisters(brothersSisters);
            }

            final Set<String> grandPaAndMaColumn = Sets.newHashSet("Дедушка:", "Бабушка:","Дедушки:","Бабушки:", "Дедушки, бабушки:");
            if (grandPaAndMaColumn.contains(line)) {
                ArrayList<String> grandPaAndMa = Lists.newArrayList(lines[count + 1].split(","));
                profileInfo.setGrandPaAndMa(grandPaAndMa);
            }

            if (line.equals("Место работы:")) {
                ArrayList<String> workplaces = profileInfo.getWorkplace();
                String newWorkplace = lines[count + 1];
                if (workplaces == null){
                    profileInfo.setWorkplace(Lists.newArrayList(newWorkplace));
                } else {
                    workplaces.add(newWorkplace);
                    profileInfo.setWorkplace(workplaces);
                }
            }

            if (line.equals("Вуз:")) {
                ArrayList<String> institutes = profileInfo.getInstitutes();
                String newInstitute = lines[count + 1];
                if (institutes == null){
                    profileInfo.setInstitutes(Lists.newArrayList(newInstitute));
                } else {
                    institutes.add(newInstitute);
                    profileInfo.setInstitutes(institutes);
                }
            }

            if (line.equals("Факультет:")) {
                ArrayList<String> faculties = profileInfo.getFaculties();
                String newFaculty = lines[count + 1];
                if (faculties == null){
                    profileInfo.setWorkplace(Lists.newArrayList(newFaculty));
                } else {
                    faculties.add(newFaculty);
                    profileInfo.setFaculties(faculties);
                }
            }

            if (line.equals("Кафедра/направление:")){
                ArrayList<String> instituteDepartments = profileInfo.getIntituteDepartments();
                String newInstituteDepartment = lines[count + 1];
                if (instituteDepartments == null){
                    profileInfo.setWorkplace(Lists.newArrayList(newInstituteDepartment));
                } else {
                    instituteDepartments.add(newInstituteDepartment);
                    profileInfo.setIntituteDepartments(instituteDepartments);
                }
            }

            if (line.equals("Школа:")){
                ArrayList<String> schools = profileInfo.getSchools();
                String newSchool = lines[count + 1];
                if (schools == null){
                    profileInfo.setWorkplace(Lists.newArrayList(newSchool));
                } else {
                    schools.add(newSchool);
                    profileInfo.setSchools(schools);
                }
            }

            ++count;
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

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
                "/Users/vitalijmonastyrev/Проекты/VkPeople/chromedriver");
        driver = new ChromeDriver();
    }

    void parse() {
        driver.get("https://www.vk.com");
        driver.findElement(By.id("index_email")).sendKeys("phone"); //логин и пароль проставить
        driver.findElement(By.id("index_pass")).sendKeys("pass");
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
                    profileInfo.setStatus(driver.findElement(By.className("my_current_info")).getText());
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

                parseInfoContainerShort(driver.findElement(By.className("profile_info_short")), profileInfo);
                parseInfoContainerFull(driver.findElement(By.className("profile_info_full")), profileInfo);

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

    private void parseInfoContainerShort(WebElement infoContainer, ProfileInfo profileInfo) {
        String lines[] = infoContainer.getText().split("\n");

        for (int count = 0; count < lines.length; ++count) {
            String line = lines[count];
            if (count == lines.length - 1) {
                continue;
            }
            String value = lines[count + 1];
            if (line.equals("День рождения:")) {
                profileInfo.setBirthDate(value);
                ++count;
            }
            if (line.equals("Город:")) {
                profileInfo.setCity(value);
                ++count;
            }
            if (line.equals("Семейное положение:")) {
                profileInfo.setFamilyStatus(value);
                ++count;
            }
            if (line.equals("Веб-сайт:")) {
                profileInfo.setSite(value);
                ++count;
            }
        }
    }

    private void parseInfoContainerFull(WebElement infoContainer, ProfileInfo profileInfo) {
        String lines[] = infoContainer.getText().split("\n");
        List<WebElement> refList = infoContainer.findElements(By.tagName("a"));

        for (int count = 0; count < lines.length; ++count) {
            String line = lines[count];
            if (count == lines.length - 1) {
                continue;
            }
            String value = lines[count + 1];

            if (line.equals("Полит. предпочтения:")) {
                profileInfo.setPoliticPreferences(value);
                ++count;
            }
            if (line.equals("Мировоззрение:")) {
                profileInfo.setWorldView(value);
                ++count;
            }
            if (line.equals("Главное в жизни:")) {
                profileInfo.setMainInLife(value);
                ++count;
            }
            if (line.equals("Главное в людях:")) {
                profileInfo.setMainInPeople(value);
                ++count;
            }
            if (line.equals("Отн. к курению:")) {
                profileInfo.setRelationToSmoke(value);
                ++count;
            }
            if (line.equals("Отн. к алкоголю:")) {
                profileInfo.setRelationToDrink(value);
                ++count;
            }
            if (line.equals("Вдохновляют:")) {
                profileInfo.setInspire(value);
                ++count;
            }
            if (line.equals("Деятельность:")) {
                ArrayList<String> activities = Lists.newArrayList(value.split(","));
                profileInfo.setActivities(activities);
                ++count;
            }

            if (line.equals("Интересы:")) {
                ArrayList<String> interests = Lists.newArrayList(value.split(","));
                profileInfo.setInterests(interests);
                ++count;
            }

            if (line.equals("Любимая музыка:")) {
                ArrayList<String> favouriteMusic = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteMusic(favouriteMusic);
                ++count;
            }

            if (line.equals("Любимые фильмы:")) {
                ArrayList<String> favouriteFilms = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteFilms(favouriteFilms);
                ++count;
            }

            if (line.equals("Любимые телешоу:")) {
                ArrayList<String> favouriteShows = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteShows(favouriteShows);
                ++count;
            }

            if (line.equals("Любимые книги:")) {
                ArrayList<String> favouriteBooks = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteBooks(favouriteBooks);
                ++count;
            }

            if (line.equals("Любимые игры:")) {
                ArrayList<String> favouriteGames = Lists.newArrayList(value.split(","));
                profileInfo.setFavouriteGames(favouriteGames);
                ++count;
            }

            final Set<String>
                    brothersSistersColumn =
                    Sets.newHashSet("Сестры:", "Братья:", "Сестра:", "Брат:", "Братья, сестры:");
            if (brothersSistersColumn.contains(line)) {
                ArrayList<String> brothersSisters = Lists.newArrayList(value.split(","));
                profileInfo.setBrothersSisters(brothersSisters);
                ++count;
            }

            final Set<String>
                    grandPaAndMaColumn =
                    Sets.newHashSet("Дедушка:", "Бабушка:", "Дедушки:", "Бабушки:",
                            "Дедушки, бабушки:");
            if (grandPaAndMaColumn.contains(line)) {
                ArrayList<String> grandPaAndMa = Lists.newArrayList(value.split(","));
                profileInfo.setGrandPaAndMa(grandPaAndMa);
                ++count;
            }

            if (line.equals("Место работы:")) {
                ArrayList<String> workplaces = profileInfo.getWorkplace();
                String newWorkplace = value;
                if (workplaces == null) {
                    profileInfo.setWorkplace(Lists.newArrayList(newWorkplace));
                } else {
                    workplaces.add(newWorkplace);
                    profileInfo.setWorkplace(workplaces);
                }
                ++count;
            }

            if (line.equals("Вуз:")) {
                ArrayList<String> institutes = profileInfo.getInstitutes();
                String newInstitute = value;
                if (institutes == null) {
                    profileInfo.setInstitutes(Lists.newArrayList(newInstitute));
                } else {
                    institutes.add(newInstitute);
                    profileInfo.setInstitutes(institutes);
                }

                //index out of bound exception!
                int addCounter = 2;
                try {
                    if (lines[count + addCounter].equals("Факультет:")) {
                        ArrayList<String> faculties = profileInfo.getFaculties();
                        String newFaculty = lines[count + addCounter + 1];
                        if (faculties == null) {
                            profileInfo.setFaculties(Lists.newArrayList(newFaculty));
                        } else {
                            faculties.add(newFaculty);
                            profileInfo.setFaculties(faculties);
                        }
                        addCounter += 2;
                    } else {
                        ArrayList<String> faculties = profileInfo.getFaculties();
                        String newFaculty = "noneSpecialRowFac75747547";
                        if (faculties == null) {
                            profileInfo.setFaculties(Lists.newArrayList(newFaculty));
                        } else {
                            faculties.add(newFaculty);
                            profileInfo.setFaculties(faculties);
                        }
                    }

                    if (lines[count + addCounter].equals("Кафедра/направление:")) {
                        ArrayList<String> instituteDepartments = profileInfo.getInstituteDepartments();
                        String newInstituteDepartment = lines[count + addCounter + 1];
                        if (instituteDepartments == null) {
                            profileInfo.setInstituteDepartments(Lists.newArrayList(newInstituteDepartment));
                        } else {
                            instituteDepartments.add(newInstituteDepartment);
                            profileInfo.setInstituteDepartments(instituteDepartments);
                        }
                        addCounter += 2;
                    } else {
                        ArrayList<String> instituteDepartments = profileInfo.getFaculties();
                        String newInstituteDepartment = "noneSpecialRowDep75747547";
                        if (instituteDepartments == null) {
                            profileInfo.setInstituteDepartments(Lists.newArrayList(newInstituteDepartment));
                        } else {
                            instituteDepartments.add(newInstituteDepartment);
                            profileInfo.setInstituteDepartments(instituteDepartments);
                        }
                    }

                    if (lines[count + addCounter].equals("Форма обучения:")) {
                        ArrayList<String> instituteForm = profileInfo.getInstituteForm();
                        String newInstituteForm = lines[count + addCounter + 1];
                        if (instituteForm == null) {
                            profileInfo.setInstituteForm(Lists.newArrayList(newInstituteForm));
                        } else {
                            instituteForm.add(newInstituteForm);
                            profileInfo.setInstituteForm(instituteForm);
                        }
                        addCounter += 2;
                    } else {
                        ArrayList<String> instituteDepartments = profileInfo.getInstituteForm();
                        String newInstituteForm = "noneSpecialRowForm75747547";
                        if (instituteDepartments == null) {
                            profileInfo.setInstituteForm(Lists.newArrayList(newInstituteForm));
                        } else {
                            instituteDepartments.add(newInstituteForm);
                            profileInfo.setInstituteForm(instituteDepartments);
                        }
                    }

                    if (lines[count + addCounter].equals("Статус:")) {
                        ArrayList<String> instituteStatus = profileInfo.getInstituteStatus();
                        String newInstituteStatus = lines[count + addCounter + 1];
                        if (instituteStatus == null) {
                            profileInfo.setInstituteStatus(Lists.newArrayList(newInstituteStatus));
                        } else {
                            instituteStatus.add(newInstituteStatus);
                            profileInfo.setInstituteStatus(instituteStatus);
                        }
                        addCounter += 2;
                    } else {
                        ArrayList<String> instituteStatus = profileInfo.getInstituteStatus();
                        String newInstituteStatus = "noneSpecialRowStatus75747547";
                        if (instituteStatus == null) {
                            profileInfo.setInstituteStatus(Lists.newArrayList(newInstituteStatus));
                        } else {
                            instituteStatus.add(newInstituteStatus);
                            profileInfo.setInstituteStatus(instituteStatus);
                        }
                    }

                } catch (Exception exc) {

                }
                count = count + addCounter - 1;
            }

            if (line.equals("Школа:")) {
                ArrayList<String> schools = profileInfo.getSchools();
                String newSchool = value;
                if (schools == null) {
                    profileInfo.setSchools(Lists.newArrayList(newSchool));
                } else {
                    schools.add(newSchool);
                    profileInfo.setSchools(schools);
                }
                ++count;
            }

            if (line.equals("Моб. телефон:")) {
                profileInfo.setMobilePhone(value);
                ++count;
            }

            if (line.equals("Доп. телефон:")) {
                profileInfo.setSecondPhone(value);
                ++count;
            }

            if (line.equals("Skype:")) {
                profileInfo.setSkype(value);
                ++count;
            }

            if (line.equals("Instagram:")) {
                for (WebElement we : refList) {
                    if (we.getAttribute("href").contains("instagram")) {
                        profileInfo.setInstagram(we.getAttribute("href"));
                    }
                }
                ++count;
            }

            if (line.equals("Twitter:")) {
                for (WebElement we : refList) {
                    if (we.getAttribute("href").contains("twitter")) {
                        profileInfo.setTwitter(we.getAttribute("href"));
                    }
                }
                ++count;
            }

            if (line.equals("Facebook:")) {
                for (WebElement we : refList) {
                    if (we.getAttribute("href").contains("facebook")) {
                        profileInfo.setFacebook(we.getAttribute("href"));
                    }
                }
                ++count;
            }

            if (line.equals("Войсковая часть:")) {
                ArrayList<String> militaryUnit = profileInfo.getMilitaryUnit();
                String newMilitaryUnit = lines[count + 1];
                if (militaryUnit == null) {
                    profileInfo.setMilitaryUnit(Lists.newArrayList(newMilitaryUnit));
                } else {
                    militaryUnit.add(newMilitaryUnit);
                    profileInfo.setMilitaryUnit(militaryUnit);
                }
                ++count;
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

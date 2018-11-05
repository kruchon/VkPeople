package org.schematch_team.parsing;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;
import java.util.*;

/**
 * Парсер для получения датасета с ВК
 */

public class SeleniumParser {
	
    private final WebDriver driver;
    
    private final String path;
    private final String email;
    private final String password;
  
    private final String empty = "";

    public SeleniumParser(String path, String email, String password) {
    	this.path = path;
        this.email = email;
        this.password = password;
        System.setProperty("webdriver.chrome.driver", this.path);
        this.driver = new ChromeDriver();
    }

    /**
     * Постранично выгружает данные со страниц ВК.
     * 
     * @param firstID - ID страницы с которой начинаем выгрузку. 
     * @param lastID - ID последней выгруженной страницы.
     */
    public void parse(long firstID, long lastID) {
    	
    	ConnectionJDBC db = null;
    	try {
    		db = new ConnectionJDBC();
		} catch (ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
		}

    	loginVK(email, password);

    	Date start = null, finish = null;
        for (long id = firstID; id <= lastID; id++) {
        	start = new Date();
        	
        	openPage(id);
        	
        	ProfileInfo profileInfo = new ProfileInfo();
      	
        	profileInfo.setId(String.valueOf(id));
        	getBlockTitle(profileInfo);
        	getBlockBody(profileInfo);
        	getBlockMain(profileInfo);
        	getFriends(profileInfo, id);
        	getFavoritePages(profileInfo, id);
        	getGroups(profileInfo, id);

        	fillEmpty(profileInfo);
        	System.out.println(profileInfo.toString());
        	try {
				db.put(profileInfo);
			} catch (Throwable e) {
				e.printStackTrace();
			}
        	
        	finish = new Date();
        	long delta = finish.getTime() - start.getTime();
        	if (delta < 1000) {
        		Random rnd = new Random(System.currentTimeMillis());
        		try {
					Thread.sleep((1000 - delta) + rnd.nextInt(200));
				} catch (InterruptedException e) {}
        	}
        }
    }

    private void loginVK(String email, String password) {
    	driver.get("https://www.vk.com");
        driver.findElement(By.id("index_email")).sendKeys(email);
        driver.findElement(By.id("index_pass")).sendKeys(password);
        driver.findElement(By.id("index_login_button")).click();
    }
    
    private void openPage(long id) {
    	driver.get("https://www.vk.com/id" + id);
   	 try {
            driver.findElement(By.className("profile_more_info_link")).click();
        } catch (Exception e) {}
    }
    
    private void getBlockTitle(ProfileInfo profileInfo) {
    	try {
    		profileInfo.setName(driver.findElement(By.className("page_name")).getText());
    	} catch (Exception e) {}
    	try {
            profileInfo.setStatus(driver.findElement(By.className("my_current_info")).getText());
        } catch (Exception e) {}
    }
    
    private void getBlockBody(ProfileInfo profileInfo) {
    	try {
    		WebElement infoContainer = driver.findElement(By.className("profile_info_short"));
        	List<String> lines = Lists.newArrayList(infoContainer.getText().split("\n"));
        	lines.remove("Скрыть подробную информацию");
        	for (int i = 0; i != lines.size()/2; i++) {
        		switch (lines.get(i*2)) {
        			case "День рождения:" :
        				profileInfo.setBirthDate(lines.get(i*2 + 1));
        				break;
        			case "Город:" :
        				profileInfo.setCity(lines.get(i*2 + 1));
        				break;
        			case "Семейное положение:" :
        				profileInfo.setFamilyStatus(lines.get(i*2 + 1));
        				break;
        			case "Веб-сайт:" :
        				profileInfo.setSite(lines.get(i*2 + 1));
        				break;
        			default:
        				break;
        		}
        	}
    	} catch (Exception e) {}
    }
    
    private void getBlockMain(ProfileInfo profileInfo) {
    	try {
    		WebElement infoContainer = driver.findElement(By.className("profile_info_full"));
    		List<WebElement> refList = infoContainer.findElements(By.tagName("a"));
    		List<String> lines = Lists.newArrayList(infoContainer.getText().split("\n"));
        	lines.remove("Основная информация");
        	lines.remove("Контактная информация");
        	lines.remove("Карьера");
        	lines.remove("Военная служба");
        	lines.remove("Жизненная позиция");
        	lines.remove("Личная информация");
        	for (int i = 0; i != lines.size()/2; i++) {
        		switch (lines.get(i*2)) {
        			case "Родной город:" :
        				profileInfo.setHomeCity(lines.get(i*2 + 1));
        				break;
        			case "Языки:" :
        				profileInfo.setLanguages(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
        				break;
        			case "Дедушка:" :
        			case "Бабушка:" :
        			case "Бабушки:" :
        			case "Дедушки:" :
        			case "Дедушки, бабушки:" :
        				profileInfo.setGrandPaAndMa(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Родители:" :
        			case "Отец:" :
        			case "Мать:" :
        				profileInfo.setParents(Lists.newArrayList(lines.get(i*2 + 1).split(" и ")));
    					break;
        			case "Сестры:" :
        			case "Братья:" :
        			case "Сестра:" :
        			case "Брат:" :
        			case "Братья, сестры:" :
        				profileInfo.setBrothersSisters(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
        				break;
        			case "Дети:" :
        				ArrayList<String> children = new ArrayList<>();
        				children.add(lines.get(i*2 + 1));
        				while (!lines.get(i*2 + 1 + 1).contains(":")) {
        					children.add(lines.get(i*2 + 1 + 1));
        					lines.remove(lines.get(i*2 + 1 + 1));
        				}
        				profileInfo.setChildren(children);
        				break;
        			case "Внуки:" :
        				ArrayList<String> grandChildren = new ArrayList<>();
        				grandChildren.add(lines.get(i*2 + 1));
        				while (!lines.get(i*2 + 1 + 1).contains(":")) {
        					grandChildren.add(lines.get(i*2 + 1 + 1));
        					lines.remove(lines.get(i*2 + 1 + 1));
        				}
        				profileInfo.setGrandChildren(grandChildren);
        				break;
        			case "Моб. телефон:" :
        				profileInfo.setMobilePhone(lines.get(i*2 + 1));
        				break;
        			case "Доп. телефон:" :
        				profileInfo.setSecondPhone(lines.get(i*2 + 1));
        				break;
        			case "Instagram:" :
        				for (WebElement we : refList) {
		                    if (we.getAttribute("href").contains("instagram")) {
		                        profileInfo.setInstagram(we.getAttribute("href"));
		                    }
		                }
        				break;
        			case "Twitter:" :
        				for (WebElement we : refList) {
		                    if (we.getAttribute("href").contains("twitter")) {
		                        profileInfo.setTwitter(we.getAttribute("href"));
		                    }
		                }
        				break;
        			case "Facebook:" :
        				for (WebElement we : refList) {
		                    if (we.getAttribute("href").contains("facebook")) {
		                        profileInfo.setFacebook(we.getAttribute("href"));
		                    }
		                }
        				break;
        			case "Место работы:" :
        				String workplace = lines.get(i*2 + 1) + "; ";
        				while (!lines.get(i*2 + 1 + 1).contains(":")) {
        					workplace += lines.get(i*2 + 1 + 1) + "; ";
        					lines.remove(lines.get(i*2 + 1 + 1));
        				}
        				workplace = workplace.substring(0, workplace.lastIndexOf(";"));
        				if ((profileInfo.getWorkplace() != null) && (!profileInfo.getWorkplace().isEmpty())) {
        					profileInfo.getWorkplace().add(workplace);
        				} else {
        					profileInfo.setWorkplace(Lists.newArrayList(workplace));
        				}
        				break;
        			case "Вуз:" :
        				if ((profileInfo.getInstitutes() != null) && (!profileInfo.getInstitutes().isEmpty())) {
            				profileInfo.getInstitutes().add(lines.get(i*2 + 1));
            			} else {
            				profileInfo.setInstitutes(Lists.newArrayList(lines.get(i*2 + 1)));
            			}
        				
        				if (lines.get((i+1)*2).equals("Факультет:")) {
        					i++;
        					if ((profileInfo.getFaculties() != null) && (!profileInfo.getFaculties().isEmpty())) {
                				profileInfo.getFaculties().add(lines.get(i*2 + 1));
                			} else {
                				profileInfo.setFaculties(Lists.newArrayList(lines.get(i*2 + 1)));
                			}
        				} else {
        					if ((profileInfo.getFaculties() != null) && (!profileInfo.getFaculties().isEmpty())) {
                				profileInfo.getFaculties().add(empty);
                			} else {
                				profileInfo.setFaculties(Lists.newArrayList(empty));
                			}
        				}
        				
        				if (lines.get((i+1)*2).equals("Кафедра/направление:")) {
        					i++;
        					if ((profileInfo.getInstituteDepartments() != null) && (!profileInfo.getInstituteDepartments().isEmpty())) {
                				profileInfo.getInstituteDepartments().add(lines.get(i*2 + 1));
                			} else {
                				profileInfo.setInstituteDepartments(Lists.newArrayList(lines.get(i*2 + 1)));
                			}
        				} else {
        					if ((profileInfo.getInstituteDepartments() != null) && (!profileInfo.getInstituteDepartments().isEmpty())) {
                				profileInfo.getInstituteDepartments().add(empty);
                			} else {
                				profileInfo.setInstituteDepartments(Lists.newArrayList(empty));
                			}
        				}
        				
        				if (lines.get((i+1)*2).equals("Форма обучения:")) {
        					i++;
        					if ((profileInfo.getInstituteForm() != null) && (!profileInfo.getInstituteForm().isEmpty())) {
                				profileInfo.getInstituteForm().add(lines.get(i*2 + 1));
                			} else {
                				profileInfo.setInstituteForm(Lists.newArrayList(lines.get(i*2 + 1)));
                			}
        				} else {
        					if ((profileInfo.getInstituteForm() != null) && (!profileInfo.getInstituteForm().isEmpty())) {
                				profileInfo.getInstituteForm().add(empty);
                			} else {
                				profileInfo.setInstituteForm(Lists.newArrayList(empty));
                			}
        				}
        				
        				if (lines.get((i+1)*2).equals("Статус:")) {
        					i++;
        					if ((profileInfo.getInstituteStatus() != null) && (!profileInfo.getInstituteStatus().isEmpty())) {
                				profileInfo.getInstituteStatus().add(lines.get(i*2 + 1));
                			} else {
                				profileInfo.setInstituteStatus(Lists.newArrayList(lines.get(i*2 + 1)));
                			}
        				} else {
        					if ((profileInfo.getInstituteStatus() != null) && (!profileInfo.getInstituteStatus().isEmpty())) {
                				profileInfo.getInstituteStatus().add(empty);
                			} else {
                				profileInfo.setInstituteStatus(Lists.newArrayList(empty));
                			}
        				}
        				break;
        			case "Школа:" :
        				String school = lines.get(i*2 + 1) + "; ";
        				while (!lines.get(i*2 + 1 + 1).contains(":")) {
        					school += lines.get(i*2 + 1 + 1) + "; ";
        					lines.remove(lines.get(i*2 + 1 + 1));
        				}
        				school = school.substring(0, school.lastIndexOf(";"));
        				if ((profileInfo.getSchools() != null) && (!profileInfo.getSchools().isEmpty())) {
        					profileInfo.getSchools().add(school);
        				} else {
        					profileInfo.setSchools(Lists.newArrayList(school));
        				}
        				break;
        			case "Войсковая часть:" :
        				String military = lines.get(i*2 + 1) + "; ";
        				while (!lines.get(i*2 + 1 + 1).contains(":")) {
        					military += lines.get(i*2 + 1 + 1) + "; ";
        					lines.remove(lines.get(i*2 + 1 + 1));
        				}
        				military = military.substring(0, military.lastIndexOf(";"));
        				if ((profileInfo.getMilitary() != null) && (!profileInfo.getMilitary().isEmpty())) {
        					profileInfo.getMilitary().add(military);
        				} else {
        					profileInfo.setMilitary(Lists.newArrayList(military));
        				}
        				break;
        			case "Полит. предпочтения:" :
        				profileInfo.setPoliticPreferences(lines.get(i*2 + 1));
    					break;
        			case "Мировоззрение:" :
        				profileInfo.setWorldView(lines.get(i*2 + 1));
    					break;
        			case "Главное в жизни:" :
        				profileInfo.setMainInLife(lines.get(i*2 + 1));
    					break;
        			case "Главное в людях:" :
        				profileInfo.setMainInPeople(lines.get(i*2 + 1));
    					break;
        			case "Отн. к курению:" :
        				profileInfo.setRelationToSmoke(lines.get(i*2 + 1));
    					break;
        			case "Отн. к алкоголю:" :
        				profileInfo.setRelationToDrink(lines.get(i*2 + 1));
    					break;
        			case "Вдохновляют:" :
        				profileInfo.setInspire(lines.get(i*2 + 1));
    					break;
        			case "Деятельность:" :
        				profileInfo.setActivities(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Интересы:" :
        				profileInfo.setInterests(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Любимая музыка:" :
        				profileInfo.setFavouriteMusic(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Любимые фильмы:" :
        				profileInfo.setFavouriteFilms(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Любимые телешоу:" :
        				profileInfo.setFavouriteShows(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Любимые книги:" :
        				profileInfo.setFavouriteBooks(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Любимые игры:" :
        				profileInfo.setFavouriteGames(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "Любимые цитаты:" :
        				profileInfo.setFavouriteQuotes(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			case "О себе:" :
        				profileInfo.setAboutMe(Lists.newArrayList(lines.get(i*2 + 1).split(",")));
    					break;
        			default:
        				break;
        		}
        	}
    	} catch (Exception e) {}
    }

    private void getFriends(ProfileInfo profileInfo, long id) {
    	driver.get("https://www.vk.com/friends?id=" + id + "&section=all");
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
        } catch (Exception e) {}
    }
    
    private void getFavoritePages(ProfileInfo profileInfo, long id) {
    	 driver.get("https://m.vk.com/id" + id + "?act=idols");
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
         } catch (Exception e) {}
    }
    
    private void getGroups(ProfileInfo profileInfo, long id) {
    	driver.get("https://m.vk.com/groups?id=" + id);
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
        } catch (Exception e) {}
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

	public void fillEmpty(ProfileInfo profileInfo) {
    	if (profileInfo.getName() == null) {
    		profileInfo.setName(empty);
		}
		if (profileInfo.getStatus() == null) {
    		profileInfo.setStatus(empty);
		}
		if (profileInfo.getFamilyStatus() == null) {
    		profileInfo.setFamilyStatus(empty);
		}
		if (profileInfo.getSite() == null) {
    		profileInfo.setSite(empty);
		}
		if (profileInfo.getBirthDate() == null) {
    		profileInfo.setBirthDate(empty);
		}
		if (profileInfo.getCity() == null) {
    		profileInfo.setCity(empty);
		}
		if (profileInfo.getFriends() == null) {
    		profileInfo.setFriends(Lists.newArrayList(empty));
		}
		if (profileInfo.getGroups() == null) {
    		profileInfo.setGroups(Lists.newArrayList(empty));
		}
		if (profileInfo.getPages() == null) {
    		profileInfo.setPages(Lists.newArrayList(empty));
		}
		if (profileInfo.getHomeCity() == null) {
    		profileInfo.setHomeCity(empty);
		}
		if (profileInfo.getBrothersSisters() == null) {
    		profileInfo.setBrothersSisters(Lists.newArrayList(empty));
		}
		if (profileInfo.getLanguages() == null) {
    		profileInfo.setLanguages(Lists.newArrayList(empty));
		}
		if (profileInfo.getGrandPaAndMa() == null) {
    		profileInfo.setGrandPaAndMa(Lists.newArrayList(empty));
		}
		if (profileInfo.getChildren() == null) {
    		profileInfo.setChildren(Lists.newArrayList(empty));
		}
		if (profileInfo.getParents() == null) {
    		profileInfo.setParents(Lists.newArrayList(empty));
		}
		if (profileInfo.getWorkplace() == null) {
    		profileInfo.setWorkplace(Lists.newArrayList(empty));
		}
		if (profileInfo.getInstitutes() == null) {
    		profileInfo.setInstitutes(Lists.newArrayList(empty));
		}
		if (profileInfo.getFaculties() == null) {
			profileInfo.setFaculties(Lists.newArrayList(empty));
		}
		if (profileInfo.getInstituteDepartments() == null) {
    		profileInfo.setInstituteDepartments(Lists.newArrayList(empty));
		}
		if (profileInfo.getInstituteForm() == null) {
    		profileInfo.setInstituteForm(Lists.newArrayList(empty));
		}
		if (profileInfo.getInstituteStatus() == null) {
    		profileInfo.setInstituteStatus(Lists.newArrayList(empty));
		}
		if (profileInfo.getMilitary() == null) {
    		profileInfo.setMilitary(Lists.newArrayList(empty));
		}
		if (profileInfo.getSchools() == null) {
    		profileInfo.setSchools(Lists.newArrayList(empty));
		}
		if (profileInfo.getPoliticPreferences() == null) {
    		profileInfo.setPoliticPreferences(empty);
		}
		if (profileInfo.getWorldView() == null) {
    		profileInfo.setWorldView(empty);
		}
		if (profileInfo.getMainInLife() == null) {
    		profileInfo.setMainInLife(empty);
		}
		if (profileInfo.getMainInPeople() == null) {
    		profileInfo.setMainInPeople(empty);
		}
		if (profileInfo.getRelationToSmoke() == null) {
    		profileInfo.setRelationToSmoke(empty);
		}
		if (profileInfo.getRelationToDrink() == null) {
    		profileInfo.setRelationToDrink(empty);
		}
		if (profileInfo.getInspire() == null) {
    		profileInfo.setInspire(empty);
		}
		if (profileInfo.getActivities() == null) {
    		profileInfo.setActivities(Lists.newArrayList(empty));
		}
		if (profileInfo.getInterests() == null) {
    		profileInfo.setInterests(Lists.newArrayList(empty));
		}
		if (profileInfo.getFavouriteMusic() == null) {
    		profileInfo.setFavouriteMusic(Lists.newArrayList(empty));
		}
		if (profileInfo.getFavouriteFilms() == null) {
    		profileInfo.setFavouriteFilms(Lists.newArrayList(empty));
		}
		if (profileInfo.getFavouriteShows() == null) {
    		profileInfo.setFavouriteShows(Lists.newArrayList(empty));
		}
		if (profileInfo.getFavouriteBooks() == null) {
    		profileInfo.setFavouriteBooks(Lists.newArrayList(empty));
		}
		if (profileInfo.getFavouriteGames() == null) {
    		profileInfo.setFavouriteGames(Lists.newArrayList(empty));
		}
		if (profileInfo.getGrandChildren() == null) {
    		profileInfo.setGrandChildren(Lists.newArrayList(empty));
		}
		if (profileInfo.getMobilePhone() == null) {
    		profileInfo.setMobilePhone(empty);
		}
		if (profileInfo.getSecondPhone() == null) {
    		profileInfo.setSecondPhone(empty);
		}
		if (profileInfo.getSkype() == null) {
    		profileInfo.setSkype(empty);
		}
		if (profileInfo.getInstagram() == null) {
    		profileInfo.setInstagram(empty);
		}
		if (profileInfo.getTwitter() == null) {
    		profileInfo.setTwitter(empty);
		}
		if (profileInfo.getFacebook() == null) {
    		profileInfo.setFacebook(empty);
		}
		if (profileInfo.getMilitaryUnit() == null) {
    		profileInfo.setMilitaryUnit(Lists.newArrayList(empty));
		}
		if (profileInfo.getFavouriteQuotes() == null) {
    		profileInfo.setFavouriteQuotes(Lists.newArrayList(empty));
		}
		if (profileInfo.getAboutMe() == null) {
    		profileInfo.setAboutMe(Lists.newArrayList(empty));
		}
	}
}

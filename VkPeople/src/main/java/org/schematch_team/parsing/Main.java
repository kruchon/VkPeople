package org.schematch_team.parsing;

public class Main {
	
	/**
	 * Отсюда подгружаем SeleniumDriver : https://chromedriver.storage.googleapis.com/index.html?path=2.43/
	 * 
	 * Данные для входа в ВК ставим в email и password соответственно.
	 * Полный путь для SeleniumDriver указываем в path.
	 * В firstID и lastID указываем диапазон выгружаемых страниц.
	 */
	
    private static final String path = "D:\\WS\\chromedriver_win32\\chromedriver.exe";
    
    private static final String email = "77478422345";
    private static final String password = "hgjnjhy84;H7hb";
    
    private static final long firstID = 513142927;
    private static final long lastID = 513142927;
	
    public static void main(String[] args){
        SeleniumParser seleniumParser = new SeleniumParser(path, email, password);
        seleniumParser.parse(firstID,lastID);
    }
}

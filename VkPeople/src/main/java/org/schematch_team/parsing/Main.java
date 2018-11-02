package org.schematch_team.parsing;

import java.sql.SQLException;

public class Main {
	
	/**
	 * Отсюда подгружаем SeleniumDriver : https://chromedriver.storage.googleapis.com/index.html?path=2.43/
	 * 
	 * Данные для входа в ВК ставим в email и password соответственно.
	 * Полный путь для SeleniumDriver указываем в path.
	 * В firstID и lastID указываем диапазон выгружаемых страниц.
	 */
	
    private static final String path = "/opt/chromedriver";
    
    private static final String email = "phone";
    private static final String password = "pass";
    
    //513142927
    private static final long firstID = 513142927;
    private static final long lastID = 513142927;
	
    public static void main(String[] args){
        SeleniumParser seleniumParser = new SeleniumParser(path, email, password);
        seleniumParser.parse(firstID,lastID);
    }
}

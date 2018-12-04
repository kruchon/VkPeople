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
    
    private static final String email = "77478422345";
    private static final String password = "hgvgU438JNjer;";
    
    private static final long firstID = 158_570;
    private static final long lastID = 300_000;
	
    public static void main(String[] args){
    	
        //SeleniumParser seleniumParser = new SeleniumParser(path, email, password);
        //seleniumParser.parse(firstID,lastID);
    	ConnectionJDBC c;
		try {
		
			
			
			c = new ConnectionJDBC();
			System.out.println(c.getFriendPairs(100000).size());
    	
    	
    	
    	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	
}

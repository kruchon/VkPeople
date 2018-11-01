package org.schematch_team.parsing;

import java.sql.*;

public class ConnectionJDBC {
	
	private final Connection connection;
	
	public ConnectionJDBC() throws ClassNotFoundException, SQLException  {
		    Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
			connection =  DriverManager.getConnection("jdbc:phoenix:localhost:2181/hbase");
	}
	
	public void put(ProfileInfo profileInfo) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(
				"UPSERT INTO VKPEOPLE(" +
					"id, " + 
					"name, " + 
					"status, " + 
					"birthDate, " + 
					"city, " + 
					"familyStatus, " + 
					"site, " + 
					"homeCity, " + 
					"languages, " + 
					"grandPaAndMa, " + 
					"parents, " + 
					"brothersSisters, " + 
					"children, " + 
					"grandChildren, " + 
					"mobilePhone, " + 
					"secondPhone, " + 
					"instagram, " + 
					"twitter, " + 
					"facebook, " + 
					"workplace, " + 
					"institutes, " + 
					"faculties, " + 
					"instituteDepartments, " + 
					"instituteForm, " + 
					"instituteStatus, " + 
					"schools, " + 
					"military, " + 
					"politicPreferences, " + 
					"worldView, " + 
					"mainInLife, " + 
					"mainInPeople, " + 
					"relationToSmoke, " + 
					"relationToDrink, " + 
					"inspire, " + 
					"activities, " + 
					"interests, " + 
					"favouriteMusic, " + 
					"favouriteFilms, " + 
					"favouriteShows, " + 
					"favouriteBooks, " + 
					"favouriteGames, " + 
					"favouriteQuotes, " + 
					"aboutMe, " + 
					"friends, " + 
					"groups, " + 
					"favoritePages) " + 
					"VALUES(" +
						"?,?,?,?,?,?,?,?,?,?," +
						"?,?,?,?,?,?,?,?,?,?," +
						"?,?,?,?,?,?,?,?,?,?," +
						"?,?,?,?,?,?,?,?,?,?," +
						"?,?,?,?,?,?));"); 
		int i = 0;
		ps.setString(i++, profileInfo.getId());
		ps.setString(i++, profileInfo.getName());
		ps.setString(i++, profileInfo.getStatus());
		ps.setString(i++, profileInfo.getBirthDate());
		ps.setString(i++, profileInfo.getCity());
		ps.setString(i++, profileInfo.getFamilyStatus());
		ps.setString(i++, profileInfo.getSite());
		ps.setString(i++, profileInfo.getHomeCity());
		ps.setArray(i++, (Array) profileInfo.getLanguages());
		ps.setArray(i++, (Array) profileInfo.getGrandPaAndMa());
		ps.setArray(i++, (Array) profileInfo.getParents());
		ps.setArray(i++, (Array) profileInfo.getBrothersSisters());
		ps.setArray(i++, (Array) profileInfo.getChildren());
		ps.setArray(i++, (Array) profileInfo.getGrandChildren());
		ps.setString(i++, profileInfo.getMobilePhone());
		ps.setString(i++, profileInfo.getSecondPhone());
		ps.setString(i++, profileInfo.getInstagram());
		ps.setString(i++, profileInfo.getTwitter());
		ps.setString(i++, profileInfo.getFacebook());
		ps.setArray(i++, (Array) profileInfo.getWorkplace());
		ps.setArray(i++, (Array) profileInfo.getInstitutes());
		ps.setArray(i++, (Array) profileInfo.getFaculties());
		ps.setArray(i++, (Array) profileInfo.getInstituteDepartments());
		ps.setArray(i++, (Array) profileInfo.getInstituteForm());
		ps.setArray(i++, (Array) profileInfo.getInstituteStatus());
		ps.setArray(i++, (Array) profileInfo.getSchools());
		ps.setArray(i++, (Array) profileInfo.getMilitary());
		ps.setString(i++, profileInfo.getPoliticPreferences());
		ps.setString(i++, profileInfo.getWorldView());
		ps.setString(i++, profileInfo.getMainInLife());
		ps.setString(i++, profileInfo.getMainInPeople());
		ps.setString(i++, profileInfo.getRelationToSmoke());
		ps.setString(i++, profileInfo.getRelationToDrink());
		ps.setString(i++, profileInfo.getInspire());
		ps.setArray(i++, (Array) profileInfo.getActivities());
		ps.setArray(i++, (Array) profileInfo.getInterests());
		ps.setArray(i++, (Array) profileInfo.getFavouriteMusic());
		ps.setArray(i++, (Array) profileInfo.getFavouriteFilms());
		ps.setArray(i++, (Array) profileInfo.getFavouriteShows());
		ps.setString(i++, profileInfo.getFacebook());
		ps.setArray(i++, (Array) profileInfo.getFavouriteGames());
		ps.setArray(i++, (Array) profileInfo.getFavouriteQuotes());
		ps.setArray(i++, (Array) profileInfo.getAboutMe());
		ps.setArray(i++, (Array) profileInfo.getFriends());
		ps.setArray(i++, (Array) profileInfo.getGroups());
		ps.setArray(i++, (Array) profileInfo.getPages());
	}
}

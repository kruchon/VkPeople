package org.schematch_team.parsing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
						"?,?,?,?,?,?)");
		int i = 1;
		ps.setString(i++, profileInfo.getId());
		ps.setString(i++, profileInfo.getName());
		ps.setString(i++, profileInfo.getStatus());
		ps.setString(i++, profileInfo.getBirthDate());
		ps.setString(i++, profileInfo.getCity());
		ps.setString(i++, profileInfo.getFamilyStatus());
		ps.setString(i++, profileInfo.getSite());
		ps.setString(i++, profileInfo.getHomeCity());
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getLanguages().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getGrandPaAndMa().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getParents().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getBrothersSisters().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getChildren().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getGrandChildren().toArray(new String[0])));
		ps.setString(i++, profileInfo.getMobilePhone());
		ps.setString(i++, profileInfo.getSecondPhone());
		ps.setString(i++, profileInfo.getInstagram());
		ps.setString(i++, profileInfo.getTwitter());
		ps.setString(i++, profileInfo.getFacebook());
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getWorkplace().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getInstitutes().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFaculties().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getInstituteDepartments().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getInstituteForm().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getInstituteStatus().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getSchools().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getMilitary().toArray(new String[0])));
		ps.setString(i++, profileInfo.getPoliticPreferences());
		ps.setString(i++, profileInfo.getWorldView());
		ps.setString(i++, profileInfo.getMainInLife());
		ps.setString(i++, profileInfo.getMainInPeople());
		ps.setString(i++, profileInfo.getRelationToSmoke());
		ps.setString(i++, profileInfo.getRelationToDrink());
		ps.setString(i++, profileInfo.getInspire());
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getActivities().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getInterests().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFavouriteMusic().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFavouriteFilms().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFavouriteShows().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFavouriteBooks().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFavouriteGames().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFavouriteQuotes().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getAboutMe().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getFriends().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getGroups().toArray(new String[0])));
		ps.setArray(i++, connection.createArrayOf("VARCHAR", profileInfo.getPages().toArray(new String[0])));
		ps.executeUpdate();
		connection.commit();
		ps.close();
	}

	public List<Integer> getFriendsIds(Integer idint) throws SQLException{
		
		List<Integer> res = new ArrayList<>();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select friends from vkpeople2 where idint = " + String.valueOf(idint));
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        
        resultSet.next();
        Array array = resultSet.getArray("friends");
        String[] arr = null;
        try {
        	arr = (String[]) array.getArray();
        } catch (Exception e){
        }
        if (arr == null) {
        	return res;
        }
        
        for (String a : arr) {
        	if (a.contains("/id")) {
        		try {
        			int id = Integer.valueOf(a.substring(a.lastIndexOf('d') + 1).trim());
        			res.add(id);
        		} catch (Exception e) {
        			
        		}
        	}
        }
        
        return res;
	}

	public List<FriendPair> getFriendPairs(Integer idint) throws SQLException{
		
		List<FriendPair> result = new ArrayList<>();
		
		if (idint < 150_000) {
			List<Integer> friendsIds = getFriendsIds(idint);
			for(Integer friendId : friendsIds) {
				result.add(new FriendPair(idint,friendId));
				if (friendId < 150_000) {
					List<Integer> friendsFridendsIds = getFriendsIds(friendId);
					for(Integer friendFriendId : friendsFridendsIds) {
						result.add(new FriendPair(friendId,friendFriendId));
						if (friendFriendId < 150_000) {
							List<Integer> friendsFriendFridendsIds = getFriendsIds(friendFriendId);
							for(Integer friendFriendFriendId : friendsFriendFridendsIds) {
								result.add(new FriendPair(friendFriendId,friendFriendFriendId));
							}
						}
						
					}
				}
			}
		} 
		
		//System.out.println(result);
		return result;
	}
}

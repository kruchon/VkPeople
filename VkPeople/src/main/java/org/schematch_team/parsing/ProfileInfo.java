package org.schematch_team.parsing;

import java.util.ArrayList;

public class ProfileInfo {
    private String id;
    private String name;
    private String familyStatus;
    private String mainEducation;
    private String site;
    private String birthDate;
    private String city;
    private ArrayList<String> friends;
    private ArrayList<String> groups;
    private ArrayList<String> pages;
    private String homeCity;
    private ArrayList<String> brothersSisters;
    private ArrayList<String> grandPaAndMa;
    private ArrayList<String> workplace;
    private ArrayList<String> institutes;
    private ArrayList<String> faculties;
    private ArrayList<String> intituteDepartments;
    private ArrayList<String> schools;
    private String politicPreferences;
    private String worldView;
    private String mainInLife;
    private String mainInPeople;
    private String relationToSmoke;
    private String relationToDrink;
    private String inspire;
    private ArrayList<String> activities;
    private ArrayList<String> interests;
    private ArrayList<String> favouriteMusic;
    private ArrayList<String> favouriteFilms;
    private ArrayList<String> favouriteShows;
    private ArrayList<String> favouriteBooks;
    private ArrayList<String> favouriteGames;

    public ProfileInfo() {
        this.id = null;
        this.name = null;
        this.familyStatus = null;
        this.mainEducation = null;
        this.site = null;
        this.birthDate = null;
        this.city = null;
        this.friends = null;
        this.groups = null;
        this.pages = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getMainEducation() {
        return mainEducation;
    }

    public void setMainEducation(String mainEducation) {
        this.mainEducation = mainEducation;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public ArrayList<String> getPages() {
        return pages;
    }

    public void setPages(ArrayList<String> pages) {
        this.pages = pages;
    }

    @Override public String toString() {
        return "ProfileInfo{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", familyStatus='"
                + familyStatus + '\'' + ", mainEducation='" + mainEducation + '\'' + ", site='"
                + site + '\'' + ", birthDate='" + birthDate + '\'' + ", city='" + city + '\''
                + ", friends=" + friends + ", groups=" + groups + ", pages=" + pages + '}';
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setBrothersSisters(ArrayList<String> brothersSisters) {
        this.brothersSisters = brothersSisters;
    }

    public ArrayList<String> getBrothersSisters() {
        return brothersSisters;
    }

    public ArrayList<String> getGrandPaAndMa() {
        return grandPaAndMa;
    }

    public void setGrandPaAndMa(ArrayList<String> grandPaAndMa) {
        this.grandPaAndMa = grandPaAndMa;
    }

    public ArrayList<String> getWorkplace() {
        return workplace;
    }

    public void setWorkplace(ArrayList<String> workplace) {
        this.workplace = workplace;
    }

    public ArrayList<String> getInstitutes() {
        return institutes;
    }

    public void setInstitutes(ArrayList<String> institutes) {
        this.institutes = institutes;
    }

    public ArrayList<String> getFaculties() {
        return faculties;
    }

    public void setFaculties(ArrayList<String> faculties) {
        this.faculties = faculties;
    }

    public ArrayList<String> getIntituteDepartments() {
        return intituteDepartments;
    }

    public void setIntituteDepartments(ArrayList<String> intituteDepartments) {
        this.intituteDepartments = intituteDepartments;
    }

    public void setSchools(ArrayList<String> schools) {
        this.schools = schools;
    }

    public ArrayList<String> getSchools() {
        return schools;
    }

    public void setPoliticPreferences(String politicPreferences) {
        this.politicPreferences = politicPreferences;
    }

    public String getPoliticPreferences() {
        return politicPreferences;
    }

    public void setWorldView(String worldView) {
        this.worldView = worldView;
    }

    public String getWorldView() {
        return worldView;
    }

    public void setMainInLife(String mainInLife) {
        this.mainInLife = mainInLife;
    }

    public String getMainInLife() {
        return mainInLife;
    }

    public void setMainInPeople(String mainInPeople) {
        this.mainInPeople = mainInPeople;
    }

    public String getMainInPeople() {
        return mainInPeople;
    }

    public void setRelationToSmoke(String relationToSmoke) {
        this.relationToSmoke = relationToSmoke;
    }

    public String getRelationToSmoke() {
        return relationToSmoke;
    }

    public void setRelationToDrink(String relationToDrink) {
        this.relationToDrink = relationToDrink;
    }

    public String getRelationToDrink() {
        return relationToDrink;
    }

    public void setInspire(String inspire) {
        this.inspire = inspire;
    }

    public String getInspire() {
        return inspire;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setFavouriteMusic(ArrayList<String> favouriteMusic) {
        this.favouriteMusic = favouriteMusic;
    }

    public ArrayList<String> getFavouriteMusic() {
        return favouriteMusic;
    }

    public void setFavouriteFilms(ArrayList<String> favouriteFilms) {
        this.favouriteFilms = favouriteFilms;
    }

    public ArrayList<String> getFavouriteFilms() {
        return favouriteFilms;
    }

    public void setFavouriteShows(ArrayList<String> favouriteShows) {
        this.favouriteShows = favouriteShows;
    }

    public ArrayList<String> getFavouriteShows() {
        return favouriteShows;
    }

    public void setFavouriteBooks(ArrayList<String> favouriteBooks) {
        this.favouriteBooks = favouriteBooks;
    }

    public ArrayList<String> getFavouriteBooks() {
        return favouriteBooks;
    }

    public void setFavouriteGames(ArrayList<String> favouriteGames) {
        this.favouriteGames = favouriteGames;
    }

    public ArrayList<String> getFavouriteGames() {
        return favouriteGames;
    }
}

package org.schematch_team.parsing;

import java.util.ArrayList;

public class ProfileInfo {
    private String id;
    private String name;
    private String status;
    private String familyStatus;
    private String site;
    private String birthDate;
    private String city;
    private ArrayList<String> friends;
    private ArrayList<String> groups;
    private ArrayList<String> pages;
    private String homeCity;
    private ArrayList<String> brothersSisters;
    private ArrayList<String> languages;
    private ArrayList<String> grandPaAndMa;
    private ArrayList<String> children;
    private ArrayList<String> parents;
    private ArrayList<String> workplace;
    private ArrayList<String> institutes;
    private ArrayList<String> faculties;
    private ArrayList<String> instituteDepartments;
    private ArrayList<String> instituteForm;
    private ArrayList<String> instituteStatus;
    private ArrayList<String> military;
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
    private ArrayList<String> grandChildren;
    private String mobilePhone;
    private String secondPhone;
    private String skype;
    private String instagram;
    private String twitter;
    private String facebook;
    private ArrayList<String> militaryUnit;
    private ArrayList<String> favouriteQuotes;
    private ArrayList<String> aboutMe;

    public ProfileInfo() {
        this.id = null;
        this.name = null;
        this.status = null;
        this.familyStatus = null;
        this.site = null;
        this.birthDate = null;
        this.city = null;
        this.friends = null;
        this.groups = null;
        this.pages = null;
        this.homeCity = null;
        this.brothersSisters = null;
        this.languages = null;
        this.grandPaAndMa = null;
        this.children = null;
        this.parents = null;
        this.workplace = null;
        this.institutes = null;
        this.faculties = null;
        this.instituteDepartments = null;
        this.instituteForm = null;
        this.instituteStatus = null;
        this.military = null;
        this.schools = null;
        this.politicPreferences = null;
        this.worldView = null;
        this.mainInLife = null;
        this.mainInPeople = null;
        this.relationToSmoke = null;
        this.relationToDrink = null;
        this.inspire = null;
        this.activities = null;
        this.interests = null;
        this.favouriteMusic = null;
        this.favouriteFilms = null;
        this.favouriteShows = null;
        this.favouriteBooks = null;
        this.favouriteGames = null;
        this.grandChildren = null;
        this.mobilePhone = null;
        this.secondPhone = null;
        this.skype = null;
        this.instagram = null;
        this.twitter = null;
        this.facebook = null;
        this.militaryUnit = null;
        this.favouriteQuotes = null;
        this.aboutMe = null;
    }

    public ArrayList<String> getMilitary () {
		return military;
	}

	public void setMilitary (ArrayList<String> military ) {
		this.military = military;
	}

	public ArrayList<String> getGrandChildren() {
		return grandChildren;
	}

	public void setGrandChildren(ArrayList<String> grandChildren) {
		this.grandChildren = grandChildren;
	}

	public ArrayList<String> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<String> children) {
		this.children = children;
	}

	public ArrayList<String> getParents() {
		return parents;
	}

	public void setParents(ArrayList<String> parents) {
		this.parents = parents;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}

	public void setFavouriteQuotes(ArrayList<String> favouriteQuotes) 
    {
        this.favouriteQuotes = favouriteQuotes;
    }

    public ArrayList<String> getFavouriteQuotes() {
        return favouriteQuotes;
    }
    
    public void setAboutMe(ArrayList<String> aboutMe) 
    {
        this.aboutMe = aboutMe;
    }

    public ArrayList<String> getAboutMe() {
        return aboutMe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public ArrayList<String> getInstituteDepartments() {
        return instituteDepartments;
    }

    public void setInstituteDepartments(ArrayList<String> instituteDepartments) {
        this.instituteDepartments = instituteDepartments;
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

    public ArrayList<String> getInstituteForm() {
        return instituteForm;
    }

    public void setInstituteForm(ArrayList<String> intituteForm) {
        this.instituteForm = intituteForm;
    }

    public ArrayList<String> getInstituteStatus() {
        return instituteStatus;
    }

    public void setInstituteStatus(ArrayList<String> intituteStatus) {
        this.instituteStatus = intituteStatus;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public ArrayList<String> getMilitaryUnit() {
        return militaryUnit;
    }

    public void setMilitaryUnit(ArrayList<String> militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    @Override public String toString() {
        return "ProfileInfo{" + 
        		"\n" + "id = " + id + 
        		"\n" + "name = " + name + 
        		"\n" + "status = " + status + 
        		"\n" + "birthDate = " + birthDate + 
        		"\n" + "city = " + city + 
        		"\n" + "familyStatus = " + familyStatus + 
        		"\n" + "site = " + site + 
        		"\n" + "homeCity = " + homeCity + 
        		"\n" + "languages = " + languages + 
        		"\n" + "grandPaAndMa = " + grandPaAndMa + 
        		"\n" + "parents = " + parents + 
        		"\n" + "brothersSisters = " + brothersSisters + 
        		"\n" + "children = " + children + 
        		"\n" + "grandChildren = " + grandChildren + 
        		"\n" + "mobilePhone = " + mobilePhone + 
        		"\n" + "secondPhone = " + secondPhone +
        		"\n" + "instagram = " + instagram + 
        		"\n" + "twitter = " + twitter + 
        		"\n" + "facebook = " + facebook +
        		"\n" + "workplace = " + workplace + 
        		"\n" + "institutes = " + institutes + 
        		"\n" + "faculties = " + faculties +
        		"\n" + "instituteDepartments = " + instituteDepartments +
        		"\n" + "instituteForm = " + instituteForm +
        		"\n" + "instituteStatus = " + instituteStatus +
        		"\n" + "schools = " + schools +
        		"\n" + "military = " + military +
        		"\n" + "politicPreferences = " + politicPreferences +
        		"\n" + "worldView = " + worldView + 
        		"\n" + "mainInLife = " + mainInLife +
        		"\n" + "mainInPeople = " + mainInPeople +
        		"\n" + "relationToSmoke = " + relationToSmoke +
        		"\n" + "relationToDrink = " + relationToDrink +
        		"\n" + "inspire = " + inspire +
        		"\n" + "activities = " + activities +
        		"\n" + "interests = " + interests +
        		"\n" + "favouriteMusic = " + favouriteMusic +
        		"\n" + "favouriteFilms = " + favouriteFilms +
        		"\n" + "favouriteShows = " + favouriteShows +
        		"\n" + "favouriteBooks = " + favouriteBooks +
        		"\n" + "favouriteGames = " + favouriteGames +
        		"\n" + "favouriteQuotes = " + favouriteQuotes +
        		"\n" + "aboutMe = " + aboutMe + 
        		"\n" + "friends = " + friends + 
        		"\n" + "groups = " + groups + 
        		"\n" + "favoritePages = " + pages + "}";
    }
}

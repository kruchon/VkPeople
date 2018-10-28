package org.schematch_team.parsing;

import java.util.ArrayList;

public class ProfileInfo {
    String id;
    String name;
    String familyStatus;
    String mainEducation;
    String site;
    String birthDate;
    String city;
    ArrayList<String> friends;
    ArrayList<String> groups;
    ArrayList<String> pages;

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
}

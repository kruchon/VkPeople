package org.schematch_team.parsing;

public class ProfileInfo {
    String id;
    String name;
    String familyStatus;
    String mainEducation;
    String site;
    String birthDate;
    String city;

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

    @Override public String toString() {
        return "ProfileInfo{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", familyStatus='"
                + familyStatus + '\'' + ", mainEducation='" + mainEducation + '\'' + ", site='"
                + site + '\'' + ", birthDate='" + birthDate + '\'' + ", city='" + city + '\'' + '}';
    }
}

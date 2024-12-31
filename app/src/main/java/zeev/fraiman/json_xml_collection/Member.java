package zeev.fraiman.json_xml_collection;

public class Member {
    private String fullName;
    private String country;
    private String politicalGroup;
    private String nationalPoliticalGroup;

    public Member(String fullName, String country, String politicalGroup, String nationalPoliticalGroup) {
        this.fullName = fullName;
        this.country = country;
        this.politicalGroup = politicalGroup;
        this.nationalPoliticalGroup = nationalPoliticalGroup;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPoliticalGroup() {
        return politicalGroup;
    }

    public void setPoliticalGroup(String politicalGroup) {
        this.politicalGroup = politicalGroup;
    }

    public String getNationalPoliticalGroup() {
        return nationalPoliticalGroup;
    }

    public void setNationalPoliticalGroup(String nationalPoliticalGroup) {
        this.nationalPoliticalGroup = nationalPoliticalGroup;
    }

    @Override
    public String toString() {
        return this.fullName+"\n"+this.country+"\n"+this.politicalGroup+"\n"+this.nationalPoliticalGroup;
    }
}

package zeev.fraiman.json_xml_collection;

public class Airport {
    private String AirportName;
    private String AirportWebsite;
    private String AirportLocationLat;
    private String AirportLocationLon;

    public Airport(String airportName, String airportWebsite, String airportLocationLat, String airportLocationLon) {
        AirportName = airportName;
        AirportWebsite = airportWebsite;
        AirportLocationLat = airportLocationLat;
        AirportLocationLon = airportLocationLon;
    }

    public String getAirportName() {
        return AirportName;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }

    public String getAirportWebsite() {
        return AirportWebsite;
    }

    public void setAirportWebsite(String airportWebsite) {
        AirportWebsite = airportWebsite;
    }

    public String getAirportLocationLat() {
        return AirportLocationLat;
    }

    public void setAirportLocationLat(String airportLocationLat) {
        AirportLocationLat = airportLocationLat;
    }

    public String getAirportLocationLon() {
        return AirportLocationLon;
    }

    public void setAirportLocationLon(String airportLocationLon) {
        AirportLocationLon = airportLocationLon;
    }
}
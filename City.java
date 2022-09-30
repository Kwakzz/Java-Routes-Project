import java.util.ArrayList;
import java.util.HashMap;

public class City {

    public City (String cityName, String countryName) {
        this.cityName = cityName;
        this.countryName = countryName;
    }

    private String cityName;
    private String countryName;
    private ArrayList<Airport> airportsInCity = new ArrayList<>();
    HashMap<Airport, City> airportsInCityHm = new HashMap<>();

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String country) {
        this.countryName = countryName;
    }

    public ArrayList<Airport> getAirportsInCity() {
        return airportsInCity;
    }

    public void addToAirportsInCity(Airport airport) {
        airportsInCity.add(airport);
    }
}

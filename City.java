import java.util.ArrayList;
import java.util.HashMap;

public class City {

    // ----- INSTANCE VARIABLES ------
    
    private String cityName;
    private String countryName;
    private ArrayList<Airport> airportsInCity = new ArrayList<>();
    HashMap<Airport, City> airportsInCityHm = new HashMap<>();

    
    // ----- CONSTRUCTORS ------
    
    public City (String cityName, String countryName) {
        this.cityName = cityName;
        this.countryName = countryName;
    }
    

    // ----- INSTANCE METHODS ------

    /**
     * @return name of city
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * set name of city to cityName
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return country city is in
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * set name of country city is in to countryName
     * @param country
     */
    public void setCountryName(String country) {
        this.countryName = countryName;
    }

    /**
     * @return array list of airports in the city
     */
    public ArrayList<Airport> getAirportsInCity() {
        return airportsInCity;
    }

    /**
     * adds an airport to the array list of airports in the city
     * @param airport
     */
    public void addToAirportsInCity(Airport airport) {
        airportsInCity.add(airport);
    }

    /**
     * @return string version of city: City name, Name of country city is in
     */
    public String toString () {
        return "(" + getCityName() + "," + getCountryName() + ")";
    }
}

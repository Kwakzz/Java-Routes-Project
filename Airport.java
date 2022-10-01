import java.util.ArrayList;
import java.util.HashMap;

public class Airport {

    // ----- INSTANCE VARIABLES ------
    
    private String airportName;
    private String airportID;
    private String airportCode;
    private City cityOfAirport;
    private Airport sourceAirport;
    private Airline sourceAirportAirline;
    private ArrayList <Airline> destinationAirportAirlines = new ArrayList<>();
    HashMap<String, Airline> destinationAirportsAirlinesHm = new HashMap<>();
    // private ArrayList <Airport> destinationAirports = new ArrayList<>();
    HashMap <String, Airport> destinationAirportsHm = new HashMap<>();


    // ----- CONSTRUCTORS ------
    
    public Airport(String airportID, String airportName, City cityOfAirport) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.cityOfAirport = cityOfAirport;
    }


    public Airport(String airportID) {
        this.airportID = airportID;
    }

    public Airport(String airportID, String airportCode) {
        this.airportID = airportID;
        this.airportCode = airportCode;
    }


    // ----- INSTANCE METHODS ------
    
    /**
     * @return name of Airport
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * set name of airport to airportName
     * @param airportName
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    /**
     * @return ID of the airport
     */
    public String getAirportID() {
        return airportID;
    }

    /**
     * set ID of airport to airportID
     * @param airportID
     */
    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    /**
     * @return code of airport
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * set code of airport to airportCode
     * @param airportCode
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    /**
     * @return city object the airport is in
     */
    public City getCityOfAirport() {
        return cityOfAirport;
    }

    /**
     * set city airport is in to cityOfAirport
     * @param cityOfAirport
     */
    public void setCityOfAirport(City cityOfAirport) {
        this.cityOfAirport = cityOfAirport;
    }

    /**
     * @return the source airport
     */
    public Airport getSourceAirport() {
        return sourceAirport;
    }

    /**
     * set source airport to sourceAirport
     * @param sourceAirport
     */
    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    /**
     * @return the airline used to fly from source airport to current airport
     */
    public Airline getSourceAirportAirline() {
        return sourceAirportAirline;
    }

    /**
     * set airline used to fly from source airport to sourceAirportAirline
     * @param sourceAirportAirline
     */
    public void setSourceAirportAirline(Airline sourceAirportAirline) {
        this.sourceAirportAirline = sourceAirportAirline;
    }

    /**
     * @return airlines used to fly to various destination airports.
     */
    public ArrayList<Airline> getDestinationAirportAirlines() {
        return destinationAirportAirlines;
    }
    
    public void addToDestinationAirportAirlines(Airline airline) {
        destinationAirportAirlines.add(airline);
    }

    /**
     * @return string version of airport: Airport id, Airport Name, and City Name
     */
    public String toString () {
        return "(" + getAirportID() + ", " + getAirportName() + ", " + getCityOfAirport().getCityName() + ")";
    }
}

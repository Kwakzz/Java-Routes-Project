import java.util.ArrayList;
import java.util.HashMap;

public class Airport {

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

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public City getCityOfAirport() {
        return cityOfAirport;
    }

    public void setCityOfAirport(City cityOfAirport) {
        this.cityOfAirport = cityOfAirport;
    }

//    public ArrayList<Airport> getDestinationAirports() {
//        return destinationAirports;
//    }
//
//    public void addToDestinationAirports(Airport destinationAirports) {
//        this.destinationAirports.add(destinationAirports);
//    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public Airline getSourceAirportAirline() {
        return sourceAirportAirline;
    }

    public void setSourceAirportAirline(Airline sourceAirportAirline) {
        this.sourceAirportAirline = sourceAirportAirline;
    }

    public ArrayList<Airline> getDestinationAirportAirlines() {
        return destinationAirportAirlines;
    }

    public void addToDestinationAirportAirlines(Airline airline) {
        destinationAirportAirlines.add(airline);
    }


    public String toString () {
        return "(" + getAirportID() + ", " + getAirportName() + ", " + getCityOfAirport().getCityName() + ")";
    }
}

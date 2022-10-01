public class Airline {

    private String airlineName;
    private String airlineCode;
    private String airlineID;
    private int noOfStops;

    public Airline(String airlineID, String airlineCode) {
        this.airlineID = airlineID;
        this.airlineCode = airlineCode;
    }

    public Airline(String airlineID, String airlineCode, int noOfStops) {
        this.airlineID = airlineID;
        this.airlineCode = airlineCode;
        this.noOfStops = noOfStops;
    }

    public Airline(String airlineCode) {
        this.airlineCode = airlineCode;
    }


    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    public int getNoOfStops() {
        return noOfStops;
    }

    public void setNoOfStops(int noOfStops) {
        this.noOfStops = noOfStops;
    }
}

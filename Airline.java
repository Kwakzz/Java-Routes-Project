public class Airline {

    // ----- INSTANCE VARIABLES ------
    
    private String airlineName;
    private String airlineCode;
    private String airlineID;
    private int noOfStops;


    // ----- CONSTRUCTORS ------
    
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


    // ----- INSTANCE METHODS ------
    
    /**
     * @return name of airline used for flight
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     * sets name of airline to airlineName
     * @param airlineName the name of the airline used for flight
     */
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    /**
     * @return airline code
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * set code of airline used for flight to airlineCode
     * @param airlineCode code of airline used for flight
     */
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    /**
     * @return id of airline used for flight
     */
    public String getAirlineID() {
        return airlineID;
    }

    /**
     * sets ID of airline used for flight to airlineID
     * @param airlineID
     */
    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    /**
     * @return number of stops during flight
     */
    public int getNoOfStops() {
        return noOfStops;
    }

    /**
     * set number of stops during flight to noOfStops
     * @param noOfStops
     */
    public void setNoOfStops(int noOfStops) {
        this.noOfStops = noOfStops;
    }
}

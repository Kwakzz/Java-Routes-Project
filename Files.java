import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Files {

    public Files() throws FileNotFoundException {
    }

    // ----- VARIABLES ------
    
    static String airlinesFilePath = "airlines.csv";
    static String airportsFilePath = "airports.csv";
    static String routesFilePath = "routes.csv";

    private final static File airlines = new File(airlinesFilePath);
    private final static File airports = new File(airportsFilePath);
    private final static File routes = new File(routesFilePath);

    static HashMap<String, String> airlinesHm = new HashMap<>();
    static HashMap<Airport, City> airportsHm = new HashMap<>();
    static HashMap<String[][], Airline> routesHm = new HashMap<>();

    static ArrayList<ArrayList<String>> airlinesAL = new ArrayList<>();
    static ArrayList<ArrayList<String>> airportsAl = new ArrayList<>();
    static ArrayList<ArrayList<String>> routesAL = new ArrayList<>();

    static Scanner airlines_read;

    static {
        try {
            airlines_read = new Scanner(new FileInputStream(airlines));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Scanner airports_read;

    static {
        try {
            airports_read = new Scanner(new FileInputStream(airports));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Scanner routes_read;

    static {
        try {
            routes_read = new Scanner(new FileInputStream(routes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ----- METHOD FOR PUTTING TUPLES IN HASHMAPS -----

    /**
     * puts records in files into Hashmaps
     */
    public static void putInHashMaps () {

        // Airport tuple is below
        // Airport ID, Name, City, Country, IATA code, ICAO code,
        // Latitude, Longitude, Altitude, Timezone, DST (Daylight savings time),
        // Tz database time zone, Type, Source of this data.
        while (airports_read.hasNextLine()) {
            String airportsTupleAsString = Files.airports_read.nextLine();
            String[] airportTuple = airportsTupleAsString.split(",");
            City newCity = new City(airportTuple[2], airportTuple[3]);
            airportsHm.put(new Airport(airportTuple[0], airportTuple[1], newCity), newCity);

            if (airportTuple[2].equals(newCity.getCityName())) {
                newCity.airportsInCityHm.put(new Airport(airportTuple[0], airportTuple[1], newCity), newCity);
                // cityBeingExplored.addToAirportsInCity(new Airport(airportTuple[0], airportTuple[1], cityBeingExplored));
            }

        }

        // Routes tuple is below
        // Airline code, Airline ID, Source airport code, Source airport ID,
        // Destination airport code, Destination airport ID, Codeshare, Stops, Equipment
        while (routes_read.hasNextLine()) {
            String routesTupleAsString = Files.routes_read.nextLine();
            String[] routesTuple = routesTupleAsString.split(",");
            String [] sourceAirportTuple = {routesTuple[3], routesTuple[2]};
            String [] destinationAirportTuple = {routesTuple[5], routesTuple[4]};
            String[][] keyTuple = {sourceAirportTuple, destinationAirportTuple};
            routesHm.put(keyTuple ,new Airline(routesTuple[1], routesTuple[0], Integer.parseInt(routesTuple[7])));

        }

    }

    // Input File's first line will contain the starting point. Winnipeg, Canada
    // Second line will contain the destination. Accra, Ghana


    // city names can be located in the airports.csv file
    // look for tuple that has the starting point/city
    // airport ids are also in the airports.csv file
    // store the airport id in that tuple


    // source airport id and destination airport id are in the routes.csv file
    // look for the tuple that contains the stored airport id as its source airport id [in the routes.csv file]
    // store the destination airport id and the airline code in that tuple
    // return to the airports.csv file and look for the tuple that has the destination airport id
    // store the city name in that tuple
    // if the city name is not equal to destination city, repeat the process.


}

import java.io.*;
import java.util.*;
import java.io.FileOutputStream;


public class Run {

    // ----- INSTANCE VARIABLES -----

    static City startCity;
    static City stopCity;
    static City [] startAndStopCities = new City[2];
    static ArrayList <City> startCities = new ArrayList<>();
    static ArrayList <City> exploredCities = new ArrayList<>();
    static ArrayList <Airport> exploredAirports = new ArrayList<>();
    static City cityBeingExplored;
    static ArrayList <City> destination = new ArrayList<>();

    // ----- METHOD FOR CHECKING IF CITY IS THE SET DESTINATION -----

    /**
     * @param cityName is the name of city we're verifying as the destination
     * @return true if destination has been found.
     */
    public static boolean foundDestination (String cityName) {
        return cityName.equals(stopCity.getCityName());
    }

    // ----- METHOD FOR DISPLAYING PATH AND WRITING IT TO A FILE -----

    /**
     * displays flight route in console and in a file.
     * @param airport is the destination airport
     * @return an array of the start city and various stops during the flight
     * @throws FileNotFoundException
     */
    public static String setPath (Airport airport) throws FileNotFoundException {
        ArrayList <Airport> airportsAlongRoute = new ArrayList<>();

        while (airport.getSourceAirport() != null) {
            airportsAlongRoute.add(airport);
            airport = airport.getSourceAirport();
        }
        airportsAlongRoute.add(airport);

        for (int i = airportsAlongRoute.size()-1; i>=1; i--) {
            System.out.printf("%s from %s to %s %d stops\n", airportsAlongRoute.get(i-1).getSourceAirportAirline().getAirlineCode(),
                    airportsAlongRoute.get(i).getAirportCode(), airportsAlongRoute.get(i-1).getAirportCode(),
                    airportsAlongRoute.get(i-1).getSourceAirportAirline().getNoOfStops());
        }
        System.out.printf("Number of flights: %d\n", airportsAlongRoute.size()-1);

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(startCity.getCityName() + "-" + stopCity.getCityName() +"_output.txt"));
        for (int i = airportsAlongRoute.size()-1; i>=1; i--) {
            printWriter.write(airportsAlongRoute.get(i-1).getSourceAirportAirline().getAirlineCode() + " from " +
                    airportsAlongRoute.get(i).getAirportCode() + " to " + airportsAlongRoute.get(i-1).getAirportCode()
                    + " " + airportsAlongRoute.get(i-1).getSourceAirportAirline().getNoOfStops() + " stops\n");
        }
        printWriter.write("Number of flights: " + (airportsAlongRoute.size() - 1));
        printWriter.close();

        Collections.reverse(airportsAlongRoute);

        return (Arrays.toString(airportsAlongRoute.toArray()));
    }

    // ----- METHOD FOR READING START LOCATION AND DESTINATION FROM INPUT FILE -----

    /**
     * @param inputFile contains the starting point and the destination
     * @throws FileNotFoundException
     */
    public static void getStartAndDestination (File inputFile) throws FileNotFoundException {
        // File is read from as a stream
        FileInputStream inputStream = new FileInputStream(inputFile);
        // Scanner reads from stream
        Scanner scanner = new  Scanner(inputStream);
        String [] start = scanner.nextLine().split(", ");
        String [] stop = scanner.nextLine().split(", ");
        startCity = new City(start[0], start[1]);
        stopCity = new City(stop[0], stop[1]);
        startAndStopCities[0] = startCity;
        startAndStopCities[1] = stopCity;
    }

    // ----- READING FROM DATA FILES TO FIND VALID ROUTE -----

    /**
     * reads data from files and searches for destination.
     * @throws FileNotFoundException
     */
    public static void readFromDataFiles() throws FileNotFoundException {

        startCities.add(startCity);
        cityBeingExplored = startCities.get(0);

        while (destination.size() == 0) {

            // System.out.printf("City being explored: %s\n", cityBeingExplored.getCityName());

            // ----- FIND AIRPORTS IN CITY BEING EXPLORED. FIRST CITY EXPLORED IS START CITY -----

            if (cityBeingExplored.equals(startCity)) {
                // read from airports.csv to obtain all airports in the city being explored
                while (Files.airports_read.hasNextLine()) {
                    // Airport tuple is below
                    // Airport ID, Name, City, Country, IATA code, ICAO code,
                    // Latitude, Longitude, Altitude, Timezone, DST (Daylight savings time),
                    // Tz database time zone, Type, Source of this data.

                    String[] airportTuple = Files.airports_read.nextLine().split(",");


                    if (airportTuple[2].equals(cityBeingExplored.getCityName())) {
                        cityBeingExplored.addToAirportsInCity(new Airport(airportTuple[0], airportTuple[1], cityBeingExplored));
                        // cityBeingExplored.addToAirportsInCity(new Airport(airportTuple[0], airportTuple[1], cityBeingExplored));
                    }
                }
            }

            // System.out.printf("%s Airports: %s\n", cityBeingExplored.getCityName(), Arrays.toString(cityBeingExplored.airportsInCityHm.keySet().toArray(new Airport[0])));

            // ----- OBTAIN IDS OF DESTINATION AIRPORTS OF AIRPORTS IN CITY BEING EXPLORED ------

            // read from routes.csv to obtain all destination airports from the start airport
            // destination airports are the children of an airport node
            while (Files.routes_read.hasNextLine()) {
                // Routes tuple is below
                // Airline code, Airline ID, Source airport code, Source airport ID,
                // Destination airport code, Destination airport ID, Codeshare, Stops, Equipment

                String[] routesTuple = Files.routes_read.nextLine().split(",");
                for (Airport airport : cityBeingExplored.getAirportsInCity()) {
                    // System.out.println(airport.getAirportName());
                    if (routesTuple[3].equals(airport.getAirportID())) {
                        if (cityBeingExplored.equals(startCity))
                            airport.setAirportCode(routesTuple[2]);
                        airport.destinationAirportsHm.put(routesTuple[5], new Airport(routesTuple[5], routesTuple[4]));
                        airport.destinationAirportsAirlinesHm.put(routesTuple[3] + ", " + routesTuple[5], new Airline(routesTuple[1], routesTuple[0], Integer.parseInt(routesTuple[7])));
                    }
                }
            }

            Files.routes_read.close();
            Files.routes_read = new Scanner(new FileInputStream(Files.routesFilePath));


            // ----- OBTAIN AIRLINE NAME FROM AIRLINES DATA FILE USING AIRLINE ID -----
            while (Files.airlines_read.hasNextLine()) {
                // Airlines tuple is below
                // Airline ID, Name, Alias, IATA code, ICAO code, Callsign, Country, Active

                String[] airlinesTuple = Files.airlines_read.nextLine().split(",");
                for (Airport airport : cityBeingExplored.getAirportsInCity()) {
                    if (exploredCities.contains(airport.getCityOfAirport()))
                        continue;
                    for (Airline airline: airport.getDestinationAirportAirlines())
                        if (airlinesTuple[3].equals(airline.getAirlineID())) {
                            airline.setAirlineName(airlinesTuple[1]);
                        }
                }
            }

            Files.airlines_read.close();
            Files.airlines_read = new Scanner(new FileInputStream(Files.airlinesFilePath));

            Files.airports_read.close();
            Files.airports_read = new Scanner(new FileInputStream(Files.airportsFilePath));

            // read from airports.csv to obtain destination airport names, and city objects of airports.
            while (Files.airports_read.hasNextLine()) {
                // Airport tuple is below
                // Airport ID, Name, City, Country, IATA code, ICAO code,
                // Latitude, Longitude, Altitude, Timezone, DST (Daylight savings time),
                // Tz database time zone, Type, Source of this data.

                String[] airportTuple = Files.airports_read.nextLine().split(",");


                // ----- CHECK IF AIRPORT IS AT DESTINATION. WE FIND NAMES OF DESTINATION AIRPORTS BY USING AIRPORT IDS OBTAINED FROM ROUTES FILE -----
                // get airports in city being explored
                for (Airport airport : cityBeingExplored.getAirportsInCity())
                    // get destination airports of airports in city being explored
                    for (Airport destinationAirport : airport.destinationAirportsHm.values()) {
                        destinationAirport.setSourceAirport(airport);
                        destinationAirport.setSourceAirportAirline(airport.destinationAirportsAirlinesHm.get(airport.getAirportID() + ", " + destinationAirport.getAirportID()));
                        if (destinationAirport.getAirportID().equals(airportTuple[0])) {
                            destinationAirport.setAirportName(airportTuple[1]);
                            destinationAirport.setCityOfAirport(new City(airportTuple[2], airportTuple[3]));
                            destinationAirport.getCityOfAirport().addToAirportsInCity(destinationAirport);
                            if (exploredAirports.contains(destinationAirport))
                                airport.destinationAirportsHm.remove(airportTuple[0]);
                            if (foundDestination(destinationAirport.getCityOfAirport().getCityName())) {
                                System.out.println("\nFound route!");
                                destination.add(destinationAirport.getCityOfAirport());
                                System.out.printf("You've arrived at %s, %s from %s, %s\n",destinationAirport.getAirportName(), destinationAirport.getCityOfAirport().getCityName()
                                , startCity.getAirportsInCity().get(0).getAirportName(), startCity.getCityName());
                                System.out.printf("Path %s", setPath(destinationAirport));
                                System.exit(0);
                            }
                        }
                    }
            }

            exploredCities.add(startCities.get(0));

            for (Airport airport: cityBeingExplored.getAirportsInCity()) {
                exploredAirports.add(airport);
                for (Airport destinationAirport : airport.destinationAirportsHm.values()) {
                    startCities.add(destinationAirport.getCityOfAirport());
                }
            }

            // print destination airports of city being explored
//            System.out.printf("\n%s destination airports: ", cityBeingExplored.getCityName());
//            for (Airport airport: cityBeingExplored.airportsInCityHm.keySet())
//                for (Airport destinationAirport: airport.destinationAirportsHm.values())
//                    System.out.println(destinationAirport);

            startCities.remove(0);

            for (City city: startCities)
                if (city != null) {
                    cityBeingExplored = city;
                    break;
                }

            // print out the cities yet to be explored. You can comment this out to reduce runtime.
            // System.out.println(Arrays.toString(startCities.toArray()));

            // print out city to be explored
            for (Airport airport: cityBeingExplored.getAirportsInCity())
                System.out.printf("City being explored: %s: %s\n",cityBeingExplored.getCityName(), airport.getAirportName());

        }

        if (destination.isEmpty())
            System.out.println("\nNo route found");

    }

    public static void main(String[] args) throws FileNotFoundException{
        getStartAndDestination(new File("input.txt"));
        readFromDataFiles();

    }

}

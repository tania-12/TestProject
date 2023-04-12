import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Random;

public class flight {

	private final String flightDAndT;
    private final String flightNum;
    private final String fromCity;
    private final String toCity;
    private final int gate;
    private String flightTime;
    private  String arrivalTime;
    private List<registration> listUsersInFlight;
    private int customerIndex;
    private static final List<flight> flights = new ArrayList<>();

 

    public String getFlightNum() {
        return flightNum;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
    public String getFlightTime() {
        return flightTime;
    }

    public List<flight> getFlights() {
        return flights;
    }

    public List<registration> getListOfRegisteredCustomersInAFlight() {
        return listUsersInFlight;
    }

    public String getFlightDAndT() {
        return flightDAndT;
    }

    public String getFromCity() {
        return fromCity;
    }

    public int getGate() {
        return gate;
    }

    public String getToCity() {
        return toCity;
    }
    
    private static final String[] cities = {
            "New York City", "London", "Los Angeles", "Stockholm", "Hong Kong", "Beijing", 
            "Tokyo", "Sydney", "Rio de Janeiro", "Seoul", "Paris", "Dubai", "Melbourne"
                    
     };

     public String[] whereTo() {
         Random rand = new Random();
         int city = rand.nextInt(cities.length);
         int city1 = rand.nextInt(cities.length);
         while (city1 == city) {
             city1 = rand.nextInt(cities.length);
         }
         String departure = cities[city];
         
         String arrival = cities[city1];
         String[] travel = new String[2];
         travel[0]= departure;
         travel[1]= arrival;
         return travel;
     }
     
     public int seatsOnFlight() {
         Random rand = new Random();
         int numOfSeats = rand.nextInt(100);
         while (numOfSeats < 60) {
             numOfSeats = rand.nextInt(100);
         }
         return numOfSeats;
     }
     private String flightDuration() {
    	    Random rand = new Random();
    	    int hours = rand.nextInt(10) + 1;
    	    int minutes = rand.nextInt(60);
    	    return String.format("%02d:%02d", hours, minutes);
    }
     public int gateNumber() {
         Random rand = new Random();
         int gateNum = rand.nextInt(10);
         return gateNum;
     }

     public String flightNumber() {
         Random rand = new Random();
         String[] airlineCodes = {"IA", "TC", "MR", "TS", "KW"}; // a list of possible 3-letter airline codes
         int flightNumber = rand.nextInt(10000); // Generate a random 4-digit flight number
         String flightNum = airlineCodes[rand.nextInt(airlineCodes.length)] + "-" + String.format("%04d", flightNumber);
         return flightNum;
     }
     flight() {
        this.flightDAndT = null;
        this.flightNum = null;
        toCity = null;
        fromCity = null;
        this.gate = 0;
    }

    flight(String flightDAndT, String flightNum, String[] trip, int gate) {
        this.flightDAndT = flightDAndT;
        this.flightNum = flightNum;
        this.fromCity = trip[0];
        this.toCity = trip[1];
        this.flightTime = flightDuration();
        this.arrivalTime = arrivalTime(flightDAndT, flightTime);
        this.listUsersInFlight = new ArrayList<>();
        this.gate = gate;
    }

    public void flightScheduler() {
        int flightsDisplayed = 4;             
        for (int i = 0; i < flightsDisplayed; i++) {
            String[]travel = whereTo();
            String flightDAndT = flightDateAndTime();
            String flightNum = flightNumber();
            int gate = gateNumber();
            flights.add(new flight(flightDAndT, flightNum, travel, gate));
        }
    }
    /**
     * Registers new Customer in this Flight.
     *
     * @param customer customer to be registered
     */
    void addUser(registration user) {
        this.listUsersInFlight.add(user);
    }

    /**
     * Adds numOfTickets to existing customer's tickets for the this flight.
     *
     * @param customer     customer in which tickets are to be added
     * @param numOfTickets number of tickets to add
     */
    void addTicketsToUser(registration user, int numTix) {
        user.addTixFlightToUser(customerIndex, numTix);
    }

    public String arrivalTime(String flightDateAndTime, String flightDuration) {
        String departureTimeString = flightDateAndTime;
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String durationString = flightDuration;
        int durationHours = Integer.parseInt(durationString.substring(0, 2));
        int durationMinutes = Integer.parseInt(durationString.substring(3));
        LocalDateTime arrivalTime = departureTime.plusHours(durationHours).plusMinutes(durationMinutes);
        return arrivalTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    void deleteFlight(String flightNumber) {
        boolean exists = false;
        for (int i = 0; i < flights.size(); i++) {
            flight flight = flights.get(i);
            if (flight.getFlightNum().equalsIgnoreCase(flightNumber)) {
                exists = true;
                flights.remove(i);
                break;
            }
        }
        if (exists) {
            displayFlightSchedule();
        } else {
            System.out.println("Invalid!");
        }
    }

    public void displayFlightSchedule() {
        System.out.println();
        System.out.println("+-------------------+------------------+----------------+-----------------+------------------+------------------+----------------+");
        System.out.format(" |   Flight Number   |  Departure City  |  Arrival City  |   Flight Time   |   Arrival Time   |     Duration     |   Gate Number  |");
        System.out.println("+-------------------+------------------+----------------+-----------------+------------------+------------------+----------------+");
        for (int i = 0; i < flights.size(); i++) {
            flight f1 = flights.get(i);
            System.out.println(f1.toString(i+1));
        }
    }

    public String toString(int i) {
        return String.format("| %-41s | %-20s | %-20s | %-30s | %-30s | %-10s  |  %-6sHrs /  %-4s  |", i, flightNum, fromCity, toCity, flightDAndT, arrivalTime, flightTime, gate);
    }

    public String flightDateAndTime() {
        Random rand = new Random();
        LocalDateTime dateTime = LocalDateTime.of(
                2024,  // year
                rand.nextInt(12) + 1,  // month (1-12)
                rand.nextInt(28) + 1,  // day (1-28)
                rand.nextInt(24),  // hour (0-23)
                rand.nextInt(60)  // minute (0-59)
        );
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
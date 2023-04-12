/*
 * FlightReservation class allows the user to book, cancel and check the status of the registered flights.
 * */

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class flightReservation implements DisplayClass {

    flight flight = new flight();
    int flightListIndex;

    /**
     * Book the numOfTickets for said flight for the specified user. Update the available seats in main system by
     * Subtracting the numOfTickets from the main system. If a new customer registers for the flight, then it adds
     * the customer to that flight, else if the user is already added to that flight, then it just updates the
     * numOfSeats of that flight.
     *
     * @param flightNo     FlightID of the flight to be booked
     * @param numOfTickets number of tickets to be booked
     * @param userID       userID of the user which is booking the flight
     */
    void bookFlight(String flightNumber, int numTix, String userID) {
        boolean exists = false;
        for (int i = 0; i < flight.getFlights().size(); i++) {
            flight f1 = flight.getFlights().get(i);
            if (flightNumber.equalsIgnoreCase(f1.getFlightNum())) {
            	for (int j = 0; j < registration.userList.size(); j++) {
            	    registration user = registration.userList.get(j);
            	    if (userID.equals(user.getUserID())) {
            	        exists = true;
            	        user.addFlightToUser(f1);
            	         addNumberOfTicketsForNewFlight(user, numTix);
            	        }
            	        break;
            	    }
            	}
            }
        
        if (!exists) {
            System.out.println("Invalid Flight Number");
        } else {
            System.out.println("Flight Booked Successfully");
        }
    }

    /**
     * Cancels the flight for a particular user 
     *
     * @param userID    ID of the user for whom the flight is to be cancelled
     */
    void cancelFlight(String userID) {
        String flightNum = "";
        Scanner input = new Scanner(System.in);
        boolean exists = false;
        for (registration customer : registration.userList) {
            if (userID.equals(customer.getUserID())) {
                if (customer.getflightsOn().size() != 0) {
                    System.out.printf("Flights registered for: ");
                    displayFlightsRegisteredByOneUser(userID);
                    System.out.print("Enter the Flight Number of the Flight you want to cancel : ");
                    flightNum = input.nextLine();
                    for (int i = 0; i < customer.getflightsOn().size(); i++) {
                        flight flight = customer.getflightsOn().get(i);
                        if (flightNum.equalsIgnoreCase(flight.getFlightNum())) {
                            exists = true;
                            customer.getflightsOn().remove(i);
                            break;
                        }
                    }
                    if (!exists) {
                        System.out.println("Invalid Flight Number");
                    }
                 else {
                    System.out.println("No Flight Registered");
                 }
               }
            }
        }
    }

    void addNumberOfTicketsForNewFlight(registration user, int numTickets) {
        user.ticketsBought.add(numTickets);
    }

    String flightStatus(flight flight) {
        boolean isFlightAvailable = false;
        for (flight list : flight.getFlights()) {
            if (list.getFlightNum().equalsIgnoreCase(flight.getFlightNum())) {
                isFlightAvailable = true;
                break;
            }
        }
        if (isFlightAvailable) {
            return "Scheduled";
        } else {
            return "Cancelled";
        }
    }

    /*toString() Method for displaying number of flights registered by single user...*/
    public String toString(int serialNum, flight flights, registration customer) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  | %-10s |", serialNum, flights.getFlights(), flights.getFlightNum(), customer.ticketsBought.get(serialNum - 1), flights.getFromCity(), flights.getToCity(), flights.getFlightTime(), flights.getGate(), flightStatus(flights));
    }

    @Override
    public void displayFlightsRegisteredByOneUser(String userID) {
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO |  Booked Tickets  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |  FLIGHT STATUS  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
        for (registration customer : registration.userList) {
            List<flight> f = customer.getflightsOn();
            int size = customer.getflightsOn().size();
            if (userID.equals(customer.getUserID())) {
                for (int i = 0; i < size; i++) {
                    System.out.println(toString((i + 1), f.get(i), customer));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
                }
            }
        }
    }

    /*overloaded toString() method for displaying all users in a flight....*/

    public String toString(int serialNum, registration customer, int index) {
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |       %-7s  |", "", (serialNum + 1), customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhone(), customer.ticketsBought.get(index));
    }


    @Override
    public void displayRegisteredUsersForAllFlight() {
        System.out.println();
        for (flight flight : flight.getFlights()) {
            List<registration> c = flight.getListOfRegisteredCustomersInAFlight();
            int size = flight.getListOfRegisteredCustomersInAFlight().size();
            if (size != 0) {
                displayHeaderForUsers(flight, c);
            }
        }
    }

    int flightIndex(List<flight> flightList, flight flight) {
        int i = -1;
        for (flight flight1 : flightList) {
            if (flight1.equals(flight)) {
                i = flightList.indexOf(flight1);
            }
        }
        return i;
    }

    @Override
    public void displayRegisteredUsersForASpecificFlight(String flightNum) {
        System.out.println();
        for (flight flight : flight.getFlights()) {
            List<registration> c = flight.getListOfRegisteredCustomersInAFlight();
            if (flight.getFlightNum().equalsIgnoreCase(flightNum)) {
                displayHeaderForUsers(flight, c);
            }
        }
    }  
   
}
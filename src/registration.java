import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Scanner;


public class registration {

    private final String userID;
    private final String password;
    public List<flight> flightsOn;
    public List<Integer> ticketsBought;
    private String name;
    private String email;
    private String phone;
    private String address;
    public static final List<registration> userList = user.getUserList();

   
    public List<flight> getflightsOn() {
        return flightsOn;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserID() {
        return userID;
    }
    public String getPassword() {
        return password;
    }
    public List<Integer> getTicketsBought() {
        return ticketsBought;
    }
 
    public String getNewID() {
        return userID;
    }
    public String newUserID(){
        Random rand = new Random();
        String userID = Integer.toString(rand.nextInt(1000));

        while (Integer.parseInt(userID) < 1000) {
            userID = Integer.toString(rand.nextInt(1000));
        }
        return userID;
    }
    
    registration() {
        this.userID = null;
        this.name = null;
        this.email = null;
        this.password = null;
        this.phone = null;
        this.address = null;
    }
    
    registration(String name, String email, String password, String phone, String address) {   
        this.userID = newUserID();
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.flightsOn = new ArrayList<>();
        this.ticketsBought = new ArrayList<>();
    }

   public void addNewCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("input your name: ");
        String name = input.nextLine();
        System.out.println("input your email address: ");
        String email = input.nextLine();
        System.out.println("input your Password: ");
        String password = input.nextLine();
        System.out.println("input your Phone number: ");
        String phone = input.nextLine();
        System.out.println("input your address: ");
        String address = input.nextLine();
        
        userList.add(new registration(name, email, password, phone, address));
    }

    /**
     * Returns String consisting of customers data(name, age, email etc...) for displaying.
     * randomIDDisplay() adds space between the userID for easy readability.
     *
     * @param i for serial numbers.
     * @return customers data in String
     */
    private String toString(int i) {
        return String.format("%15s| %-40s | %-40s | %-40s | %-12s |", "", i, userID, name, email, address, phone);
    }

    /**
     * Searches for customer with the given ID and displays the customers' data if found.
     *
     * @param ID of the searching/required customer
     */
    public void existingUser(String ID) {
        boolean exists = false;
        registration customerWithID = userList.get(0);
        for (int i = 0; i < userList.size(); i++) {
            registration user = userList.get(i);
            if (ID.equals(user.getUserID())) {
                System.out.println("Existing User: ");
                exists = true;
                customerWithID = user;
                break;
            }
        }
        if (exists) {
            System.out.println(customerWithID.toString(1));
        } else {
            System.out.println("Not Found");
        }
    }

public void deleteUser(String ID) {
        boolean userFound = false;
        for (int i = 0; i < userList.size(); i++) {
            registration customer = userList.get(i);
            if (ID.equals(customer.getUserID())) {
                userFound = true;
                userList.remove(i);
                System.out.println("Customer: ");
                break;
            }
        }
        if (!userFound) {
            System.out.println("Invalid");
        }
    }

    /**
     * Associates a new flight with the specified customer
     *
     * @param f flight to associate
     */
    void addFlightToUser(flight f) {
        this.flightsOn.add(f);
//        numOfFlights++;
    }

    /**
     * Adds numOfTickets to already booked flights
     * @param index at which flight is registered in the arraylist
     * @param numOfTickets how many tickets to add
     */
    void addTixFlightToUser(int x, int tickets) {
        int numTickets = ticketsBought.get(x) + tickets;
        this.ticketsBought.set(x, numTickets);
    }

}
/*
 * This class is intended to be the main class for this Project. All necessary methods are getting calls from this class.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class user {

    //        ************************************************************ Fields ************************************************************

    /*2D Array to store administration credentials. Default credentials are stored on [0][0] index. Max number of administration can be 10....*/
    static String[][] adminUserNameAndPassword = new String[10][2];
    private static List<registration> userList = new ArrayList<>();
    
    static void mainMenu() {
        System.out.println("Press 0 to Exit.");
        System.out.println("Press 1 to Login as Passenger.");
        System.out.println("Press 2 to Register as Pasenger.");
        System.out.println("Enter the menu choice:    ");
    }

    //        ************************************************************ Behaviors/Methods ************************************************************

    public static void main(String[] args) {
        int countNumOfUsers = 1;
        RolesAndPermissions r1 = new RolesAndPermissions();
        flight f1 = new flight();
        flightReservation flightBooking = new flightReservation();
        registration c1 = new registration();
        f1.flightScheduler();
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to SkyPass AirLines: Please enter a value.");
        mainMenu();
        int menuChoice = input.nextInt();
        while (menuChoice < 0 || menuChoice > 3) {
            System.out.print("Invalid Input, Please enter value between 0 - 2: ");
            menuChoice = input.nextInt();
        }


        do {
            Scanner input2 = new Scanner(System.in);
            /* If desiredOption is 1 then call the login method.... if default credentials are used then set the permission
             * level to standard/default where the user can just view the customer's data...if not found, then return -1, and if
             * data is found then show the user display menu for adding, updating, deleting and searching users/customers...
             * */
             if (menuChoice == 1) {
            	System.out.println("User login");
                System.out.print("\n\nEnter Email to Login : \t");
                String userName = input2.nextLine();
                System.out.print("Enter Password : \t");
                String password = input2.nextLine();
                String[] result = r1.userRegistered(userName, password).split("-");

                if (Integer.parseInt(result[0]) == 1) {
                    int menuAction;
                    System.out.printf("Logged in. Enter a value from the menu to complete desired action");
                    do {
                        System.out.printf("Menu");
                        System.out.printf("Enter 1 to Book a flight....\n");
                        System.out.printf("Enter 2 to delete your account....\n");
                        System.out.printf("Enter 3 to Display Flight Schedule....\n");
                        System.out.printf("Enter 4 to Cancel a Flight....\n");
                        System.out.printf("Enter 5 to Display all flights registered");
                        System.out.printf("Enter 0 to Go back to the Main Menu/Logout");
                        System.out.println("Enter menu option:   ");
                        menuAction = input.nextInt();
                        if (menuAction == 1) {
                        	System.out.println("Book your Flight");
                            f1.displayFlightSchedule();
                            System.out.print("\nEnter the desired flight number to book :\t ");
                            String flightToBeBooked = input2.nextLine();
                            System.out.print("Enter the Number of tickets for " + flightToBeBooked + " flight :   ");
                            int numTickets = input.nextInt();
                            while (numTickets > 10) {
                                System.out.print("ERROR!! You can't book more than 10 tickets at a time for single flight....Enter number of tickets again : ");
                                numTickets = input.nextInt();
                            }
                            flightBooking.bookFlight(flightToBeBooked, numTickets, result[1]);
                        } 
                        else if (menuAction == 2) {
                            System.out.print("Delete account? Enter Y or y to continue:");
                            char confirmationChar = input2.nextLine().charAt(0);
                            if (confirmationChar == 'Y' || confirmationChar == 'y') {
                                c1.deleteUser(result[1]);
                                System.out.printf("account deleted");
                                menuAction = 0;
                            } else {
                                System.out.println("Action has been cancelled...");
                            }
                        } else if (menuAction == 3) {
                        	System.out.println("Flight Schedule: ");
                            f1.displayFlightSchedule();
                        
                        } else if (menuAction == 4) {
                        	System.out.println("Cancel Flight: ");
                        	flightBooking.cancelFlight(result[1]);
                        } else if (menuAction == 5) {
                        	System.out.println("Your Flights: ");
                        	flightBooking.displayFlightsRegisteredByOneUser(result[1]);
                        } else {
                        	 if(menuAction != 0) {
                        		 System.out.println("Invalid, Try again"); 
                        	 }
                             menuAction = 0;
                          }
                        } while (menuAction != 0);

                } else {
                    System.out.printf("New User? Try Registering");
                }
            } else if (menuChoice == 2) {
            	System.out.println("Sign Up: ");
                c1.addNewCustomer();
             
            }

            mainMenu();
            menuChoice = input2.nextInt();
            while (menuChoice < 0 || menuChoice > 8) {
            	System.out.print("Invalid Input, Please enter value between 0 - 2: ");
                menuChoice = input2.nextInt();
            }
        } while (menuChoice != 0);
    
    }
    public static List<registration> getUserList() {
        return userList;
    }
}
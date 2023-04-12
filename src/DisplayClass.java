import java.util.List;

public interface DisplayClass {

    void displayRegisteredUsersForAllFlight();

    void displayRegisteredUsersForASpecificFlight(String flightNum);

    void displayHeaderForUsers(flight flight, List<registration> c);

    void displayFlightsRegisteredByOneUser(String userID);

}

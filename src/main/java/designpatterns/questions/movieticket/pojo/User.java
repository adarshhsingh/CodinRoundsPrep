package designpatterns.questions.movieticket.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Booking> bookingHistory;

    public void register() {
        // Registration logic
    }

    public void login() {
        // Login logic
    }

    public List<Booking> viewBookingHistory() {
        // Return booking history
        return new ArrayList<>();
    }
}

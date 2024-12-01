package designpatterns.questions.movieticket.pojo;

import java.util.List;

public class Booking {
    private int id;
    private User user;
    private Show show;
    private List<Seat> selectedSeats;
    private String status;

    public Booking(User user, Show show, List<Seat> selectedSeats) {
        this.user = user;
        this.show = show;
        this.selectedSeats = selectedSeats;
        this.status = "Pending";
    }

    public void createBooking() {
        // Booking creation logic
    }

    public void viewBooking() {
        // View booking details
    }
}

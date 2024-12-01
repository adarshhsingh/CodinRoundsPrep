package designpatterns.questions.movieticket.pojo;
import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private int id;
    private Event event;
    private Venue venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Seat> seats;

    public Show(Event event, Venue venue, LocalDateTime startTime, LocalDateTime endTime, List<Seat> seats) {
        this.event = event;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
    }

    // Getters and setters
}


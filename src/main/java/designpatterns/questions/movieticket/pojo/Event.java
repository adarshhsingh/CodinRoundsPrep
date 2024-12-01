package designpatterns.questions.movieticket.pojo;

import designpatterns.questions.movieticket.pojo.Show;
import designpatterns.questions.movieticket.pojo.Venue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int id;
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private Venue venue;
    private List<Show> shows;

    public static List<Event> search(String query) {
        // Search logic
        return new ArrayList<>();
    }

    public void viewDetails() {
        // View event details
    }
}

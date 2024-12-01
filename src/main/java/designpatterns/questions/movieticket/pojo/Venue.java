package designpatterns.questions.movieticket.pojo;

public class Venue {
    private int id;
    private String name;
    private String location;
    private int totalSeats;

    public Venue(String name, String location, int totalSeats) {
        this.name = name;
        this.location = location;
        this.totalSeats = totalSeats;
    }

    // Getters and setters
}

package designpatterns.questions.movieticket.pojo;

public class Admin {
    private int id;
    private String name;
    private String email;
    private String password;

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addEvent(Event event) {
        // Add event logic
    }

    public void updateEvent(Event event) {
        // Update event logic
    }

    public void deleteEvent(int eventId) {
        // Delete event logic
    }
}


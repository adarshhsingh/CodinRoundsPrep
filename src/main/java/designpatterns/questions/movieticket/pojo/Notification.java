package designpatterns.questions.movieticket.pojo;

public class Notification {
    private int id;
    private User user;
    private Event event;
    private String message;

    public Notification(User user, Event event, String message) {
        this.user = user;
        this.event = event;
        this.message = message;
    }

    public void sendNotification() {
        // Send notification logic
    }
}

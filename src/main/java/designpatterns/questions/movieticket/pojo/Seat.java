package designpatterns.questions.movieticket.pojo;

public class Seat {
    private int seatNumber;
    private boolean isReserved;
    private double price;

    public Seat(int seatNumber, double price) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.isReserved = false;
    }

    // Getters and setters
}


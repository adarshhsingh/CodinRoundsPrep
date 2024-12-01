package designpatterns.structural;

// Subsystem classes
class FlightBooking {
    public void bookFlight() {
        System.out.println("Flight booked successfully.");
    }
}

class HotelBooking {
    public void bookHotel() {
        System.out.println("Hotel booked successfully.");
    }
}

class CarRental {
    public void rentCar() {
        System.out.println("Car rented successfully.");
    }
}

// Facade
class TravelFacade {
    private FlightBooking flightBooking;
    private HotelBooking hotelBooking;
    private CarRental carRental;

    public TravelFacade() {
        this.flightBooking = new FlightBooking();
        this.hotelBooking = new HotelBooking();
        this.carRental = new CarRental();
    }

    public void bookCompleteTravelPackage() {
        flightBooking.bookFlight();
        hotelBooking.bookHotel();
        carRental.rentCar();
    }
}


/**
 * When to Use:
 *
 * 1. To simplify a client’s interaction with a system by
 *    hiding the underlying complex code.
 *
 * 2. To interact with the methods present in a library without
 *    knowing the processing that happens in the background.
 *
 * Example: Imagine a complex system for booking a flight, hotel, and car rental.
 */
public class FacadePatternMain {
    public static void main(String[] args) {
        TravelFacade travelFacade = new TravelFacade();
        travelFacade.bookCompleteTravelPackage();
    }
}

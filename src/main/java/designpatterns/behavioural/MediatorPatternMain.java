package designpatterns.behavioural;

import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface AirTrafficControl {
    void sendMessage(String message, Aircraft aircraft);
}

// Concrete Mediator
class AirTrafficControlCenter implements AirTrafficControl {
    private List<Aircraft> aircrafts;

    public AirTrafficControlCenter() {
        aircrafts = new ArrayList<>();
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    @Override
    public void sendMessage(String message, Aircraft aircraft) {
        for (Aircraft a : aircrafts) {
            if (a != aircraft) {
                a.receiveMessage(message);
            }
        }
    }
}

// Colleague Interface
abstract class Aircraft {
    protected AirTrafficControl atc;
    protected String id;

    public Aircraft(AirTrafficControl atc, String id) {
        this.atc = atc;
        this.id = id;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}

// Concrete Colleague
class Boeing737 extends Aircraft {
    public Boeing737(AirTrafficControl atc, String id) {
        super(atc, id);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(id + " sending message: " + message);
        atc.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(id + " received message: " + message);
    }
}


/**
 * Mediator Pattern
 *
 * When to Use:
 *
 * 1. To avoid the tight coupling of objects in a system with many objects.
 * 2. To improve code readability.
 * 3. To make code easier to maintain.
 *
 * Real-World Example: Air Traffic Control System
 *
 * Imagine an air traffic control system that manages communication between multiple aircraft.
 */
public class MediatorPatternMain {
    public static void main(String[] args) {
        AirTrafficControl atc = new AirTrafficControlCenter();
        Aircraft aircraft1 = new Boeing737(atc, "B737-1");
        Aircraft aircraft2 = new Boeing737(atc, "B737-2");
        Aircraft aircraft3 = new Boeing737(atc, "B737-3");

        ((AirTrafficControlCenter) atc).addAircraft(aircraft1);
        ((AirTrafficControlCenter) atc).addAircraft(aircraft2);
        ((AirTrafficControlCenter) atc).addAircraft(aircraft3);

        aircraft1.sendMessage(aircraft1.id+ " Requesting takeoff clearance.");
        aircraft2.sendMessage(aircraft2.id +" Requesting landing clearance.");
    }
}

package designpatterns.behavioural;

import java.util.ArrayList;
import java.util.List;

// Subject
class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

// Observer Interface
interface Observer {
    void update(int temperature);
}

// Concrete Observer
class TemperatureDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("TemperatureDisplay: Current temperature is " + temperature + " degrees.");
    }
}

// Concrete Observer
class ForecastDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("ForecastDisplay: Forecasting based on current temperature of " + temperature + " degrees.");
    }
}


/**
 * ObserverPattern
 *
 * When to Use:
 *
 * 1. To improve code management by breaking down large
 *    applications into a system of loosely-coupled objects.
 *
 * 2. To improve communication between different parts of the application.
 *
 * 3. To create a one-to-many dependency between objects that are loosely coupled.
 *
 * Example: Real-World Example: Weather Station
 *
 * Imagine a weather station that notifies multiple displays when the weather changes.
 */
public class ObserverPatternMain {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        TemperatureDisplay tempDisplay = new TemperatureDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        station.addObserver(tempDisplay);
        station.addObserver(forecastDisplay);

        station.setTemperature(25);
        station.setTemperature(30);
    }
}

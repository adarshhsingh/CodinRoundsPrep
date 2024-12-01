package machinecoding.parkinglot;

public class ParkingSpot {
    String spotId;
    ParkingSpotType type;
    boolean isAvailable;
    Vehicle currentVehicle;
    public void assignVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.isAvailable = false;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
        this.isAvailable = true;
    }

    public boolean isSpotAvailable() {
        return isAvailable;
    }
}

package machinecoding.parkinglot;

import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private String floorNumber;
    private Map<ParkingSpotType, List<ParkingSpot>> availableSpots;
    private Map<String, ParkingSpot> occupiedSpots;
}

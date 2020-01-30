package ParkingLot;

public class Bus extends Vehicle {

    public Bus() {
        spotsNeeded = 5;
        size = VehicleSize.Large;
    }

    /** Checks if the spot is a Large. Doesn't check num of spots */
    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.Large;
    }

    @Override
    public void print() {
        System.out.print("B");
    }
}

package ParkingLot;

public class Car extends Vehicle {
    public Car() {
        spotsNeeded = 1;
        size = VehicleSize.Compact;
    }

    /** Checks if the spot is a Compact or a Large. */
    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.Large || spot.getSize() == VehicleSize.Compact;
    }

    @Override
    public void print() {
        System.out.print("C");
    }


}

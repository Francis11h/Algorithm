package ParkingLot;

public class Motorcycle extends Vehicle {
    public Motorcycle() {
        spotsNeeded = 1;
        size = VehicleSize.Motorcycle;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return true;
    }

    @Override
    public void print() {
        System.out.print("M");
    }
}

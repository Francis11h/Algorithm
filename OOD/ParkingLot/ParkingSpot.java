package ParkingLot;

/**
 * The ParkingSpot is implemented by having just a variable which represents the size of the spot
 * We could have implemented this by having classes for LargeSpot, CompactSpot, and MotorcycleSpot which inherit from ParkingSpot, but this is probably overkilled.
 * The spots probably do not have different behaviors, other than their sizes.
 */
public class ParkingSpot {
    private Vehicle vehicle;
    private VehicleSize spotSize;
    private int row;
    private int spotNumber;
    //ToDo
    private Level level;

    public ParkingSpot(Level lvl, int r, int n, VehicleSize s) {
        this.level = lvl;
        this.row = r;
        this.spotNumber = n;
        this.spotSize = s;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }
    /** Check if the spot is big enough and is available */
    public boolean canFitVehicle(Vehicle vehicle) {
        /**
         * Within an instance method or a constructor,
         * 	    this is a reference to the "current object" —
         * 	        the object "whose method or constructor is being called".
         */
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    /** Park vehicle in this spot. */
    public boolean park(Vehicle v) {
        if (!canFitVehicle(v)) return false;

        this.vehicle = v;               // assign v to this parkingspot's attribute vehicle
        vehicle.parkingSpot(this);  // call method in the class Vehicle
        return true;
    }

    /** Remove vehicle from spot, and notify level that a new spot is available */
    public void removeVehicle() {
        level.spotFreed();
        vehicle = null;
    }

    // print spot's info
    public void print() {
        if (vehicle == null) {
            if (spotSize == VehicleSize.Compact) {
                System.out.print("c");
            } else if (spotSize == VehicleSize.Large) {
                System.out.print("l");
            } else if (spotSize == VehicleSize.Motorcycle) {
                System.out.print("m");
            }
        } else {
            vehicle.print();
        }
    }




    public int getRow() {
        return row;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleSize getSize() {
        return spotSize;
    }
}

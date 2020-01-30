package ParkingLot;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    protected int spotsNeeded;
    protected VehicleSize size;
    protected String licensePlate; // id for a vehicle
    // id for parking where may occupy multi
    protected List<ParkingSpot> parkingSpots = new ArrayList<>();


    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    /** Park vehicle in this spot (among others,potentially) */
    public void parkingSpot(ParkingSpot s) {
        parkingSpots.add(s);
    }

    /** Remove vehicle from spot, and notify spot
         that it's gone */
//    public void clearSpots() {
//        for (int i = 0; i < parkingSpots.size(); i++) {
//            parkingSpots.get(i).removeVehicle();
//        }
//        parkingSpots.clear();   //Removes all of the elements from this list
//    }

    /** Compares the SIZE only.
        It does not check if it has enough spots. */

    /**this abstract method need to be implement in subclas*/
    public abstract boolean canFitInSpot(ParkingSpot spot);

    public abstract void print();
}

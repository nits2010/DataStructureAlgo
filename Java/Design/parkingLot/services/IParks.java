package Java.Design.parkingLot.services;

import parkingLot.slots.Slot;
import parkingLot.vehicle.Vehicle;

import java.util.Optional;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public interface IParks {

    Optional<Slot> park(Vehicle vehicle);

    void unPark(Vehicle vehicle);
}

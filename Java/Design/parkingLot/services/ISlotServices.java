package Java.Design.parkingLot.services;

import Java.Design.parkingLot.slots.Slot;
import Java.Design.parkingLot.vehicle.Vehicle;

import java.util.Optional;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public interface ISlotServices {


    Optional<Slot> getFreeSlot();

    Slot getSlotOfThisVehicle(Vehicle vehicle);

    void fillSlot(Vehicle vehicle, Slot slot);

    void putBack(int level, Slot slot);
}

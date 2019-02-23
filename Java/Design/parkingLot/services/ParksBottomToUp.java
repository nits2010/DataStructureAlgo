package Java.Design.parkingLot.services;

import parkingLot.ParkingLot;
import parkingLot.slots.Slot;
import parkingLot.vehicle.Vehicle;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public class ParksBottomToUp implements IParks {


    private ParkingLot parkingLot;

    private SlotServices slotServices;

    private Map<Integer, List<Slot>> vehicleInfo = new HashMap<>();


    public ParksBottomToUp(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.slotServices = new SlotServices(parkingLot);
    }


    @Override
    public Optional<Slot> park(Vehicle vehicle) {

        Optional<Slot> freeSlot = slotServices.getFreeSlot();

        Slot slot = null;
        if (freeSlot.isPresent()) {
            slot = freeSlot.get();
            slot.setOccupied(true);
            slot.setVehicle(vehicle);

            insertIntoParkInfo(slot, vehicle);
            slotServices.fillSlot(vehicle, slot);

        }

        return Optional.ofNullable(slot);
    }

    private void insertIntoParkInfo(Slot slot, Vehicle vehicle) {

        int registrationNumberHash = vehicle.getRegistrationNumber().hashCode();
        int colorModelHash = Objects.hash(vehicle.getColor(), vehicle.getModel());

        if (!vehicleInfo.containsKey(registrationNumberHash)) {
            vehicleInfo.put(registrationNumberHash, Collections.singletonList(slot));
        }

        List<Slot> slots = vehicleInfo.get(colorModelHash);

        if (null == slots) {
            slots = new LinkedList<>();
            slots.add(slot);
            vehicleInfo.put(colorModelHash, slots);
        } else {
            slots.add(slot);
        }


    }


    private void removeFromParkInfo(Slot slot, Vehicle vehicle) {

        int registrationNumberHash = vehicle.getRegistrationNumber().hashCode();
        int colorModelHash = Objects.hash(vehicle.getColor(), vehicle.getModel());

        vehicleInfo.remove(registrationNumberHash);
        vehicleInfo.get(colorModelHash).remove(slot);


    }


    @Override
    public void unPark(Vehicle vehicle) {
        Slot slot = slotServices.getSlotOfThisVehicle(vehicle);
        slotServices.putBack(slot.getLevelNumber(), slot);
        removeFromParkInfo(slot, vehicle);
    }
}

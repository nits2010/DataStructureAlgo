package Java.Design.parkingLot.services;

import Java.Design.parkingLot.parkingLot.ParkingLot;
import Java.Design.parkingLot.slots.Slot;
import Java.Design.parkingLot.vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public class SlotServices implements ISlotServices {

    private final Map<Integer, List<Slot>> freeSlots;

    private final Map<String, Slot> occupiedSlots;

    public SlotServices(ParkingLot parkingLot) {
        this.freeSlots = getFreeSlots(parkingLot);
        this.occupiedSlots = getOccupiedSlots(parkingLot);

    }

    private Map<String, Slot> getOccupiedSlots(ParkingLot parkingLot) {

        Map<String, Slot> occupiedSlots = new HashMap<>();
        List<List<Slot>> parkingLots = parkingLot.getParkingLot();

        List<Slot> occupiedSlotsOnThisLevel = null;
        for (int level = 0; level < parkingLots.size(); level++) {
            List<Slot> slotsOnThisLevel = parkingLots.get(level);

            occupiedSlotsOnThisLevel = slotsOnThisLevel
                    .stream()
                    .filter(slot -> slot.isOccupied())
                    .collect(Collectors.toList());

        }

        for (Slot slot : occupiedSlotsOnThisLevel) {
            occupiedSlots.put(slot.getVehicle().getRegistrationNumber(), slot);

        }

        return occupiedSlots;
    }


    private Map<Integer, List<Slot>> getFreeSlots(final ParkingLot parkingLot) {

        Map<Integer, List<Slot>> freeSlots = new HashMap<>();
        List<List<Slot>> parkingLots = parkingLot.getParkingLot();

        for (int level = 0; level < parkingLots.size(); level++) {
            List<Slot> slotsOnThisLevel = parkingLots.get(level);

            List<Slot> freeSlotsOnThisLevel = slotsOnThisLevel.stream().filter(slot -> !slot.isOccupied()).collect(Collectors.toList());

            freeSlots.put(level, freeSlotsOnThisLevel);


        }
        return freeSlots;
    }

    @Override
    public Optional<Slot> getFreeSlot() {

        for (Integer level : freeSlots.keySet()) {

            List<Slot> thisLevelSlots = freeSlots.get(level);

            if (thisLevelSlots.size() > 0) {
                Slot slot = thisLevelSlots.get(0);
                thisLevelSlots.remove(0);
                return Optional.of(slot);
            }
        }


        return Optional.empty();
    }


    @Override
    public void fillSlot(Vehicle vehicle, Slot slot) {
        occupiedSlots.put(vehicle.getRegistrationNumber(), slot);
    }

    @Override
    public Slot getSlotOfThisVehicle(Vehicle vehicle) {
        return occupiedSlots.get(vehicle.getRegistrationNumber());
    }

    @Override
    public void putBack(int level, Slot slot) {
        slot.setOccupied(false);
        freeSlots.get(level).add(slot);
    }
}

package Java.Design.parkingLot.parkingLot;

import Java.Design.parkingLot.slots.Slot;
import Java.Design.parkingLot.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public class ParkingLot {

    private final List<List<Slot>> parkingLot;

    public ParkingLot(int levels, Map<Integer, Integer> slotsInfo) {

        parkingLot = new ArrayList<>(levels);

        for (Integer level : slotsInfo.keySet()) {

            int noOfSlotsOnThisLevel = slotsInfo.get(level);

            List<Slot> slots = new ArrayList<>(noOfSlotsOnThisLevel);

            for (int i = 0; i < noOfSlotsOnThisLevel; i++) {
                slots.add(new Slot(VehicleType.CAR, level));
            }

            parkingLot.add(slots);
        }

    }



    public List<List<Slot>> getParkingLot() {
        return parkingLot;
    }
}

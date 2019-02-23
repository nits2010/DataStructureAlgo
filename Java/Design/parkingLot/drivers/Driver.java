package Java.Design.parkingLot.drivers;

import parkingLot.ParkingLot;
import parkingLot.constants.Color;
import parkingLot.parking.services.IParks;
import parkingLot.parking.services.ParksBottomToUp;
import parkingLot.slots.Slot;
import parkingLot.vehicle.Car;
import parkingLot.vehicle.Vehicle;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public class Driver {


    public static void main(String args[]) {


        int levels = 2;

        Map<Integer, Integer> slotsInfo = getSlotsInfo(levels);


        ParkingLot parkingLot = new ParkingLot(levels, slotsInfo);


        IParks parkinService = new ParksBottomToUp(parkingLot);


        Vehicle maruti = new Car("KA01XYZ", Color.BLUE, "maruti");
        Vehicle maruti1 = new Car("KA01X678", Color.GREEN, "maruti");
        Vehicle maruti2 = new Car("KA01Xuyu", Color.RED, "maruti");
        Vehicle maruti3 = new Car("KA01X628", Color.GREEN, "maruti");
        Vehicle maruti4 = new Car("KA0111uyu", Color.RED, "maruti");
        Vehicle maruti5 = new Car("KA0111usdyu", Color.RED, "maruti");

        List<Vehicle> vehicles = Arrays.asList(maruti, maruti1, maruti2, maruti3, maruti4, maruti5);


        for (Vehicle vehicle : vehicles) {
            Optional<Slot> slot = parkinService.park(vehicle);

            if (slot.isPresent()) {
                System.out.println("Level " + slot.get().getLevelNumber() + " vehicle : " + vehicle);
            } else {
                System.out.println("Parking Full");

            }


        }

        parkinService.unPark(maruti3);

        Optional<Slot> slot = parkinService.park(maruti3);

        if (slot.isPresent()) {
            System.out.println("Level " + slot.get().getLevelNumber() + " vehicle : " + maruti3);
        } else {
            System.out.println("Parking Full");

        }


    }


    public static Map<Integer, Integer> getSlotsInfo(int levels) {
        Map<Integer, Integer> slotsInfo = new HashMap<>();


        for (int i = 0; i < levels; i++) {
            slotsInfo.put(i, 2);
        }

        return slotsInfo;
    }
}

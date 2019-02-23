package Java.Design.parkingLot.vehicle;

import Java.Design.parkingLot.constants.Color;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public class Car extends Vehicle {

    public Car(String registrationNumber, Color color, String model) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.model = model;
    }



    @Override
    VehicleType getType() {
        return VehicleType.CAR;
    }
}

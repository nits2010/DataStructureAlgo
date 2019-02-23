package Java.Design.parkingLot.vehicle;

import Java.Design.parkingLot.constants.Color;

import java.util.Objects;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public abstract class Vehicle {

    public String registrationNumber;

    public VehicleType type;

    public Color color;

    public String model;


    abstract VehicleType getType();

    public Color getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNumber, vehicle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", type=" + type +
                ", color=" + color +
                ", model='" + model + '\'' +
                '}';
    }
}

package Java.Design.parkingLot.slots;

import parkingLot.vehicle.Vehicle;
import parkingLot.vehicle.VehicleType;

import java.util.Objects;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 23/02/19
 * Description:
 */
public class Slot {

    private VehicleType type;

    //Is occupied
    private boolean occupied = false;


    private Vehicle vehicle;


    private int levelNumber;


    public Slot(VehicleType type, int levelNumber) {
        this.type = type;
        this.levelNumber = levelNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "type=" + type +
                ", occupied=" + occupied +
                ", vehicle=" + vehicle +
                ", levelNumber=" + levelNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(vehicle, slot.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle, levelNumber);
    }
}

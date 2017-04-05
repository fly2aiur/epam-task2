package by.epam.cherednik.entity.car.passenger;

import by.epam.cherednik.entity.car.AbstractCar;

public abstract class AbstractPassengerCar extends AbstractCar {
    protected int maxPassengers;

    public AbstractPassengerCar(int id, int maxPassengers) {
        super(id);
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }
}

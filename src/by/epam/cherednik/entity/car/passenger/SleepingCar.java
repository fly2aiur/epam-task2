package by.epam.cherednik.entity.car.passenger;

import by.epam.cherednik.entity.car.CarType;

public class SleepingCar extends AbstractPassengerCar {
    private int bedsPerCompartment;

    public SleepingCar(int id, int maxPassengers, int bedsPerCompartment) {
        super(id, maxPassengers);
        this.bedsPerCompartment = bedsPerCompartment;
    }

    public int getBedsPerCompartment() {
        return bedsPerCompartment;
    }

    public void setBedsPerCompartment(int bedsPerCompartment) {
        this.bedsPerCompartment = bedsPerCompartment;
    }

    @Override
    public CarType getType() {
        return CarType.SLEEPING_CAR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleepingCar coachCar = (SleepingCar) o;
        return getId() == coachCar.getId()
                && getMaxPassengers() == coachCar.getMaxPassengers()
                && getBedsPerCompartment() == coachCar.getBedsPerCompartment();
    }

    @Override
    public int hashCode() {
        int result;
        result = getId();
        result = 31 * result + getMaxPassengers();
        result = 31 * result + getBedsPerCompartment();
        return result;
    }

    @Override
    public String toString() {
        return "CoachCar{" +
                "id=" + getId() +
                ", maxPassengers=" + getMaxPassengers() +
                ", bedsPerCompartment=" + getBedsPerCompartment() +
                "} ";
    }
}

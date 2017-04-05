package by.epam.cherednik.entity.car.freight;

import by.epam.cherednik.entity.car.CarType;

public class BaggageCar extends AbstractFreightCar {
    private double maxCargoWeightPerPerson;

    public BaggageCar(int id, double maxCargoWeight, double maxCargoWeightPerPerson) {
        super(id, maxCargoWeight);
        this.maxCargoWeightPerPerson = maxCargoWeightPerPerson;
    }

    public double getMaxCargoWeightPerPerson() {
        return maxCargoWeightPerPerson;
    }

    public void setMaxCargoWeightPerPerson(double maxCargoWeightPerPerson) {
        this.maxCargoWeightPerPerson = maxCargoWeightPerPerson;
    }

    @Override
    public CarType getType() {
        return CarType.BAGGAGE_CAR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaggageCar that = (BaggageCar) o;
        return getId() == that.getId() &&
                Double.compare(that.getMaxCargoWeightPerPerson(), getMaxCargoWeightPerPerson()) == 0 &&
                Double.compare(that.getMaxCargoWeight(), getMaxCargoWeight()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        temp = Double.doubleToLongBits(getMaxCargoWeightPerPerson());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getMaxCargoWeight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BaggageCar{" +
                "id=" + getId() +
                ", maxCargoWeight=" + getMaxCargoWeight() +
                ", maxCargoWeightPerPerson=" + getMaxCargoWeightPerPerson() +
                "} ";
    }
}

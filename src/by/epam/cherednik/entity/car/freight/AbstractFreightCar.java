package by.epam.cherednik.entity.car.freight;

import by.epam.cherednik.entity.car.AbstractCar;

public abstract class AbstractFreightCar extends AbstractCar {
    private double maxCargoWeight;

    public AbstractFreightCar(int id, double maxCargoWeight) {
        super(id);
        this.maxCargoWeight = maxCargoWeight;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(double maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }
}

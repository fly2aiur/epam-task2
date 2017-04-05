package by.epam.cherednik.entity.train;

import by.epam.cherednik.entity.car.AbstractCar;

import java.util.ArrayList;

public class Train {
    private ArrayList<AbstractCar> cars;

    public Train() {
        cars = new ArrayList<>();
    }

    public void add(AbstractCar car) {
        cars.add(car);
    }

    public void remove(AbstractCar car) {
        cars.remove(car);
    }

    public ArrayList<AbstractCar> getCars() {
        return cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        return getCars().equals(train.getCars());
    }

    @Override
    public int hashCode() {
        return getCars().hashCode();
    }

    @Override
    public String toString() {
        return "Train{" +
                "cars=" + cars +
                '}';
    }
}

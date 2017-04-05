package by.epam.cherednik.util;

import by.epam.cherednik.entity.car.AbstractCar;
import by.epam.cherednik.entity.car.freight.AbstractFreightCar;
import by.epam.cherednik.entity.car.passenger.AbstractPassengerCar;
import by.epam.cherednik.entity.train.Train;

import java.util.List;
import java.util.stream.Collectors;

public class TrainUtil {
    public int calculateTotalPassengers(Train train) {
        int result = 0;
        for (AbstractCar car : getPassengerCars(train)) {
            result += ((AbstractPassengerCar) car).getMaxPassengers();
        }
        return result;
    }

    public double calculateTotalCargoWeight(Train train) {
        double result = 0.0;
        for (AbstractCar car : getFreightCars(train)) {
            result += ((AbstractFreightCar) car).getMaxCargoWeight();
        }
        return result;
    }

    public List<AbstractCar> findByPassengers(Train train, int passengers) {
        return getPassengerCars(train).stream()
                .filter(o -> ((AbstractPassengerCar) o).getMaxPassengers() > passengers)
                .collect(Collectors.toList());
    }

    public List<AbstractCar> getPassengerCars(Train train) {
        return train.getCars().stream()
                .filter(o -> o instanceof AbstractPassengerCar).collect(Collectors.toList());
    }

    public List<AbstractCar> getFreightCars(Train train) {
        return train.getCars().stream()
                .filter(o -> o instanceof AbstractFreightCar).collect(Collectors.toList());
    }
}
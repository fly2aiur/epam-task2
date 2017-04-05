package by.epam.cherednik.util.comparator;

import by.epam.cherednik.entity.car.AbstractCar;

import java.util.Comparator;

public class IdComparator implements Comparator<AbstractCar> {
    @Override
    public int compare(AbstractCar o1, AbstractCar o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}

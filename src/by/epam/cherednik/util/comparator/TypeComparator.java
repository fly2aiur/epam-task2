package by.epam.cherednik.util.comparator;

import by.epam.cherednik.entity.car.AbstractCar;

import java.util.Comparator;

public class TypeComparator implements Comparator<AbstractCar> {
    @Override
    public int compare(AbstractCar o1, AbstractCar o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
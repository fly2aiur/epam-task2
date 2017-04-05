package by.epam.cherednik.util;

import by.epam.cherednik.entity.car.AbstractCar;
import by.epam.cherednik.entity.train.Train;
import by.epam.cherednik.util.comparator.IdComparator;
import by.epam.cherednik.util.comparator.TypeComparator;

import java.util.Comparator;

public class TrainSorter {
    public void sortCars(Train train, Sorting sorting) {
        train.getCars().sort(sorting.getComparator());
    }

    public static enum Sorting {
        TYPE, ID, TYPE_AND_ID;

        static {
            TYPE.comparator = new TypeComparator();
            ID.comparator = new IdComparator();
            TYPE_AND_ID.comparator = new TypeComparator().thenComparing(new IdComparator());
        }

        private Comparator<AbstractCar> comparator;

        Comparator<AbstractCar> getComparator() {
            return comparator;
        }
    }
}

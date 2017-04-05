package by.epam.cherednik.util;

import by.epam.cherednik.entity.car.freight.BaggageCar;
import by.epam.cherednik.entity.car.passenger.CoachCar;
import by.epam.cherednik.entity.car.passenger.SleepingCar;
import by.epam.cherednik.entity.train.Train;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TrainSorterTest {
    public Train train;
    public TrainSorter trainSorter;

    @Before
    public void setUp() throws Exception {
        train = new Train();
        train.add(new SleepingCar(17, 24, 2));
        train.add(new BaggageCar(33, 1400.2, 16.5));
        train.add(new BaggageCar(14, 8800, 160));
        train.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));

        trainSorter = new TrainSorter();
    }

    @After
    public void tearDown() throws Exception {
        train = null;
        trainSorter = null;
    }

    @Test
    public void sortByType() throws Exception {
        Train sorted = new Train();
        sorted.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));
        sorted.add(new SleepingCar(17, 24, 2));
        sorted.add(new BaggageCar(33, 1400.2, 16.5));
        sorted.add(new BaggageCar(14, 8800, 160));

        trainSorter.sortCars(train, TrainSorter.Sorting.TYPE);

        assertThat(train.getCars(), is(sorted.getCars()));
    }

    @Test
    public void sortById() throws Exception {
        Train sorted = new Train();
        sorted.add(new BaggageCar(14, 8800, 160));
        sorted.add(new SleepingCar(17, 24, 2));
        sorted.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));
        sorted.add(new BaggageCar(33, 1400.2, 16.5));

        trainSorter.sortCars(train, TrainSorter.Sorting.ID);

        assertThat(train.getCars(), is(sorted.getCars()));
    }

    @Test
    public void sortByTypeAndId() throws Exception {
        Train sorted = new Train();
        sorted.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));
        sorted.add(new SleepingCar(17, 24, 2));
        sorted.add(new BaggageCar(14, 8800, 160));
        sorted.add(new BaggageCar(33, 1400.2, 16.5));

        trainSorter.sortCars(train, TrainSorter.Sorting.TYPE_AND_ID);

        assertThat(train.getCars(), is(sorted.getCars()));
    }

}
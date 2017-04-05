package by.epam.cherednik.util;

import by.epam.cherednik.entity.car.AbstractCar;
import by.epam.cherednik.entity.car.freight.BaggageCar;
import by.epam.cherednik.entity.car.passenger.CoachCar;
import by.epam.cherednik.entity.car.passenger.SleepingCar;
import by.epam.cherednik.entity.train.Train;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TrainUtilTest {
    public Train train;
    public TrainUtil trainUtil;

    @Before
    public void setUp() throws Exception {
        train = new Train();
        train.add(new SleepingCar(17, 24, 2));
        train.add(new BaggageCar(33, 1200.2, 16.5));
        train.add(new BaggageCar(14, 8800, 160));
        train.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));

        trainUtil = new TrainUtil();
    }

    @After
    public void tearDown() throws Exception {
        train = null;
        trainUtil = null;
    }

    @Test
    public void calculateTotalPassengers() throws Exception {
        int actual = trainUtil.calculateTotalPassengers(train);
        int expected = 82;
        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalCargoWeight() throws Exception {
        double actual = trainUtil.calculateTotalCargoWeight(train);
        double expected = 10_000.2;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void sortByType() throws Exception {
        Train sorted = new Train();
        sorted.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));
        sorted.add(new SleepingCar(17, 24, 2));
        sorted.add(new BaggageCar(33, 1200.2, 16.5));
        sorted.add(new BaggageCar(14, 8800, 160));

        trainUtil.sortCart(train, TrainUtil.Sorting.TYPE);

        assertThat(train.getCars(), is(sorted.getCars()));
    }

    @Test
    public void sortById() throws Exception {
        Train sorted = new Train();
        sorted.add(new BaggageCar(14, 8800, 160));
        sorted.add(new SleepingCar(17, 24, 2));
        sorted.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));
        sorted.add(new BaggageCar(33, 1200.2, 16.5));

        trainUtil.sortCart(train, TrainUtil.Sorting.ID);

        assertThat(train.getCars(), is(sorted.getCars()));
    }

    @Test
    public void sortByTypeAndId() throws Exception {
        Train sorted = new Train();
        sorted.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));
        sorted.add(new SleepingCar(17, 24, 2));
        sorted.add(new BaggageCar(14, 8800, 160));
        sorted.add(new BaggageCar(33, 1200.2, 16.5));

        trainUtil.sortCart(train, TrainUtil.Sorting.TYPE_AND_ID);

        assertThat(train.getCars(), is(sorted.getCars()));
    }

    @Test
    public void findByPassengers() throws Exception {
        List<AbstractCar> expected = new ArrayList<>();
        expected.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));

        List<AbstractCar> actual = trainUtil.findByPassengers(train, 40);

        assertThat(actual, is(expected));
    }

    @Test
    public void getPassengerCars() throws Exception {
        List<AbstractCar> expected = new ArrayList<>();
        expected.add(new SleepingCar(17, 24, 2));
        expected.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));

        List<AbstractCar> actual = trainUtil.getPassengerCars(train);

        assertThat(actual, is(expected));
    }

    @Test
    public void getFreightCars() throws Exception {
        List<AbstractCar> expected = new ArrayList<>();
        expected.add(new BaggageCar(33, 1200.2, 16.5));
        expected.add(new BaggageCar(14, 8800, 160));

        List<AbstractCar> actual = trainUtil.getFreightCars(train);

        assertThat(actual, is(expected));
    }
}
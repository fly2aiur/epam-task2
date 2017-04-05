package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.freight.BaggageCar;
import by.epam.cherednik.entity.car.passenger.CoachCar;
import by.epam.cherednik.entity.car.passenger.SleepingCar;
import by.epam.cherednik.entity.train.Train;
import by.epam.cherednik.reader.Reader;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TrainParserTest {
    @Test
    public void parseAllTrains() throws Exception {
        TrainParser parser = new TrainParser();

        ArrayList<Train> actual;
        actual = parser.parseAllTrains(new Reader().readFileAsString("resources/data.txt"));

        ArrayList<Train> expected = new ArrayList<>();

        Train train1 = new Train();
        train1.add(new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO));
        train1.add(new CoachCar(29, 84, CoachCar.SeatDistribution.TWO_PLUS_THREE));

        Train train2 = new Train();
        train2.add(new SleepingCar(27, 24, 2));
        train2.add(new BaggageCar(19, 5600, 60));

        expected.add(train1);
        expected.add(train2);

        assertThat(actual, is(expected));
    }
}
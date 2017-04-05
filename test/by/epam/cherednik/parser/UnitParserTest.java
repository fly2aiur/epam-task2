package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.passenger.CoachCar;
import by.epam.cherednik.exception.ParserException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UnitParserTest {
    @Parameter
    public String source;
    @Parameter(1)
    public int expectedId;
    @Parameter(2)
    public int expectedPassengers;
    @Parameter(3)
    public CoachCar.SeatDistribution expectedSeatDistribution;
    @Parameter(4)
    public int expectedBedsPerCompartment;
    @Parameter(5)
    public double expectedMaxCargoWeight;
    @Parameter(6)
    public double expectedCargoWeightPerPassenger;
    @Parameter(7)
    public Class<? extends Exception> expectedException;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    UnitParser parser;

    @Parameterized.Parameters(name = "Test {index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "id=\"26\"; passengers=\"58\"; seat_distribution=\"two_plus_two\";beds_per_compartment=\"4\"" +
                                " max_cargo_weight=\"1222.2\" max_cargo_weight_per_passenger=\"65.5\"",
                        26,
                        58,
                        CoachCar.SeatDistribution.TWO_PLUS_TWO,
                        4,
                        1222.2,
                        65.5,
                        null,
                },
                {
                        "id=\"1\"; passengers=\"6\"; seat_distribution=\"two_plus_three\"; beds_per_compartment=\"2\"" +
                                " max_cargo_weight=\"120.7\" max_cargo_weight_per_passenger=\"11\"",
                        1,
                        6,
                        CoachCar.SeatDistribution.TWO_PLUS_THREE,
                        2,
                        120.7,
                        11.0,
                        null,
                },
                {
                        "weighht=\"27\";  seat_distri bution=\"two_plus_two\"; beds_per_compatrment=\"4\"" +
                                " max_carg o_weight=\"120.7\" cargo_weightper_passenger=\"11\"",
                        27,
                        0,
                        CoachCar.SeatDistribution.TWO_PLUS_TWO,
                        4,
                        120.7,
                        11,
                        ParserException.class,
                },
                {
                        "id=\"26a\"; passengers=\"-7\"; seat_distribution=\"2+2\"; beds_per_compartment=\"\"" +
                                " max_cargo_weight=\"1we.7\" max_cargo_weight_per_passenger=\"9;6\"",
                        26,
                        7,
                        CoachCar.SeatDistribution.TWO_PLUS_TWO,
                        4,
                        100.7,
                        9.6,
                        ParserException.class,
                },
                {
                        "",
                        0,
                        0,
                        null,
                        0,
                        0,
                        0,
                        ParserException.class,
                },
        });
    }

    @Test
    public void parseMaxCargoWeightTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        double maxCargoWeight = parser.parseMaxCargoWeight(source);
        assertEquals(expectedMaxCargoWeight, maxCargoWeight, 0.001);
    }

    @Test
    public void parseCargoWeightPerPassengerTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        double cargoWeightPerPassenger = parser.parseCargoWeightPerPassenger(source);
        assertEquals(expectedCargoWeightPerPassenger, cargoWeightPerPassenger, 0.001);
    }

    @Before
    public void setUp() throws Exception {
        parser = new UnitParser();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
    }

    @Test
    public void parseIdTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        int id = parser.parseId(source);
        assertEquals(id, expectedId);
    }

    @Test
    public void parsePassengersTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        int passengers = parser.parsePassengers(source);
        assertEquals(passengers, expectedPassengers);
    }

    @Test
    public void parseSeatDistributionTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        CoachCar.SeatDistribution seatDistribution = parser.parseSeatDistribution(source);
        assertEquals(expectedSeatDistribution, seatDistribution);
    }

    @Test
    public void parseBedsPerCompartmentTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        int bedsPerCompartment = parser.parseBedsPerCompartment(source);
        assertEquals(expectedBedsPerCompartment, bedsPerCompartment);
    }
}
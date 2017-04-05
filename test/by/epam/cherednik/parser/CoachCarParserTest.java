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
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CoachCarParserTest {
    @Parameter
    public String source;
    @Parameter(1)
    public CoachCar expectedCar;
    @Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CoachCarParser parser;

    @Parameters(name = "Test {index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "id=\"26\"; passengers=\"58\"; seat_distribution=\"two_plus_two\"; beds_per_compartment=\"4\"",
                        new CoachCar(26, 58, CoachCar.SeatDistribution.TWO_PLUS_TWO),
                        null,
                },
                {
                        "id=\"1\"; passengers=\"6\"; seat_distribution=\"two_plus_three\"; beds_per_compartment=\"2\"",
                        new CoachCar(1, 6, CoachCar.SeatDistribution.TWO_PLUS_THREE),
                        null,
                },
                {
                        "id=\"27.7\"; pasengers=\"58\"; seat_distribution=two_plus_two; beds_per_compartment=\"4\"",
                        null,
                        ParserException.class,
                },
                {
                        "id=\"26a.75\"; passengers=\"7.7\"; seat_distribution=\"2+2\"; beds_per_compartment=\"\"",
                        null,
                        ParserException.class,
                },
                {
                        "",
                        null,
                        ParserException.class,
                },
        });
    }

    @Before
    public void setUp() throws Exception {
        parser = new CoachCarParser();
    }

    @After
    public void tearDown() throws Exception {
        parser = null;
    }

    @Test
    public void parseTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        CoachCar car = parser.parse(source);
        assertEquals(expectedCar, car);
    }
}
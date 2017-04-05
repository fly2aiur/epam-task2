package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.passenger.SleepingCar;
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
public class SleepingCarParserTest {
    @Parameter
    public String source;
    @Parameter(1)
    public SleepingCar expectedCar;
    @Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SleepingCarParser parser;

    @Parameters(name = "Test {index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "id=\"24\"; passengers=\"38\"; beds_per_compartment=\"4\"",
                        new SleepingCar(24, 38, 4),
                        null,
                },
                {
                        "id=\"1\"; passengers=\"6\"; seat_distribution=\"two_plus_three\"; beds_per_compartment=\"2\"",
                        new SleepingCar(1, 6, 2),
                        null,
                },
                {
                        "id=\"26.75\"; pasengers=\"58\"; beds_per_compartment=\"4\"",
                        null,
                        ParserException.class,
                },
                {
                        "id=\".75\"; passengers=\"7.7\";beds_per_compartment=\"\"",
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
        parser = new SleepingCarParser();
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
        SleepingCar car = parser.parse(source);
        assertEquals(expectedCar, car);
    }
}
package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.freight.BaggageCar;
import by.epam.cherednik.exception.ParserException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BaggageCarParserTest {
    @Parameter
    public String source;
    @Parameter(1)
    public BaggageCar expectedCar;
    @Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private BaggageCarParser parser;

    @Parameters(name = "Test {index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "id=\"26\"; max_cargo_weight=\"1222.2\" max_cargo_weight_per_passenger=\"65.5\"",
                        new BaggageCar(26, 1222.2, 65.5),
                        null,
                },
                {
                        "id=\"1\"; max_cargo_weight=\"1600\" max_cargo_weight_per_passenger=\"32\"",
                        new BaggageCar(1, 1600, 32),
                        null,
                },
                {
                        "id=\"27.7\"; max_cargo_w t=\"1222.2\" max_cargo_weight_per_passenger=\"65.5\"",
                        null,
                        ParserException.class,
                },
                {
                        "id=\"26a.75\"; max_cargo_weight=\"122cs.2\" max_cargo_weight_per_passenger=\"8..8\"",
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
        parser = new BaggageCarParser();
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
        BaggageCar car = parser.parse(source);
        assertEquals(expectedCar, car);
    }
}
package by.epam.cherednik.reader;

import by.epam.cherednik.exception.DataFileException;
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
public class ReaderTest {
    @Parameter
    public String pathname;
    @Parameter(1)
    public String expectedString;
    @Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Reader reader;

    @Parameters(name = "Test {index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "resources/test.txt",
                        "train{" +
                                "sleeping_car[id=\"17\"; passengers=\"24\"; beds_per_compartment=\"2.7\"]" +
                                "}",
                        null,
                },
                {
                        "resources/trains.txt",
                        null,
                        DataFileException.class,
                }
        });
    }

    @Before
    public void setUp() throws Exception {
        reader = new Reader();
    }

    @After
    public void tearDown() throws Exception {
        reader = null;
    }

    @Test
    public void readFileAsStringTest() throws Exception {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        String actualString = reader.readFileAsString(pathname);
        assertEquals(expectedString, actualString);
    }
}
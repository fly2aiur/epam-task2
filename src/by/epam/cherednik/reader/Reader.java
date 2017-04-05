package by.epam.cherednik.reader;

import by.epam.cherednik.exception.DataFileException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private static final Logger LOGGER = LogManager.getLogger();

    public String readFileAsString(String pathname) throws DataFileException {
        LOGGER.log(Level.DEBUG, "Reading file: " + pathname);

        StringBuilder dataString = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(pathname)))) {
            reader.lines().forEach(dataString::append);
        } catch (IOException e) {
            throw new DataFileException("Error occurred while reading data file: " + pathname, e);
        }

        return dataString.toString();
    }
}

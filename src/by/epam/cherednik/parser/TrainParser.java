package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.AbstractCar;
import by.epam.cherednik.entity.train.Train;
import by.epam.cherednik.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String TRAIN_PATTERN = "(train)\\s*\\{(.+?)}";
    private static final String CAR_PATTERN = "(\\w+)\\s*\\[(.+?)]";

    public ArrayList<Train> parseAllTrains(String source) {
        LOGGER.debug("Parsing trains from String");
        ArrayList<Train> trains = new ArrayList<>();
        Pattern p = Pattern.compile(TRAIN_PATTERN, Pattern.DOTALL);
        Matcher m = p.matcher(source);
        while (m.find()) {
            String trainString = m.group(2);
            try {
                trains.add(parseTrain(trainString));
            } catch (ParserException e) {
                LOGGER.error("Couldn't parse a train", e);
            }
        }
        LOGGER.debug("Successfully created " + trains.size() + " trains");
        return trains;
    }

    public Train parseTrain(String source) throws ParserException {
        LOGGER.debug("Parsing train from String " + source);
        ArrayList<AbstractCar> cars = new ArrayList<>();
        Pattern p = Pattern.compile(CAR_PATTERN, Pattern.DOTALL);
        Matcher m = p.matcher(source);
        while (m.find()) {
            String type = m.group(1);
            String params = m.group(2);
            try {
                AbstractCar car = parseVehicle(type, params);
                cars.add(car);
            } catch (ParserException e) {
                LOGGER.error("Couldn't parse a vehicle", e);
            }
        }
        if (cars.isEmpty()) {
            throw new ParserException("Couldn't parse any cars");
        }
        Train train = new Train();
        cars.forEach(train::add);
        return train;
    }

    public AbstractCar parseVehicle(String type, String params) throws ParserException {
        AbstractCarParser parser;
        AbstractCar car;
        switch (type.toUpperCase()) {
            case "COACH_CAR":
                parser = new CoachCarParser();
                break;
            case "SLEEPING_CAR":
                parser = new SleepingCarParser();
                break;
            case "BAGGAGE_CAR":
                parser = new BaggageCarParser();
                break;
            default:
                throw new ParserException("Couldn't parse vehicle type");
        }
        car = parser.parse(params);
        return car;
    }
}


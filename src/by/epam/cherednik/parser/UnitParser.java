package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.passenger.CoachCar;
import by.epam.cherednik.exception.ParserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UnitParser {
    private static final String ID_PATTERN = "id=\"(\\d+)\"";
    private static final String PASSENGERS_PATTERN = "passengers=\"(\\d+)\"";
    private static final String SEAT_DISTRIBUTION_PATTERN = "seat_distribution=\"(\\w+)\"";
    private static final String BEDS_PER_COMPARTMENT_PATTERN = "beds_per_compartment=\"(\\d+)\"";
    private static final String MAX_CARGO_WEIGHT_PATTERN = "max_cargo_weight=\"(\\d*\\.?\\d+)\"";
    private static final String CARGO_WEIGHT_PER_PASSENGER_PATTERN = "max_cargo_weight_per_passenger=\"(\\d*\\.?\\d+)\"";

    int parseId(String source) throws ParserException {
        Pattern p = Pattern.compile(ID_PATTERN);
        Matcher m = p.matcher(source);
        int id;
        if (m.find()) {
            String s = m.group(1);
            id = Integer.parseInt(m.group(1));
            if (id > 0) {
                return id;
            } else {
                throw new ParserException("Invalid id value");
            }
        } else {
            throw new ParserException("Couldn't find id");
        }
    }

    int parsePassengers(String source) throws ParserException {
        Pattern p = Pattern.compile(PASSENGERS_PATTERN);
        Matcher m = p.matcher(source);
        int passengers;
        if (m.find()) {
            String s = m.group(1);
            passengers = Integer.parseInt(m.group(1));
            if (passengers > 0) {
                return passengers;
            } else {
                throw new ParserException("Invalid passengers value");
            }
        } else {
            throw new ParserException("Couldn't find passengers");
        }
    }

    CoachCar.SeatDistribution parseSeatDistribution(String source) throws ParserException {
        Pattern p = Pattern.compile(SEAT_DISTRIBUTION_PATTERN);
        Matcher m = p.matcher(source);
        CoachCar.SeatDistribution seatDistribution;
        if (m.find()) {
            try {
                seatDistribution = CoachCar.SeatDistribution.valueOf(m.group(1).toUpperCase());
                return seatDistribution;
            } catch (IllegalArgumentException e) {
                throw new ParserException("Invalid seat distribution value", e);
            }
        } else {
            throw new ParserException("Couldn't find seat distribution");
        }
    }

    int parseBedsPerCompartment(String source) throws ParserException {
        Pattern p = Pattern.compile(BEDS_PER_COMPARTMENT_PATTERN);
        Matcher m = p.matcher(source);
        int bedsPerCompartment;
        if (m.find()) {
            bedsPerCompartment = Integer.parseInt(m.group(1));
            if (bedsPerCompartment > 0) {
                return bedsPerCompartment;
            } else {
                throw new ParserException("Invalid beds per compartment value");
            }
        } else {
            throw new ParserException("Couldn't find beds per compartment");
        }
    }

    double parseMaxCargoWeight(String source) throws ParserException {
        Pattern p = Pattern.compile(MAX_CARGO_WEIGHT_PATTERN);
        Matcher m = p.matcher(source);
        double maxCargoWeight;
        if (m.find()) {
            maxCargoWeight = Double.parseDouble(m.group(1));
            if (maxCargoWeight > 0.0) {
                return maxCargoWeight;
            } else {
                throw new ParserException("Invalid max cargo weight value");
            }
        } else {
            throw new ParserException("Couldn't find max cargo weight");

        }
    }

    double parseCargoWeightPerPassenger(String source) throws ParserException {
        Pattern p = Pattern.compile(CARGO_WEIGHT_PER_PASSENGER_PATTERN);
        Matcher m = p.matcher(source);
        double cargoWeightPerPassenger;
        if (m.find()) {
            cargoWeightPerPassenger = Double.parseDouble(m.group(1));
            if (cargoWeightPerPassenger > 0.0) {
                return cargoWeightPerPassenger;
            } else {
                throw new ParserException("Invalid cargo weight per passenger value");
            }
        } else {
            throw new ParserException("Couldn't find cargo weight per passenger");
        }
    }
}

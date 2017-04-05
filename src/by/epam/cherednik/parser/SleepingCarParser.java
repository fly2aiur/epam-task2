package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.passenger.SleepingCar;
import by.epam.cherednik.exception.ParserException;

public class SleepingCarParser extends AbstractCarParser<SleepingCar> {
    @Override
    public SleepingCar parse(String params) throws ParserException {
        UnitParser p = new UnitParser();
        int weight = p.parseId(params);
        int passengers = p.parsePassengers(params);
        int bedsPerCompartment = p.parseBedsPerCompartment(params);
        return new SleepingCar(weight, passengers, bedsPerCompartment);
    }
}
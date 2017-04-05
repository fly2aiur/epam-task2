package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.passenger.CoachCar;
import by.epam.cherednik.exception.ParserException;

public class CoachCarParser extends AbstractCarParser<CoachCar> {
    @Override
    public CoachCar parse(String params) throws ParserException {
        UnitParser p = new UnitParser();
        int id = p.parseId(params);
        int passengers = p.parsePassengers(params);
        CoachCar.SeatDistribution seatDistribution = p.parseSeatDistribution(params);
        return new CoachCar(id, passengers, seatDistribution);
    }
}
package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.freight.BaggageCar;
import by.epam.cherednik.exception.ParserException;

public class BaggageCarParser extends AbstractCarParser<BaggageCar> {
    @Override
    public BaggageCar parse(String params) throws ParserException {
        UnitParser p = new UnitParser();
        int id = p.parseId(params);
        double maxCargoWeight = p.parseMaxCargoWeight(params);
        double maxCargoWeightPerPerson = p.parseCargoWeightPerPassenger(params);
        return new BaggageCar(id, maxCargoWeight, maxCargoWeightPerPerson);
    }
}

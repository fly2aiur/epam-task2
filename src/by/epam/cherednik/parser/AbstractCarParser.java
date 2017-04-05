package by.epam.cherednik.parser;

import by.epam.cherednik.entity.car.AbstractCar;
import by.epam.cherednik.exception.ParserException;

public abstract class AbstractCarParser<T extends AbstractCar> {
    public abstract T parse(String params) throws ParserException;
}

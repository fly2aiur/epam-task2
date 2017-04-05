package by.epam.cherednik.entity.car.passenger;

import by.epam.cherednik.entity.car.CarType;

public class CoachCar extends AbstractPassengerCar {
    private SeatDistribution seatDistribution;

    public CoachCar(int id, int maxPassengers, SeatDistribution seatDistribution) {
        super(id, maxPassengers);
        this.seatDistribution = seatDistribution;
    }

    public SeatDistribution getSeatDistribution() {
        return seatDistribution;
    }

    public void setSeatDistribution(SeatDistribution seatDistribution) {
        this.seatDistribution = seatDistribution;
    }

    @Override
    public CarType getType() {
        return CarType.COACH_CAR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoachCar coachCar = (CoachCar) o;

        return getId() == coachCar.getId()
                && getMaxPassengers() == coachCar.getMaxPassengers()
                && getSeatDistribution() == coachCar.getSeatDistribution();
    }

    @Override
    public int hashCode() {
        int result;
        result = getId();
        result = 31 * result + getMaxPassengers();
        result = 31 * result + getSeatDistribution().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CoachCar{" +
                "id=" + getId() +
                ", maxPassengers=" + getMaxPassengers() +
                ", seatDistribution=" + getSeatDistribution() +
                "} ";
    }

    public enum SeatDistribution {
        TWO_PLUS_TWO, TWO_PLUS_THREE
    }
}

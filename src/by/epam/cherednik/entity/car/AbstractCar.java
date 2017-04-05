package by.epam.cherednik.entity.car;

public abstract class AbstractCar {
    private int id;

    public AbstractCar(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract CarType getType();
}

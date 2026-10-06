package abstraction.assigment_problems;

public abstract class Drone {

    protected String id;

    public Drone(String id) {
        this.id = id;
    }

    public abstract String fly();
}
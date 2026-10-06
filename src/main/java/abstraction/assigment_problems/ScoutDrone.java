package abstraction.assigment_problems;

public class ScoutDrone extends Drone {

    public ScoutDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return id + " scouting";
    }
}
package abstraction.class_problems;

public class StringInstrument extends Instrument {

    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return superPlay();
    }

    protected String superPlay() {
        return "Strumming the strings";
    }
}
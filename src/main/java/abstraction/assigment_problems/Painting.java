package abstraction.assigment_problems;

public class Painting extends ArtPiece {

    private String title;

    public Painting(String title) {
        super(title);
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}
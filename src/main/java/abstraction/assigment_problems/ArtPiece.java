package abstraction.assigment_problems;

public abstract class ArtPiece {

    private final String pieceId;

    private static int counter = 1000;

    public ArtPiece(String title) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }

        counter++;
        pieceId = "ART-" + counter;
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}
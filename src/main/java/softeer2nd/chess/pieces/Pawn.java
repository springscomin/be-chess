package softeer2nd.chess.pieces;

public class Pawn {
    public static final String WHITE = "white";
    public static final String BLACK = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';
    public static final String COLOR_ERROR_MESSAGE = "검은색 또는 흰색만 입력할 수 있습니다";

    private final String color;
    private final char representation;

    public Pawn() {
        this.color = WHITE;
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(final String color, final char representation) {
        validateColor(color);
        this.color = color;
        this.representation = representation;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    private void validateColor(String color) {
        if (color.equals(WHITE) || color.equals(BLACK)) return;
        throw new IllegalArgumentException(COLOR_ERROR_MESSAGE);
    }
}

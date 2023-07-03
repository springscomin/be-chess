package softeer2nd;

public class Pawn {
    public static final String WHITE = "white";
    public static final String BLACK = "black";
    public static final String COLOR_ERROR_MESSAGE = "검은색 또는 흰색만 입력할 수 있습니다";
    private final String color;

    public Pawn(final String color) {
        validateColor(color);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void validateColor(String color) {
        if (color.equals(WHITE) || color.equals(BLACK)) return;
        throw new IllegalArgumentException(COLOR_ERROR_MESSAGE);
    }
}

package softeer2nd;

public class Pawn {

    private final String color;

    public Pawn(String color) {
        validateColor(color);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void validateColor(String color) {
        if (color.equals("white") || color.equals("black")) return;
        throw new IllegalArgumentException("검은색 또는 흰색만 입력할 수 있습니다");
    }
}

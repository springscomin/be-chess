package softeer2nd.chess.pieces;

public class Piece {
    public static final String WHITE = "white";
    public static final String BLACK = "black";
    public static final String COLOR_ERROR_MESSAGE = "검은색 또는 흰색만 입력할 수 있습니다";

    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char WHITE_KING_REPRESENTATION = 'k';

    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char BLACK_KING_REPRESENTATION = 'K';

    private final String color;
    private final PieceType name;
    private final char representation;

    private Piece(String color, PieceType name, char representation) {
        validateColor(color);
        this.color = color;
        this.name = name;
        this.representation = representation;
    }

    public PieceType getName() {
        return name;
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

    public static Piece createWhitePawn() {
        return new Piece(WHITE, PieceType.PAWN, Piece.WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK, PieceType.PAWN, Piece.BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE, PieceType.KNIGHT, WHITE_KNIGHT_REPRESENTATION);
    }


    public static Piece createBlackKnight() {
        return new Piece(BLACK, PieceType.KNIGHT, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE, PieceType.ROOK, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK, PieceType.ROOK, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE, PieceType.BISHOP, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK, PieceType.BISHOP, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE, PieceType.QUEEN, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK, PieceType.QUEEN, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE, PieceType.KING, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK, PieceType.KING, BLACK_KING_REPRESENTATION);
    }
}

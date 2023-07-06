package softeer2nd.chess.pieces;

import java.util.Objects;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

public class Piece {
    private final Color color;
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                '}';
    }

    public Color getColor() {
        return color;
    }

    public boolean matchesColorAndType(Color color, Type type) {
        return this.color.equals(color) && this.type.equals(type);
    }

    public boolean matchesColor(Color color) {
        return this.color.equals(color);
    }

    public double getDefaultPoint() {
        return type.getDefaultPoint();
    }

    public char getRepresentation() {
        if (isBlank()) {
            return type.getDefaultRepresentation();
        }
        if (isWhite()) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public boolean isPawn() {
        return type.equals(PAWN);
    }

    public boolean isBlank() {
        return type.equals(NO_PIECE);
    }

    public boolean isWhite() {
        return color.equals(WHITE);
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }

    public static Piece createBlank() {
        return new Piece(NO_COLOR, NO_PIECE);
    }

    public static Piece createWhitePawn() {
        return createWhitePiece(PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlackPiece(PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhitePiece(KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlackPiece(KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhitePiece(ROOK);
    }

    public static Piece createBlackRook() {
        return createBlackPiece(ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhitePiece(BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlackPiece(BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhitePiece(QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlackPiece(QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhitePiece(KING);
    }

    public static Piece createBlackKing() {
        return createBlackPiece(KING);
    }

    private static Piece createWhitePiece(Type type) {
        return new Piece(WHITE, type);
    }

    private static Piece createBlackPiece(Type type) {
        return new Piece(BLACK, type);
    }


    public enum Color {
        WHITE, BLACK, NO_COLOR;
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;

        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }

        public char getDefaultRepresentation() {
            return representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }
}

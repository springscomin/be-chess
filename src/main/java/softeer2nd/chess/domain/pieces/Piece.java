package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;

import java.util.Objects;

import static softeer2nd.chess.domain.pieces.Piece.Color.*;
import static softeer2nd.chess.domain.pieces.Piece.Type.*;

public class Piece {
    private final Color color;
    private final Type type;
    private final Position position;

    private Piece(Position position, Color color, Type type) {
        this.position = position;
        this.color = color;
        this.type = type;
    }

    public static Piece createMovedPiece(Piece piece, Position destination) {
        Color color = piece.color;
        Type type = piece.type;
        return new Piece(destination, color, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                ", position=" + position +
                '}';
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
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

    public static Piece createBlank(Position position) {
        return new Piece(position, NO_COLOR, NO_PIECE);
    }

    public static Piece createWhitePawn(Position position) {
        return createWhitePiece(position, PAWN);
    }

    public static Piece createBlackPawn(Position position) {
        return createBlackPiece(position, PAWN);
    }

    public static Piece createWhiteKnight(Position position) {
        return createWhitePiece(position, KNIGHT);
    }

    public static Piece createBlackKnight(Position position) {
        return createBlackPiece(position, KNIGHT);
    }

    public static Piece createWhiteRook(Position position) {
        return createWhitePiece(position, ROOK);
    }

    public static Piece createBlackRook(Position position) {
        return createBlackPiece(position, ROOK);
    }

    public static Piece createWhiteBishop(Position position) {
        return createWhitePiece(position, BISHOP);
    }

    public static Piece createBlackBishop(Position position) {
        return createBlackPiece(position, BISHOP);
    }

    public static Piece createWhiteQueen(Position position) {
        return createWhitePiece(position, QUEEN);
    }

    public static Piece createBlackQueen(Position position) {
        return createBlackPiece(position, QUEEN);
    }

    public static Piece createWhiteKing(Position position) {
        return createWhitePiece(position, KING);
    }

    public static Piece createBlackKing(Position position) {
        return createBlackPiece(position, KING);
    }

    private static Piece createWhitePiece(Position position, Type type) {
        return new Piece(position, WHITE, type);
    }

    private static Piece createBlackPiece(Position position, Type type) {
        return new Piece(position, BLACK, type);
    }


    public enum Color {
        WHITE, BLACK, NO_COLOR;
    }

    public enum Type {
        PAWN(1.0),
        ROOK(5.0),
        KNIGHT(2.5),
        BISHOP(3.0),
        QUEEN(9.0),
        KING(0.0),
        NO_PIECE(0.0);

        private final double defaultPoint;

        Type(double defaultPoint) {
            this.defaultPoint = defaultPoint;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }
    }
}

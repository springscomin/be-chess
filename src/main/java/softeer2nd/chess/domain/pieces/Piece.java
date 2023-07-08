package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.Objects;

import static softeer2nd.chess.domain.pieces.enums.PieceColor.*;
import static softeer2nd.chess.domain.pieces.enums.PieceType.*;


public class Piece {
    private final PieceColor color;
    private final PieceType type;

    private Piece(PieceColor color, PieceType type) {
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

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public boolean matchesColorAndType(PieceColor color, PieceType type) {
        return this.color.equals(color) && this.type.equals(type);
    }

    public boolean matchesColor(PieceColor color) {
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

    private static Piece createWhitePiece(PieceType type) {
        return new Piece(WHITE, type);
    }

    private static Piece createBlackPiece(PieceType type) {
        return new Piece(BLACK, type);
    }
}

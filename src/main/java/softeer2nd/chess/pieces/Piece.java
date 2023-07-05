package softeer2nd.chess.pieces;

import static softeer2nd.chess.pieces.PieceColor.*;
import static softeer2nd.chess.pieces.PieceType.*;

public class Piece {
    private final PieceColor color;
    private final PieceType type;

    private Piece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public PieceColor getColor() {
        return color;
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
        return new Piece(WHITE, PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK, PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE, KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK, KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE, ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK, ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE, BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK, BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE, QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK, QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE, KING);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK, KING);
    }
}

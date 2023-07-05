package softeer2nd.chess.pieces;

import static softeer2nd.chess.pieces.PieceColor.BLACK;
import static softeer2nd.chess.pieces.PieceColor.WHITE;

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
        if (color.equals(WHITE)) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }


    public boolean isWhite() {
        return color.equals(WHITE);
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE, PieceType.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK, PieceType.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE, PieceType.KNIGHT);
    }


    public static Piece createBlackKnight() {
        return new Piece(BLACK, PieceType.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE, PieceType.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK, PieceType.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE, PieceType.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK, PieceType.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE, PieceType.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK, PieceType.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE, PieceType.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK, PieceType.KING);
    }
}

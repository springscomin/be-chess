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

    public boolean matchesColorAndType(PieceColor pieceColor, PieceType pieceType) {
        if (color.equals(pieceColor) && type.equals(pieceType)) return true;
        return false;
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

    private static Piece createWhitePiece(PieceType pieceType) {
        return new Piece(WHITE, pieceType);
    }

    private static Piece createBlackPiece(PieceType pieceType) {
        return new Piece(BLACK, pieceType);
    }
}

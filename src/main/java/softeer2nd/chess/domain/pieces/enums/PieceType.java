package softeer2nd.chess.domain.pieces.enums;

public enum PieceType {
    PAWN(1.0),
    ROOK(5.0),
    KNIGHT(2.5),
    BISHOP(3.0),
    QUEEN(9.0),
    KING(0.0),
    NO_PIECE(0.0);

    private final double defaultPoint;

    PieceType(double defaultPoint) {
        this.defaultPoint = defaultPoint;
    }

    public double getDefaultPoint() {
        return defaultPoint;
    }
}

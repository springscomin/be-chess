package softeer2nd.chess.pieces;

public enum PieceType {
    PAWN('p'),
    KNIGHT('n'),
    ROOK('r'),
    BISHOP('b'),
    QUEEN('q'),
    KING('k'),
    NO_PIECE('.');

    private final char representation;

    PieceType(char representation) {
        this.representation = representation;
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

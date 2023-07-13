package softeer2nd.chess.flow;

import softeer2nd.chess.domain.pieces.enums.PieceColor;

public class Turn {
    private PieceColor color;

    public Turn() {
        color = PieceColor.WHITE;
    }

    public PieceColor next() {
        if (color.equals(PieceColor.WHITE)) {
            color = PieceColor.BLACK;
            return color;
        }
        color = PieceColor.WHITE;
        return color;
    }

    public void back() {
        next();
    }

    public boolean isWhiteTurn() {
        return color == PieceColor.WHITE;
    }

    public PieceColor getColor() {
        return color;
    }
}

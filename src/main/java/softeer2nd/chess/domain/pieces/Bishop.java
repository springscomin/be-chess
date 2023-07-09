package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;

public class Bishop extends Piece {
    private Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        return null;
    }

    public static Bishop createWhite() {
        return new Bishop(PieceColor.WHITE);
    }

    public static Bishop createBlack() {
        return new Bishop(PieceColor.BLACK);
    }
}

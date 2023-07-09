package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;

public class Queen extends Piece {
    private Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        return null;
    }

    public static Queen createWhite() {
        return new Queen(PieceColor.WHITE);
    }

    public static Queen createBlack() {
        return new Queen(PieceColor.BLACK);
    }
}

package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;

public class Knight extends Piece{

    private Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }
    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        return null;
    }

    public static Knight createWhite() {
        return new Knight(PieceColor.WHITE);
    }

    public static Knight createBlack() {
        return new Knight(PieceColor.BLACK);
    }
}

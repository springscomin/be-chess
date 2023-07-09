package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceDirection;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;

public class King extends Piece{
    public static List<PieceDirection> directions = PieceDirection.everyDirection();

    private King(PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        return null;
    }

    public static King createWhite() {
        return new King(PieceColor.WHITE);
    }

    public static King createBlack() {
        return new King(PieceColor.BLACK);
    }
}

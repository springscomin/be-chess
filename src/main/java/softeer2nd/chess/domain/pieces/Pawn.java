package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;

public class Pawn extends Piece {
    private Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        return null;
    }

    public static Pawn createWhite() {
        return new Pawn(PieceColor.WHITE);
    }

    public static Pawn createBlack() {
        return new Pawn(PieceColor.BLACK);
    }
}

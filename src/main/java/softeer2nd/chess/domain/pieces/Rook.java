package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;

public class Rook extends Piece{
    private Rook(PieceColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        return null;
    }

    public static Rook createWhite() {
        return new Rook(PieceColor.WHITE);
    }

    public static Rook createBlack() {
        return new Rook(PieceColor.BLACK);
    }
}

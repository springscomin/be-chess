package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.Collections;
import java.util.List;

public class Blank extends Piece {

    private Blank() {
        super(PieceColor.NO_COLOR, PieceType.NO_PIECE);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        return Collections.emptyList();
    }

    public static Blank create() {
        return new Blank();
    }
}

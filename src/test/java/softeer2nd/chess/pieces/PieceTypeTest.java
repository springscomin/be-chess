package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTypeTest {
    @Test
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', PieceType.PAWN.getWhiteRepresentation());
        assertEquals('P', PieceType.PAWN.getBlackRepresentation());
    }

    @Test
    public void getBlankRepresentationTest() {
        assertEquals('.', PieceType.NO_PIECE.getDefaultRepresentation());
    }
}

package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeTest {
    @Test
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());
    }

    @Test
    public void getBlankRepresentationTest() {
        assertEquals('.', Piece.Type.NO_PIECE.getDefaultRepresentation());
    }
}

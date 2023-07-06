package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Piece 테스트")
public class PieceTest {

    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.Type.PAWN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.Type.KNIGHT.getBlackRepresentation());

        verifyPiece(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.Type.ROOK.getWhiteRepresentation());
        verifyPiece(Piece.createBlackRook(), Piece.Color.BLACK, Piece.Type.ROOK.getBlackRepresentation());

        verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.Type.BISHOP.getBlackRepresentation());

        verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.Type.QUEEN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKing(), Piece.Color.BLACK, Piece.Type.KING.getBlackRepresentation());
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("흰색 기물의 색상 테스트")
    void isWhiteTest() {
        Piece whitePawn = Piece.createWhitePawn();

        Assertions.assertTrue(whitePawn.isWhite());
        Assertions.assertFalse(whitePawn.isBlack());
    }


    @Test
    @DisplayName("검은색 기물의 색상 테스트")
    void isBlackTest() {
        Piece blackPawn = Piece.createBlackPawn();

        Assertions.assertTrue(blackPawn.isBlack());
        Assertions.assertFalse(blackPawn.isWhite());
    }
}

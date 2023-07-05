package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Piece 테스트")
public class PieceTest {

    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), PieceColor.WHITE, PieceType.PAWN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackPawn(), PieceColor.BLACK, PieceType.PAWN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKnight(), PieceColor.WHITE, PieceType.KNIGHT.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKnight(), PieceColor.BLACK, PieceType.KNIGHT.getBlackRepresentation());

        verifyPiece(Piece.createWhiteRook(), PieceColor.WHITE, PieceType.ROOK.getWhiteRepresentation());
        verifyPiece(Piece.createBlackRook(), PieceColor.BLACK, PieceType.ROOK.getBlackRepresentation());

        verifyPiece(Piece.createWhiteBishop(), PieceColor.WHITE, PieceType.BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.createBlackBishop(), PieceColor.BLACK, PieceType.BISHOP.getBlackRepresentation());

        verifyPiece(Piece.createWhiteQueen(), PieceColor.WHITE, PieceType.QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackQueen(), PieceColor.BLACK, PieceType.QUEEN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKing(), PieceColor.WHITE, PieceType.KING.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKing(), PieceColor.BLACK, PieceType.KING.getBlackRepresentation());
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final char representation) {
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

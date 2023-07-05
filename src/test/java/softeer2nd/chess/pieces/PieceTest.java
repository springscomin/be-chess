package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Piece 테스트")
public class PieceTest {

    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), PieceColor.WHITE, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), PieceColor.BLACK, Piece.BLACK_PAWN_REPRESENTATION);

        verifyPiece(Piece.createWhiteKnight(), PieceColor.WHITE, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(), PieceColor.BLACK, Piece.BLACK_KNIGHT_REPRESENTATION);

        verifyPiece(Piece.createWhiteRook(), PieceColor.WHITE, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createBlackRook(), PieceColor.BLACK, Piece.BLACK_ROOK_REPRESENTATION);

        verifyPiece(Piece.createWhiteBishop(), PieceColor.WHITE, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(), PieceColor.BLACK, Piece.BLACK_BISHOP_REPRESENTATION);

        verifyPiece(Piece.createWhiteQueen(), PieceColor.WHITE, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackQueen(), PieceColor.BLACK, Piece.BLACK_QUEEN_REPRESENTATION);

        verifyPiece(Piece.createWhiteKing(), PieceColor.WHITE, Piece.WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(), PieceColor.BLACK, Piece.BLACK_KING_REPRESENTATION);
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

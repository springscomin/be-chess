package softeer2nd.chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessGameTest {

    @DisplayName("기물 점수 계산 기능 테스트")
    @Test
    public void calculatePoint() throws Exception {
        Board board = Board.createEmptyBoard();
        ChessGame game = new ChessGame(board);

        board.addPiece(new Position("b6"), Piece.createBlackPawn());
        board.addPiece(new Position("e6"), Piece.createBlackQueen());
        board.addPiece(new Position("b8"), Piece.createBlackKing());
        board.addPiece(new Position("c8"), Piece.createBlackRook());

        board.addPiece(new Position("f2"), Piece.createWhitePawn());
        board.addPiece(new Position("g2"), Piece.createWhitePawn());
        board.addPiece(new Position("e1"), Piece.createWhiteRook());
        board.addPiece(new Position("f1"), Piece.createWhiteKing());

        assertEquals(15.0, game.calculatePoint(PieceColor.BLACK), 0.01);
        assertEquals(7.0, game.calculatePoint(PieceColor.WHITE), 0.01);
    }

    @DisplayName("기물 이동 기능 테스트")
    @Test
    public void moveTest() {
        Board board = Board.createInitialBoard();
        ChessGame game = new ChessGame(board);
        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");
        board.addPiece(sourcePosition, Piece.createBlackQueen());

        game.movePiece(sourcePosition, targetPosition);

        assertEquals(Piece.createBlank(), board.findPieceByPosition(sourcePosition));
        assertEquals(Piece.createBlackQueen(), board.findPieceByPosition(targetPosition));
    }
}

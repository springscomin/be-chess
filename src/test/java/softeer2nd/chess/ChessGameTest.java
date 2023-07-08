package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessGameTest {

    @DisplayName("기물 점수 계산 기능 테스트")
    @Test
    public void calculatePoint() throws Exception {
        Board board = Board.createEmptyBoard();
        ChessGame game = new ChessGame(board);

        board.addPiece(Piece.createBlackPawn(new Position("b6")));
        board.addPiece(Piece.createBlackQueen(new Position("e6")));
        board.addPiece(Piece.createBlackKing(new Position("b8")));
        board.addPiece(Piece.createBlackRook(new Position("c8")));

        board.addPiece(Piece.createWhitePawn(new Position("f2")));
        board.addPiece(Piece.createWhitePawn(new Position("g2")));
        board.addPiece(Piece.createWhiteRook(new Position("e1")));
        board.addPiece(Piece.createWhiteKing(new Position("f1")));

        assertEquals(15.0, game.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, game.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @DisplayName("기물 이동 기능 테스트")
    @Test
    public void moveTest() {
        Board board = Board.createInitialBoard();
        ChessGame game = new ChessGame(board);
        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");
        board.addPiece(Piece.createBlackQueen(sourcePosition));

        game.movePiece(sourcePosition, targetPosition);

        assertEquals(Piece.createBlank(sourcePosition), board.findPieceByPosition(sourcePosition));
        assertEquals(Piece.createBlackQueen(targetPosition), board.findPieceByPosition(targetPosition));
    }
}

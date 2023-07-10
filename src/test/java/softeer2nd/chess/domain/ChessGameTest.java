package softeer2nd.chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.pieces.*;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessGameTest {

    @DisplayName("기물 점수 계산 기능 테스트")
    @Test
    public void calculatePoint() throws Exception {
        Board board = Board.createEmptyBoard();
        ChessGame game = new ChessGame(board);

        board.addPiece(new Position("b6"), Pawn.createBlack());
        board.addPiece(new Position("e6"), Queen.createBlack());
        board.addPiece(new Position("b8"), King.createBlack());
        board.addPiece(new Position("c8"), Rook.createBlack());

        board.addPiece(new Position("f2"), Pawn.createWhite());
        board.addPiece(new Position("g2"), Pawn.createWhite());
        board.addPiece(new Position("e1"), Rook.createWhite());
        board.addPiece(new Position("f1"), King.createWhite());

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
        board.addPiece(sourcePosition, Queen.createBlack());

        game.movePiece(sourcePosition, targetPosition);

        assertEquals(Blank.create(), board.findPieceByPosition(sourcePosition));
        assertEquals(Queen.createBlack(), board.findPieceByPosition(targetPosition));
    }
}

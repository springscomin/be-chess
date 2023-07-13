package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.King;
import softeer2nd.chess.domain.pieces.Pawn;
import softeer2nd.chess.domain.pieces.Queen;
import softeer2nd.chess.domain.pieces.Rook;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculatorTest {
    @DisplayName("기물 점수 계산 기능 테스트")
    @Test
    void calculatePoint() {
        Board board = Board.createEmptyBoard();
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        board.addPiece(Position.fromChessNotation("b6"), Pawn.createBlack());
        board.addPiece(Position.fromChessNotation("e6"), Queen.createBlack());
        board.addPiece(Position.fromChessNotation("b8"), King.createBlack());
        board.addPiece(Position.fromChessNotation("c8"), Rook.createBlack());

        board.addPiece(Position.fromChessNotation("f2"), Pawn.createWhite());
        board.addPiece(Position.fromChessNotation("g2"), Pawn.createWhite());
        board.addPiece(Position.fromChessNotation("e1"), Rook.createWhite());
        board.addPiece(Position.fromChessNotation("f1"), King.createWhite());

        assertEquals(15.0, scoreCalculator.calculatePoint(board, PieceColor.BLACK), 0.01);
        assertEquals(7.0, scoreCalculator.calculatePoint(board, PieceColor.WHITE), 0.01);
    }
}

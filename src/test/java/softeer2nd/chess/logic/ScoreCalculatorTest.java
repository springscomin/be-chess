package softeer2nd.chess.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.King;
import softeer2nd.chess.domain.pieces.Pawn;
import softeer2nd.chess.domain.pieces.Queen;
import softeer2nd.chess.domain.pieces.Rook;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ScoreCalculator 테스트")
class ScoreCalculatorTest {
    ScoreCalculator scoreCalculator = new ScoreCalculator();

    @DisplayName("기물 점수 계산 기능 테스트")
    @Test
    void calculatePoint() {
        Board board = Board.createEmptyBoard();


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

    @DisplayName("같은 색 폰이 동일한 열에 위치한다면 페널티 점수가 발생한다")
    @Test
    void penaltyPointTest() {
        Board board = Board.createEmptyBoard();

        board.addPiece(Position.fromChessNotation("a1"), Pawn.createWhite());
        board.addPiece(Position.fromChessNotation("a2"), Pawn.createWhite());

        double point = scoreCalculator.calculatePoint(board, PieceColor.WHITE);

        assertEquals(1.0, point, 0.01);
    }
}

package softeer2nd.chess.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.King;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WinnerDeterminer 테스트")
class WinnerDeterminerTest {
    private WinnerDeterminer winnerDeterminer;

    @BeforeEach
    void init() {
        winnerDeterminer = new WinnerDeterminer();
    }

    @DisplayName("검은색/흰색 왕이 모두 살아있는 경우 winner는 없다.")
    @Test
    void hasNoWinnerTest() {
        Board board = Board.createEmptyBoard();
        board.addPiece(Position.fromChessNotation("a1"), King.createBlack());
        board.addPiece(Position.fromChessNotation("a2"), King.createWhite());

        assertFalse(winnerDeterminer.hasWinner(board));
    }

    @DisplayName("검은색 왕만 있다면 winner는 검은이다.")
    @Test
    void findWinnerTest1() {
        Board board = Board.createEmptyBoard();
        board.addPiece(Position.fromChessNotation("a1"), King.createBlack());

        PieceColor winner = winnerDeterminer.getWinner(board);

        assertTrue(winnerDeterminer.hasWinner(board));
        assertEquals(PieceColor.BLACK, winner);
    }

    @DisplayName("흰색 왕만 있다면 winner는 흰색이다.")
    @Test
    void findWinnerTest2() {
        Board board = Board.createEmptyBoard();
        board.addPiece(Position.fromChessNotation("a1"), King.createWhite());

        PieceColor winner = winnerDeterminer.getWinner(board);

        assertTrue(winnerDeterminer.hasWinner(board));
        assertEquals(PieceColor.WHITE, winner);
    }
}

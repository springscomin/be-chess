package softeer2nd.chess.logic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.*;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PieceMover 테스트")
class PieceMoverTest {
    private final PieceMover pieceMover = new PieceMover();

    @DisplayName("blank 는 빈 공간을 의미하므로 이동할 수 없다..")
    @Test
    public void blankMoveTest() {
        Board board = Board.createInitialBoard();
        Blank blank = Blank.create();

        Position sourcePosition = Position.fromChessNotation("b2");
        Position targetPosition = Position.fromChessNotation("b3");
        board.addPiece(sourcePosition, blank);

        Assertions.assertThatThrownBy(() ->
                        pieceMover.movePiece(board, sourcePosition, targetPosition, PieceColor.BLACK))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("기물 이동 기능 테스트")
    @Test
    public void moveTest() {
        Board board = Board.createInitialBoard();

        Position sourcePosition = Position.fromChessNotation("b2");
        Position targetPosition = Position.fromChessNotation("b3");
        Queen blackQueen = Queen.createBlack();
        board.addPiece(sourcePosition, blackQueen);

        pieceMover.movePiece(board, sourcePosition, targetPosition, PieceColor.BLACK);

        assertEquals(Blank.create(), board.findPieceByPosition(sourcePosition));
        assertEquals(Queen.createBlack(), board.findPieceByPosition(targetPosition));
    }

    @DisplayName("기물의 이동 능력 밖의 위치에는 도달 할 수 없다.")
    @Test
    public void moveTest2() {
        Board board = Board.createInitialBoard();

        Position sourcePosition = Position.fromChessNotation("b2");
        Position targetPosition = Position.fromChessNotation("b3");
        Knight knight = Knight.createBlack();
        board.addPiece(sourcePosition, knight);

        Assertions.assertThatThrownBy(() ->
                        pieceMover.movePiece(board, sourcePosition, targetPosition, PieceColor.BLACK))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("White pawn은 대각선 위가 빈 공간일 경우 이동할 수 없음.")
    @Test
    void pawnFailTest() {
        Board board = Board.createEmptyBoard();
        Position sourcePosition = Position.fromChessNotation("a1");
        Position targetPosition = Position.fromChessNotation("b2");
        board.addPiece(sourcePosition, Pawn.createWhite());

        Assertions.assertThatThrownBy(() ->
                        pieceMover.movePiece(board, sourcePosition, targetPosition, PieceColor.WHITE))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("White pawn은 바로 위에 Black기물이 있을 때 이동할 수 없음.")
    @Test
    void pawnFailTest2() {
        Board board = Board.createEmptyBoard();
        Position sourcePosition = Position.fromChessNotation("a1");
        Position targetPosition = Position.fromChessNotation("a2");

        board.addPiece(sourcePosition, Pawn.createWhite());
        board.addPiece(targetPosition, Pawn.createBlack());

        Assertions.assertThatThrownBy(() ->
                        pieceMover.movePiece(board, sourcePosition, targetPosition, PieceColor.WHITE))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("경로 상에 기물이 놓여있을 경우 이동할 수 없음.")
    @Test
    void moveFailTest() {
        Board board = Board.createEmptyBoard();
        Position sourcePosition = Position.fromChessNotation("a1");
        Position midPosition = Position.fromChessNotation("a4");
        Position targetPosition = Position.fromChessNotation("a7");

        board.addPiece(sourcePosition, Rook.createWhite());
        board.addPiece(midPosition, Pawn.createBlack());

        Assertions.assertThatThrownBy(() ->
                        pieceMover.movePiece(board, sourcePosition, targetPosition, PieceColor.WHITE))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("target Position에 같은 색의 기물이 놓여있을 경우 이동할 수 없음.")
    @Test
    void moveFailTest2() {
        Board board = Board.createEmptyBoard();
        Position sourcePosition = Position.fromChessNotation("a1");
        Position targetPosition = Position.fromChessNotation("a7");

        board.addPiece(sourcePosition, Rook.createWhite());
        board.addPiece(targetPosition, Pawn.createWhite());

        Assertions.assertThatThrownBy(() ->
                        pieceMover.movePiece(board, sourcePosition, targetPosition, PieceColor.WHITE))
                .isInstanceOf(RuntimeException.class);
    }
}

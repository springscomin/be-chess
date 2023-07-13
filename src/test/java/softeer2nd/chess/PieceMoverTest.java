package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.*;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PieceMover 테스트")
class PieceMoverTest {

    @DisplayName("기물 이동 기능 테스트")
    @Test
    public void moveTest() {
        Board board = Board.createInitialBoard();
        PieceMover game = new PieceMover();

        Position sourcePosition = Position.fromChessNotation("b2");
        Position targetPosition = Position.fromChessNotation("b3");
        Queen blackQueen = Queen.createBlack();
        board.addPiece(sourcePosition, blackQueen);

        game.movePiece(board, sourcePosition, targetPosition, PieceColor.BLACK);

        assertEquals(Blank.create(), board.findPieceByPosition(sourcePosition));
        assertEquals(Queen.createBlack(), board.findPieceByPosition(targetPosition));
    }
}

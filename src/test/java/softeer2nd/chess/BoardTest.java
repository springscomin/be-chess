package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.PieceColor;
import softeer2nd.chess.pieces.PieceType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.utils.StringUtils.appendNewLine;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void create() throws Exception {
        board.initialize();

        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR")
                        + appendNewLine("PPPPPPPP")
                        + blankRank + blankRank + blankRank + blankRank
                        + appendNewLine("pppppppp")
                        + appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @DisplayName("특정 타입, 색상의 기물 개수 확인 테스트")
    @Test
    public void countPieceByColorAndTypeTest() {
        board.initialize();

        int numOfBlackPawn = board.countPieceByColorAndType(PieceColor.BLACK, PieceType.PAWN);
        int numOfWhiteKing = board.countPieceByColorAndType(PieceColor.WHITE, PieceType.KING);

        assertEquals(8, numOfBlackPawn);
        assertEquals(1, numOfWhiteKing);
    }

    @DisplayName("특정 위치의 있는 기물 찾기 테스트")
    @Test
    public void findPieceTest() {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @DisplayName("임의의 위치에 기물 추가 기능 테스트")
    @Test
    public void addNewPieceTest() {
        board.initializeEmptyBoard();

        Piece blackKing = Piece.createBlackKing();
        String coord = "d5";

        board.addNewPiece(blackKing, coord);

        assertEquals(blackKing, board.findPiece(coord));
    }
}

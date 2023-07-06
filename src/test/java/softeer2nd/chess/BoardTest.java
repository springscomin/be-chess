package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Piece.Type.*;
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

        int numOfBlackPawn = board.countPieceByColorAndType(Piece.Color.BLACK, Piece.Type.PAWN);
        int numOfWhiteKing = board.countPieceByColorAndType(Piece.Color.WHITE, Piece.Type.KING);

        assertEquals(8, numOfBlackPawn);
        assertEquals(1, numOfWhiteKing);
    }

    @DisplayName("특정 위치에 있는 기물 찾기 테스트")
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

    @Test
    public void calculatePoint() throws Exception {
        board.initializeEmptyBoard();

        board.addNewPiece(Piece.createBlackPawn(), "b6");
        board.addNewPiece(Piece.createBlackQueen(), "e6");
        board.addNewPiece(Piece.createBlackKing(), "b8");
        board.addNewPiece(Piece.createBlackRook(), "c8");

        board.addNewPiece(Piece.createWhitePawn(), "f2");
        board.addNewPiece(Piece.createWhitePawn(), "g2");
        board.addNewPiece(Piece.createWhiteRook(), "e1");
        board.addNewPiece(Piece.createWhiteKing(), "f1");

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    @DisplayName("검은색 기물들을 제대로 정렬하는지 확인")
    @Test
    public void getSortedPiecesTest() {
        board.initializeEmptyBoard();

        board.addNewPiece(Piece.createBlackPawn(), "b6");
        board.addNewPiece(Piece.createBlackQueen(), "e6");
        board.addNewPiece(Piece.createBlackRook(), "c8");

        List<Piece> ascendingBlackPieces = board.getSortedAscendingPieces(Piece.Color.BLACK);
        List<Piece> descendingBlackPieces = board.getSortedDescendingPieces(Piece.Color.BLACK);

        assertEquals(PAWN.getDefaultPoint(), ascendingBlackPieces.get(0).getDefaultPoint(), 0.01);
        assertEquals(QUEEN.getDefaultPoint(), descendingBlackPieces.get(0).getDefaultPoint(), 0.01);
    }
}

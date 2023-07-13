package softeer2nd.chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import softeer2nd.chess.domain.pieces.*;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static softeer2nd.chess.domain.pieces.enums.PieceType.PAWN;
import static softeer2nd.chess.domain.pieces.enums.PieceType.QUEEN;

@DisplayName("Board 테스트")
public class BoardTest {
    @DisplayName("보드판 초기화(기물 배치) 테스트")
    @Test
    public void create() throws Exception {
        Board board = Board.createInitialBoard();

        assertEquals(32, board.countPiece());
    }

    @DisplayName("특정 타입, 색상의 기물 개수 구하는 기능 테스트")
    @ParameterizedTest(name = "{0},{1} 기물 -> 총 {2}개")
    @CsvSource(value = {"BLACK,PAWN,8", "WHITE,KING,1", "BLACK,QUEEN,1", "WHITE,KNIGHT,2"})
    public void countPieceByColorAndTypeTest(PieceColor color, PieceType type, int count) {
        Board board = Board.createInitialBoard();

        int numOfPiece = board.countPieceByColorAndType(color, type);

        assertEquals(count, numOfPiece);
    }


    @DisplayName("특정 위치에 있는 기물 조회 기능 테스트")
    @ParameterizedTest(name = "\"{0}\" => {1}, {2}")
    @CsvSource(value = {"a8,BLACK,ROOK", "h8,BLACK,ROOK", "a1,WHITE,ROOK", "h1,WHITE,ROOK"})
    public void findPieceTest(String coordinate, PieceColor expectedColor, PieceType expectedType) {
        Board board = Board.createInitialBoard();
        Position position = Position.fromChessNotation(coordinate);

        Piece piece = board.findPieceByPosition(position);

        assertTrue(piece.matchesColorAndType(expectedColor, expectedType));
    }

    @DisplayName("임의의 위치에 기물 추가 기능 테스트")
    @Test
    public void addNewPieceTest() {
        Board board = Board.createEmptyBoard();
        Position position = Position.fromChessNotation("d5");
        Piece blackKing = King.createBlack();

        board.addPiece(position, blackKing);

        Piece actualPiece = board.findPieceByPosition(position);
        assertEquals(blackKing, actualPiece);
    }

    //
    @DisplayName("기물 정렬 기능 테스트")
    @Test
    public void getSortedPiecesTest() {
        Board board = Board.createEmptyBoard();
        board.addPiece(Position.fromChessNotation("b6"), Pawn.createBlack());
        board.addPiece(Position.fromChessNotation("e6"), Queen.createBlack());
        board.addPiece(Position.fromChessNotation("c8"), Rook.createBlack());

        List<Piece> ascendingBlackPieces = board.getSortedAscendingPieces(PieceColor.BLACK);
        List<Piece> descendingBlackPieces = board.getSortedDescendingPieces(PieceColor.BLACK);

        assertEquals(PAWN.getDefaultPoint(), ascendingBlackPieces.get(0).getDefaultPoint(), 0.01);
        assertEquals(QUEEN.getDefaultPoint(), descendingBlackPieces.get(0).getDefaultPoint(), 0.01);
    }
}

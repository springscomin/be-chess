package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static softeer2nd.chess.domain.pieces.Piece.Type.PAWN;
import static softeer2nd.chess.domain.pieces.Piece.Type.QUEEN;

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
    public void countPieceByColorAndTypeTest(Piece.Color color, Piece.Type type, int count) {
        Board board = Board.createInitialBoard();

        int numOfPiece = board.countPieceByColorAndType(color, type);

        assertEquals(count, numOfPiece);
    }


    @DisplayName("특정 위치에 있는 기물 조회 기능 테스트")
    @ParameterizedTest(name = "\"{0}\" => {1}, {2}")
    @CsvSource(value = {"a8,BLACK,ROOK", "h8,BLACK,ROOK", "a1,WHITE,ROOK", "h1,WHITE,ROOK"})
    public void findPieceTest(String coordinate, Piece.Color expectedColor, Piece.Type expectedType) {
        Board board = Board.createInitialBoard();
        Position position = new Position(coordinate);

        Piece piece = board.findPieceByPosition(position);

        assertTrue(piece.matchesColorAndType(expectedColor, expectedType));
    }

    @DisplayName("임의의 위치에 기물 추가 기능 테스트")
    @Test
    public void addNewPieceTest() {
        Board board = Board.createEmptyBoard();
        Position position = new Position("d5");
        Piece blackKing = Piece.createBlackKing(position);

        board.addPiece(blackKing);

        Piece actualPiece = board.findPieceByPosition(position);
        assertEquals(blackKing, actualPiece);
    }

//
    @DisplayName("기물 정렬 기능 테스트")
    @Test
    public void getSortedPiecesTest() {
        Board board = Board.createEmptyBoard();
        board.addPiece(Piece.createBlackPawn(new Position("b6")));
        board.addPiece(Piece.createBlackQueen(new Position("e6")));
        board.addPiece(Piece.createBlackRook(new Position("c8")));

        List<Piece> ascendingBlackPieces = board.getSortedAscendingPieces(Piece.Color.BLACK);
        List<Piece> descendingBlackPieces = board.getSortedDescendingPieces(Piece.Color.BLACK);

        assertEquals(PAWN.getDefaultPoint(), ascendingBlackPieces.get(0).getDefaultPoint(), 0.01);
        assertEquals(QUEEN.getDefaultPoint(), descendingBlackPieces.get(0).getDefaultPoint(), 0.01);
    }
}

package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
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
import static softeer2nd.utils.StringUtils.appendNewLine;

@DisplayName("Board 테스트")
public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @DisplayName("보드판 초기화(기물 배치) 테스트")
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

    @DisplayName("특정 타입, 색상의 기물 개수 구하는 기능 테스트")
    @ParameterizedTest(name = "{0},{1} 기물 -> 총 {2}개")
    @CsvSource(value = {"BLACK,PAWN,8", "WHITE,KING,1", "BLACK,QUEEN,1", "WHITE,KNIGHT,2"})
    public void countPieceByColorAndTypeTest(Piece.Color color, Piece.Type type, int count) {
        board.initialize();

        int numOfPiece = board.countPieceByColorAndType(color, type);

        assertEquals(count, numOfPiece);
    }

    @DisplayName("특정 위치에 있는 기물 조회 기능 테스트")
    @ParameterizedTest(name = "\"{0}\" => {1}, {2}")
    @CsvSource(value = {"a8,BLACK,ROOK", "h8,BLACK,ROOK", "a1,WHITE,ROOK", "h1,WHITE,ROOK"})
    public void findPieceTest(String coordinate, Piece.Color expectedColor, Piece.Type expectedType) {
        board.initialize();

        Piece piece = board.findPiece(coordinate);

        assertTrue(piece.matchesColorAndType(expectedColor, expectedType));
    }

    @DisplayName("임의의 위치에 기물 추가 기능 테스트")
    @Test
    public void addNewPieceTest() {
        board.initializeEmptyBoard();

        String pos = "d5";

        Piece blackKing = Piece.createBlackKing(new Position(pos));

        board.addPiece(blackKing);

        assertEquals(blackKing, board.findPiece(pos));
    }

    @DisplayName("기물 점수 계산 기능 테스트")
    @Test
    public void calculatePoint() throws Exception {
        board.initializeEmptyBoard();

        board.addPiece(Piece.createBlackPawn(new Position("b6")));
        board.addPiece(Piece.createBlackQueen(new Position("e6")));
        board.addPiece(Piece.createBlackKing(new Position("b8")));
        board.addPiece(Piece.createBlackRook(new Position("c8")));

        board.addPiece(Piece.createWhitePawn(new Position("f2")));
        board.addPiece(Piece.createWhitePawn(new Position("g2")));
        board.addPiece(Piece.createWhiteRook(new Position("e1")));
        board.addPiece(Piece.createWhiteKing(new Position("f1")));

        System.out.println(board.showBoard());

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);

    }

    @DisplayName("기물 정렬 기능 테스트")
    @Test
    public void getSortedPiecesTest() {
        board.initializeEmptyBoard();

        board.addPiece(Piece.createBlackPawn(new Position("b6")));
        board.addPiece(Piece.createBlackQueen(new Position("e6")));
        board.addPiece(Piece.createBlackRook(new Position("c8")));

        List<Piece> ascendingBlackPieces = board.getSortedAscendingPieces(Piece.Color.BLACK);
        List<Piece> descendingBlackPieces = board.getSortedDescendingPieces(Piece.Color.BLACK);

        assertEquals(PAWN.getDefaultPoint(), ascendingBlackPieces.get(0).getDefaultPoint(), 0.01);
        assertEquals(QUEEN.getDefaultPoint(), descendingBlackPieces.get(0).getDefaultPoint(), 0.01);
    }

    @DisplayName("기물 이동 기능 테스트")
    @Test
    public void moveTest() {
        board.initializeEmptyBoard();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.addPiece(Piece.createBlackQueen(new Position(sourcePosition)));

        board.move(sourcePosition, targetPosition);

        assertEquals(Piece.createBlank(new Position(sourcePosition)), board.findPiece(sourcePosition));
        assertEquals(Piece.createBlackQueen(new Position(targetPosition)), board.findPiece(targetPosition));
    }
}

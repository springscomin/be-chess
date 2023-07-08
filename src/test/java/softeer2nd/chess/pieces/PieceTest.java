package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Piece 테스트")
public class PieceTest {

    @DisplayName("기물 생성 테스트")
    @ParameterizedTest(name = "{0}->색: {1}, 타입: {2}")
    @MethodSource("createPieceParameters")
    public void create_piece(final Piece piece, final Piece.Color color, final Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }

    static Stream<Arguments> createPieceParameters() {
        Position position = new Position("a1");
        return Stream.of(
                arguments(Piece.createWhitePawn(position), Piece.Color.WHITE, Piece.Type.PAWN),
                arguments(Piece.createBlackPawn(position), Piece.Color.BLACK, Piece.Type.PAWN),
                arguments(Piece.createWhiteRook(position), Piece.Color.WHITE, Piece.Type.ROOK),
                arguments(Piece.createBlackRook(position), Piece.Color.BLACK, Piece.Type.ROOK),
                arguments(Piece.createWhiteBishop(position), Piece.Color.WHITE, Piece.Type.BISHOP),
                arguments(Piece.createBlackBishop(position), Piece.Color.BLACK, Piece.Type.BISHOP),
                arguments(Piece.createWhiteKnight(position), Piece.Color.WHITE, Piece.Type.KNIGHT),
                arguments(Piece.createBlackKnight(position), Piece.Color.BLACK, Piece.Type.KNIGHT),
                arguments(Piece.createWhiteQueen(position), Piece.Color.WHITE, Piece.Type.QUEEN),
                arguments(Piece.createBlackQueen(position), Piece.Color.BLACK, Piece.Type.QUEEN),
                arguments(Piece.createWhiteKing(position), Piece.Color.WHITE, Piece.Type.KING),
                arguments(Piece.createBlackKing(position), Piece.Color.BLACK, Piece.Type.KING)
        );
    }
}

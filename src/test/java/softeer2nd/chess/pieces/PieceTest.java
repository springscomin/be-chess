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
    @ParameterizedTest(name = "{0}->색: {1}, 표현: {2}")
    @MethodSource("createPieceParameters")
    public void create_piece(final Piece piece, final Piece.Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    static Stream<Arguments> createPieceParameters() {
        Position position = new Position("a1");
        return Stream.of(
                arguments(Piece.createWhitePawn(position), Piece.Color.WHITE, Piece.Type.PAWN.getWhiteRepresentation()),
                arguments(Piece.createBlackPawn(position), Piece.Color.BLACK, Piece.Type.PAWN.getBlackRepresentation()),
                arguments(Piece.createWhiteRook(position), Piece.Color.WHITE, Piece.Type.ROOK.getWhiteRepresentation()),
                arguments(Piece.createBlackRook(position), Piece.Color.BLACK, Piece.Type.ROOK.getBlackRepresentation()),
                arguments(Piece.createWhiteBishop(position), Piece.Color.WHITE, Piece.Type.BISHOP.getWhiteRepresentation()),
                arguments(Piece.createBlackBishop(position), Piece.Color.BLACK, Piece.Type.BISHOP.getBlackRepresentation()),
                arguments(Piece.createWhiteKnight(position), Piece.Color.WHITE, Piece.Type.KNIGHT.getWhiteRepresentation()),
                arguments(Piece.createBlackKnight(position), Piece.Color.BLACK, Piece.Type.KNIGHT.getBlackRepresentation()),
                arguments(Piece.createWhiteQueen(position), Piece.Color.WHITE, Piece.Type.QUEEN.getWhiteRepresentation()),
                arguments(Piece.createBlackQueen(position), Piece.Color.BLACK, Piece.Type.QUEEN.getBlackRepresentation()),
                arguments(Piece.createWhiteKing(position), Piece.Color.WHITE, Piece.Type.KING.getWhiteRepresentation()),
                arguments(Piece.createBlackKing(position), Piece.Color.BLACK, Piece.Type.KING.getBlackRepresentation())
        );
    }
}

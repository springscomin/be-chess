package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        return Stream.of(
                arguments(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN.getWhiteRepresentation()),
                arguments(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.Type.PAWN.getBlackRepresentation()),
                arguments(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.Type.ROOK.getWhiteRepresentation()),
                arguments(Piece.createBlackRook(), Piece.Color.BLACK, Piece.Type.ROOK.getBlackRepresentation()),
                arguments(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP.getWhiteRepresentation()),
                arguments(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.Type.BISHOP.getBlackRepresentation()),
                arguments(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT.getWhiteRepresentation()),
                arguments(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.Type.KNIGHT.getBlackRepresentation()),
                arguments(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN.getWhiteRepresentation()),
                arguments(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.Type.QUEEN.getBlackRepresentation()),
                arguments(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING.getWhiteRepresentation()),
                arguments(Piece.createBlackKing(), Piece.Color.BLACK, Piece.Type.KING.getBlackRepresentation())
        );
    }
}

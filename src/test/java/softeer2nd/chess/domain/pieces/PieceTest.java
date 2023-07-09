package softeer2nd.chess.domain.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Piece 테스트")
public class PieceTest {

    @DisplayName("기물 생성 테스트")
    @ParameterizedTest(name = "{0}->색: {1}, 타입: {2}")
    @MethodSource("createPieceParameters")
    public void create_piece(final Piece piece, final PieceColor color, final PieceType type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }

    static Stream<Arguments> createPieceParameters() {
        return Stream.of(
                arguments(Pawn.createWhite(), PieceColor.WHITE, PieceType.PAWN),
                arguments(Pawn.createBlack(), PieceColor.BLACK, PieceType.PAWN),
                arguments(Rook.createWhite(), PieceColor.WHITE, PieceType.ROOK),
                arguments(Rook.createBlack(), PieceColor.BLACK, PieceType.ROOK),
                arguments(Bishop.createWhite(), PieceColor.WHITE, PieceType.BISHOP),
                arguments(Bishop.createBlack(), PieceColor.BLACK, PieceType.BISHOP),
                arguments(Knight.createWhite(), PieceColor.WHITE, PieceType.KNIGHT),
                arguments(Knight.createBlack(), PieceColor.BLACK, PieceType.KNIGHT),
                arguments(Queen.createWhite(), PieceColor.WHITE, PieceType.QUEEN),
                arguments(Queen.createBlack(), PieceColor.BLACK, PieceType.QUEEN),
                arguments(King.createWhite(), PieceColor.WHITE, PieceType.KING),
                arguments(King.createBlack(), PieceColor.BLACK, PieceType.KING)
        );
    }
}

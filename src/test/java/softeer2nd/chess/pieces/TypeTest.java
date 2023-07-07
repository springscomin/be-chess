package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static softeer2nd.chess.pieces.Piece.Type.*;

@DisplayName("Piece.Type 테스트")
public class TypeTest {

    @DisplayName("기물 타입 - 흰색 표현, 검은색 표현 테스트")
    @ParameterizedTest(name = "{0} -> white : {1}, black : {2}")
    @MethodSource("representationTestParameters")
    public void getRepresentationTest(Piece.Type type, char whiteRepresentation, char blackRepresentation) throws Exception {
        assertEquals(whiteRepresentation, type.getWhiteRepresentation());
        assertEquals(blackRepresentation, type.getBlackRepresentation());
    }

    static Stream<Arguments> representationTestParameters() {
        return Stream.of(
                arguments(PAWN, 'p', 'P'),
                arguments(ROOK, 'r', "R"),
                arguments(KNIGHT, 'n', "N"),
                arguments(BISHOP, 'b', "B"),
                arguments(QUEEN, 'q', "Q"),
                arguments(KING, 'k', "K")
        );
    }
}

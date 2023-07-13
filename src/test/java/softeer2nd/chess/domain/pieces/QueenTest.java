package softeer2nd.chess.domain.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Queen 테스트")
class QueenTest {

    @DisplayName("Black Queen 생성 테스트")
    @Test
    void createTest() {
        Queen blackQueen = Queen.createBlack();

        assertEquals(PieceType.QUEEN, blackQueen.getType());
        assertEquals(PieceColor.BLACK, blackQueen.getColor());
    }

    @DisplayName("이동 경로 테스트")
    @ParameterizedTest(name = "{0} -> {1}, 경로 :{2}")
    @MethodSource(value = "getFromToRoutes")
    void findPositionsOnRouteTest(String from, String to, List<String> stringRoutes) {
        Queen whiteQueen = Queen.createWhite();
        List<Position> routes = stringRoutes.stream()
                .map(Position::fromChessNotation)
                .collect(Collectors.toList());

        List<Position> positionsOnRoute
                = whiteQueen.getPositionsOnRoute(Position.fromChessNotation(from), Position.fromChessNotation(to));

        assertThat(positionsOnRoute).containsExactlyElementsOf(routes);
    }

    static Stream<Arguments> getFromToRoutes() {
        return Stream.of(
                arguments("a8", "a1", List.of("a7", "a6", "a5", "a4", "a3", "a2", "a1")), // 남
                arguments("a1", "a3", List.of("a2", "a3")), // 북
                arguments("a5", "d5", List.of("b5", "c5", "d5")), // 동
                arguments("e6", "b6", List.of("d6", "c6", "b6")), // 서
                arguments("d4", "g7", List.of("e5", "f6", "g7")), // 북동
                arguments("d4", "a7", List.of("c5", "b6", "a7")), // 북서
                arguments("d4", "g1", List.of("e3", "f2", "g1")), // 남동
                arguments("d4", "a1", List.of("c3", "b2", "a1")), // 남서
                arguments("a8", "b6", Collections.emptyList())
        );
    }
}

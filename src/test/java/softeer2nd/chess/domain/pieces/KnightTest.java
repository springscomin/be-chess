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

public class KnightTest {
    @DisplayName("White Knight 생성 테스트")
    @Test
    void createTest() {
        Knight whiteKnight = Knight.createWhite();

        assertEquals(PieceType.KNIGHT, whiteKnight.getType());
        assertEquals(PieceColor.WHITE, whiteKnight.getColor());
    }

    @DisplayName("이동 경로 테스트")
    @ParameterizedTest(name = "{0} -> {1}, 경로 :{2}")
    @MethodSource(value = "getFromToRoutes")
    void findPositionsOnRouteTest(String from, String to, List<String> stringRoutes) {
        Knight whiteKnight = Knight.createWhite();
        List<Position> routes = stringRoutes.stream()
                .map(Position::fromChessNotation)
                .collect(Collectors.toList());

        List<Position> positionsOnRoute
                = whiteKnight.getPositionsOnRoute(Position.fromChessNotation(from), Position.fromChessNotation(to));

        assertThat(positionsOnRoute).containsExactlyElementsOf(routes);
    }

    static Stream<Arguments> getFromToRoutes() {
        return Stream.of(
                arguments("d4", "c2", List.of("c2")),
                arguments("d4", "e2", List.of("e2")),
                arguments("d4", "f3", List.of("f3")),
                arguments("d4", "b3", List.of("b3")),
                arguments("d4", "f5", List.of("f5")),
                arguments("d4", "b5", List.of("b5")),
                arguments("d4", "c6", List.of("c6")),
                arguments("d4", "e6", List.of("e6")),
                arguments("a8", "a6", Collections.emptyList())
        );
    }
}

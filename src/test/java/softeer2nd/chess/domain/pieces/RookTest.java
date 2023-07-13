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

class RookTest {
    @DisplayName("White Rook 생성 테스트")
    @Test
    void createTest() {
        Rook whiteRook = Rook.createWhite();

        assertEquals(PieceType.ROOK, whiteRook.getType());
        assertEquals(PieceColor.WHITE, whiteRook.getColor());
    }

    @DisplayName("이동 경로 테스트")
    @ParameterizedTest(name = "{0} -> {1}, 경로 :{2}")
    @MethodSource(value = "getFromToRoutes")
    void findPositionsOnRouteTest(String from, String to, List<String> stringRoutes) {
        Rook whiteRook = Rook.createWhite();
        List<Position> routes = stringRoutes.stream()
                .map(Position::fromChessNotation)
                .collect(Collectors.toList());

        List<Position> positionsOnRoute
                = whiteRook.getPositionsOnRoute(Position.fromChessNotation(from), Position.fromChessNotation(to));

        assertThat(positionsOnRoute).containsExactlyElementsOf(routes);
    }

    static Stream<Arguments> getFromToRoutes() {
        return Stream.of(
                arguments("a8", "a1", List.of("a7", "a6", "a5", "a4", "a3", "a2", "a1")), // 남
                arguments("a1", "a3", List.of("a2", "a3")), // 북
                arguments("a5", "d5", List.of("b5", "c5", "d5")), // 동
                arguments("e6", "b6", List.of("d6", "c6", "b6")), // 서
                arguments("a8", "b6", Collections.emptyList())
        );
    }
}

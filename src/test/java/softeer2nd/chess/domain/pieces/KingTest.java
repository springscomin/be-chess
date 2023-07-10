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
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class KingTest {
    @DisplayName("White King 생성 테스트")
    @Test
    void createTest() {
        King whiteKing = King.createWhite();

        assertEquals(PieceType.KING, whiteKing.getType());
        assertEquals(PieceColor.WHITE, whiteKing.getColor());
    }

    @DisplayName("이동 경로 테스트")
    @ParameterizedTest(name = "{0} -> {1}, 경로 :{2}")
    @MethodSource(value = "getFromToRoutes")
    void findPositionsOnRouteTest(String from, String to, List<String> stringRoutes) {
        King blackKing = King.createBlack();
        List<Position> routes = stringRoutes.stream()
                .map(Position::new)
                .collect(Collectors.toList());

        List<Position> positionsOnRoute
                = blackKing.getPositionsOnRoute(new Position(from), new Position(to));

        assertThat(positionsOnRoute).containsExactlyElementsOf(routes);
    }

    static Stream<Arguments> getFromToRoutes() {
        return Stream.of(
                arguments("d4", "d3", List.of("d3")), // 남
                arguments("d4", "d5", List.of("d5")), // 북
                arguments("d4", "e4", List.of("e4")), // 동
                arguments("d4", "c4", List.of("c4")), // 서
                arguments("d4", "e5", List.of("e5")), // 북동
                arguments("d4", "c5", List.of("c5")), // 북서
                arguments("d4", "e3", List.of("e3")), // 남동
                arguments("d4", "c3", List.of("c3")), // 남서
                arguments("a8", "a6", Collections.emptyList())
        );
    }
}

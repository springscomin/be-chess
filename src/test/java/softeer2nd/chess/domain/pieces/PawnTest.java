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

class PawnTest {
    @DisplayName("Black Pawn 생성 테스트")
    @Test
    void createTest() {
        Pawn blackPawn = Pawn.createBlack();

        assertEquals(PieceType.PAWN, blackPawn.getType());
        assertEquals(PieceColor.BLACK, blackPawn.getColor());
    }

    @DisplayName("BlackPawn 이동 경로 테스트")
    @ParameterizedTest(name = "{0} -> {1}, 경로 :{2}")
    @MethodSource(value = "getBlackPawnFromToRoutes")
    void findPositionsOnRoute_black_pawn_Test(String from, String to, List<String> stringRoutes) {
        Pawn blackPawn = Pawn.createBlack();
        List<Position> routes = stringRoutes.stream()
                .map(Position::new)
                .collect(Collectors.toList());

        List<Position> positionsOnRoute
                = blackPawn.getPositionsOnRoute(new Position(from), new Position(to));

        assertThat(positionsOnRoute).containsExactlyElementsOf(routes);
    }

    @DisplayName("White Pawn 이동 경로 테스트")
    @ParameterizedTest(name = "{0} -> {1}, 경로 :{2}")
    @MethodSource(value = "getWhitePawnFromToRoutes")
    void findPositionsOnRoute_white_pawn_Test(String from, String to, List<String> stringRoutes) {
        Pawn whitePawn = Pawn.createWhite();
        List<Position> routes = stringRoutes.stream()
                .map(Position::new)
                .collect(Collectors.toList());

        List<Position> positionsOnRoute
                = whitePawn.getPositionsOnRoute(new Position(from), new Position(to));

        assertThat(positionsOnRoute).containsExactlyElementsOf(routes);
    }

    static Stream<Arguments> getBlackPawnFromToRoutes() {
        return Stream.of(
                arguments("d7", "c6", List.of("c6")),
                arguments("d7", "d6", List.of("d6")),
                arguments("d7", "d5", List.of("d6", "d5")),
                arguments("d7", "e6", List.of("e6")),
                arguments("d7", "e5", Collections.emptyList())
        );
    }

    static Stream<Arguments> getWhitePawnFromToRoutes() {
        return Stream.of(
                arguments("b2", "a3", List.of("a3")),
                arguments("b2", "b3", List.of("b3")),
                arguments("b2", "b4", List.of("b3", "b4")),
                arguments("b2", "c3", List.of("c3")),
                arguments("d7", "e5", Collections.emptyList())
        );
    }
}

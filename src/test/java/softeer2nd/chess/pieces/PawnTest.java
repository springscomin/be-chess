package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Pawn 테스트")
public class PawnTest {

    @Test
    @DisplayName("폰 기본 생성자 테스트")
    void createDefaultTest() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Pawn.WHITE);
        assertThat(pawn.getRepresentation()).isEqualTo(Pawn.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("흰색 폰 생성 테스트")
    void createWhitePawnTest() {
        verifyPawn(Pawn.WHITE, Pawn.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 폰 생성 테스트")
    void createBlackPawnTest() {
        verifyPawn(Pawn.BLACK, Pawn.BLACK_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 또는 흰색이 아닌 다른 색이 입력된 경우 폰 생성 실패")
    void createPawnTestFail() {
        String color = "red";

        assertThatThrownBy(() -> new Pawn(color, '?'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Pawn.COLOR_ERROR_MESSAGE);
    }

    private static void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }
}

package softeer2nd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Pawn 테스트")
public class PawnTest {
    @Test
    @DisplayName("흰색 폰 생성 테스트")
    void createWhitePawnTest() {
        Pawn pawn = new Pawn("white");
        assertThat(pawn.getColor()).isEqualTo("white");
    }

    @Test
    @DisplayName("검은색 폰 생성 테스트")
    void createBlackPawnTest() {
        Pawn pawn = new Pawn("black");
        assertThat(pawn.getColor()).isEqualTo("black");
    }

    @Test
    @DisplayName("폰 생성 실패 - 검은색 또는 흰색이 아닌 다른 색이 입력된 경우")
    void createPawnTestFail() {
        String color = "red";

        assertThatThrownBy(() -> new Pawn(color))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

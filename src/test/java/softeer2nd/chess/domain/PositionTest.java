package softeer2nd.chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("Position 생성 실패 테스트 - 범위 밖")
    @Test
    void generateTest() {
        String position = "k0";

        Assertions.assertThatThrownBy(() -> Position.fromChessNotation(position))
                .isInstanceOf(RuntimeException.class);
    }


}
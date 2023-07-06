package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("보드의 좌표 표기법과 물리 좌표간의 변환 테스트")
class CoordinateConverterTest {

    @DisplayName("a8 -> (0, 0)")
    @Test
    void convertNotationToIndexTest1() {
        String notation = "a8";
        int expectedRow = 0;
        int expectedCol = 0;

        verifyConvert(notation, expectedRow, expectedCol);
    }

    @DisplayName("d7 -> (1, 3)")
    @Test
    void convertNotationToIndexTest2() {
        String notation = "d7";
        int expectedRow = 1;
        int expectedCol = 3;

        verifyConvert(notation, expectedRow, expectedCol);
    }

    private static void verifyConvert(String notation, int expectedRankIndex, int expectedFileIndex) {
        Position position = CoordinateConverter.convertNotationToPosition(notation);

        assertEquals(expectedRankIndex, position.getRankIndex());
        assertEquals(expectedFileIndex, position.getFileIndex());
    }
}

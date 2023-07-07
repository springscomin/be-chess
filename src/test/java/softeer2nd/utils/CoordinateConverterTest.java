package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("체스판의 문자 좌표와 물리 좌표간의 변환 테스트")
class CoordinateConverterTest {

    @DisplayName("좌표 변환 테스트")
    @ParameterizedTest(name = "\"{0}\" -> ({1},{2})")
    @CsvSource(value = {"a8,0,0", "d7,1,3"})
    void convertNotationToPositionTest(String coordinate, int expectedRankIdx, int expectedFileIdx) {
        Position position = CoordinateConverter.convertNotationToPosition(coordinate);

        assertEquals(expectedRankIdx, position.getRankIndex());
        assertEquals(expectedFileIdx, position.getFileIndex());
    }

}

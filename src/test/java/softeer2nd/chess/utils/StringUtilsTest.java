package softeer2nd.chess.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {
    @Test
    void appendNewLineTest() {
        Assumptions.assumeTrue(System.getProperty("line.separator").equals("\n"));

        String content = "content";
        String appendedNewLine = StringUtils.appendNewLine(content);

        Assertions.assertEquals("content\n", appendedNewLine);
    }
}

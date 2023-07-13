package softeer2nd.chess.view.console;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.Command;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConsoleCommandParser 테스트")
class ConsoleCommandParserTest {
    private static ConsoleCommandParser commandParser;

    @BeforeAll
    static void inti() {
        commandParser = new ConsoleCommandParser();
    }

    @DisplayName("등록되지 않은 명령어 입력 시, 예외 반환")
    @Test
    void unEnrolledCommandTest() {
        String inputCommand = "asdvd";

        assertThatThrownBy(() -> commandParser.parseCommand(inputCommand))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("start 입력 시 Command의 타입은 START여야 함")
    @Test
    void startTest() {
        String inputCommand = "start";

        Command command = commandParser.parseCommand(inputCommand);

        assertTrue(command.isStart());
        assertFalse(command.isEnd());
        assertFalse(command.isMove());
    }

    @DisplayName("end 입력 시 command 타입은 END 여야 함.")
    @Test
    void endTest() {
        String inputCommand = "end";

        Command command = commandParser.parseCommand(inputCommand);

        assertTrue(command.isEnd());
        assertFalse(command.isStart());
        assertFalse(command.isMove());
    }

    @DisplayName("move 입력 시 인자가 2개여야 함.")
    @Test
    void moveTest() {
        String inputCommand = "move a2 b2";

        Command command = commandParser.parseCommand(inputCommand);
        String[] args = command.getArgs();

        assertTrue(command.isMove());
        assertThat(args).hasSize(2);
        assertEquals(args[0], "a2");
        assertEquals(args[1], "b2");
    }

    @DisplayName("move 입력 시 인자 2개를 입력하지 않은 경우 예외 반환")
    @Test
    void moveCommandWithOutTwoArgs() {
        String inputCommand = "move a2";

        assertThatThrownBy(() -> commandParser.parseCommand(inputCommand))
                .isInstanceOf(RuntimeException.class);
    }
}
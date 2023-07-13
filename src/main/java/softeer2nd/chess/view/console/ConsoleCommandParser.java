package softeer2nd.chess.view.console;

import softeer2nd.Command;

import static softeer2nd.chess.view.console.ChessConsoleView.*;

public class ConsoleCommandParser {
    public Command parseCommand(String stringCommand) {
        String[] commands = stringCommand.split(" ");
        String operation = commands[0];

        if (operation.equals(START)) {
            return new Command(Command.CommandType.START);
        }
        if (operation.equals(END)) {
            return new Command(Command.CommandType.END);
        }
        if (operation.equals(MOVE)) {
            verifyTwoArgs(commands);
            return new Command(Command.CommandType.MOVE, commands[1], commands[2]);
        }
        throw new RuntimeException("올바른 명령 아님");
    }


    private void verifyTwoArgs(String[] commands) {
        if (commands.length != 3) {
            throw new RuntimeException("인자가 2개여야 합니다.");
        }
    }

}

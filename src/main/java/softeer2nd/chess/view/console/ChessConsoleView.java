package softeer2nd.chess.view.console;

import softeer2nd.Command;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;
import softeer2nd.chess.view.ChessView;
import softeer2nd.utils.StringUtils;

import java.util.List;
import java.util.Scanner;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;
import static softeer2nd.utils.StringUtils.NEWLINE;

public class ChessConsoleView implements ChessView {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    @Override
    public Command getCommand() {
        showEmptyLine();
        showMessage("명령어를 입력하세요.");
        String stringCommand = getConsoleInput();
        showEmptyLine();
        return parseCommand(stringCommand);
    }

    private Command parseCommand(String stringCommand) {
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

    public String getConsoleInput() {
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void showBoard(List<List<Piece>> board) {
        String representations = makeBoardRepresentation(board);
        showMessage(representations);
    }

    @Override
    public void showMessage(String message) {
        message = StringUtils.appendNewLine(message);
        System.out.print(message);
    }

    @Override
    public void showError(RuntimeException error) {
        showMessage("[error] : " + error.getMessage());
    }

    private void showEmptyLine() {
        showMessage("");
    }

    private String makeBoardRepresentation(final List<List<Piece>> board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int rankIndex = 0; rankIndex < BOARD_LENGTH; rankIndex++) {
            List<Piece> rank = board.get(rankIndex);
            String rankRepresentation = makeRankRepresentation(rank);
            stringBuilder.append(rankRepresentation).append(" ").append(BOARD_LENGTH - rankIndex).append(NEWLINE);
        }
        stringBuilder.append("abcdefgh");
        return stringBuilder.toString();
    }

    private String makeRankRepresentation(List<Piece> rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece piece : rank) {
            PieceType type = piece.getType();
            PieceColor color = piece.getColor();
            char representation = ViewRepresentation.getByTypeAndColor(type, color);
            stringBuilder.append(representation);
        }
        return stringBuilder.toString();
    }
}

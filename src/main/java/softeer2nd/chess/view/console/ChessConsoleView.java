package softeer2nd.chess.view.console;

import softeer2nd.Command;
import softeer2nd.CommandType;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.view.ChessView;

import java.util.List;
import java.util.Scanner;

import static softeer2nd.utils.StringUtils.NEWLINE;

public class ChessConsoleView implements ChessView {
    @Override
    public Command getCommand() {
        String stringCommand = getConsoleInput();
        return parseCommand(stringCommand);
    }

    private Command parseCommand(String stringCommand) {
        String[] commands = stringCommand.split(" ");
        String operation = commands[0];

        if (operation.equals("start")) {
            return new Command(CommandType.START);
        }
        if (operation.equals("end")) {
            return new Command(CommandType.END);
        }
        if (operation.equals("move")) {
            return new Command(CommandType.MOVE, commands[1], commands[2]);
        }
        throw new RuntimeException("올바른 명령 아님");
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
        System.out.println(message);
    }

    private String makeBoardRepresentation(final List<List<Piece>> board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<Piece> rank : board) {
            String rankRepresentation = makeRankRepresentation(rank);
            stringBuilder.append(rankRepresentation).append(NEWLINE);
        }
        return stringBuilder.toString();
    }

    private String makeRankRepresentation(List<Piece> rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece piece : rank) {
            Piece.Type type = piece.getType();
            Piece.Color color = piece.getColor();
            char representation = ViewRepresentation.getByTypeAndColor(type, color);
            stringBuilder.append(representation);
        }
        return stringBuilder.toString();
    }
}

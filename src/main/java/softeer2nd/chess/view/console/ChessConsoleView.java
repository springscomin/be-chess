package softeer2nd.chess.view.console;

import softeer2nd.chess.flow.Command;
import softeer2nd.chess.flow.Turn;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;
import softeer2nd.chess.view.ChessView;
import softeer2nd.chess.utils.StringUtils;

import java.util.List;
import java.util.Scanner;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;

public class ChessConsoleView implements ChessView {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    public ConsoleCommandParser commandParser = new ConsoleCommandParser();

    @Override
    public void showInfo() {
        showMessage("체스 게임에 오신걸 환영합니다.");
    }

    @Override
    public void showRequestStart() {
        showMessage("게임을 시작하려면 \"start\"를 입력하세요.");
    }

    @Override
    public void showTurn(Turn turn) {
        showEmptyLine();
        if (turn.isWhiteTurn()) {
            showMessage("White 차례입니다.");
            return;
        }
        showMessage("Black 차례입니다.");
    }

    @Override
    public Command getCommand() {
        while (true) {
            try {
                String stringCommand = getConsoleInput();
                return commandParser.parseCommand(stringCommand);
            } catch (RuntimeException e) {
                showError(e);
            }
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

    @Override
    public void showScore(PieceColor pieceColor, double whiteTeamScore) {
        showMessage(pieceColor + " 의 점수는 " + whiteTeamScore + "입니다.");
    }

    private void showEmptyLine() {
        showMessage("");
    }

    private String makeBoardRepresentation(final List<List<Piece>> board) {
        StringBuilder stringBuilder = new StringBuilder(NEWLINE);
        for (int rankIndex = 0; rankIndex < BOARD_LENGTH; rankIndex++) {
            List<Piece> rank = board.get(rankIndex);
            String rankRepresentation = makeRankRepresentation(rank);
            stringBuilder.append(rankRepresentation).append("|").append(BOARD_LENGTH - rankIndex).append(NEWLINE);
        }
        stringBuilder.append("--------").append(NEWLINE).append("abcdefgh");
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

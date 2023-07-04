package softeer2nd;

import softeer2nd.chess.ChessController;

import java.util.Scanner;

public class ChessApp {
    public static final String START = "start";
    public static final String END = "end";

    private final ChessController chessController = new ChessController();

    private boolean isStarted = false;

    public void run() {
        printConsoleOutput("체스 게임");
        printConsoleOutput("시작 : start, 종료 : end");

        while (true) {
            String input = getConsoleInput();
            if (START.equals(input) && isStarted) printConsoleOutput("이미 시작 되었습니다.");
            else if (START.equals(input)) initSetting();
            else if (END.equals(input)) exitSystem();
            else printConsoleOutput("???");
        }
    }

    private void initSetting() {
        isStarted = true;
        chessController.init();
        String board = chessController.getBoard();
        printConsoleOutput(board);
    }

    private static void exitSystem() {
        System.exit(0);
    }

    private String getConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private void printConsoleOutput(String message) {
        System.out.println(message);
    }
}

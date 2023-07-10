package softeer2nd;

import softeer2nd.chess.controller.ChessController;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.view.ChessView;
import softeer2nd.chess.view.console.ChessConsoleView;

import java.util.List;

public class ChessApp {
    private final ChessView chessView = new ChessConsoleView();
    private final ChessController chessController = new ChessController();

    private boolean isStarted = false;

    public void run() {
        chessView.showMessage("체스 게임\n시작 : start, 종료 : end");

        while (true) {
            try {
                Command command = chessView.getCommand();
                if (command.isStart() && isStarted) chessView.showMessage("이미 시작됨.");
                else if (command.isStart()) initSetting();
                else if (command.isEnd()) exitSystem();
                else if (command.isMove()) move(command);
                showBoard();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void move(Command command) {
        String[] args = command.getArgs();
        chessController.movePiece(args[0], args[1]);
    }

    private void initSetting() {
        isStarted = true;
        chessController.init();
    }

    private void showBoard() {
        List<List<Piece>> board = chessController.getBoard();
        chessView.showBoard(board);
    }

    private static void exitSystem() {
        System.exit(0);
    }
}

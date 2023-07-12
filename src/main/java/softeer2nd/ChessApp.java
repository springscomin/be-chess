package softeer2nd;

import softeer2nd.chess.Game;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.view.ChessView;
import softeer2nd.chess.view.console.ChessConsoleView;

import java.util.List;

public class ChessApp {
    private final ChessView chessView = new ChessConsoleView();
    private final Game game = new Game();

    private boolean isStarted = false;

    public void run() {
        chessView.showMessage("체스 게임\n시작 : start, 종료 : end");
        while (true) {
            try {
                Command command = chessView.getCommand();
                if (command.isStart() && isStarted) chessView.showMessage("이미 시작됨.");
                if (command.isStart()) initSetting();
                if (command.isEnd()) exitSystem();
                if (command.isMove()) move(command);
                showBoard();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void move(Command command) {
        String[] args = command.getArgs();
        game.movePiece(args[0], args[1]);
    }

    private void initSetting() {
        isStarted = true;
        game.init();
    }

    private void showBoard() {
        List<List<Piece>> board = game.getBoard();
        chessView.showBoard(board);
    }

    private static void exitSystem() {
        System.exit(0);
    }
}

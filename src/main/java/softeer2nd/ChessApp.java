package softeer2nd;

import softeer2nd.chess.Game;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.view.ChessView;
import softeer2nd.chess.view.console.ChessConsoleView;

import java.util.List;

public class ChessApp {
    private final ChessView chessView = new ChessConsoleView();
    private Game game;

    public void run() {
        chessView.showMessage("체스 게임\n시작 : start, 종료 : end");

        while (true) {
            try {
                Command command = chessView.getCommand();
                if (command.isStart()) initSetting();
                if (command.isEnd()) exitGame();
                if (command.isMove()) move(command);
                showBoard();
            } catch (RuntimeException e) {
                chessView.showError(e);
            }
        }
    }

    private void initSetting() {
        if (game != null) {
            throw new RuntimeException("이미 시작됨.");
        }
        game = new Game();
        game.init();
    }

    private void showBoard() {
        List<List<Piece>> board = game.getBoard();
        chessView.showBoard(board);
    }

    private void move(Command command) {
        String[] args = command.getArgs();
        game.movePiece(args[0], args[1]);
    }

    private static void exitGame() {
        System.exit(0);
    }
}

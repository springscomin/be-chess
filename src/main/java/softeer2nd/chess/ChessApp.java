package softeer2nd.chess;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.flow.Command;
import softeer2nd.chess.flow.Game;
import softeer2nd.chess.flow.Turn;
import softeer2nd.chess.view.ChessView;
import softeer2nd.chess.view.console.ChessConsoleView;

import java.util.List;

public class ChessApp {
    private final ChessView chessView = new ChessConsoleView();
    private Game game;

    public void run() {
        start();
        while (true) {
            process();
        }
    }

    private void process() {
        showCurrentState();
        try {
            Command command = chessView.getCommand();
            if (command.isEnd()) {
                exitGame();
            }
            if (command.isMove()) {
                move(command);
            }
            showScore();
        } catch (RuntimeException e) {
            chessView.showError(e);
        }
    }

    private void start() {
        chessView.showInfo();
        while (true) {
            chessView.showRequestStart();
            Command command = chessView.getCommand();
            if (command.isStart()) {
                initSetting();
                break;
            }
        }
    }

    private void showCurrentState() {
        showBoard();
        showTurn();
    }

    private void showTurn() {
        Turn turn = game.getTurn();
        chessView.showTurn(turn);
    }

    private void initSetting() {
        game = new Game();
        game.init();
    }

    private void showScore() {
        double blackTeamScore = game.getScore(PieceColor.BLACK);
        double whiteTeamScore = game.getScore(PieceColor.WHITE);
        chessView.showScore(PieceColor.BLACK, blackTeamScore);
        chessView.showScore(PieceColor.WHITE, whiteTeamScore);
    }

    private void showBoard() {
        List<List<Piece>> board = game.getBoard();
        chessView.showBoard(board);
    }

    private void move(Command command) {
        String[] args = command.getArgs();
        game.movePiece(args[0], args[1]);
    }

    private void exitGame() {
        showScore();
        System.exit(0);
    }
}

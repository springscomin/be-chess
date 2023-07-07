package softeer2nd.chess.controller;

import softeer2nd.chess.domain.Board;

public class ChessController {
    private Board board;
    public void init() {
        board = new Board();
        board.initialize();
    }

    public String getBoard() {
        return board.showBoard();
    }
}

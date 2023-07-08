package softeer2nd.chess.controller;

import softeer2nd.chess.domain.Board;

public class ChessController {
    private Board board;
    public void init() {
        board = Board.createInitialBoard();
    }

    public String getBoard() {
        return board.showBoard();
    }
}

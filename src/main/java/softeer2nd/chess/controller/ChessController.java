package softeer2nd.chess.controller;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

public class ChessController {
    private Board board;
    public void init() {
        board = Board.createInitialBoard();
    }

    public List<List<Piece>> getBoard() {
        return board.showBoard();
    }
}

package softeer2nd.chess;

import softeer2nd.chess.domain.Board;

public class ChessGameManager {
    private Board board;

    public void initializeBoard() {
        board = Board.createInitialBoard();
    }

}

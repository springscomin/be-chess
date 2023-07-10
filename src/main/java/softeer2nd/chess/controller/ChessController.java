package softeer2nd.chess.controller;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.ChessGame;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

public class ChessController {
    private Board board;
    private ChessGame game;
    public void init() {
        board = Board.createInitialBoard();
        game = new ChessGame(board);
    }

    public List<List<Piece>> getBoard() {
        return board.showBoard();
    }

    public void movePiece(String sourcePosition, String destPosition) {
        game.movePiece(new Position(sourcePosition), new Position(destPosition));
    }
}

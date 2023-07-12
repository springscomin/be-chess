package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.ChessGame;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

public class Game {
    private Board board;
    private ChessGame game;
    public void init() {

    }

    public List<List<Piece>> getBoard() {
        return board.showBoard();
    }

    public void movePiece(String sourcePosition, String destPosition) {
        game.movePiece(new Position(sourcePosition), new Position(destPosition));
    }
}

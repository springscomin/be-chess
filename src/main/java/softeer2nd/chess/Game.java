package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.ChessGame;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

public class Game {
    private Board board;
    private ChessGame chessGame;

    // TODO
    // 턴 개념 추가

    public void init() {
        board = Board.createInitialBoard();
        chessGame = new ChessGame(board);
    }

    public List<List<Piece>> getBoard() {
        return board.showBoard();
    }

    public void movePiece(String sourcePosition, String destPosition) {
        chessGame.movePiece(new Position(sourcePosition), new Position(destPosition));
    }
}

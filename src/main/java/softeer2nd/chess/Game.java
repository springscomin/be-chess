package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import java.util.List;

public class Game {
    private Board board;
    private PieceMover pieceMover;
    private ScoreCalculator scoreCalculator;
    private Turn turn;

    public void init() {
        board = Board.createInitialBoard();
        pieceMover = new PieceMover();
        turn = new Turn();
    }

    public List<List<Piece>> getBoard() {
        return board.showBoard();
    }

    public void movePiece(String sourcePosition, String destPosition) {
        try {
            PieceColor color = turn.next();
            pieceMover.movePiece(board, new Position(sourcePosition), new Position(destPosition), color);
        } catch (RuntimeException exception) {
            turn.back();
            throw exception;
        }
    }
}

package softeer2nd.chess.flow;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.logic.PieceMover;
import softeer2nd.chess.logic.ScoreCalculator;
import softeer2nd.chess.logic.WinnerDeterminer;

import java.util.List;

public class Game {
    private Board board;
    private PieceMover pieceMover;
    private ScoreCalculator scoreCalculator;
    private WinnerDeterminer winnerDeterminer;
    private Turn turn;

    public void init() {
        board = Board.createInitialBoard();
        pieceMover = new PieceMover();
        scoreCalculator = new ScoreCalculator();
        winnerDeterminer = new WinnerDeterminer();
        turn = new Turn();
    }

    public List<List<Piece>> getBoard() {
        return board.showBoard();
    }

    public void movePiece(String source, String destination) {
        try {
            PieceColor turn = this.turn.next();
            Position sourcePosition = Position.fromChessNotation(source);
            Position destPosition = Position.fromChessNotation(destination);
            pieceMover.movePiece(board, sourcePosition, destPosition, turn);
        } catch (RuntimeException exception) {
            turn.back();
            throw exception;
        }
    }

    public double getScore(PieceColor color) {
        return scoreCalculator.calculatePoint(board, color);
    }

    public Turn getTurn() {
        return turn;
    }

    public boolean isEnd() {
        return winnerDeterminer.hasWinner(board);
    }

    public PieceColor findWinner() {
        return winnerDeterminer.getWinner(board);
    }
}

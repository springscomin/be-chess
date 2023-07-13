package softeer2nd.chess.logic;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import java.util.Optional;

public class WinnerDeterminer {
    public boolean hasWinner(Board board) {
        Optional<PieceColor> winner = findWinner(board);
        return winner.isPresent();
    }

    public PieceColor getWinner(Board board) {
        return findWinner(board).get();
    }

    private Optional<PieceColor> findWinner(Board board) {
        Optional<Piece> blackKing = board.findKingByColor(PieceColor.BLACK);
        Optional<Piece> whiteKing = board.findKingByColor(PieceColor.WHITE);
        if (blackKing.isPresent() && whiteKing.isPresent()) {
            return Optional.empty();
        }
        if (blackKing.isPresent()) {
            return Optional.of(PieceColor.BLACK);
        }
        return Optional.of(PieceColor.WHITE);
    }
}

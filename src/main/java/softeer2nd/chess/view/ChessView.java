package softeer2nd.chess.view;

import softeer2nd.chess.flow.Command;
import softeer2nd.chess.flow.Turn;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import java.util.List;

public interface ChessView {
    void showInfo();

    void showRequestStart();

    void showTurn(Turn turn);

    Command getCommand();

    void showBoard(List<List<Piece>> boards);

    void showMessage(String message);

    void showError(RuntimeException e);

    void showScore(PieceColor pieceColor, double whiteTeamScore);
}

package softeer2nd.chess.view;

import softeer2nd.Command;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

public interface ChessView {
    Command getCommand();

    void showBoard(List<List<Piece>> boards);

    void showMessage(String message);

    void showError(RuntimeException e);
}

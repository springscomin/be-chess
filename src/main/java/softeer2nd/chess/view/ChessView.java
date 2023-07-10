package softeer2nd.chess.view;

import softeer2nd.Command;
import softeer2nd.chess.domain.pieces.Piece;

import java.util.List;

public interface ChessView {
    public Command getCommand();

    public void showBoard(List<List<Piece>> boards);

    public void showMessage(String message);
}

package softeer2nd.chess.view.console;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.view.ChessView;

import java.util.List;

import static softeer2nd.utils.StringUtils.NEWLINE;

public class ChessConsoleView implements ChessView {
    @Override
    public void printBoard(Board board) {
        String representations = makeBoardRepresentation(board);
        System.out.println(representations);
    }

    private String makeBoardRepresentation(final Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        List<List<Piece>> boards = board.getBoard();
        for (List<Piece> rank : boards) {
            String rankRepresentation = makeRankRepresentation(rank);
            stringBuilder.append(rankRepresentation).append(NEWLINE);
        }
        return stringBuilder.toString();
    }

    private String makeRankRepresentation(List<Piece> rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece piece : rank) {
            Piece.Type type = piece.getType();
            Piece.Color color = piece.getColor();
            char representation = ViewRepresentation.getByTypeAndColor(type, color);
            stringBuilder.append(representation);
        }
        return stringBuilder.toString();
    }
}

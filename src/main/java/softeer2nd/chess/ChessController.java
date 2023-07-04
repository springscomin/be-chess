package softeer2nd.chess;

public class ChessController {
    private Board board;
    public void init() {
        board = new Board();
        board.initialize();
    }

    public String getBoard() {
        return board.print();
    }
}

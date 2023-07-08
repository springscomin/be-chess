package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.Piece.Color;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class ChessGameManager {
    private Board board;

    public void initializeBoard() {
        board = Board.createInitialBoard();
    }

    public double calculatePoint(Color color) {
        return IntStream.range(0, BOARD_LENGTH)
                .mapToDouble(file -> calculateFilePoint(file, color))
                .sum();
    }

    private double calculateFilePoint(int fileIndex, Color color) {
        List<Piece> pieces = board.findPiecesInFile(fileIndex)
                .stream()
                .filter(piece -> piece.matchesColor(color))
                .collect(Collectors.toList());

        double point = calPoint(pieces);
        int numOfPawns = getNumOfPawns(pieces);
        return point - calPenaltyPoint(numOfPawns);
    }

    private int getNumOfPawns(List<Piece> pieces) {
        return (int) pieces.stream()
                .filter(Piece::isPawn)
                .count();
    }

    private double calPoint(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getDefaultPoint)
                .sum();
    }

    private double calPenaltyPoint(int numOfPawn) {
        if (numOfPawn >= 2) {
            return numOfPawn * 0.5;
        }
        return 0;
    }
}

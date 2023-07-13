package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class ScoreCalculator {
    public double calculatePoint(Board board, PieceColor color) {
        return IntStream.range(0, BOARD_LENGTH)
                .mapToDouble(file -> calculateFilePoint(board, file, color))
                .sum();
    }

    private double calculateFilePoint(Board board, int fileIndex, PieceColor color) {
        List<Piece> piecesInFile = findPiecesInFileByColor(board, fileIndex, color);

        double point = calPoint(piecesInFile);
        int numOfPawns = getNumOfPawns(piecesInFile);
        return point - calPenaltyPoint(numOfPawns);
    }

    private List<Piece> findPiecesInFileByColor(Board board, int fileIndex, PieceColor color) {
        return board.findPiecesInFile(fileIndex)
                .stream()
                .filter(piece -> piece.matchesColor(color))
                .collect(Collectors.toList());
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

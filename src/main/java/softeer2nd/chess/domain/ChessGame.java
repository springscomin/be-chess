package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        Piece piece = board.findPieceByPosition(sourcePosition);
        validateIsNotBlank(piece);

        List<Position> positionsOnRoute = piece.getPositionsOnRoute(sourcePosition, targetPosition);
        validateCanMoveTo(positionsOnRoute);

        board.removePiece(sourcePosition);
        board.addPiece(targetPosition, piece);
    }

    private void validateCanMoveTo(List<Position> positionsOnRoute) {
        if (positionsOnRoute.isEmpty()) {
            throw new RuntimeException("이동할 수 없음.");
        }
    }

    private void validateIsNotBlank(Piece piece) {
        if (piece.isBlank()) {
            throw new RuntimeException("Blank Piece 입니다.");
        }
    }

    public double calculatePoint(PieceColor color) {
        return IntStream.range(0, BOARD_LENGTH)
                .mapToDouble(file -> calculateFilePoint(file, color))
                .sum();
    }

    private double calculateFilePoint(int fileIndex, PieceColor color) {
        List<Piece> piecesInFile = findPiecesInFileByColor(fileIndex, color);

        double point = calPoint(piecesInFile);
        int numOfPawns = getNumOfPawns(piecesInFile);
        return point - calPenaltyPoint(numOfPawns);
    }

    private List<Piece> findPiecesInFileByColor(int fileIndex, PieceColor color) {
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

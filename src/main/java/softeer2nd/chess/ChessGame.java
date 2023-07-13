package softeer2nd.chess;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceDirection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(Position sourcePosition, Position targetPosition, PieceColor color) {
        Piece piece = board.findPieceByPosition(sourcePosition);
        verifyMatchColor(piece, color);

        List<Position> positionsOnRoute = piece.getPositionsOnRoute(sourcePosition, targetPosition);
        verifyRouteExist(positionsOnRoute);
        verifyNoPieceOnRoute(positionsOnRoute, color);

        if (piece.isPawn()) {
            verifyPawnMove(sourcePosition, targetPosition);
        }

        board.removePiece(sourcePosition);
        board.addPiece(targetPosition, piece);
    }

    private void verifyPawnMove(Position sourcePosition, Position targetPosition) {
        int diffRank = targetPosition.getRankIndex() - sourcePosition.getRankIndex();
        int diffFile = targetPosition.getFileIndex() - sourcePosition.getFileIndex();

        if (PieceDirection.isDiagonal(diffRank, diffFile)) {
            Piece pieceOnDestination = board.findPieceByPosition(targetPosition);
            if (pieceOnDestination.isBlank()) {
                throw new RuntimeException("폰은 대각 방향의 빈 공간으로 이동할 수 없습니다.");
            }
        }
        if (PieceDirection.isLinear(diffRank, diffFile)) {
            Piece pieceOnDestination = board.findPieceByPosition(targetPosition);
            if (!pieceOnDestination.isBlank()) {
                throw new RuntimeException("폰은 상대방 기물이 앞에 있을 때 전진할 수 없습니다.");
            }
        }
    }

    private void verifyNoPieceOnRoute(List<Position> positionsOnRoute, PieceColor color) {
        Position destination = positionsOnRoute.remove(positionsOnRoute.size() - 1);
        for (Position position : positionsOnRoute) {
            Piece pieceOnRoute = board.findPieceByPosition(position);
            if (!pieceOnRoute.isBlank()) {
                throw new RuntimeException("경로상에 기물이 존재합니다.");
            }
        }
        Piece pieceOnDestination = board.findPieceByPosition(destination);
        if (pieceOnDestination.matchesColor(color)) {
            throw new RuntimeException("경로상에 기물이 존재합니다.");
        }
    }

    private void verifyRouteExist(List<Position> positionsOnRoute) {
        if (positionsOnRoute.isEmpty()) {
            throw new RuntimeException("이동할 수 없음.");
        }
    }

    private void verifyMatchColor(Piece piece, PieceColor color) {
        if (piece.matchesColor(color)) {
            return;
        }
        if (piece.isBlank()) {
            throw new RuntimeException("해당 위치에 기물이 없습니다.");
        }
        throw new RuntimeException(color.name() + "차례 입니다.");
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

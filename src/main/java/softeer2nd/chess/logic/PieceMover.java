package softeer2nd.chess.logic;

import softeer2nd.chess.domain.Board;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceDirection;

import java.util.List;

public class PieceMover {
    public void movePiece(Board board, Position sourcePosition, Position targetPosition, PieceColor color) {
        Piece piece = board.findPieceByPosition(sourcePosition);
        List<Position> positionsOnRoute = piece.getPositionsOnRoute(sourcePosition, targetPosition);

        verifyMatchColor(piece, color);
        verifyRouteExist(positionsOnRoute);
        verifyNoPieceOnRoute(board, positionsOnRoute, color);
        if (piece.isPawn()) {
            verifyPawnMove(board, sourcePosition, targetPosition);
        }

        board.removePiece(sourcePosition);
        board.addPiece(targetPosition, piece);
    }

    private void verifyPawnMove(Board board, Position sourcePosition, Position targetPosition) {
        int diffRank = targetPosition.getRankIndex() - sourcePosition.getRankIndex();
        int diffFile = targetPosition.getFileIndex() - sourcePosition.getFileIndex();

        if (PieceDirection.isDiagonal(diffRank, diffFile)) {
            verifyDiagonalDirection(board, targetPosition);
        }
        if (PieceDirection.isLinear(diffRank, diffFile)) {
            verifyLinearDirection(board, targetPosition);
        }
    }

    private static void verifyLinearDirection(Board board, Position targetPosition) {
        Piece pieceOnDestination = board.findPieceByPosition(targetPosition);
        if (!pieceOnDestination.isBlank()) {
            throw new RuntimeException("폰은 상대방 기물이 앞에 있을 때 전진할 수 없습니다.");
        }
    }

    private static void verifyDiagonalDirection(Board board, Position targetPosition) {
        Piece pieceOnDestination = board.findPieceByPosition(targetPosition);
        if (pieceOnDestination.isBlank()) {
            throw new RuntimeException("폰은 대각 방향의 빈 공간으로 이동할 수 없습니다.");
        }
    }

    private void verifyNoPieceOnRoute(Board board, List<Position> positionsOnRoute, PieceColor color) {
        Position destination = positionsOnRoute.remove(positionsOnRoute.size() - 1);
        for (Position position : positionsOnRoute) {
            Piece pieceOnRoute = board.findPieceByPosition(position);
            if (!pieceOnRoute.isBlank()) {
                throw new RuntimeException("경로상에 기물이 존재합니다.");
            }
        }
        verifyNoSameColorOnTarget(board, color, destination);
    }

    private static void verifyNoSameColorOnTarget(Board board, PieceColor color, Position destination) {
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
}

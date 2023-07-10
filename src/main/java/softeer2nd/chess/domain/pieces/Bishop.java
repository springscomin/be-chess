package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceDirection;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.*;

public class Bishop extends Piece {
    public static List<PieceDirection> directions = PieceDirection.diagonalDirection();

    private Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        Map<PieceDirection, List<Position>> positionsOnDirection = findAllPositionCanMove(start);
        for (PieceDirection direction : positionsOnDirection.keySet()) {
            List<Position> positions = positionsOnDirection.get(direction);
            if (positions.contains(dest)) {
                int destIndex = positions.indexOf(dest);
                return positions.subList(0, destIndex + 1);
            }
            ;
        }
        return Collections.emptyList();
    }

    private Map<PieceDirection, List<Position>> findAllPositionCanMove(Position start) {
        Map<PieceDirection, List<Position>> directionRoutes = new HashMap<>();
        for (PieceDirection direction : directions) {
            List<Position> positions = new ArrayList<>();
            addPositionsOnDirection(direction, start, positions);
            directionRoutes.put(direction, positions);
        }
        return directionRoutes;
    }

    private void addPositionsOnDirection(PieceDirection direction, Position current, List<Position> positions) {
        int nextRank = current.getRankIndex() + direction.getYDegree();
        int nextFile = current.getFileIndex() + direction.getXDegree();
        if (!Position.isValid(nextRank, nextFile)) return;
        Position nextPosition = new Position(nextRank, nextFile);
        positions.add(nextPosition);
        addPositionsOnDirection(direction, nextPosition, positions);
    }

    public static Bishop createWhite() {
        return new Bishop(PieceColor.WHITE);
    }

    public static Bishop createBlack() {
        return new Bishop(PieceColor.BLACK);
    }
}

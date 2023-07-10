package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceDirection;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.*;

public class King extends Piece{
    public static List<PieceDirection> directions = PieceDirection.everyDirection();

    private King(PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        Map<PieceDirection, List<Position>> positionsOnDirection = findAllPositionCanMove(start);
        for (PieceDirection direction : positionsOnDirection.keySet()) {
            List<Position> positions = positionsOnDirection.get(direction);
            if (positions.contains(dest)) {
                return positions;
            };
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
    }


    public static King createWhite() {
        return new King(PieceColor.WHITE);
    }

    public static King createBlack() {
        return new King(PieceColor.BLACK);
    }
}

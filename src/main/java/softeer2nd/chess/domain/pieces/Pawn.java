package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceDirection;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.*;

public class Pawn extends Piece {
    public static List<PieceDirection> whitePawnDirection = PieceDirection.whitePawnDirection();
    public static List<PieceDirection> blackPawnDirection = PieceDirection.blackPawnDirection();

    private Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public List<Position> getPositionsOnRoute(Position start, Position dest) {
        Map<PieceDirection, List<Position>> positionsOnDirection = findAllPositionCanMove(start);
        for (PieceDirection direction : positionsOnDirection.keySet()) {
            List<Position> positions = positionsOnDirection.get(direction);
            if (positions.contains(dest)) {
                return positions;
            }
        }
        return Collections.emptyList();
    }

    private Map<PieceDirection, List<Position>> findAllPositionCanMove(Position start) {
        Map<PieceDirection, List<Position>> directionRoutes = new HashMap<>();

        List<PieceDirection> directions = blackPawnDirection;
        if (this.getColor().equals(PieceColor.WHITE)) {
            directions = whitePawnDirection;
        }
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

    public static Pawn createWhite() {
        return new Pawn(PieceColor.WHITE);
    }

    public static Pawn createBlack() {
        return new Pawn(PieceColor.BLACK);
    }
}

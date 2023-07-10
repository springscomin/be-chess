package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.List;
import java.util.Objects;

import static softeer2nd.chess.domain.pieces.enums.PieceType.NO_PIECE;
import static softeer2nd.chess.domain.pieces.enums.PieceType.PAWN;


public abstract class Piece {
    private final PieceColor color;
    private final PieceType type;

    protected Piece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract List<Position> getPositionsOnRoute(Position start, Position dest);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                '}';
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public boolean matchesColorAndType(PieceColor color, PieceType type) {
        return this.color.equals(color) && this.type.equals(type);
    }

    public boolean matchesColor(PieceColor color) {
        return this.color.equals(color);
    }

    public double getDefaultPoint() {
        return type.getDefaultPoint();
    }

    public boolean isPawn() {
        return type.equals(PAWN);
    }

    public boolean isBlank() {
        return type.equals(NO_PIECE);
    }
}

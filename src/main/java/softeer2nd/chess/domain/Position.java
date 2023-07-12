package softeer2nd.chess.domain;

import java.util.Objects;

import static softeer2nd.chess.domain.Board.BOARD_LENGTH;

public class Position {
    private final int rankIndex;
    private final int fileIndex;

    public Position(int rankIndex, int fileIndex) {
        this.rankIndex = rankIndex;
        this.fileIndex = fileIndex;
    }

    public Position(String sourcePosition) {
        validateChessNotation(sourcePosition);
        char rowNotation = sourcePosition.charAt(1);
        char colNotation = sourcePosition.charAt(0);

        this.rankIndex = BOARD_LENGTH - (rowNotation - '0');
        this.fileIndex = colNotation - 'a';
    }

    private void validateChessNotation(String sourcePosition) {
        char rowNotation = sourcePosition.charAt(1);
        char colNotation = sourcePosition.charAt(0);
        if (rowNotation < '1' || rowNotation > '8' || colNotation < 'a' || colNotation > 'h') {
            throw new RuntimeException("유효하지 않은 위치 정보입니다");
        }
    }

    public static boolean isValid(int rank, int file) {
        return rank >= 0 && rank < BOARD_LENGTH && file >= 0 && file < BOARD_LENGTH;
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rankIndex == position.rankIndex && fileIndex == position.fileIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankIndex, fileIndex);
    }

    @Override
    public String toString() {
        return "Position{" +
                "rankIndex=" + rankIndex +
                ", fileIndex=" + fileIndex +
                '}';
    }
}

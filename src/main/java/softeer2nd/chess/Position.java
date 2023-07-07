package softeer2nd.chess;

public class Position {
    private final int rankIndex;
    private final int fileIndex;

    public Position(int rankIndex, int fileIndex) {
        this.rankIndex = rankIndex;
        this.fileIndex = fileIndex;
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public int getFileIndex() {
        return fileIndex;
    }
}

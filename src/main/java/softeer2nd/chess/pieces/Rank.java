package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private final List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void update(int index, Piece piece) {
        pieces.set(index, piece);
    }

    public Piece findByIndex(int index) {
        return pieces.get(index);
    }

    public List<Piece> findByColor(Piece.Color color) {
        List<Piece> findPieces = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece.matchesColor(color)) {
                findPieces.add(piece);
            }
        }
        return findPieces;
    }

    public int countPieces() {
        int pieceCount = 0;
        for (Piece piece : pieces) {
            if (piece.isBlank()) continue;
            pieceCount++;
        }
        return pieceCount;
    }

    public int countPieceByColorAndType(Piece.Color color, Piece.Type type) {
        int count = 0;
        for (Piece piece : pieces) {
            if (piece.matchesColorAndType(color, type)) {
                count++;
            }
        }
        return count;
    }

    public String getLineRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece piece : pieces) {
            char representation = piece.getRepresentation();
            stringBuilder.append(representation);
        }
        return stringBuilder.toString();
    }

    public static Rank createBlankRank(int rankIndex) {
        List<Piece> blankPieces = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            blankPieces.add(Piece.createBlank(new Position(rankIndex, fileIndex)));
        }
        return new Rank(blankPieces);
    }

    public static Rank CreateWhitePawnRank(int rankIndex) {
        List<Piece> whitePawns = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            whitePawns.add(Piece.createWhitePawn(new Position(rankIndex, fileIndex)));
        }
        return new Rank(whitePawns);
    }

    public static Rank CreateBlackPawnRank(int rankIndex) {
        List<Piece> blackPawns = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            blackPawns.add(Piece.createBlackPawn(new Position(rankIndex, fileIndex)));
        }
        return new Rank(blackPawns);
    }

    public static Rank createWhiteOfficersRank(int rankIndex) {
        List<Piece> whiteOfficers = new ArrayList<>();
        whiteOfficers.add(Piece.createWhiteRook(new Position(rankIndex, 0)));
        whiteOfficers.add(Piece.createWhiteKnight(new Position(rankIndex, 1)));
        whiteOfficers.add(Piece.createWhiteBishop(new Position(rankIndex, 2)));
        whiteOfficers.add(Piece.createWhiteQueen(new Position(rankIndex, 3)));
        whiteOfficers.add(Piece.createWhiteKing(new Position(rankIndex, 4)));
        whiteOfficers.add(Piece.createWhiteBishop(new Position(rankIndex, 5)));
        whiteOfficers.add(Piece.createWhiteKnight(new Position(rankIndex, 6)));
        whiteOfficers.add(Piece.createWhiteRook(new Position(rankIndex, 7)));
        return new Rank(whiteOfficers);
    }

    public static Rank createBlackOfficersRank(int rankIndex) {
        List<Piece> blackOfficers = new ArrayList<>();
        blackOfficers.add(Piece.createBlackRook(new Position(rankIndex, 0)));
        blackOfficers.add(Piece.createBlackKnight(new Position(rankIndex, 1)));
        blackOfficers.add(Piece.createBlackBishop(new Position(rankIndex, 2)));
        blackOfficers.add(Piece.createBlackQueen(new Position(rankIndex, 3)));
        blackOfficers.add(Piece.createBlackKing(new Position(rankIndex, 4)));
        blackOfficers.add(Piece.createBlackBishop(new Position(rankIndex, 5)));
        blackOfficers.add(Piece.createBlackKnight(new Position(rankIndex, 6)));
        blackOfficers.add(Piece.createBlackRook(new Position(rankIndex, 7)));
        return new Rank(blackOfficers);
    }

    public double getPiecePointAtIndex(int fileIndex, Piece.Color color) {
        Piece piece = pieces.get(fileIndex);
        if (piece.matchesColor(color)) {
            return piece.getDefaultPoint();
        }
        return 0;
    }

    public boolean isPiecePawn(int fileIndex, Piece.Color color) {
        Piece piece = pieces.get(fileIndex);
        if (!piece.matchesColor(color)) {
            return false;
        }
        return piece.isPawn();
    }
}

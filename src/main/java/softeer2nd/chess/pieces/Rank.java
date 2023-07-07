package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;

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

    public static Rank createBlankRank() {
        List<Piece> blankPieces = new ArrayList<>();
        for (int num = 0; num < Board.BOARD_LENGTH; num++) {
            blankPieces.add(Piece.createBlank());
        }
        return new Rank(blankPieces);
    }

    public static Rank CreateWhitePawnRank() {
        List<Piece> whitePawns = new ArrayList<>();
        for (int num = 0; num < Board.BOARD_LENGTH; num++) {
            whitePawns.add(Piece.createWhitePawn());
        }
        return new Rank(whitePawns);
    }

    public static Rank CreateBlackPawnRank() {
        List<Piece> blackPawns = new ArrayList<>();
        for (int num = 0; num < Board.BOARD_LENGTH; num++) {
            blackPawns.add(Piece.createBlackPawn());
        }
        return new Rank(blackPawns);
    }

    public static Rank createWhiteOfficersRank() {
        List<Piece> whiteOfficers = new ArrayList<>();
        whiteOfficers.add(Piece.createWhiteRook());
        whiteOfficers.add(Piece.createWhiteKnight());
        whiteOfficers.add(Piece.createWhiteBishop());
        whiteOfficers.add(Piece.createWhiteQueen());
        whiteOfficers.add(Piece.createWhiteKing());
        whiteOfficers.add(Piece.createWhiteBishop());
        whiteOfficers.add(Piece.createWhiteKnight());
        whiteOfficers.add(Piece.createWhiteRook());
        return new Rank(whiteOfficers);
    }

    public static Rank createBlackOfficersRank() {
        List<Piece> blackOfficers = new ArrayList<>();
        blackOfficers.add(Piece.createBlackRook());
        blackOfficers.add(Piece.createBlackKnight());
        blackOfficers.add(Piece.createBlackBishop());
        blackOfficers.add(Piece.createBlackQueen());
        blackOfficers.add(Piece.createBlackKing());
        blackOfficers.add(Piece.createBlackBishop());
        blackOfficers.add(Piece.createBlackKnight());
        blackOfficers.add(Piece.createBlackRook());
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

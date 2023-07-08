package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rank {
    private final List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void update(int index, Piece piece) {
        pieces.set(index, piece);
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public Piece getPiece(int index) {
        return pieces.get(index);
    }

    public List<Piece> findByColor(PieceColor color) {
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

    public int countPieceByColorAndType(PieceColor color, PieceType type) {
        int count = 0;
        for (Piece piece : pieces) {
            if (piece.matchesColorAndType(color, type)) {
                count++;
            }
        }
        return count;
    }

    public static Rank createBlankRank() {
        List<Piece> blankPieces = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            blankPieces.add(Piece.createBlank());
        }
        return new Rank(blankPieces);
    }

    public static Rank CreateWhitePawnRank() {
        List<Piece> whitePawns = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            whitePawns.add(Piece.createWhitePawn());
        }
        return new Rank(whitePawns);
    }

    public static Rank CreateBlackPawnRank() {
        List<Piece> blackPawns = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
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

    public double getPiecePointAtIndex(int fileIndex, PieceColor color) {
        Piece piece = pieces.get(fileIndex);
        if (piece.matchesColor(color)) {
            return piece.getDefaultPoint();
        }
        return 0;
    }

    public boolean isPiecePawn(int fileIndex, PieceColor color) {
        Piece piece = pieces.get(fileIndex);
        if (!piece.matchesColor(color)) {
            return false;
        }
        return piece.isPawn();
    }

    public void remove(Position position) {
        Piece blankPiece = Piece.createBlank();
        pieces.set(position.getFileIndex(), blankPiece);
    }
}

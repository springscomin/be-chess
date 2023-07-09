package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.*;
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
            blankPieces.add(Blank.create());
        }
        return new Rank(blankPieces);
    }

    public static Rank CreateWhitePawnRank() {
        List<Piece> whitePawns = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            whitePawns.add(Pawn.createWhite());
        }
        return new Rank(whitePawns);
    }

    public static Rank CreateBlackPawnRank() {
        List<Piece> blackPawns = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            blackPawns.add(Pawn.createBlack());
        }
        return new Rank(blackPawns);
    }

    public static Rank createWhiteOfficersRank() {
        List<Piece> whiteOfficers = new ArrayList<>();
        whiteOfficers.add(Rook.createWhite());
        whiteOfficers.add(Knight.createWhite());
        whiteOfficers.add(Bishop.createWhite());
        whiteOfficers.add(Queen.createWhite());
        whiteOfficers.add(King.createWhite());
        whiteOfficers.add(Bishop.createWhite());
        whiteOfficers.add(Knight.createWhite());
        whiteOfficers.add(Rook.createWhite());
        return new Rank(whiteOfficers);
    }

    public static Rank createBlackOfficersRank() {
        List<Piece> blackOfficers = new ArrayList<>();
        blackOfficers.add(Rook.createBlack());
        blackOfficers.add(Knight.createBlack());
        blackOfficers.add(Bishop.createBlack());
        blackOfficers.add(Queen.createBlack());
        blackOfficers.add(King.createBlack());
        blackOfficers.add(Bishop.createBlack());
        blackOfficers.add(Knight.createBlack());
        blackOfficers.add(Rook.createBlack());
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
        Piece blankPiece = Blank.create();
        pieces.set(position.getFileIndex(), blankPiece);
    }
}

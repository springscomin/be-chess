package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.*;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rank {
    private final List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void add(int index, Piece piece) {
        pieces.set(index, piece);
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public Piece getPiece(int index) {
        return pieces.get(index);
    }

    public List<Piece> findByColor(PieceColor color) {
        return pieces.stream()
                .filter(piece -> piece.matchesColor(color))
                .collect(Collectors.toList());
    }

    public int countPieces() {
        return (int) pieces.stream()
                .filter(piece -> !piece.isBlank())
                .count();
    }

    public int countPieceByColorAndType(PieceColor color, PieceType type) {
        return (int) pieces.stream()
                .filter(piece -> piece.matchesColorAndType(color, type))
                .count();
    }

    public void remove(Position position) {
        Piece blankPiece = Blank.create();
        pieces.set(position.getFileIndex(), blankPiece);
    }

    public static Rank createBlankRank() {
        List<Piece> blankPieces = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            blankPieces.add(Blank.create());
        }
        return new Rank(blankPieces);
    }

    public static Rank CreateWhitePawnRank() {
        List<Piece> whitePawns =
                IntStream.range(0, Board.BOARD_LENGTH)
                        .mapToObj(fileIdx -> Pawn.createWhite())
                        .collect(Collectors.toList());
        return new Rank(whitePawns);
    }

    public static Rank CreateBlackPawnRank() {
        List<Piece> blackPawns =
                IntStream.range(0, Board.BOARD_LENGTH)
                        .mapToObj(fileIdx -> Pawn.createBlack())
                        .collect(Collectors.toList());
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
}

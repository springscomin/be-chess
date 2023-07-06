package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Rank;
import softeer2nd.utils.CoordinateConverter;

import java.util.*;

import static softeer2nd.utils.StringUtils.NEWLINE;

public class Board {
    public static final int BLACK_OFFICER_PIECES_INIT_RANK = 0;
    public static final int BLACK_PAWN_INIT_LINE = 1;
    public static final int WHITE_PAWN_INIT_LINE = 6;
    public static final int WHITE_OFFICER_PIECES_INIT_LINE = 7;

    public static final int BOARD_LENGTH = 8;

    private List<Rank> boards;

    public void initialize() {
        initializeEmptyBoard();
        addWhitePawns();
        addWhiteOfficerPieces();
        addBlackPawns();
        addBlackOfficerPieces();
    }

    public int pieceCount() {
        int pieceCount = 0;
        for (Rank rank : boards) {
            pieceCount += rank.countPieces();
        }
        return pieceCount;
    }

    public String showBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Rank rank : boards) {
            String lineRepresentation = rank.getLineRepresentation();
            stringBuilder.append(lineRepresentation).append(NEWLINE);
        }

        return stringBuilder.toString();
    }

    public void initializeEmptyBoard() {
        boards = new ArrayList<>();
        for (int row = 0; row < BOARD_LENGTH; row++) {
            Rank emptyRank = Rank.createEmptyRank();
            boards.add(emptyRank);
        }
    }

    private void addWhitePawns() {
        Rank whitePawns = Rank.CreateWhitePawnRank();
        boards.set(WHITE_PAWN_INIT_LINE, whitePawns);
    }

    private void addBlackPawns() {
        Rank blackPawns = Rank.CreateBlackPawnRank();
        boards.set(BLACK_PAWN_INIT_LINE, blackPawns);
    }

    private void addWhiteOfficerPieces() {
        Rank whiteOfficers = Rank.createWhiteOfficersRank();
        boards.set(WHITE_OFFICER_PIECES_INIT_LINE, whiteOfficers);
    }

    private void addBlackOfficerPieces() {
        Rank blackOfficers = Rank.createBlackOfficersRank();
        boards.set(BLACK_OFFICER_PIECES_INIT_RANK, blackOfficers);
    }

    public int countPieceByColorAndType(Piece.Color color, Piece.Type type) {
        int count = 0;
        for (Rank rank : boards) {
            count += rank.countPieceByColorAndType(color, type);
        }
        return count;
    }

    public Piece findPiece(String coordinate) {
        List<Integer> index = CoordinateConverter.convertChessNotationToIndex(coordinate);
        int rank = index.get(0);
        int file = index.get(1);

        return findPieceByIndex(rank, file);
    }

    private Piece findPieceByIndex(int rankIndex, int fileIndex) {
        Rank rank = boards.get(rankIndex);
        return rank.findByIndex(fileIndex);
    }

    public void addNewPiece(Piece piece, String coord) {
        List<Integer> index = CoordinateConverter.convertChessNotationToIndex(coord);
        int rankIndex = index.get(0);
        int fileIndex = index.get(1);

        Rank rank = boards.get(rankIndex);
        rank.update(fileIndex, piece);
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0;
        for (int col = 0; col < BOARD_LENGTH; col++) {
            point += calculateVerticalPoint(col, color);
        }
        return point;
    }

    private double calculateVerticalPoint(int fileIndex, Piece.Color color) {
        double point = 0;
        int numOfPawn = 0;
        for (Rank rank : boards) {
            Piece piece = rank.findByIndex(fileIndex);
            if (!piece.matchesColor(color)) continue;
            if (piece.isPawn()) {
                numOfPawn++;
            }
            point += piece.getDefaultPoint();
        }
        if (numOfPawn >= 2) {
            point -= numOfPawn * 0.5;
        }
        return point;
    }

    public List<Piece> getSortedAscendingPieces(Piece.Color color) {
        List<Piece> sortedPieces = findPiecesByColor(color);
        sortedPieces.sort((piece1, piece2) -> (int) (piece1.getDefaultPoint() - piece2.getDefaultPoint()));
        return sortedPieces;
    }

    public List<Piece> getSortedDescendingPieces(Piece.Color color) {
        List<Piece> sortedPieces = findPiecesByColor(color);
        sortedPieces.sort((piece1, piece2) -> (int) (piece2.getDefaultPoint() - piece1.getDefaultPoint()));
        return sortedPieces;
    }

    private List<Piece> findPiecesByColor(Piece.Color color) {
        List<Piece> findPieces = new ArrayList<>();
        for (Rank rank : boards) {
            findPieces.addAll(rank.findByColor(color));
        }
        return findPieces;
    }
}

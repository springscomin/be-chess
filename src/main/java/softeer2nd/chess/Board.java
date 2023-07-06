package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
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
            Rank emptyRank = Rank.createBlankRank();
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

    private void addRank(int rankIndex, Rank rank){
        boards.set(rankIndex, rank);
    }

    public int countPieceByColorAndType(Color color, Type type) {
        int count = 0;
        for (Rank rank : boards) {
            count += rank.countPieceByColorAndType(color, type);
        }
        return count;
    }

    public Piece findPiece(String coordinate) {
        Position position = CoordinateConverter.convertNotationToPosition(coordinate);
        return findPieceByPosition(position);
    }

    private Piece findPieceByPosition(Position position) {
        Rank rank = boards.get(position.getRankIndex());
        return rank.findByIndex(position.getFileIndex());
    }

    public void addNewPiece(Piece piece, String coordinate) {
        Position position = CoordinateConverter.convertNotationToPosition(coordinate);
        Rank rank = boards.get(position.getRankIndex());
        rank.update(position.getFileIndex(), piece);
    }

    public double calculatePoint(Color color) {
        double point = 0;
        for (int col = 0; col < BOARD_LENGTH; col++) {
            point += calculateFilePoint(col, color);
        }
        return point;
    }

    private double calculateFilePoint(int fileIndex, Color color) {
        double point = 0;
        int numOfPawn = 0;
        for (Rank rank : boards) {
            point += rank.getPiecePointAtIndex(fileIndex, color);
            if (rank.isPiecePawn(fileIndex, color)) {
                numOfPawn++;
            }
        }
        return point - calPenaltyPoint(numOfPawn);
    }

    private double calPenaltyPoint(int numOfPawn) {
        if (numOfPawn >= 2) {
            return numOfPawn * 0.5;
        }
        return 0;
    }

    private static double getPoint(double point, int numOfPawn) {
        if (numOfPawn >= 2) {
            point -= numOfPawn * 0.5;
        }
        return point;
    }

    public List<Piece> getSortedAscendingPieces(Color color) {
        List<Piece> sortedPieces = findPiecesByColor(color);
        sortedPieces.sort((piece1, piece2) -> (int) (piece1.getDefaultPoint() - piece2.getDefaultPoint()));
        return sortedPieces;
    }

    public List<Piece> getSortedDescendingPieces(Color color) {
        List<Piece> sortedPieces = findPiecesByColor(color);
        sortedPieces.sort((piece1, piece2) -> (int) (piece2.getDefaultPoint() - piece1.getDefaultPoint()));
        return sortedPieces;
    }

    private List<Piece> findPiecesByColor(Color color) {
        List<Piece> findPieces = new ArrayList<>();
        for (Rank rank : boards) {
            findPieces.addAll(rank.findByColor(color));
        }
        return findPieces;
    }
}

package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.Piece.Color;
import softeer2nd.chess.domain.pieces.Piece.Type;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return boards.stream()
                .mapToInt(Rank::countPieces)
                .sum();
    }

    public String showBoard() {
        return boards.stream()
                .map(rank -> {
                    String lineRepresentation = rank.getLineRepresentation();
                    return lineRepresentation + NEWLINE;
                }).collect(Collectors.joining());
    }

    public void initializeEmptyBoard() {
        boards = IntStream.range(0, BOARD_LENGTH)
                .mapToObj(Rank::createBlankRank)
                .collect(Collectors.toList());
    }

    private void addWhitePawns() {
        Rank whitePawns = Rank.CreateWhitePawnRank(Board.WHITE_PAWN_INIT_LINE);
        boards.set(WHITE_PAWN_INIT_LINE, whitePawns);
    }

    private void addBlackPawns() {
        Rank blackPawns = Rank.CreateBlackPawnRank(Board.BLACK_PAWN_INIT_LINE);
        boards.set(BLACK_PAWN_INIT_LINE, blackPawns);
    }

    private void addWhiteOfficerPieces() {
        Rank whiteOfficers = Rank.createWhiteOfficersRank(Board.WHITE_OFFICER_PIECES_INIT_LINE);
        boards.set(WHITE_OFFICER_PIECES_INIT_LINE, whiteOfficers);
    }

    private void addBlackOfficerPieces() {
        Rank blackOfficers = Rank.createBlackOfficersRank(Board.BLACK_OFFICER_PIECES_INIT_RANK);
        boards.set(BLACK_OFFICER_PIECES_INIT_RANK, blackOfficers);
    }

    public int countPieceByColorAndType(Color color, Type type) {
        return boards.stream()
                .mapToInt(rank -> rank.countPieceByColorAndType(color, type))
                .sum();
    }

    public Piece findPiece(String coordinate) {
        Position position = new Position(coordinate);
        return findPieceByPosition(position);
    }

    private Piece findPieceByPosition(Position position) {
        Rank rank = boards.get(position.getRankIndex());
        return rank.get(position.getFileIndex());
    }

    public void addPiece(Piece piece) {
        Position position = piece.getPosition();
        Rank rank = boards.get(position.getRankIndex());
        rank.update(position.getFileIndex(), piece);
    }

    public double calculatePoint(Color color) {
        return IntStream.range(0, BOARD_LENGTH)
                .mapToDouble(file -> calculateFilePoint(file, color))
                .sum();
    }

    private double calculateFilePoint(int fileIndex, Color color) {
        double point = calPointsInFile(fileIndex, color);
        int numOfPawn = getNumOfColorPawnInFile(fileIndex, color);
        return point - calPenaltyPoint(numOfPawn);
    }

    private double calPointsInFile(int fileIndex, Color color) {
        return boards.stream()
                .map(rank -> rank.get(fileIndex))
                .filter(piece -> piece.matchesColor(color))
                .mapToDouble(Piece::getDefaultPoint)
                .sum();
    }

    private int getNumOfColorPawnInFile(int fileIndex, Color color) {
        return (int) boards.stream()
                .map(rank -> rank.get(fileIndex))
                .filter(Piece::isPawn)
                .filter(piece -> piece.matchesColor(color))
                .count();
    }

    private double calPenaltyPoint(int numOfPawn) {
        if (numOfPawn >= 2) {
            return numOfPawn * 0.5;
        }
        return 0;
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
        return boards.stream()
                .flatMap(rank -> rank.findByColor(color).stream())
                .collect(Collectors.toList());
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece piece = findPiece(sourcePosition);
        removePiece(piece);

        Position destination = new Position(targetPosition);
        Piece movedPiece = Piece.createMovedPiece(piece, destination);
        addPiece(movedPiece);
    }

    private void removePiece(Piece piece) {
        Position position = piece.getPosition();
        Rank rank = boards.get(position.getRankIndex());
        rank.remove(position);
    }
}

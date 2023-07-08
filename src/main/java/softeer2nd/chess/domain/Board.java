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

    private Board(){}

    public static Board createInitialBoard() {
        Board board = createEmptyBoard();
        board.initialize();
        return board;
    }

    public static Board createEmptyBoard() {
        Board board = new Board();
        board.initializeEmptyBoard();
        return board;
    }

    private void initializeEmptyBoard() {
        boards = IntStream.range(0, BOARD_LENGTH)
                .mapToObj(Rank::createBlankRank)
                .collect(Collectors.toList());
    }

    private void initialize() {
        addWhitePawns();
        addWhiteOfficerPieces();
        addBlackPawns();
        addBlackOfficerPieces();
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

    public int countPiece() {
        return boards.stream()
                .mapToInt(Rank::countPieces)
                .sum();
    }

    public int countPieceByColorAndType(Color color, Type type) {
        return boards.stream()
                .mapToInt(rank -> rank.countPieceByColorAndType(color, type))
                .sum();
    }

    public String showBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : boards) {
            String lineRepresentation = rank.getLineRepresentation();
            stringBuilder.append(lineRepresentation).append(NEWLINE);
        }
        return stringBuilder.toString();
    }

    public Piece findPiece(String coordinate) {
        Position position = new Position(coordinate);
        return findPieceByPosition(position);
    }

    public Piece findPieceByPosition(Position position) {
        Rank rank = boards.get(position.getRankIndex());
        return rank.get(position.getFileIndex());
    }

    public List<Piece> findPiecesInFile(int fileIndex) {
        return boards.stream()
                .map(rank -> rank.get(fileIndex))
                .collect(Collectors.toList());
    }

    private List<Piece> findPiecesByColor(Color color) {
        return boards.stream()
                .flatMap(rank -> rank.findByColor(color).stream())
                .collect(Collectors.toList());
    }

    public void addPiece(Piece piece) {
        Position position = piece.getPosition();
        Rank rank = boards.get(position.getRankIndex());
        rank.update(position.getFileIndex(), piece);
    }

    public void removePiece(Piece piece) {
        Position position = piece.getPosition();
        Rank rank = boards.get(position.getRankIndex());
        rank.remove(position);
    }
}

package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    public static final int BLACK_OFFICER_PIECES_INIT_RANK = 0;
    public static final int BLACK_PAWN_INIT_LINE = 1;
    public static final int WHITE_PAWN_INIT_LINE = 6;
    public static final int WHITE_OFFICER_PIECES_INIT_LINE = 7;

    public static final int BOARD_LENGTH = 8;

    private List<Rank> boards;

    private Board() {
    }

    public List<List<Piece>> showBoard() {
        return boards.stream()
                .map(rank -> Collections.unmodifiableList(rank.getPieces()))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Board createEmptyBoard() {
        Board board = new Board();
        board.initializeEmptyBoard();
        return board;
    }

    public static Board createInitialBoard() {
        Board board = createEmptyBoard();
        board.initialize();
        return board;
    }

    private void initializeEmptyBoard() {
        boards = IntStream.range(0, BOARD_LENGTH)
                .mapToObj(n -> Rank.createBlankRank())
                .collect(Collectors.toList());
    }

    private void initialize() {
        addWhitePawns();
        addWhiteOfficerPieces();
        addBlackPawns();
        addBlackOfficerPieces();
    }

    private void addWhitePawns() {
        Rank whitePawns = Rank.createWhitePawnRank();
        boards.set(WHITE_PAWN_INIT_LINE, whitePawns);
    }

    private void addBlackPawns() {
        Rank blackPawns = Rank.createBlackPawnRank();
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

    public int countPiece() {
        return boards.stream()
                .mapToInt(Rank::countPieces)
                .sum();
    }

    public int countPieceByColorAndType(PieceColor color, PieceType type) {
        return boards.stream()
                .mapToInt(rank -> rank.countPieceByColorAndType(color, type))
                .sum();
    }

    public Piece findPieceByPosition(Position position) {
        Rank rank = boards.get(position.getRankIndex());
        return rank.getPiece(position.getFileIndex());
    }

    private List<Piece> findPiecesByColor(PieceColor color) {
        List<Piece> findPieces = new ArrayList<>();
        for (Rank rank : boards) {
            findPieces.addAll(rank.findByColor(color));
        }
        return findPieces;
    }

    public List<Piece> findPiecesInFile(int fileIndex) {
        return boards.stream()
                .map(rank -> rank.getPiece(fileIndex))
                .collect(Collectors.toList());
    }

    public void addPiece(Position position, Piece piece) {
        Rank rank = boards.get(position.getRankIndex());
        rank.add(position.getFileIndex(), piece);
    }

    public void removePiece(Position position) {
        Rank rank = boards.get(position.getRankIndex());
        rank.remove(position);
    }

    public List<Piece> getSortedAscendingPieces(PieceColor color) {
        List<Piece> sortedPieces = findPiecesByColor(color);
        sortedPieces.sort((piece1, piece2) -> (int) (piece1.getDefaultPoint() - piece2.getDefaultPoint()));
        return sortedPieces;
    }

    public List<Piece> getSortedDescendingPieces(PieceColor color) {
        List<Piece> sortedPieces = findPiecesByColor(color);
        sortedPieces.sort((piece1, piece2) -> (int) (piece2.getDefaultPoint() - piece1.getDefaultPoint()));
        return sortedPieces;
    }
}

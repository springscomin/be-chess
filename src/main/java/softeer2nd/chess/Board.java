package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static softeer2nd.utils.StringUtils.NEWLINE;

public class Board {
    public static final char EMPTY_REPRESENTATION = '.';

    public static final int BLACK_PAWN_INIT_LINE = 1;
    public static final int WHITE_PAWN_INIT_LINE = 6;

    public static final int BOARD_LENGTH = 8;

    private List<List<Piece>> boards;

    public void initialize() {
        initializeEmptyBoard();
        addWhitePawns();
        addBlackPawns();
    }

    public String getWhitePawnsResult() {
        List<Piece> whitePawns = boards.get(WHITE_PAWN_INIT_LINE);
        return makeLineRepresentation(whitePawns);
    }

    public String getBlackPawnsResult() {
        List<Piece> blackPawns = boards.get(BLACK_PAWN_INIT_LINE);
        return makeLineRepresentation(blackPawns);
    }

    public String print() {
    public String showBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        for (List<Piece> line : boards) {
            String lineRepresentation = makeLineRepresentation(line);
            stringBuilder.append(lineRepresentation).append(NEWLINE);
        }

        return stringBuilder.toString();
    }


    private String makeLineRepresentation(List<Piece> pawns) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece pawn : pawns) {
            char representation = getRepresentation(pawn);
            stringBuilder.append(representation);
        }
        return stringBuilder.toString();
    }

    private char getRepresentation(Piece pawn) {
        char representation = EMPTY_REPRESENTATION;
        if (!Objects.isNull(pawn)) representation = pawn.getRepresentation();
        return representation;
    }

    private void initializeEmptyBoard() {
        boards = new ArrayList<>();
        for (int row = 0; row < BOARD_LENGTH; row++) {
            ArrayList<Piece> pawns = new ArrayList<>();
            for (int col = 0; col < BOARD_LENGTH; col++) pawns.add(null);
            boards.add(pawns);
        }
    }

    private void addWhitePawns() {
        List<Piece> whitePawns = boards.get(WHITE_PAWN_INIT_LINE);
        whitePawns.replaceAll(piece -> Piece.createWhitePawn());
    }

    private void addBlackPawns() {
        List<Piece> blackPawns = boards.get(BLACK_PAWN_INIT_LINE);
        blackPawns.replaceAll(piece -> Piece.createBlackPawn());
    }
}

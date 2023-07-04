package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    public static final char LINE_BREAK = '\n';
    public static final char EMPTY_REPRESENTATION = '.';

    public static final int BLACK_PAWN_INIT_LINE = 1;
    public static final int WHITE_PAWN_INIT_LINE = 6;

    public static final int BOARD_LENGTH = 8;

    private List<List<Pawn>> boards;

    public void initialize() {
        initializeEmptyBoard();
        addWhitePawn();
        addBlackPawn();
    }

    public String getWhitePawnsResult() {
        List<Pawn> whitePawns = boards.get(WHITE_PAWN_INIT_LINE);
        return makeLineRepresentation(whitePawns);
    }

    public String getBlackPawnsResult() {
        List<Pawn> blackPawns = boards.get(BLACK_PAWN_INIT_LINE);
        return makeLineRepresentation(blackPawns);
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();

        for (List<Pawn> line : boards) {
            String lineRepresentation = makeLineRepresentation(line);
            stringBuilder.append(lineRepresentation).append(LINE_BREAK);
        }

        return stringBuilder.toString();
    }


    private String makeLineRepresentation(List<Pawn> pawns) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pawn pawn : pawns) {
            char representation = getRepresentation(pawn);
            stringBuilder.append(representation);
        }
        return stringBuilder.toString();
    }

    private char getRepresentation(Pawn pawn) {
        char representation = EMPTY_REPRESENTATION;
        if (!Objects.isNull(pawn)) representation = pawn.getRepresentation();
        return representation;
    }

    private void initializeEmptyBoard() {
        boards = new ArrayList<>();
        for (int row = 0; row < BOARD_LENGTH; row++) {
            ArrayList<Pawn> pawns = new ArrayList<>();
            for (int col = 0; col < BOARD_LENGTH; col++) pawns.add(null);
            boards.add(pawns);
        }
    }

    private void addWhitePawn() {
        List<Pawn> whitePawns = boards.get(WHITE_PAWN_INIT_LINE);
        whitePawns.replaceAll(pawn -> new Pawn(Pawn.WHITE, Pawn.WHITE_REPRESENTATION));
    }

    private void addBlackPawn() {
        List<Pawn> blackPawns = boards.get(BLACK_PAWN_INIT_LINE);
        blackPawns.replaceAll(pawn -> new Pawn(Pawn.BLACK, Pawn.BLACK_REPRESENTATION));
    }
}

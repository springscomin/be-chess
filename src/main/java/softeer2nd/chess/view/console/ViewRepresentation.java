package softeer2nd.chess.view.console;

import softeer2nd.chess.domain.pieces.Piece.Color;
import softeer2nd.chess.domain.pieces.Piece.Type;

import java.util.Arrays;

public enum ViewRepresentation {
    BLANK('.', Type.NO_PIECE, Color.NO_COLOR),

    BLACK_PAWN('P', Type.PAWN, Color.BLACK),
    BLACK_ROOK('R', Type.ROOK, Color.BLACK),
    BLACK_KNIGHT('N', Type.KNIGHT, Color.BLACK),
    BLACK_BISHOP('B', Type.BISHOP, Color.BLACK),
    BLACK_QUEEN('Q', Type.QUEEN, Color.BLACK),
    BLACK_KING('K', Type.KING, Color.BLACK),

    WHITE_PAWN('p', Type.PAWN, Color.WHITE),
    WHITE_ROOK('r', Type.ROOK, Color.WHITE),
    WHITE_KNIGHT('n', Type.KNIGHT, Color.WHITE),
    WHITE_BISHOP('b', Type.BISHOP, Color.WHITE),
    WHITE_QUEEN('q', Type.QUEEN, Color.WHITE),
    WHITE_KING('k', Type.KING, Color.WHITE),
    ;

    private final char representation;
    private final Type pieceType;
    private final Color pieceColor;

    ViewRepresentation(char representation, Type type, Color black) {
        this.representation = representation;
        this.pieceType = type;
        this.pieceColor = black;
    }

    public static char getByTypeAndColor(Type type, Color color) {
        return Arrays.stream(ViewRepresentation.values())
                .filter(representation -> representation.pieceType.equals(type))
                .filter(representation -> representation.pieceColor.equals(color))
                .findFirst()
                .orElseThrow()
                .representation;
    }
}

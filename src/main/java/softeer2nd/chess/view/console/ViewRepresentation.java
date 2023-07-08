package softeer2nd.chess.view.console;

import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.Arrays;

public enum ViewRepresentation {
    BLANK('.', PieceType.NO_PIECE, PieceColor.NO_COLOR),

    BLACK_PAWN('P', PieceType.PAWN, PieceColor.BLACK),
    BLACK_ROOK('R', PieceType.ROOK, PieceColor.BLACK),
    BLACK_KNIGHT('N', PieceType.KNIGHT, PieceColor.BLACK),
    BLACK_BISHOP('B', PieceType.BISHOP, PieceColor.BLACK),
    BLACK_QUEEN('Q', PieceType.QUEEN, PieceColor.BLACK),
    BLACK_KING('K', PieceType.KING, PieceColor.BLACK),

    WHITE_PAWN('p', PieceType.PAWN, PieceColor.WHITE),
    WHITE_ROOK('r', PieceType.ROOK, PieceColor.WHITE),
    WHITE_KNIGHT('n', PieceType.KNIGHT, PieceColor.WHITE),
    WHITE_BISHOP('b', PieceType.BISHOP, PieceColor.WHITE),
    WHITE_QUEEN('q', PieceType.QUEEN, PieceColor.WHITE),
    WHITE_KING('k', PieceType.KING, PieceColor.WHITE),
    ;

    private final char representation;
    private final PieceType pieceType;
    private final PieceColor pieceColor;

    ViewRepresentation(char representation, PieceType type, PieceColor black) {
        this.representation = representation;
        this.pieceType = type;
        this.pieceColor = black;
    }

    public static char getByTypeAndColor(PieceType type, PieceColor color) {
        return Arrays.stream(ViewRepresentation.values())
                .filter(representation -> representation.pieceType.equals(type))
                .filter(representation -> representation.pieceColor.equals(color))
                .findFirst()
                .orElseThrow()
                .representation;
    }
}

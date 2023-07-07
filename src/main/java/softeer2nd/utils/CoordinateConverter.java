package softeer2nd.utils;

import softeer2nd.chess.Position;

import static softeer2nd.chess.Board.BOARD_LENGTH;

public class CoordinateConverter {
    private CoordinateConverter() {
    }

    public static Position convertNotationToPosition(String notation) {
        char rowNotation = notation.charAt(1);
        char colNotation = notation.charAt(0);

        int rowIndex = BOARD_LENGTH - (rowNotation - '0');
        int colIndex = colNotation - 'a';

        return new Position(rowIndex, colIndex);
    }
}

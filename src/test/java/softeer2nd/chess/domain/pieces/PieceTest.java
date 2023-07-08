package softeer2nd.chess.domain.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.domain.Position;
import softeer2nd.chess.domain.pieces.enums.PieceColor;
import softeer2nd.chess.domain.pieces.enums.PieceType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Piece 테스트")
public class PieceTest {

    @DisplayName("기물 생성 테스트")
    @ParameterizedTest(name = "{0}->색: {1}, 타입: {2}")
    @MethodSource("createPieceParameters")
    public void create_piece(final Piece piece, final PieceColor color, final PieceType type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }

    static Stream<Arguments> createPieceParameters() {
        Position position = new Position("a1");
        return Stream.of(
                arguments(Piece.createWhitePawn(position), PieceColor.WHITE, PieceType.PAWN),
                arguments(Piece.createBlackPawn(position), PieceColor.BLACK, PieceType.PAWN),
                arguments(Piece.createWhiteRook(position), PieceColor.WHITE, PieceType.ROOK),
                arguments(Piece.createBlackRook(position), PieceColor.BLACK, PieceType.ROOK),
                arguments(Piece.createWhiteBishop(position), PieceColor.WHITE, PieceType.BISHOP),
                arguments(Piece.createBlackBishop(position), PieceColor.BLACK, PieceType.BISHOP),
                arguments(Piece.createWhiteKnight(position), PieceColor.WHITE, PieceType.KNIGHT),
                arguments(Piece.createBlackKnight(position), PieceColor.BLACK, PieceType.KNIGHT),
                arguments(Piece.createWhiteQueen(position), PieceColor.WHITE, PieceType.QUEEN),
                arguments(Piece.createBlackQueen(position), PieceColor.BLACK, PieceType.QUEEN),
                arguments(Piece.createWhiteKing(position), PieceColor.WHITE, PieceType.KING),
                arguments(Piece.createBlackKing(position), PieceColor.BLACK, PieceType.KING)
        );
    }
}

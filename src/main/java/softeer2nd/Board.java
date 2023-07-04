package softeer2nd;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Pawn> boards;

    public Board() {
        this.boards = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        boards.add(pawn);
    }

    public Pawn findPawn(int position) {
        return boards.get(position);
    }

    public int size() {
        return boards.size();
    }
}

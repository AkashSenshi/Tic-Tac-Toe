package strategy;

import model.Board;
import model.Move;
import model.Player;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Move move, Board board) {
        return false;
    }

    @Override
    public void handleUndo(Move move) {

    }
}


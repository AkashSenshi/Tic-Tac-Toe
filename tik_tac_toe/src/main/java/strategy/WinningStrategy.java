package strategy;

import model.Board;
import model.Move;
import model.Player;

public interface WinningStrategy {
    public boolean checkWinner(Move move, Board board);
    public void handleUndo(Move move);
}

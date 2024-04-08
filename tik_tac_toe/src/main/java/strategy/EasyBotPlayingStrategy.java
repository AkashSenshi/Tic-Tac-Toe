package strategy;

import model.Board;
import model.Cell;
import model.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Cell makeMove(Board board) {
        List<List<Cell>> grid = board.getGrid();
        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.EMPTY) {
                    return cell;
                }
            }
        }
        return null;
    }
}

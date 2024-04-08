package strategy;

import model.Board;
import model.Move;
import model.Player;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy {
    HashMap<Integer, HashMap<Character, Integer>> boardSymbolLookUp = new HashMap<>();

    public RowWinningStrategy() {
        this.boardSymbolLookUp = new HashMap<>();
    }

    @Override
    public boolean checkWinner(Move move, Board board) {
        // jb bhi check karne aayega to add krega
        int row = move.getCell().getRow();
//        System.out.println("Row : " + row);

        HashMap<Character, Integer> rowSnamShot = boardSymbolLookUp.getOrDefault(row, new HashMap<>());
        int symbolCount = rowSnamShot.getOrDefault(move.getCell().getSymbol().getSym(), 0);
        symbolCount += 1;
        rowSnamShot.put(move.getCell().getSymbol().getSym(), symbolCount);
        boardSymbolLookUp.put(row, rowSnamShot);
//        System.out.println("Symbol : " + move.getCell().getSymbol().getSym() + " : " + );

        if (symbolCount == board.getSize()) {
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Move move) {
        int row = move.getCell().getRow();
        HashMap<Character, Integer> rowSnamShot = boardSymbolLookUp.getOrDefault(row, new HashMap<>());
        int symbolCount = rowSnamShot.getOrDefault(move.getCell().getSymbol().getSym(), 0);
        symbolCount -= 1;
        rowSnamShot.put(move.getCell().getSymbol().getSym(), symbolCount);
        boardSymbolLookUp.put(symbolCount, rowSnamShot);

    }
}

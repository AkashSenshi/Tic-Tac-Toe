package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
    List<List<Cell>> grid;

    public Board(int size) {
        this.size = size;
        grid = new ArrayList<>();
        prepareGrid();
    }

    public void prepareGrid() {
        for (int i = 0; i < size; i++) {
            List<Cell> gridRow = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                gridRow.add(new Cell(i, j));
            }
            grid.add(gridRow);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }


    public void displayBoard() {
        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                if (cell.cellState == CellState.EMPTY) {
                    System.out.print("|-|");
                } else {
                    System.out.print("|" + cell.getSymbol().getSym() + "|");
                }

            }
            System.out.println();
        }
    }
}

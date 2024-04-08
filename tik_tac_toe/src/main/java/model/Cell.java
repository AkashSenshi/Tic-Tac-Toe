package model;

public class Cell {
    int row;
    int column;
    CellState cellState;
    Symbol symbol;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        cellState = CellState.EMPTY;
        this.symbol = null;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}

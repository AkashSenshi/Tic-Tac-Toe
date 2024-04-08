package model;

import java.util.Objects;

public class Symbol {
    char sym;
    String color;

    public Symbol(char sym, String color) {
        this.sym = sym;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol)) return false;
        Symbol symbol = (Symbol) o;
        return getSym() == symbol.getSym() && Objects.equals(getColor(), symbol.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSym(), getColor());
    }

    public char getSym() {
        return sym;
    }

    public void setSym(char sym) {
        this.sym = sym;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

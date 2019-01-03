package com.mmc.chomp;

class Chocolate {
    private final int row;
    private final int col;
    private boolean isTaken;

    public Chocolate(int row, int col, boolean isTaken) {
        this.row = row;
        this.col = col;
        this.isTaken = isTaken;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void take() {
        isTaken = true;
    }
}

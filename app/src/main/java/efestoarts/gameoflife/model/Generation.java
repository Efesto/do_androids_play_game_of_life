package efestoarts.gameoflife.model;

public class Generation {
    private final int size;
    private final boolean[][] cells;

    public Generation(int size, boolean[][] cells) {
        this.size = size;
        this.cells = cells;
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getCells() {
        return cells;
    }
}

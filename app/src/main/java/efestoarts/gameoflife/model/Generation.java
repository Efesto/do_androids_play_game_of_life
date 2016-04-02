package efestoarts.gameoflife.model;

import java.util.Arrays;

public class Generation {
    private final int size;
    public boolean[][] cells;

    public Generation(int size) {
        this.size = size;
        cells = new boolean[size][size];
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Generation that = (Generation) o;

        return size == that.size && Arrays.deepEquals(cells, that.cells);
    }
}

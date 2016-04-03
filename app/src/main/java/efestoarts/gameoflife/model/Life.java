package efestoarts.gameoflife.model;

public class Life {

    public Generation currentGeneration;

    public Life(Generation generation) {
        currentGeneration = generation;
    }

    public Generation nextGeneration()
    {
        Generation nextGeneration = new Generation(currentGeneration.getSize());

        for(int indexY = 0; indexY < currentGeneration.getSize(); indexY++) {
            for (int indexX = 0; indexX < currentGeneration.getSize(); indexX++) {

                nextGeneration.cells[indexY][indexX] = currentGeneration.cells[indexY][indexX];

                int livingNeighboursCount = getLivingNeighboursCount(indexX, indexY);

                if (livingNeighboursCount < 2) {
                    nextGeneration.cells[indexY][indexX] = false;
                }

                if (livingNeighboursCount > 3) {
                    nextGeneration.cells[indexY][indexX] = false;
                }

                if (livingNeighboursCount == 3) {
                    nextGeneration.cells[indexY][indexX] = true;
                }
            }
        }

        currentGeneration = nextGeneration;

        return nextGeneration;
    }

    private int getLivingNeighboursCount(int x, int y) {
        int counter = 0;
        for (int indexY = Math.max(0, y - 1); indexY <= Math.min(currentGeneration.getSize()-1, y + 1); indexY++) {
            for (int indexX = Math.max(0, x - 1); indexX <= Math.min(currentGeneration.getSize()-1, x + 1); indexX++) {

                if(currentGeneration.cells[indexY][indexX] && (indexX != x || indexY != y))
                    counter++;
            }
        }
        return counter;
    }
}

/**
 * 
 * @author Ezio Dai
 * 
 *         This is the final garden class that I decide to use, it is a simple
 *         2d array with some methods that an be called from other class.
 *
 */
public class GARDENA {
    private PLANT[][] gardena = new PLANT[0][0];

    // This is the constructor, it needs a size and will recreate a graden in
    // given size.
    public GARDENA(int row, int col) {
        gardena = new PLANT[row][col];
        for (int a = 0; a < gardena.length; a++) {
            for (int b = 0; b < gardena[a].length; b++) {
                gardena[a][b] = new PLANT();
            }
        }
    }

    // This method is to reset values in original array.
    public void reSet(int[] loct, PLANT plant) {
        gardena[loct[0]][loct[1]] = plant;
    }

    // This print method is funny, a nested loop of course. For printing things
    // that in one row compeletly, I have to use these loops. it will wrong each
    // row of garden and each row of plots step by step. It's maybe complex, but
    // really work.
    public void PRINT() {
        System.out.println("> PRINT");
        for (int a = 0; a < gardena.length; a++) {
            for (int c = 0; c < 5; c++) {
                for (int b = 0; b < gardena[a].length; b++) {
                    // System.out.println(a + " " + b);
                    gardena[a][b].PRINT(c);
                }
                System.out.println();
            }

        }
        System.out.println();
    }

    // This is method to get plant in gardens.
    public PLANT get(int[] loct) {
        return gardena[loct[0]][loct[1]];
    }

    // This is the method to get garden's size.
    public int[] size() {
        int[] size = new int[2];
        size[1] = gardena[0].length;
        size[0] = gardena.length;
        return size;
    }
}

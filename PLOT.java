/**
 * 
 * @author Ezio Dai
 * 
 *         This is basic class of all things, simply, it is just a 2-d array
 *         with 5*5 and filled by '.' .
 *
 */
public class PLOT {
    final String[][] plot = new String[5][5];

    // This is the method that will clean all things in the plot
    public void refresh()
    {
        for (int a = 0; a < plot.length; a++) {
            for (int b = 0; b < plot[a].length; b++) {
                plot[a][b] = ".";
            }
        }
    }

    // This method is to set a name in given location.
    public void set(int a, int b, String name) {
        plot[a][b] = name;
    }

    // This method is to get a name from given location.
    public String get(int a, int b) {
        return plot[a][b];
    }

    // This is the print function, it will print the whole col in a given row.
    // This is for, print all things in one row, continue in GARDENA class.
    public void PRINT(int c) {
            for (int b = 0; b < plot[c].length; b++) {
                System.out.print(plot[c][b]);
            }
        }
}

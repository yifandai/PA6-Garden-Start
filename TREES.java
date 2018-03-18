/**
 * 
 * @author Ezio Dai
 * 
 *         This is the one of the subclass of PLANT, called TREES. As we want,
 *         I overload all methods in PLANT and made it specific.
 *
 */
public class TREES extends PLANT {
    private final String oak = "o", willow = "w", banana = "b", coconut = "c",
            pine = "p";
    PLOT plot = new PLOT();

    // This is the constructor, it needs a name to create a plot.
    public TREES(String name) {
        plot.refresh();
        plot.set(4, 2, name);
    }

    // For trees, it need grow only above, so I write a method like this.
    public void GROW(int num) {
        int count = 4;
        do {
            if (count < 0) {
                // System.out.println("Exceeding the range");
                break;
            } else if (!plot.get(count, 2).equals(".")) {
                count -= 1;
            } else {
                plot.set(count, 2, plot.get(4, 2));
                count -= 1;
                num -= 1;
            }
        } while (num > 0);
    }

    // Just call the print in plot.
    public void PRINT(int c) {
        plot.PRINT(c);
    }

    // This is a method that can return the type name.
    public String toString() {
        return "tree";
    }

    // This is the method that return plant's name.
    public String getName() {
        return plot.get(4, 2);
    }

    // This the the CUT method, it only work in trees class.
    public void CUT() {
        String name = plot.get(4, 2);
        plot.refresh();
        // plot.set(4, 2, name);
    }
}

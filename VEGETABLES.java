/**
 * 
 * @author Ezio Dai
 * 
 *         This is the one of the subclass of PLANT, called VEGETABLES. As we
 *         want,
 *         I overload all methods in PLANT and made it specific.
 *
 */
public class VEGETABLES extends PLANT {
    private final String garlic = "g", zucchini = "z", tomato = "t", yam = "y",
            lettuce = "l";
    PLOT plot = new PLOT();

    // This is the constructor, it needs a name to create a plot.
    public VEGETABLES(String name) {
        plot.refresh();
        plot.set(0, 2, name);
    }

    // For vegetables, it need grow only down side, so I write a method like
    // this.
    public void GROW(int num) {
        int count = 0;
        do {
            if (count > 4) {
                // System.out.println("Exceeding the range");
                break;
            } else if (!plot.get(count, 2).equals(".")) {
                count += 1;
            } else {
                plot.set(count, 2, plot.get(0, 2));
                count += 1;
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
        return "vegetable";
    }

    // This is the method that return plant's name.
    public String getName() {
        if (plot.get(2, 2).equals("l")) {
            return "e";
        } else {
            return plot.get(2, 2);
        }
    }

    // This the the HARVEST method, it only work in vegetanles class.
    public void HARVEST() {
        String name = plot.get(0, 2);
        plot.refresh();
        // plot.set(0, 2, name);
    }
}

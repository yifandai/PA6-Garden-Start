import java.util.ArrayList;
/**
 * 
 * @author Ezio Dai
 * 
 *         This is the one of the subclass of PLANT, called FLOWERS. As we want,
 *         I overload all methods in PLANT and made it specific.
 *
 */
public class FLOWERS extends PLANT {
    private final String iris = "i", lily = "l", rose = "r", daisy = "d",
            tulip = "t", sunflower = "s";
    PLOT plot = new PLOT();

    // This is the constructor, it needs a name to create a plot.
    public FLOWERS(String name) {
        plot.refresh();
        plot.set(2, 2, name);
    }

    // For flowers, it need grow every direction, so I write a method like this.
    public void GROW(int num) {
        ArrayList<int[]> locts = detGrowStart();
        ArrayList<int[]> newLocts = new ArrayList<int[]>();
        do {
            locts = FGrow(locts, newLocts);
            if (locts.isEmpty()) {
                // System.out.println("Exceeding range");
                num = 0;
            }
            num -= 1;
        } while (num > 0);
    }

    // This is the specific grow methods for flowers, which is, in 6 conditions,
    // the flower will grow, which is in if conditions.
    private ArrayList<int[]> FGrow(ArrayList<int[]> locts,
            ArrayList<int[]> newLocts) {
        if (locts.isEmpty()) {
            return newLocts;
        }
        else {
            int[] loct = locts.get(0);
            growAsPoss(loct, newLocts);
            locts.remove(0);
            return FGrow(locts, newLocts);
        }
    }

    private ArrayList<int[]> detGrowStart() {
        ArrayList<int[]> locts = new ArrayList<int[]>();
        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                int[] save = { a, b };
                if (!plot.get(a, b).equals(".") && canGrow(save)) {
                    locts.add(save);
                }
            }
        }
        return locts;
    }
    // This is to check whether it can grow or not
    private boolean canGrow(int[] loct) {
        if ((loct[0] < 4 && plot.get(loct[0] + 1, loct[1]).equals("."))
                || (loct[0] > 0 && plot.get(loct[0] - 1, loct[1]).equals("."))
                || (loct[1] < 4 && plot.get(loct[0], loct[1] + 1).equals("."))
                || (loct[1] > 0
                        && plot.get(loct[0], loct[1] - 1).equals("."))) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<int[]> growAsPoss(int[] loct, ArrayList<int[]> newLocts) {
        if (loct[0] < 4 && plot.get(loct[0] + 1, loct[1]).equals(".")) {
            plot.set(loct[0] + 1, loct[1], plot.get(2, 2));
            int[] newLoct = { loct[0] + 1, loct[1] };
            if (canGrow(newLoct)) {
                newLocts.add(newLoct);
            }
            return growAsPoss(loct, newLocts);
        } else if (loct[0] > 0 && plot.get(loct[0] - 1, loct[1]).equals(".")) {
            plot.set(loct[0] - 1, loct[1], plot.get(2, 2));
            int[] newLoct = { loct[0] - 1, loct[1] };
            if (canGrow(newLoct)) {
                newLocts.add(newLoct);
            }
            return growAsPoss(loct, newLocts);
        } else if (loct[1] < 4 && plot.get(loct[0], loct[1] + 1).equals(".")) {
            plot.set(loct[0], loct[1] + 1, plot.get(2, 2));
            int[] newLoct = { loct[0], loct[1] + 1 };
            if (canGrow(newLoct)) {
                newLocts.add(newLoct);
            }
            return growAsPoss(loct, newLocts);
        } else if (loct[1] > 0 && plot.get(loct[0], loct[1] - 1).equals(".")) {
            plot.set(loct[0], loct[1] - 1, plot.get(2, 2));
            int[] newLoct = { loct[0], loct[1] - 1 };
            if (canGrow(newLoct)) {
                newLocts.add(newLoct);
            }
            return growAsPoss(loct, newLocts);
        } else {
            return newLocts;
        }
    }

    // Just call the print in plot.
    public void PRINT(int c) {
        plot.PRINT(c);
    }

    // This is a method that can return the type name.
    public String toString() {
        return "flower";
    }

    // This is the method that return plant's name.
    public String getName() {
        if (plot.get(2, 2).equals("t")) {
            return "u";
        } else {
            return plot.get(2, 2);
        }
    }

    // This the the PICK method, it only work in flower class.
    public void PICK() {
        String name = plot.get(2, 2);
        plot.refresh();
        // plot.set(2, 2, name);
    }
}

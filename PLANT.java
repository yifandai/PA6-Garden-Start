/**
 * 
 * @author Ezio Dai
 * 
 *         This is the mother class all FLWOERS, TREES and VEGETABLES, well
 *         there's noting really work here, it just create the method name.
 *
 */
public class PLANT {
    PLOT plot = new PLOT();
    public PLANT() {
        plot.refresh();
    }

    public PLANT(String name) {
        plot.refresh();
        plot.set(0, 0, ".");
    }

    public void GROW(int num) {

    }

    public void CUT() {

    }

    public void HARVEST() {

    }

    public void PICK() {

    }

    public void PRINT(int c) {
        plot.PRINT(c);
    }

    public String toString() {
        return "PLANT";
    }

    public String getName() {
        return "NAME";
    }
}

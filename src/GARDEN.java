
/**
 * 
 * @author Ezio Dai
 * 
 *         This is the class that I use as my garden before, it using arrayList to create a garden, but I found 2d array is easier in using. So this class didn't been use any more.
 *
 */
import java.util.ArrayList;
public class GARDEN {
    private ArrayList<ArrayList<PLANT>> garden = new ArrayList<ArrayList<PLANT>>();

    public GARDEN(int row, int col) {
        garden = new ArrayList<ArrayList<PLANT>>();
        ArrayList<PLANT> save = new ArrayList<PLANT>(col);
        for (int b = 0; b < col; b++) {
            PLANT plant = new PLANT(null);
            save.add(plant);
        }
        for (int a = 0; a < row; a++) {
            garden.add(save);
        }
    }

    public void insert(int[] location, PLANT plant) {
        ArrayList<PLANT> store = new ArrayList<PLANT>();
        store.add(location[1], plant);
        garden.add(location[0], store);
    }

    public void reSet(int[] location, PLANT plant) {
        ArrayList<PLANT> store = garden.get(location[0]);
        store.set(location[1], plant);
        garden.set(location[0], store);
    }
    public void PRINT() {
        System.out.println("> PRINT");
        for (int a = 0; a < garden.size(); a++) {
            for (int b = 0; b < garden.get(a).size(); b++) {
                // garden.get(a).get(b).PRINT();
            }
            System.out.println();
        }
    }

    public PLANT get(int[] location) {
        return garden.get(location[0]).get(location[1]);
    }

    public int[] size() {
        int[] size = new int[2];
        size[1] = garden.get(0).size();
        size[0] = garden.size();
        return size;
    }
}

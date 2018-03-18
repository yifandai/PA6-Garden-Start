import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author Ezio Dai
 *
 *         This program is to simulate a garden, it will read in a file with
 *         commands inside, including PLANT, GROW, CUT, HARVEST, PICK and PRINT.
 *         There gonna be 3 types of 16 things in total, which are VEGETASBLES,
 *         TREES and FLOWERS. For each type of plant, it will answer different
 *         commands such as HARVERST is for VEGETABLES, CUT is for TREES and
 *         PICK is for FLOWERS. Also, different tyoes of plants will grow
 *         differently and from different locations, but they can only grow 4
 *         times otherwise they exceede the garden range. Of course, at the
 *         begining, the file must give the size of the garden, including the
 *         total rows and cols.
 * 
 *         You may notice that I have two 'garden-like' classes, one is 'GARDEN'
 *         and another one is 'GARDENA'. Well, I was want to use arrayList as my
 *         garden base, which is 'GARDEN'. However I finally find that a 2-d
 *         array is what I need, so I rewrite a new class called 'GARDENA', so
 *         don't worry about it. I just keep 'GARDEN' for remaindering
 */
public class PA6Main {
    // This main is short, but some other methods will be a little bit longer.
    public static void main(String[] args) {
        Scanner input = openFile(args[0]);
        processFile(input);
        System.exit(0);
    }

    // As always, the method that open the file.
    public static Scanner openFile(String fileName) {
        Scanner file = new Scanner(System.in);
        try {
            file = new Scanner(new File(fileName));
        } catch (Exception e) {
            System.out.println(
                    "Error:  Encountered  a  problem  when  opening  file "
                            + fileName);
            System.exit(1);
        }
        return file;
    }

    // This is the method that create a garden with the given size.
    public static GARDENA buildGarden(int[] size) {
        int rows = size[0], cols = size[1];
        GARDENA garden = new GARDENA(rows, cols);
        return garden;
    }

    // This is the method that go through the input file.
    public static void processFile(Scanner input) {
        int[] size = processSize(input);
        GARDENA garden = buildGarden(size);
        input.nextLine();
        processCommand(garden, input);
    }

    // This method will read the file and return the given size of the garden.
    public static int[] processSize(Scanner input) {
        int[] size = new int[2];
        int b = 0, c = 0;
        for (int a = 0; a < 2; a++) {
            String[] save = input.nextLine().split(" ");
            try {
                if (save[0].equals("rows:")) {
                    b = Integer.parseInt(save[1]);
                    size[0] = b;
                } else if (save[0].equals("cols:")) {
                    c = Integer.parseInt(save[1]);
                    if (c > 16) {
                        System.out.println("Too many plot columns.");
                        System.exit(1);
                    }
                    size[1] = c;
                }
            } catch (Exception e) {
                System.err.print("Invlid input for rows/cols!");
                System.exit(1);
            }

        }
        return size;
    }

    // This the method that process every command, longer than 30, but just 5
    // lines more.
    public static void processCommand(GARDENA garden, Scanner input) {
        do {
            String PS = input.nextLine();
            String[] save = PS.split(" ");
            try {// This try catch is to pause the loop for a while
                Thread.sleep(111);// and make the rest part has time finish
            } catch (InterruptedException ex) {// themself.
                Thread.currentThread().interrupt();
            }
            switch (save[0].toUpperCase()) {
            case "PLANT":
                processPlant(garden, save);
                break;
            case "GROW":
                System.out.println("> " + PS.substring(0, 4).toUpperCase()
                        + PS.substring(4) + "\n");
                processGrow(garden, save);
                break;
            case "PRINT":
                garden.PRINT();
                break;
            case "HARVEST":
                System.out.println("> " + PS.substring(0, 7).toUpperCase()
                        + PS.substring(7) + "\n");
                processHARVEST(garden, save);
                break;
            case "PICK":
                System.out.println("> " + PS.substring(0, 4).toUpperCase()
                        + PS.substring(4) + "\n");
                processPICK(garden, save);
                break;
            case "CUT":
                System.out.println("> " + PS.substring(0, 3).toUpperCase()
                        + PS.substring(3) + "\n");
                processCUT(garden, save);
                break;
            default:
                System.err.println("invalid command!");
                System.exit(1);
                break;
            }
        } while (input.hasNext());
    }

    // This is the method process PLANT command.
    public static void processPlant(GARDENA garden, String[] save) {
        PLANT plant = new PLANT(null);
        int[] location = detLocation(save[1]);// For tulip and lettuce, I use
        if (save[2].equals("tulip") || save[2].equals("lettuce")) { // u and e
            plant = detType(String.valueOf(save[2].charAt(1)));//// to id them
        } else {
            plant = detType(String.valueOf(save[2].charAt(0)));
        }
        garden.reSet(location, plant);// PLANT it in garden.
    }

    // This is the method process GROW. Because of 10 '}', it's around 40 lines
    // The nested loop inside is for going around all possible plants in
    // garden.
    public static void processGrow(GARDENA garden, String[] save) {
        int[] size = garden.size();
        if (save.length == 2) {// This is for GROW[num] only.
            for (int rows = 0; rows < size[0]; rows++) {
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    doGrow(garden, save[1], loct);
                }
            }
        } else if (save[2].contains(")")) {// This is for given loct one.
            int[] loct = detLocation(save[2]);
            doGrow(garden, save[1], loct);
        } else if ("ilrdtsowbcpgzy".contains(save[2].substring(0, 1))
                && !save[2].equals("tree")) {
            for (int rows = 0; rows < size[0]; rows++) {
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).getName()
                            .equals(save[2].substring(0, 1))
                            || (garden.get(loct).getName().equals("u")
                                    && save[2].equals("tulip"))
                            || (garden.get(loct).getName().equals("e")
                                    && save[2].equals("lettuce"))) {
                        doGrow(garden, save[1], loct);
                    }
                }
            }
        } else if (save[2].equals("tree")// This is for given type one.
                || "fv".contains(save[2].substring(0, 1))) {
            for (int rows = 0; rows < size[0]; rows++) {
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).toString().equals(save[2])) {
                        doGrow(garden, save[1], loct);
                    }
                }
            }
        }
    }

    // This is the method process HARVEST command
    // The nested loop inside is for going around all possible plants in
    // garden.
    public static void processHARVEST(GARDENA garden, String[] save) {
        int[] size = garden.size();
        if (save.length == 1) {// Just for HAR
            for (int rows = 0; rows < size[0]; rows++) {
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).toString().equals("vegetable")) {
                        doHar(garden, loct);
                    }
                }
            }
        } else if (save[1].charAt(0) == '(') {// For a loct
            int[] loct = detLocation(save[1]);
            if (loct[0] > size[0] || loct[1] > size[1] || loct[0] < 0
                    || loct[1] < 0
                    || !garden.get(loct).toString().equals("vegetable")) {
                System.out.println("Can't harvest there.");
                System.out.println();
            } else {
                doHar(garden, loct);
            }
        } else if ("gztye".contains(save[1].substring(0, 1))) {// For a given
                                                               // type
            for (int rows = 0; rows < size[0]; rows++) {
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).getName().equals(save[1].substring(0, 1))) {
                        doHar(garden, loct);
                    }
                }
            }
        }
    }

    // This is the method process PICK command
    // The nested loop inside is for going around all possible plants in
    // garden.
    public static void processPICK(GARDENA garden, String[] save) {

        int[] size = garden.size();
        if (save.length == 1) {// Just for Pick
            for (int rows = 0; rows < size[0]; rows++) {
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).toString().equals("flower")) {
                        doPick(garden, loct);
                    }
                }
            }
        } else if (save[1].charAt(0) == '(') {// For a loct
            int[] loct = detLocation(save[1]);
            if (loct[0] > size[0] || loct[1] > size[1] || loct[0] < 0
                    || loct[1] < 0
                    || !garden.get(loct).toString().equals("flower")) {
                System.out.println("Can't pick there.");
                System.out.println();
            } else {
                doPick(garden, loct);
            }
        } else if ("ilrdus".contains(save[1].substring(0, 1))) {// For a given
            for (int rows = 0; rows < size[0]; rows++) {// Type
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).getName()
                            .equals(save[1].substring(0, 1))) {
                        doPick(garden, loct);
                    }
                }
            }
        }
    }

    // The nested loop inside is for going around all possible plants in
    // gatrden.
    public static void processCUT(GARDENA garden, String[] save) {
        String SS = save[1].substring(0,1);
        int[] size = garden.size();
        if (save.length == 1) {// Just for CUT.
            for (int rows = 0; rows < size[0]; rows++) {
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).toString().equals("tree")) {
                        doCut(garden, loct);
                    }
                }
            }
        } else if (save[1].charAt(0) == '(') {// For a loct
            int[] loct = detLocation(save[1]);
            if (loct[0] > size[0] || loct[1] > size[1] || loct[0] < 0
                    || loct[1] < 0
                    || !garden.get(loct).toString().equals("tree")) {
                System.out.println("Can't cut there.");
                System.out.println();
            } else {
                doCut(garden, loct);
            }
        } else if ("owbcp".contains(save[1].substring(0, 1))) {// For a given
            for (int rows = 0; rows < size[0]; rows++) {// Type
                for (int cols = 0; cols < size[1]; cols++) {
                    int[] loct = { rows, cols };
                    if (garden.get(loct).getName()
                            .equals(save[1].substring(0, 1))) {
                        doCut(garden, loct);
                    }
                }
            }
        }
    }

    // This the method that using name of plant to determine their type.
    // I use the first letter to determine type, and for tulip, I use 'u'
    // instead of 't' and for lettuce, I use 'e' instead of 'l'.
    public static PLANT detType(String name) {
        if ("ilrdus".contains(name)) {
            FLOWERS flower = new FLOWERS(null);
            if (name.equals("u")) {
                flower = new FLOWERS("t");
            } else {
                flower = new FLOWERS(name);
            }
            return flower;
        } else if ("owbcp".contains(name)) {
            TREES tree = new TREES(name);
            return tree;
        } else if ("gztye".contains(name)) {
            VEGETABLES vege = new VEGETABLES(null);
            if (name.equals("e")) {
                vege = new VEGETABLES("l");
            } else {
                vege = new VEGETABLES(name);
            }
            return vege;
        } else {
            return null;
        }
    }

    // This is the method is read the location and turn in into an array for
    // further using.
    public static int[] detLocation(String loct) {
        int[] location = new int[2];
        loct = loct.substring(1, loct.length() - 1);// Remove '()'
        String[] place = loct.split(",");// Remove ','
        try {
            location[0] = Integer.parseInt(place[0]);
            location[1] = Integer.parseInt(place[1]);
        } catch (Exception e) {
            System.err.println("Wrong location giving!");
            System.exit(1);
        }
        return location;
    }

    // This is the method really call GROW, it needs loction, frequncy and
    // garden ofcourse.
    public static void doGrow(GARDENA garden, String times, int[] loct) {
        try {
            int freq = Integer.parseInt(times);
            PLANT plant = garden.get(loct);
            plant.GROW(freq);
            garden.reSet(loct, plant);
        }

        catch (Exception e) {
            System.out.println("Can't grow there.\n");
        }

    }

    // This is the one call HARVEST.
    public static void doHar(GARDENA garden, int[] loct) {
        PLANT plant = garden.get(loct);
        plant.HARVEST();
        garden.reSet(loct, plant);
    }

    // This is the one call PICK.
    public static void doPick(GARDENA garden, int[] loct) {
        PLANT plant = garden.get(loct);
        plant.PICK();
        garden.reSet(loct, plant);
    }

    // This is the one call CUT.
    public static void doCut(GARDENA garden, int[] loct) {
        PLANT plant = garden.get(loct);
        plant.CUT();
        garden.reSet(loct, plant);
    }
}

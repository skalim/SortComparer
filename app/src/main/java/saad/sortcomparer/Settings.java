package saad.sortcomparer;

import java.util.ArrayList;

/**
 * Created by Saad on 24-Jan-16.
 * size: size of data selected on second screen
 */
public final class Settings {
    public static ArrayList<String> algorithmsSelected = new ArrayList<>();
    public static boolean listSelected = false;
    public static int size = 1;

    public static void addSelected(String name) {
        switch (name) {
            case "Selection":
                algorithmsSelected.add("SELECTION");
                break;
            case "Insertion":
                algorithmsSelected.add("INSERTION");
                break;
            case "Merge":
                algorithmsSelected.add("MERGE");
                break;
        }
    }

    public static void removeSelected(String name) {
        switch (name) {
            case "Selection":
                algorithmsSelected.remove("SELECTION");
                break;
            case "Insertion":
                algorithmsSelected.remove("INSERTION");
                break;
            case "Merge":
                algorithmsSelected.remove("MERGE");
                break;
        }
    }

    public static void reset(){
        algorithmsSelected.clear();
        listSelected = false;
        size = 1;
    }

}

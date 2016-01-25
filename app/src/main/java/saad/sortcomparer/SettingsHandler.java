package saad.sortcomparer;

import java.util.ArrayList;

/**
 * Created by Saad on 24-Jan-16.
 */
public class SettingsHandler {
    public final int SELECTION = 0;
    public final int INSERTION = 1;

    public static class Settings{
        public static ArrayList<String> algorithmsSelected = new ArrayList<>();
        public static boolean listSelected = false;
        public static int size = 1;

        public static void addSelected(String name){
            switch (name){
                case "Selection": algorithmsSelected.add("SELECTION");
                case "Insertion": algorithmsSelected.add("INSERTION");
            }

        }

        public static void removeSelected(String name){
            switch (name){
                case "Selection": algorithmsSelected.remove("SELECTION");
                case "Insertion": algorithmsSelected.remove("INSERTION");
            }
        }
    }
}

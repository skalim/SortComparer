package saad.sortcomparer.homescreen;

import android.util.Log;

/**
 * Created by Saad on 23-Jan-16.
 */
public class SortListItem {
    final String TAG = "skalim";
    String name;
    String description;

    public SortListItem(String name, String description) {
        this.name = name;
        this.description = description;
        Log.d(TAG, name + " " + description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package saad.sortcomparer.saad.sortcomparer.thirdscreen;

import android.widget.TextView;

import java.awt.font.TextAttribute;

/**
 * Created by Saad on 24-Jan-16.
 */
public class Console {

    String[] lines;
    int numLines;

    public Console() {
        numLines = 0;
        this.lines = new String[10];
    }

    public String getLine(int position) {
        return lines[position];
    }

    public void nextLine(String line){
        if( numLines < 10){
            lines[ numLines ] = line;
            numLines++;
            return;
        }

        for(int i = 0; i < 8; i++){
            lines[i] = lines[i+1];
        }
        lines[9] = line;
    }
}

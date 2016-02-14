package saad.sortcomparer.thirdscreen;

import android.widget.TextView;

import java.awt.font.TextAttribute;

/**
 * Created by Saad on 24-Jan-16.
 */
public class Console {

    String[] lines;

    int numLines;
    int iterator;

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

        for(iterator = 0; iterator <= 8; iterator++){
            lines[iterator] = lines[iterator+1];
        }
        lines[9] = line;
    }

    public int getNumLines() {
        return numLines;
    }
}
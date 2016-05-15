package saad.sortcomparer.sort;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.data.Entry;

import java.io.Serializable;

/**
 * Created by Saad on 26-Jan-16.
 * time - time to sort in ns
 */
public class Statistics implements Serializable {
    String name;
    double time;
    long numCompares;
    long numSwaps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    /**
     *
     * @return string that shows time in ns, ms or s. eg "0.15 ms", "3.59 s"
     */
    public String getFormattedTime(){
        String toReturn = String.format("%.2f", time) + " ns";

        if( time >= 1000000000 ){
            double newTime = time/1000000000;
            toReturn = String.format("%,.2f", newTime) + " s";
        } else if( time >= 1000000 ){
            double newTime = time/1000000;
            toReturn = String.format("%,.2f", newTime) + " ms";
        }

        return toReturn;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public long getNumCompares() {
        return numCompares;
    }

    public String getFormattedNumCompares() {
        return String.format("%,d", numCompares);
    }

    public void setNumCompares(long numCompares) {
        this.numCompares = numCompares;
    }

    public long getNumSwaps() {
        return numSwaps;
    }

    public String getFormattedNumSwaps() {
        return String.format("%,d", numSwaps);
    }

    public void setNumSwaps(long numSwaps) {
        this.numSwaps = numSwaps;
    }
}

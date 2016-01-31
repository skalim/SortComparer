package saad.sortcomparer.sort;

import java.io.Serializable;

/**
 * Created by Saad on 26-Jan-16.
 * time - time to sort in ms
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

    public void setTime(double time) {
        time /= 1000000;
        this.time = time;
    }

    public long getNumCompares() {
        return numCompares;
    }

    public void setNumCompares(long numCompares) {
        this.numCompares = numCompares;
    }

    public long getNumSwaps() {
        return numSwaps;
    }

    public void setNumSwaps(long numSwaps) {
        this.numSwaps = numSwaps;
    }
}

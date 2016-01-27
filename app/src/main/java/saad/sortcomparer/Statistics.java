package saad.sortcomparer;

import java.io.Serializable;

/**
 * Created by Saad on 26-Jan-16.
 */
public class Statistics implements Serializable {
    String name;
    double time;
    long numCompares;

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
        this.time = time;
    }

    public long getNumCompares() {
        return numCompares;
    }

    public void setNumCompares(long numCompares) {
        this.numCompares = numCompares;
    }
}

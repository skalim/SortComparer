package saad.sortcomparer.sort;

import saad.sortcomparer.Statistics;
import saad.sortcomparer.thirdscreen.ThirdScreen;

/**
 * Created by Saad on 23-Jan-16.
 */
public class Sort {
    private SortData data;
    private long timeStarted;

    public Sort(int size, boolean structure) {
        data = new SortData(size, structure);
    }

    public SortData getData() {
        return data;
    }

    public Statistics selectionSort(ThirdScreen.SortingTask task) {
        Statistics stats = new Statistics();
        stats.setName("Selection");
        int numCompares = 0;
        timeStarted = System.nanoTime();
        for (int i = 0; i < data.size(); i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = i; j < data.size(); j++) {
                numCompares++;
                if (data.get(j) < min) {
                    min = data.get(j);
                    minIndex = j;
                    task.doProgress();
                }
            }
            data.swap(i, minIndex);
            //task.doProgress();
        }

        stats.setTime(System.nanoTime() - timeStarted);
        stats.setNumCompares(numCompares);

        return stats;
    }

    public Statistics insertionSort(ThirdScreen.SortingTask task) {
        Statistics stats = new Statistics();
        stats.setName("Insertion");
        int numCompares = 0;
        timeStarted = System.nanoTime();

        for (int i = 1; i < data.size(); i++) {
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                numCompares++;
                if (data.get(j) > data.get(k)) {
                    data.swap(k, j);
                    k = j;
                    task.doProgress();
                }
            }
        }

        stats.setTime(System.nanoTime() - timeStarted);
        stats.setNumCompares(numCompares);

        return stats;
    }
}

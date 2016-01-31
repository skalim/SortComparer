package saad.sortcomparer.sort;

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
        long numCompares = 0;
        long numSwaps = 0;
        timeStarted = System.nanoTime();
        for (int i = 0; i < data.size(); i++) {
            task.doProgress();
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = i; j < data.size(); j++) {
                numCompares++;
                if (data.get(j) < min) {
                    min = data.get(j);
                    minIndex = j;
                }
            }
            if( data.swap(i, minIndex) ){
                numSwaps++;
            }
        }

        stats.setTime(System.nanoTime() - timeStarted);
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);

        return stats;
    }

    public Statistics insertionSort(ThirdScreen.SortingTask task) {
        Statistics stats = new Statistics();
        stats.setName("Insertion");
        long numCompares = 0;
        long numSwaps = 0;
        timeStarted = System.nanoTime();

        for (int i = 1; i < data.size(); i++) {
            task.doProgress();
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                numCompares++;
                if (data.get(j) > data.get(k)) {
                    if( data.swap(i, j)){
                        numSwaps++;
                    }
                    k = j;
                }
            }
        }

        stats.setTime(System.nanoTime() - timeStarted);
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);
        return stats;
    }
}

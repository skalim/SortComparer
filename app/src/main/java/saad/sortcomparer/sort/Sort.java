package saad.sortcomparer.sort;

import java.util.HashMap;

import saad.sortcomparer.thirdscreen.ThirdScreen;

/**
 * Created by Saad on 23-Jan-16.
 */
public class Sort {
    public final HashMap<String, String> names;

    private SortData data;
    private long timeStarted;
    private int numCompares = 0;
    private int numSwaps = 0;

    public Sort(SortData data) {
        this.data = new SortData(data.size(), !data.isArray());
        for(int i = 0; i < data.size(); i++){
            this.data.set(i, data.get(i));
        }

        names = new HashMap<>();
        names.put("merge", "Merge    ");
        names.put("selection", "Selection");
        names.put("insertion", "Insertion");
    }

    public Sort(int size, boolean structure) {
        data = new SortData(size, structure);
        names = new HashMap<>();
        names.put("merge", "Merge    ");
        names.put("selection", "Selection");
        names.put("insertion", "Insertion");
    }

    public SortData getData() {
        return data;
    }

    //Selection Sort
    public Statistics selectionSort(ThirdScreen.SortingTask task) {
        Statistics stats = new Statistics();
        stats.setName("Selection");
        task.doProgress();
        timeStarted = System.nanoTime();
        for (int i = 0; i < data.size(); i++) {
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
        task.doProgress();
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);

        return stats;
    }

    //Insertion Sort
    public Statistics insertionSort(ThirdScreen.SortingTask task) {
        Statistics stats = new Statistics();
        stats.setName("Insertion");
        task.doProgress();
        timeStarted = System.nanoTime();

        for (int i = 1; i < data.size(); i++) {
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                numCompares++;
                if (data.get(j) > data.get(k)) {
                    if( data.swap(i, j) ){
                        numSwaps++;
                    }
                    k = j;
                }
            }
        }

        stats.setTime(System.nanoTime() - timeStarted);
        task.doProgress();
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);
        return stats;
    }

    //Merge Sort functions


    public Statistics mergeSort( ThirdScreen.SortingTask task ){
        task.doProgress();
        Statistics stats = new Statistics();
        stats.setName(names.get("merge"));
        long timeStarted = System.nanoTime();
        mergeSortRecursion(data);
        stats.setTime(System.nanoTime() - timeStarted);
        task.doProgress();
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);
        return stats;
    }

    public void mergeSortRecursion(SortData data){
        if( data.size() < 2){
            return;
        }

        int mid = (int) Math.floor(data.size()/2);
        SortData leftData = new SortData( mid, !data.isArray() );
        for( int i = 0; i < leftData.size(); i++ ){
            leftData.set(i, data.get(i));
        }

        SortData rightData = new SortData( data.size()-mid, !data.isArray() );
        for( int i = 0, j = mid; i < rightData.size(); i++, j++ ){
            rightData.set(i, data.get(j));
        }

        mergeSortRecursion(leftData);
        mergeSortRecursion(rightData);
        merge(leftData, rightData, data);
    }

    public void merge(SortData leftData, SortData rightData, SortData auxiliaryData ){
        int leftSize = leftData.size();
        int rightSize = rightData.size();

        int i = 0; int j = 0; int k = 0;
        while( i < leftSize && j < rightSize ){
            numCompares++;
            numSwaps++;
            if( leftData.get(i) <= rightData.get(j) ){
                auxiliaryData.set(k, leftData.get(i));
                i++;
            } else{
                auxiliaryData.set(k, rightData.get(j));
                j++;
            }
            k++;
        }

        while( i < leftSize ){
            auxiliaryData.set(k, leftData.get(i));
            k++;
            i++;
            numSwaps++;
        }
        while( j < rightSize ){
            auxiliaryData.set(k, rightData.get(j));
            k++;
            j++;
            numSwaps++  ;
        }
    }
}

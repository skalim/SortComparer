package saad.sortcomparer.sort;

import java.util.HashMap;
import java.util.Random;

import saad.sortcomparer.sortingdialog.SortDialogFragment;

/**
 * Created by Saad on 23-Jan-16.
 */
public class Sort {
    public final HashMap<String, String> names;

    private SortData data;
    private long timeStarted;
    private long numCompares = 0;
    private long numSwaps = 0;

    private Random random;

    public Sort(SortData data) {
        this.data = new SortData(data.size(), !data.isArray());
        for(int i = 0; i < data.size(); i++){
            this.data.set(i, data.get(i));
        }

        names = new HashMap<>();
        names.put("merge", "Merge    ");
        names.put("selection", "Selection");
        names.put("insertion", "Insertion");

        random = new Random();
    }

    public Sort(int size, boolean structure) {
        data = new SortData(size, structure);
        names = new HashMap<>();
        names.put("merge", "Merge    ");
        names.put("selection", "Selection");
        names.put("insertion", "Insertion");

        random = new Random();
    }

    public SortData getData() {
        return data;
    }

    //Selection Sort
    public Statistics selectionSort(SortDialogFragment.SortingTask task) {
        Statistics stats = new Statistics();
        stats.setName("Selection");
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
            task.doProgress();
        }

        stats.setTime(System.nanoTime() - timeStarted);
        task.doProgress();
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);

        return stats;
    }

    //Insertion Sort
    public Statistics insertionSort(SortDialogFragment.SortingTask task) {
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
            task.doProgress();
        }

        stats.setTime(System.nanoTime() - timeStarted);
        task.doProgress();
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);
        return stats;
    }

    //Merge Sort functions
    public Statistics mergeSort( SortDialogFragment.SortingTask task ){
        task.doProgress();
        Statistics stats = new Statistics();
        stats.setName(names.get("merge"));
        long timeStarted = System.nanoTime();
        mergeSortRecursion(data, task);
        stats.setTime(System.nanoTime() - timeStarted);
        task.doProgress();
        stats.setNumCompares(numCompares);
        stats.setNumSwaps(numSwaps);
        return stats;
    }

    public void mergeSortRecursion(SortData data, SortDialogFragment.SortingTask task){
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

        mergeSortRecursion(leftData, task);
        mergeSortRecursion(rightData, task);

        //Merge step
        int leftSize = leftData.size();
        int rightSize = rightData.size();

        int i = 0; int j = 0; int k = 0;
        while( i < leftSize && j < rightSize ){
            numCompares++;

            if( leftData.get(i) <= rightData.get(j) ){
                data.set(k++, leftData.get(i++));
            } else{
                data.set(k++, rightData.get(j++));
                numSwaps++;
                task.doProgress();
            }
        }

        while( i < leftSize ){
            data.set(k++, leftData.get(i++));
            task.doProgress();
        }
        while (j < rightSize ){
            data.set(k++, rightData.get(j++));
            task.doProgress();
        }
    }

    //Quick sort
    public void quickSort(){
        quickSortRecursion(data, 0, data.size() - 1);
    }

    public void quickSortRecursion(SortData data, int low, int high) {
        if( data.size() <= 0 || low >= high){
            return;
        }

        int pivot = low + (high-low)/2;
        System.out.println(pivot);
        int i = low, j = high;
        while (i <= j) {
            while (data.get(i) < data.get(pivot)) {
                i++;
            }

            while (data.get(j) > data.get(pivot)) {
                j--;
            }

            if (i <= j) {
                data.swap(i, j);
                i++;
                j--;
            }
        }

        if( low < j ){
            quickSortRecursion(data, low, j);
        }

        if( high > i ){
            quickSortRecursion(data, i, high);
        }
    }
}

package test;

import android.os.AsyncTask;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import saad.sortcomparer.sort.Sort;
import saad.sortcomparer.sort.SortData;
import saad.sortcomparer.thirdscreen.ThirdScreen;

/**
 * Created by Saad on 24-Jan-16.
 */
public class SortTest {
    Sort sort;
    ThirdScreen.SortingTask sortingTask;
    SortData sortData = new SortData(1000, false);

    @Test
    public void testSelectionSort() {
        //test array
        //sort = new Sort(1000, false);
        //sort.selectionSort( sortingTask );
        //assertTrue(sort.getData().isSorted());
        //test list
        //sort = new Sort(1000, true);
        //sort.selectionSort( sortingTask );
        //assertTrue(sort.getData().isSorted());
        //test bigger array
        //sort = new Sort(1000000, false);
        //sort.selectionSort( sortingTask );
        //assertTrue(sort.getData().isSorted());
        sort = new Sort( sortData );
        sort.selectionSort(sortingTask);

        sort.getData().print();
        sortData.print();

    }

    @Test
    public void testInsertionSort() {
        //test array
        // = new Sort(1000, false);
        //sort.getData().print();
        //sort.insertionSort();
        //sort.getData().print();
        //assertTrue(sort.getData().isSorted());
        //test list
       // sort = new Sort(1000, true);
        //sort.insertionSort();
        //assertTrue(sort.getData().isSorted());
    }

    @Test
    public void testMergeSort() {
        //test array
        sort = new Sort(100, false);
        sort.getData().print();
        sort.mergeSort(sortingTask);
        sort.getData().print();
        assertTrue(sort.getData().isSorted());
        //test list
        sort = new Sort(1000, true);
        sort.mergeSort(sortingTask);
        assertTrue(sort.getData().isSorted());
        //test bigger array
        //sort = new Sort(1000000, false);
        //sort.selectionSort( sortingTask );
        //assertTrue(sort.getData().isSorted());
    }

}
package test;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import saad.sortcomparer.sort.Sort;

/**
 * Created by Saad on 24-Jan-16.
 */
public class SortTest /*extends TestCase */{
    Sort sort;

    @Test
    public void testSelectionSort() {
        //test array
        //sort = new Sort(1000, false);
        //sort.selectionSort();
        assertTrue(sort.getData().isSorted());
        //test list
       // sort = new Sort(1000, true);
        //sort.selectionSort();
        assertTrue(sort.getData().isSorted());
    }

    @Test
    public void testInsertionSort() {
        //test array
        // = new Sort(1000, false);
        sort.getData().print();
        //sort.insertionSort();
        sort.getData().print();
        assertTrue(sort.getData().isSorted());
        //test list
       // sort = new Sort(1000, true);
        //sort.insertionSort();
        assertTrue(sort.getData().isSorted());
    }
}
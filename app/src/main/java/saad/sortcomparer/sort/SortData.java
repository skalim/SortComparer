package saad.sortcomparer.sort;

import java.util.ArrayList;
import java.util.Random;

import saad.sortcomparer.saad.sortcomparer.thirdscreen.Console;

/**
 * Created by Saad on 23-Jan-16.
 */
public class SortData {
    private final boolean LIST = true;

    private int[] array;
    private ArrayList<Integer> list;
    private int size;
    private Random random;
    private boolean isArray;

    public Console getConsole() {
        return console;
    }

    private Console console;

    /**
     *
     * @param size size of data structure
     * @param structure true for list, false for array
     */
    public SortData(int size, boolean structure) {
        console = new Console();
        random = new Random();
        this.size = size;

        if(structure == LIST){
            initList();
            isArray = false;
        }else{
            initArray();
            isArray = true;
        }
    }

    public void initArray(){
        array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = getNumber();
        }
    }

    public void initList(){
        list = new ArrayList<Integer>();
        for(int i = 0; i < size; i++){
            list.add( getNumber() );
        }
    }

    public int getNumber(){
        return random.nextInt(2000000000)+1;
    }

    public int get(int position){
        if( isArray ){
            return array[position];
        }else{
            return list.get(position);
        }
    }

    public int size(){
        return size;
    }

    public void swap(int i, int j){
        if( i == j){
            return;
        }

        console.nextLine("swap(" + String.valueOf(i) + ", " + String.valueOf(j) + ")");

        if( isArray ){
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }else{
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }

    public void print(){
        if(isArray){
            for(int i = 0; i < size; i++){
                System.out.print(array[i] + " ");
            }
        }else{
            for(int i = 0; i < size; i++){
                System.out.print(list.get(i) + " ");
            }
        }

        System.out.println();
    }

    public boolean isSorted(){
        if( isArray ){
            for(int i = 0; i < size-1; i++){
                if( array[i] > array[i+1] ){
                    return false;
                }
            }
        }else{
            for(int i = 0; i < size-1; i++){
                if( list.get(i) > list.get(i+1)){
                    return false;
                }
            }
        }

        return true;
    }
}

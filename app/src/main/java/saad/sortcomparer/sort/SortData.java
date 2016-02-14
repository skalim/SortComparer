package saad.sortcomparer.sort;

import java.util.ArrayList;
import java.util.Random;

import saad.sortcomparer.thirdscreen.Console;

/**
 * Created by Saad on 23-Jan-16.
 */
public class SortData {
    private final boolean LIST = true;

    private Console console;
    private short[] array;
    private ArrayList<Short> list;
    private int size;
    private Random random;
    private boolean isArray;

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
        array = new short[size];
        for(int i = 0; i < size; i++){
            array[i] = (short)getNumber();
        }
    }

    public void initList(){
        list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add( (short)getNumber() );
        }
    }

    public int getNumber(){
        return random.nextInt(32766)+1;
    }

    public short get(int position){
        if( isArray ){
            return array[position];
        }else{
            return list.get(position);
        }
    }

    public void set(int position, short value){
        if( isArray ){
            array[position] = value;
            console.nextLine("set" + String.valueOf(array[position]) + " at " + position);

        }else{
            list.set(position, value);
            console.nextLine("set" + String.valueOf(list.get(position)) + " at " + position);
        }
    }

    public int size(){
        return size;
    }

    /**
     *
     * @param i first index to swap
     * @param j second index to swap
     *  @return true if swapped, false otherwise
     *
     */
    public boolean swap(int i, int j){
        if( i == j){
            return false;
        }

        if( isArray ){
            console.nextLine("swap(" + String.valueOf(array[i]) + ", " + String.valueOf(array[j]) + ")");
            short tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }else{
            console.nextLine("swap(" + String.valueOf(list.get(i)) + ", " + String.valueOf(list.get(j)) + ")");
            short tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }

        return true;
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

    public short getMin(){
        short min = 32767;
        if( isArray ){
            for(int i = 0; i < size; i++){
                if( array[i] < min){
                    min = array[i];
                }
            }
        }else{
            for(int i = 0; i < size; i++){
                if( list.get(i) < min){
                    min = list.get(i);
                }
            }
        }
        return min;
    }

    public short getMax(){
        short max = 0;
        if( isArray ){
            for(int i = 0; i < size; i++){
                if( array[i] > max){
                    max = array[i];
                }
            }
        }else{
            for(int i = 0; i < size; i++){
                if( list.get(i) > max){
                    max = list.get(i);
                }
            }
        }
        return max;
    }

    public Console getConsole() {
        return console;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }

}

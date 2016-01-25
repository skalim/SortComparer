package saad.sortcomparer.sort;

import android.widget.TextView;

/**
 * Created by Saad on 23-Jan-16.
 */
public class Sort {
    private SortData data;
    private TextView[] textViews;

    public Sort(int size, boolean structure, TextView[] textViews) {
        data = new SortData(size, structure);
        this.textViews = textViews;
    }

    public SortData getData() {
        return data;
    }

    public void updateTextViews(){
        for(int i = 0; i < textViews.length; i++){
            //System.out.println("Updating TextView: " + data.getConsole().getLine(i));
            textViews[i].setText( data.getConsole().getLine(i) );
        }
    }

    public void selectionSort() {
        for (int i = 0; i < data.size(); i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = i; j < data.size(); j++) {
                if (data.get(j) < min) {
                    min = data.get(j);
                    minIndex = j;
                }
            }
            data.swap(i, minIndex);
            updateTextViews();
        }
    }

    public void insertionSort(){
        for(int i = 1; i < data.size(); i++){
            int k = i;
            for(int j = i-1; j >= 0; j--){
                if( data.get(j) > data.get(k)){
                    data.swap(k, j);
                    k = j;
                }
            }
        }
    }
}

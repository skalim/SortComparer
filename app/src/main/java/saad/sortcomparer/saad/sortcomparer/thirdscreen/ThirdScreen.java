package saad.sortcomparer.saad.sortcomparer.thirdscreen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import saad.sortcomparer.R;
import saad.sortcomparer.SettingsHandler;
import saad.sortcomparer.sort.Sort;

public class ThirdScreen extends Activity {
    Console console;
    TextView[] textViews;
    Sort[] sort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sort = new Sort[ SettingsHandler.Settings.algorithmsSelected.size() ];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        initTextViews();
        initSort();
        //new SortingTask().execute(sort);
        for (int i = 0; i < SettingsHandler.Settings.algorithmsSelected.size(); i++){
            switch (SettingsHandler.Settings.algorithmsSelected.get(i)){
                case "SELECTION": sort[i].selectionSort();

                case "INSERTION": sort[i].insertionSort();
            }
        }
    }

    /*private class SortingTask extends AsyncTask<Sort[], Console, Void> {

        @Override
        protected void onPreExecute() {
            initTextViews();
            initSort();
        }

        @Override
        protected Void doInBackground(Sort[]... params) {
            Sort[] sort = params[0];
            for(int i = 0; i < SettingsHandler.Settings.algorithmsSelected.size(); i++){
                switch (SettingsHandler.Settings.algorithmsSelected.get(i)){
                    case "SELECTION": System.out.println("Staring sort");sort[i].selectionSort(); publishProgress();

                    case "INSERTION": sort[i].insertionSort();
                }
            }
            return null;
        }

        protected void onProgressUpdate() {
            updateTextViews();
        }

        protected void onPostExecute(Integer result) {
        }
    }

    public void updateTextViews(){
        for(int i = 0; i < textViews.length; i++){
            System.out.println("Updating TextView: " + console.getLine(i));
            textViews[i].setText( console.getLine(i) );
        }
    }*/


    public void initSort(){
        for(int i = 0; i < sort.length; i++){
            sort[i] = new Sort( SettingsHandler.Settings.size, SettingsHandler.Settings.listSelected, textViews );
        }
    }

    public void initTextViews(){
        textViews = new TextView[10];
        textViews[0] = (TextView) findViewById(R.id.tv_0);
        textViews[1] = (TextView) findViewById(R.id.tv_1);
        textViews[2] = (TextView) findViewById(R.id.tv_2);
        textViews[3] = (TextView) findViewById(R.id.tv_3);
        textViews[4] = (TextView) findViewById(R.id.tv_4);
        textViews[5] = (TextView) findViewById(R.id.tv_5);
        textViews[6] = (TextView) findViewById(R.id.tv_6);
        textViews[7] = (TextView) findViewById(R.id.tv_7);
        textViews[8] = (TextView) findViewById(R.id.tv_8);
        textViews[9] = (TextView) findViewById(R.id.tv_9);
    }
}

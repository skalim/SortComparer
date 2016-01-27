package saad.sortcomparer.thirdscreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.text.StaticLayout;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.Statistics;
import saad.sortcomparer.resultsscreen.ResultsActivity;
import saad.sortcomparer.sort.Sort;

public class ThirdScreen extends Activity {
    TextView[] textViews;
    Console console;
    Sort[] sort;
    TextView startMessage;
    TextView endMessage;
    TextView sortName;
    Statistics[] statistics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        initTextViews();
        initSort();
        new SortingTask().execute(sort);
    }

    public class SortingTask extends AsyncTask<Sort[], Void, Void> {
        String sortNameString;

        @Override
        protected void onPreExecute() {
            startMessage.setVisibility(View.VISIBLE);
            sortName.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Sort[]... params) {
            Sort[] sort = params[0];
            for (int i = 0; i < Settings.algorithmsSelected.size(); i++) {
                switch (Settings.algorithmsSelected.get(i)) {
                    case "SELECTION":
                        sortNameString = "Selection sort";
                        console = sort[i].getData().getConsole();
                        statistics[i] = sort[i].selectionSort(this);
                        break;

                    case "INSERTION":
                        sortNameString = "Insertion sort";
                        console = sort[i].getData().getConsole();
                        statistics[i] = sort[i].insertionSort(this);
                        break;
                }
            }
            return null;
        }

        public void doProgress(Void... values) {
            publishProgress(values);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            sortName.setText(sortNameString);
            updateTextViews();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            endMessage.setVisibility(View.VISIBLE);
        }
    }

    public void updateTextViews() {
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(console.getLine(i));
        }
    }

    public void initSort() {
        sort = new Sort[Settings.algorithmsSelected.size()];
        statistics = new Statistics[Settings.algorithmsSelected.size()];
        for (int i = 0; i < sort.length; i++) {
            sort[i] = new Sort(Settings.size, Settings.listSelected);
        }
    }

    public void initTextViews() {
        startMessage = (TextView) findViewById(R.id.preexecute_message);
        endMessage = (TextView) findViewById(R.id.postexecute_message);
        sortName = (TextView) findViewById(R.id.sort_name);
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

    public void showResultsButton(View view){
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("Statistics", statistics);
        startActivity(intent);
    }
}

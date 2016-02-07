package saad.sortcomparer.thirdscreen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.*;
import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.firstscreen.Animator;
import saad.sortcomparer.resultsscreen.ResultsActivity;
import saad.sortcomparer.sort.SortData;
import saad.sortcomparer.sort.Statistics;
import saad.sortcomparer.sort.Sort;


public class ThirdScreen extends Activity {
    TextView[] textViews;
    Console console;
    Sort[] sort;
    TextView startMessage;
    TextView endMessage;
    TextView sortName;
    Statistics[] statistics;
    SortData sortData;

    Typeface face;
    RelativeLayout button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        face = Typeface.createFromAsset(getAssets(), "fonts/Minecraftia-Regular.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        initTextViews();
        initSort();
        button = (RelativeLayout) findViewById(R.id.results_button);
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
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
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

                    case "MERGE":
                        sortNameString = "Merge sort";
                        console = sort[i].getData().getConsole();
                        statistics[i] = sort[i].mergeSort( this );
                        break;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            button.setVisibility(View.VISIBLE);
            new Animator().animateIn(button);
        }
    }

    public void updateTextViews() {
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(console.getLine(i));
        }
    }

    public void initSort() {
        sortData = new SortData(Settings.size, Settings.listSelected);
        sort = new Sort[Settings.algorithmsSelected.size()];
        statistics = new Statistics[Settings.algorithmsSelected.size()];
        for (int i = 0; i < sort.length; i++) {
            sort[i] = new Sort( sortData );
        }
    }

    public void initTextViews() {
        startMessage = (TextView) findViewById(R.id.preexecute_message);
        startMessage.setTypeface(face);
        endMessage = (TextView) findViewById(R.id.postexecute_message);
        endMessage.setTypeface(face);
        sortName = (TextView) findViewById(R.id.sort_name);
        sortName.setTypeface(face);
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
        for(int i = 0; i < textViews.length; i++){
            textViews[i].setTypeface(face);
        }
    }

    public void nextScreen(View view){
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("Statistics", statistics);
        startActivity(intent);
    }

}

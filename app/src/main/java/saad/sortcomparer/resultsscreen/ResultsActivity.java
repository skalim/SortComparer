package saad.sortcomparer.resultsscreen;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.firstscreen.Animator;
import saad.sortcomparer.firstscreen.LinearLayoutManager;

import saad.sortcomparer.sort.Statistics;


public class ResultsActivity extends Activity {
    private RecyclerView mRecyclerView;
    private GraphListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    Statistics[] statistics;
    TextView result;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        result = (TextView) findViewById(R.id.result);

        statistics = (Statistics[]) getIntent().getSerializableExtra("Statistics");
        sortStatsDescending();

        mRecyclerView = (RecyclerView) findViewById(R.id.time_graph_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new GraphListAdapter(statistics);
        mRecyclerView.setAdapter(mAdapter);

        setResult();

    }


    public void sortStatsDescending() {
        for (int i = 0; i < statistics.length; i++) {
            Double max = 0d;
            int minIndex = 0;
            for (int j = i; j < statistics.length; j++) {
                if (statistics[j].getTime() > max) {
                    max = statistics[j].getTime();
                    minIndex = j;
                }
            }

            Statistics tmp = statistics[i];
            statistics[i] = statistics[minIndex];
            statistics[minIndex] = tmp;
        }
    }

    public void setResult() {
        resultString = "";
        for (int i = 0; i < statistics.length; i++) {
            resultString += statistics[i].getName() + "\n Time: " + statistics[i].getTime() + " ms \n "
                    + "Compares: " + statistics[i].getNumCompares() + "\n"
                    + "Swaps: " + statistics[i].getNumSwaps() + "\n";
        }
        resultString += "Data size:  " + Settings.size;
        result.setText(resultString);
    }
}

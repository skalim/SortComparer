package saad.sortcomparer.resultsscreen;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import saad.sortcomparer.R;
import saad.sortcomparer.homescreen.SlidingCardLayout;

/**
 * Created by Saad on 27-Mar-16.
 */
public class TimeGraphManager extends GraphManager {
    private PieChart pieChart;

    public TimeGraphManager(AppCompatActivity activity, String title, int slidingCardLayoutResourceId) {
        super(activity, title, slidingCardLayoutResourceId);
        pieChart = (PieChart) getSlidingCardLayout().findViewById(R.id.pie_chart);
        setUpPieChart();
    }

    private void setUpPieChart(){
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for(int i = 0; i < statistics.length; i++){
            entries.add( new Entry(  (float)statistics[i].getTime(), i) );
            labels.add(statistics[i].getName());
        }

        PieDataSet dataset = new PieDataSet(entries, "Time");
        PieData data = new PieData(labels, dataset);
        pieChart.setData(data);
    }


}

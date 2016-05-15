package saad.sortcomparer.resultsscreen;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.model.BarSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.BarChartView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;
import saad.sortcomparer.R;
import saad.sortcomparer.homescreen.SlidingCardBehavior;
import saad.sortcomparer.homescreen.SlidingCardLayout;
import saad.sortcomparer.sort.Statistics;

public class GraphsActivity extends AppCompatActivity {
    final String TAG = "skalim";
    private FloatingActionButton fab;

    private Statistics[] statistics;

    private TimeGraphManager timeGraphManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        bind();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        timeGraphManager = new TimeGraphManager( this, "Time", R.id.graphs_time );
    }

    private void bind(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }
}

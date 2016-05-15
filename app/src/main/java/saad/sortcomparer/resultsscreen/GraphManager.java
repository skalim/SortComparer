package saad.sortcomparer.resultsscreen;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import saad.sortcomparer.R;
import saad.sortcomparer.homescreen.SlidingCardLayout;
import saad.sortcomparer.sort.Statistics;

/**
 * Created by Saad on 27-Mar-16.
 */
public abstract class GraphManager {
    private SlidingCardLayout slidingCardLayout;
    Statistics[] statistics;

    public GraphManager( AppCompatActivity activity, String title,int slidingCardLayoutResourceId){
        slidingCardLayout = (SlidingCardLayout) activity.findViewById(slidingCardLayoutResourceId);
        TextView header = ( TextView ) slidingCardLayout.findViewById(R.id.header);
        header.setText(title);

        statistics = (Statistics[]) activity.getIntent().getSerializableExtra("Statistics");
    }

    public SlidingCardLayout getSlidingCardLayout() {
        return slidingCardLayout;
    }

    public Statistics[] getStatistics() {
        return statistics;
    }
}

package saad.sortcomparer.resultsscreen;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.sort.Statistics;

public class ResultsActivity extends FragmentActivity {
    ViewPager mPager;
    MyFragmentStatePagerAdapter mPagerAdapter;
    private Statistics[] statistics;

    TextView result;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        statistics = (Statistics[]) getIntent().getSerializableExtra("Statistics");

        result = (TextView) findViewById(R.id.result);

        mPagerAdapter = new MyFragmentStatePagerAdapter( getSupportFragmentManager() );
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter( mPagerAdapter );

        setResult();
    }

    public Statistics[] getStatistics(){
        return statistics;
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

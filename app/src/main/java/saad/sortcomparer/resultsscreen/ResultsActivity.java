package saad.sortcomparer.resultsscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.firstscreen.MainActivity;
import saad.sortcomparer.sort.Statistics;
import saad.sortcomparer.thirdscreen.ThirdScreen;

public class ResultsActivity extends FragmentActivity {
    ViewPager mPager;
    MyFragmentStatePagerAdapter mPagerAdapter;
    private Statistics[] statistics;

    public static final String TAG = "ResultsActivity";
    //TextView result;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        statistics = (Statistics[]) getIntent().getSerializableExtra("Statistics");
        //result = (TextView) findViewById(R.id.result);

        mPagerAdapter = new MyFragmentStatePagerAdapter( getSupportFragmentManager() );
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);

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
        Log.d(TAG, resultString);
        //result.setText(resultString);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Start Over?")
                .setMessage("")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void onClickStartOver(View v){
        onBackPressed();
    }
}

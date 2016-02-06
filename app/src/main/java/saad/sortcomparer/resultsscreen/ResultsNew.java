package saad.sortcomparer.resultsscreen;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import saad.sortcomparer.R;

public class ResultsNew extends FragmentActivity {
    ViewPager mPager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_new2);
    }

}

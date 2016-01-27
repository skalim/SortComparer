package saad.sortcomparer.resultsscreen;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.Statistics;

public class ResultsActivity extends Activity {
    Statistics[] statistics;
    TextView result;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resultString = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        statistics = (Statistics[]) getIntent().getSerializableExtra("Statistics");
        result = (TextView) findViewById(R.id.result);
        setResult();
    }

    public void setResult(){
        for(int i = 0; i < statistics.length; i++){
            resultString += statistics[i].getName() + "\n Time: " + statistics[i].getTime() + " ns \n " + "Compares: " + statistics[i].getNumCompares() + "\n";
        }
        resultString += "Data size:  " + Settings.size;
        result.setText(resultString);
    }
}

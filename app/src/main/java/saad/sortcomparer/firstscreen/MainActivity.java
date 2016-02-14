package saad.sortcomparer.firstscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.secondscreen.SecondScreen;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private ArrayList<Data> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initDataset();
        Settings.reset();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.sortingAlgorithmsListRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SortingAlgorithmListAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initDataset(){
        mDataset = new ArrayList<>();
        mDataset.add(new Data("Selection", "n2"));
        mDataset.add(new Data("Insertion", "n2"));
        mDataset.add(new Data("Merge", "nlogn"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void nextScreen(View view) {
        if(Settings.algorithmsSelected.size() < 1 ){
            return;
        }
        Intent intent = new Intent(this, SecondScreen.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

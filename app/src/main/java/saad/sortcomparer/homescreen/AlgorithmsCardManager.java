package saad.sortcomparer.homescreen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import saad.sortcomparer.R;

/**
 * Created by Saad on 05-Mar-16.
 */
public class AlgorithmsCardManager {
    private ArrayList<SortListItem> mDatasetSorts;

    private SlidingCardLayout mCardAlgorithms;
    private AppCompatActivity activity;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public AlgorithmsCardManager(Context context) {
        this.activity  = (AppCompatActivity) context;
        mCardAlgorithms = (SlidingCardLayout) activity.findViewById(R.id.card_select_sorts);
        TextView headerAlgorithms = (TextView) mCardAlgorithms.findViewById(R.id.header);
        headerAlgorithms.setText(activity.getResources().getString(R.string.header_sorts));
        initDataset();
        setUpRecycler();
    }


    private void initDataset(){
        mDatasetSorts = new ArrayList<>();
        String[] algorithms = activity.getResources().getStringArray(R.array.sorting_algorithms);
        for( int i = 0; i < algorithms.length; i = i+2 ){
            mDatasetSorts.add( new SortListItem( algorithms[i], algorithms[i+1] ));
        }
    }

    private  void setUpRecycler(){
        mRecyclerView = (RecyclerView) mCardAlgorithms.findViewById(R.id.sorts_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SortingAlgorithmListAdapter(mDatasetSorts);
        mRecyclerView.setAdapter(mAdapter);
    }
}

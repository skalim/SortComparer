package saad.sortcomparer.resultsscreen.old;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import saad.sortcomparer.R;
import saad.sortcomparer.homescreen.LinearLayoutManager;

public class SortSwapsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private GraphListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sort_swaps, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.swaps_graph_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager( getActivity() );
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GraphListAdapter(((ResultsActivity) getActivity()).getStatistics(), "swaps");
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}

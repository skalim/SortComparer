package saad.sortcomparer.resultsscreen;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.firstscreen.Animator;
import saad.sortcomparer.firstscreen.Data;
import saad.sortcomparer.sort.Statistics;

/**
 * Created by Saad on 23-Jan-16.
 */
public class GraphListAdapter extends RecyclerView.Adapter<GraphListAdapter.ViewHolder> {
    private Statistics[] mDataset;
    private String graphType;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, value;
        private ProgressBar bar;
        private boolean doneAnimation;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            value = (TextView) view.findViewById(R.id.value);
            bar = (ProgressBar) view.findViewById(R.id.bar);
            doneAnimation = false;
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public GraphListAdapter(Statistics[] myDataset, String graphType) {
        mDataset = myDataset;
        this.graphType = graphType;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GraphListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.graph_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset[position].getName());
        Double barValue = 0d;
        switch (graphType) {
            case "time":
                Double time = mDataset[position].getTime();
                barValue = time / getMaxStatistic("time").getTime() * 100;
                holder.value.setText( mDataset[position].getFormattedTime() );
                break;
            case "compares":
                Long compares = mDataset[position].getNumCompares();
                barValue = compares.doubleValue() / getMaxStatistic("compares").getNumCompares() * 100;
                holder.value.setText( mDataset[position].getFormattedNumCompares() );
                System.out.println("skalim--- setting bar value: " + barValue + " for compare: " + compares);
                break;
            case "swaps":
                Long swaps = mDataset[position].getNumSwaps();
                barValue = swaps.doubleValue() / getMaxStatistic("swaps").getNumSwaps() * 100;
                holder.value.setText( mDataset[position].getFormattedNumSwaps() );
                break;
        }
        holder.bar.setProgress(barValue.intValue());
        /*if (!holder.doneAnimation) {
            new Animator().animateBar(holder.bar, barValue.intValue());
            holder.doneAnimation = true;
        }*/
    }

    public Statistics getMaxStatistic(String maxWhat) {
        int maxIndex = 0;

        switch (maxWhat) {
            case "time":
                Double maxTime = 0d;
                for (int i = 0; i < mDataset.length; i++) {
                    if (maxTime < mDataset[i].getTime()) {
                        maxTime = mDataset[i].getTime();
                        maxIndex = i;
                    }
                }
                return mDataset[maxIndex];
            case "swaps":
                Long maxSwaps = 0l;
                for (int i = 0; i < mDataset.length; i++) {
                    if (maxSwaps < mDataset[i].getNumSwaps()) {
                        maxSwaps = mDataset[i].getNumSwaps();
                        maxIndex = i;
                    }
                }
                return mDataset[maxIndex];
            case "compares":
                Long maxCompares = 0l;
                for (int i = 0; i < mDataset.length; i++) {
                    if (maxCompares < mDataset[i].getNumCompares()) {
                        maxCompares = mDataset[i].getNumCompares();
                        maxIndex = i;
                    }
                }
                return mDataset[maxIndex];
        }
        return mDataset[0];
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}

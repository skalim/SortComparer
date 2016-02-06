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
public class GraphListAdapter extends RecyclerView.Adapter<GraphListAdapter.ViewHolder>{
    private Statistics[] mDataset;
    private Animator animator;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, value;
        private ProgressBar bar;
        private boolean doneAnimation = false;

        public ViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            value = (TextView)view.findViewById(R.id.value);
            bar = (ProgressBar) view.findViewById(R.id.bar);

        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public GraphListAdapter( Statistics[] myDataset ) {
        mDataset = myDataset;
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
        Double time = mDataset[position].getTime();
        time = time / mDataset[0].getTime() * 100;


        holder.name.setText(mDataset[position].getName());
        holder.value.setText(String.format( "%.2f", mDataset[position].getTime() ) );
        if( !holder.doneAnimation ){
            animator = new Animator();
            animator.animateBar( holder.bar, time.intValue() );
            holder.doneAnimation = true;
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}

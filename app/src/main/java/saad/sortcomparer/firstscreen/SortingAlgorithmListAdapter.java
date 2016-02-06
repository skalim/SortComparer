package saad.sortcomparer.firstscreen;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;

/**
 * Created by Saad on 23-Jan-16.
 */
public class SortingAlgorithmListAdapter extends RecyclerView.Adapter<SortingAlgorithmListAdapter.ViewHolder>{
    private ArrayList<Data> mDataset;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public CardView cardView;
        public TextView algorithmNameTV;
        public TextView algorithmDescriptionTV;
        public ImageView checkCircle;
        public boolean isChecked;
        public Animator animator;

        public ViewHolder(View view) {
            super(view);
            algorithmNameTV = (TextView) view.findViewById(R.id.algorithm_name);
            algorithmDescriptionTV = (TextView) view.findViewById(R.id.algorithm_desciption);
            checkCircle = (ImageView) view.findViewById(R.id.check_circle);
            cardView = (CardView) view.findViewById(R.id.algorithm_item);
            animator = new Animator();
            isChecked = false;
            this.view = view;
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    System.out.println("listener called");
                    if(isChecked){
                        animator.animateOut(checkCircle);
                        checkCircle.setVisibility(View.INVISIBLE);
                        cardView.setCardBackgroundColor(Color.parseColor("#263238"));
                        isChecked = false;
                        System.out.println("Removing: " + algorithmNameTV.getText().toString());
                        Settings.removeSelected( algorithmNameTV.getText().toString() );
                    }else{
                        animator.animateIn(checkCircle);
                        checkCircle.setVisibility(View.VISIBLE);
                        cardView.setCardBackgroundColor(Color.parseColor("#37474F"));
                        isChecked = true;
                        System.out.println("Adding: " + algorithmNameTV.getText().toString());
                        Settings.addSelected( algorithmNameTV.getText().toString() );
                    }
                }
            });
        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public SortingAlgorithmListAdapter(ArrayList<Data> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SortingAlgorithmListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.algorithm_item_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.algorithmNameTV.setText(mDataset.get(position).getName());
        holder.algorithmDescriptionTV.setText( mDataset.get(position).getDescription() );
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

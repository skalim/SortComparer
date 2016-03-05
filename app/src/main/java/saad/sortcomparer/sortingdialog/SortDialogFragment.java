package saad.sortcomparer.sortingdialog;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import saad.sortcomparer.Animator;
import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.sort.Sort;
import saad.sortcomparer.sort.SortData;
import saad.sortcomparer.sort.Statistics;
import saad.sortcomparer.resultsscreen.ResultsActivity;


/**
 */
public class SortDialogFragment extends DialogFragment implements View.OnClickListener{

    View view;
    TextView sortingText;
    LinearLayout resultsButton;
    TextView cancelButton;

    Console console;
    Sort[] sort;
    TextView startMessage;
    TextView endMessage;
    TextView sortName;
    Statistics[] statistics;
    SortData sortData;

    Typeface face;

    SortingTask sortingTask;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        LayoutInflater i = getActivity().getLayoutInflater();

        view = i.inflate(R.layout.fragment_sort_dialog, null);
        face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Minecraftia-Regular.ttf");
        initTextViews();
        initSort();
        sortingTask = new SortingTask();
        sortingTask.execute(sort);

        b.setView(view);
        return b.create();
    }

    @Override
    public void onDestroy() {
        sortingTask.cancel(true);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.results_button:
                onClickSeeResults();
                break;
            case R.id.cancel_button:
                sortingTask.cancel(true);
                getDialog().dismiss();
                break;
        }
    }

    public class SortingTask extends AsyncTask<Sort[], Void, Void> {
        String sortNameString;

        @Override
        protected void onPreExecute() {
            startMessage.setVisibility(View.VISIBLE);
            sortName.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Sort[]... params) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            Sort[] sort = params[0];
            for (int i = 0; i < Settings.algorithmsSelected.size(); i++) {
                switch (Settings.algorithmsSelected.get(i)) {
                    case "SELECTION":
                        sortNameString = "Selection sort";
                        console = sort[i].getData().getConsole();
                        statistics[i] = sort[i].selectionSort(this);
                        break;

                    case "INSERTION":
                        sortNameString = "Insertion sort";
                        console = sort[i].getData().getConsole();
                        statistics[i] = sort[i].insertionSort(this);
                        break;

                    case "MERGE":
                        sortNameString = "Merge sort";
                        console = sort[i].getData().getConsole();
                        statistics[i] = sort[i].mergeSort(this);
                        break;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void doProgress(Void... values) {
            publishProgress(values);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            sortName.setText(sortNameString);
            updateTextViews();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            cancelButton.setVisibility(View.GONE);
            endMessage.setVisibility(View.VISIBLE);
            resultsButton.setVisibility(View.VISIBLE);
            new Animator().animateIn(resultsButton);
        }
    }

    public void updateTextViews() {
        String text = "";
        for (int i = 0; i < console.getNumLines(); i++) {
            text += console.getLine(i) + "\n";
        }
        sortingText.setText(text);
    }

    public void initSort() {
        sortData = new SortData(Settings.size, Settings.listSelected);
        sort = new Sort[Settings.algorithmsSelected.size()];
        statistics = new Statistics[Settings.algorithmsSelected.size()];
        for (int i = 0; i < sort.length; i++) {
            sort[i] = new Sort(sortData);
        }
    }

    public void initTextViews() {
        startMessage = (TextView) view.findViewById(R.id.preexecute_message);
        startMessage.setTypeface(face);
        endMessage = (TextView) view.findViewById(R.id.postexecute_message);
        endMessage.setTypeface(face);
        sortName = (TextView) view.findViewById(R.id.sort_name);
        sortName.setTypeface(face);
        sortingText = (TextView) view.findViewById(R.id.sort_text);
        sortingText.setTypeface(face);

        resultsButton = (LinearLayout) view.findViewById(R.id.results_button);
        cancelButton = (TextView) view.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(this);
        resultsButton.setOnClickListener(this);
    }

    public void onClickSeeResults(){
        Intent intent = new Intent(getActivity(), ResultsActivity.class);
        intent.putExtra("Statistics", statistics);
        startActivity(intent);
        getDialog().dismiss();
        getActivity().finish();
    }
}

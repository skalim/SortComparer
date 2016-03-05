package saad.sortcomparer.homescreen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.sortingdialog.SortDialogFragment;

public class MainActivity extends AppCompatActivity {
    // Views
    View coordinatorLayout;
    private FloatingActionButton mFab;

    private SettingsCardManager settingsCardManager;
    private AlgorithmsCardManager algorithmsCardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        setUpViews();
    }

    private void bind(){
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void setUpViews(){
        settingsCardManager = new SettingsCardManager(this);
        algorithmsCardManager = new AlgorithmsCardManager(this);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.size = settingsCardManager.dataStructureSize();
                if (Settings.numSelected() < 1) {
                    Snackbar.make(coordinatorLayout, R.string.error_no_algorithms_selected,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                } else if( settingsCardManager.noDataStructureSelected() ){
                    Snackbar.make(coordinatorLayout, R.string.error_no_datastructure_selected,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }
                SortDialogFragment sortFragment = new SortDialogFragment();
                sortFragment.show(getFragmentManager(), "sort");
            }
        });
    }

}

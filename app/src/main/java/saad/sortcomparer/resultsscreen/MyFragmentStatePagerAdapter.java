package saad.sortcomparer.resultsscreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

/**
 * Created by Saad on 06-Feb-16.
 */
public class MyFragmentStatePagerAdapter extends FragmentPagerAdapter {

    public MyFragmentStatePagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SortTimeFragment();
            case 1:
                return new SortComparesFragment();
            case 2:
                return new SortSwapsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        String title = "";
        switch (position){
            case 0:
                title = "Time";
                break;
            case 1:
                title = "Compares";
                break;
            case 2:
                title = "Swaps";
                break;
        }
        return title;
    }

}

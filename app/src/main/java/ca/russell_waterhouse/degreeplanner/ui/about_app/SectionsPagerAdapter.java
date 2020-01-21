package ca.russell_waterhouse.degreeplanner.ui.about_app;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.security.InvalidParameterException;

import ca.russell_waterhouse.degreeplanner.R;
import ca.russell_waterhouse.degreeplanner.ui.about_app.fragments.AboutDevelopmentFragment;
import ca.russell_waterhouse.degreeplanner.ui.about_app.fragments.OpenSourceContributionsFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.about_the_development, R.string.about_open_source};
    private final Context mContext;

    SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position){
            case 0:
                return AboutDevelopmentFragment.newInstance();
            case 1:
                return OpenSourceContributionsFragment.newInstance();
            default:
                throw new InvalidParameterException("more than 2 items were expected when only 2 are supported");
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
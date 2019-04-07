package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new ColorsFragment();
        } else if(position == 1){
            return new FamilyFragment();
        } else if(position == 2){
            return new NumbersFragment();
        } else if(position == 3){
            return new PhrasesFragment();
        } else if(position == 4){
            return new CountdownFragment();
        } else if(position == 5){
            return new MusicPlayerFragment();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    /**
     *  Set the tab layout's tab names with the view pager's adapter's titles.
     *
     *  Following method is comming from TapLayout class.
     *  As you can see, it use Adapter's getPageTitle method to populate tab's title.
     *
     *
     *  private void populateFromPagerAdapter() {
     *         removeAllTabs();
     *
     *         if (mPagerAdapter != null) {
     *             final int adapterCount = mPagerAdapter.getCount();
     *             for (int i = 0; i < adapterCount; i++) {
     *                 addTab(newTab().setText(mPagerAdapter.getPageTitle(i)), false);
     *             }
     *
     *             // Make sure we reflect the currently set ViewPager item
     *             if (mViewPager != null && adapterCount > 0) {
     *                 final int curItem = mViewPager.getCurrentItem();
     *                 if (curItem != getSelectedTabPosition() && curItem < getTabCount()) {
     *                     selectTab(getTabAt(curItem));
     *                 }
     *             }
     *         } else {
     *             removeAllTabs();
     *         }
     *     }
     */
    public CharSequence getPageTitle (int position) {
        if(position == 0){
            return mContext.getString(R.string.category_colors);
        } if (position == 1){
            return mContext.getString(R.string.category_family);
        } if (position == 2){
            return mContext.getString(R.string.category_numbers);
        } if (position == 3){
            return mContext.getString(R.string.category_phrases);
        } if (position == 4){
            return mContext.getString(R.string.category_countdown);
        } if (position == 5){
            return mContext.getString(R.string.category_musicplayer);
        } else {
            return "";
        }
    }
}

package yu.yufragmentdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    ArrayList<String> fragmentTitles;

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        fragmentTitles = new ArrayList<>();
        fragmentTitles.add("CHATS");
        fragmentTitles.add("STATUS");
        fragmentTitles.add("CALLS");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return ChatsFragment.newInstance(fragmentTitles.get(position), position);
            case 1:
                return StatusFragment.newInstance(fragmentTitles.get(position), position);
            case 2:
                return CallsFragment.newInstance(fragmentTitles.get(position), position);
            default:
                return ChatsFragment.newInstance(fragmentTitles.get(position), position);
        }
    }

    @Override
    public int getCount() {
        return fragmentTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return fragmentTitles.get(position);
    }
}

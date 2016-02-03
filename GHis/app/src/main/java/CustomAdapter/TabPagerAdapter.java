package CustomAdapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.thinh.ghis.LearnHistory;
import com.example.thinh.ghis.MainContent;

/**
 * Created by thinh on 11/7/2015.
 */
public class TabPagerAdapter extends FragmentPagerAdapter{

    public TabPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainContent();
            case 1:
                return new LearnHistory();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

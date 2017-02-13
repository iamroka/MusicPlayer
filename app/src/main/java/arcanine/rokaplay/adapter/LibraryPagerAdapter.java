package arcanine.rokaplay.adapter;
/**
 * Created by Acer on 6/23/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import arcanine.rokaplay.view.fragment.AlbumFragment;
import arcanine.rokaplay.view.fragment.ArtistFragment;
import arcanine.rokaplay.view.fragment.GeneresFragment;
import arcanine.rokaplay.view.fragment.PlaylistFragment;
import arcanine.rokaplay.view.fragment.SongsFragment;


//Extending FragmentStatePagerAdapter
public class LibraryPagerAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public LibraryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new SongsFragment();
            case 1:
                return new AlbumFragment();
            case 2:
                return new ArtistFragment();
            case 3:
                return new PlaylistFragment();
            case 4:
                return new GeneresFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        switch (position){
            case 0:
                return "Songs";
            case 1:
                return "Album";
            case 2:
                return "Artist";
            case 3:
                return "Playlist";
            case 4:
                return "Generes";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}

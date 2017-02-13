package arcanine.rokaplay.view.fragment;

/**
 * Created by Rohan on 6/23/2016.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arcanine.rokaplay.R;
import arcanine.rokaplay.adapter.SongsListAdapter;
import arcanine.rokaplay.presenter.SongsPresenter;
import arcanine.rokaplay.view.iview.ISongsView;

//Our class extending fragment
public class SongsFragment extends BaseFragment implements ISongsView {
    final String TAG = getClass().getSimpleName();
    SongsPresenter mSongsPresenter;
    // to get the list of songs

    private RecyclerView songRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View songView = inflater.inflate(R.layout.fragment_songs, container, false);
        songRecyclerView = (RecyclerView) songView.findViewById(R.id.song_list);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSongsPresenter= new SongsPresenter(this);
        return songView;
    }

    @Override
    public void setSongListAdapter(SongsListAdapter mSongsAdapter) {
        songRecyclerView.setAdapter(mSongsAdapter);
    }
}


package arcanine.rokaplay.presenter;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import arcanine.rokaplay.adapter.SongsListAdapter;
import arcanine.rokaplay.adapter.listener.BaseRecyclerAdapterListener;
import arcanine.rokaplay.model.SongData;
import arcanine.rokaplay.presenter.ipresenter.ISongsPresenter;
import arcanine.rokaplay.view.iview.ISongsView;

/**
 * Created by Roka on 1/14/2017.
 */

public class SongsPresenter extends BasePresenter implements ISongsPresenter {
    private ISongsView iSongsView;
    private ArrayList<SongData> songDataList = new ArrayList<>();
    private SongsListAdapter mSongsAdapter;

    public SongsPresenter(ISongsView iSongsView) {
        super(iSongsView);
        this.iSongsView = iSongsView;
        getSongList();
    }

    @Override
    public void onCreatePresenter(Bundle bundle) {

    }

    public void getSongList() {
        //retrieve song info
        ContentResolver musicResolver = iSongsView.getActivity().getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, MediaStore.Audio.Media.IS_MUSIC, null, null);
        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DISPLAY_NAME);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int durationColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            /*int songArtURIColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Albums.ALBUM_ART);*/
            int songArtURIColumn = 0;
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisDuration = musicCursor.getString(durationColumn);
                String thisAlbumArt = musicCursor.getString(songArtURIColumn);
                songDataList.add(new SongData(thisId, thisTitle, thisArtist, thisDuration, thisAlbumArt));
            }
            while (musicCursor.moveToNext());
            musicCursor.close();
            /*Collections.sort(songDataList, new Comparator<SongData>() {
                public int compare(SongData a, SongData b) {
                    return a.getTitle().compareTo(b.getTitle());
                }
            });*/
            setupMailListAdapter(songDataList);
        }

    }
    private BaseRecyclerAdapterListener<SongData> mSongDataAdapterListener = new BaseRecyclerAdapterListener<SongData>() {
        @Override
        public void onClickItem(SongData data) {
           // iSongsView.showMessage(data.getTitle());
        }
    };
    private void setupMailListAdapter(List<SongData> songDataList){
        if (mSongsAdapter == null) {
            mSongsAdapter = new SongsListAdapter(songDataList, mSongDataAdapterListener);
            iSongsView.setSongListAdapter(mSongsAdapter);
        }else {
            mSongsAdapter.resetItems(songDataList);
        }
    }


}

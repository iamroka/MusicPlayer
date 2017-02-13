package arcanine.rokaplay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arcanine.rokaplay.R;
import arcanine.rokaplay.adapter.listener.BaseRecyclerAdapterListener;
import arcanine.rokaplay.model.SongData;
import arcanine.rokaplay.adapter.viewholder.SongViewHolder;

/**
 * Created by Rohan on 8/17/2016.
 */
public class SongsListAdapter extends BaseRecyclerAdapter<SongData,SongViewHolder> {
    private ArrayList<SongData> songDataList;
    // Caution: we are not supposed to bring context inside the adapter at any cause;
    private BaseRecyclerAdapterListener<SongData> listener;

    public SongsListAdapter(List<SongData> data,BaseRecyclerAdapterListener<SongData> listener) {
        super(data);
        this.listener=listener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_songs, parent, false),listener);
    }
}

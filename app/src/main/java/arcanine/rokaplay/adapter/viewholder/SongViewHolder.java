package arcanine.rokaplay.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import arcanine.rokaplay.R;
import arcanine.rokaplay.adapter.listener.BaseRecyclerAdapterListener;
import arcanine.rokaplay.model.SongData;

/**
 * Created by rohan on 8/10/2016.
 */
public class SongViewHolder extends BaseViewHolder<SongData> implements View.OnClickListener {
    private ImageView songArt, songOptions;
    private TextView songTitle, songArtist;
    private BaseRecyclerAdapterListener<SongData> listener;
    public SongViewHolder(View itemView,BaseRecyclerAdapterListener<SongData> listener) {
        super(itemView);
        this.listener=listener;
        bindHolder();

    }

    private void bindHolder() {
        songArt = (ImageView) itemView.findViewById(R.id.song_art);
        songOptions = (ImageView) itemView.findViewById(R.id.songs_options);
        songTitle = (TextView) itemView.findViewById(R.id.song_name);
        songArtist = (TextView) itemView.findViewById(R.id.song_artist);
    }

    @Override
    void populateData(SongData data) {
        songTitle.setText(data.getTitle());
        //Glide.with(itemView.getContext()).load(data.getAlbumArt()).into(songArt);
        songArtist.setText(data.getArtist());
        songOptions.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.songs_options:
                //Toast.makeText(itemView.getContext(), data.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

package arcanine.rokaplay.view.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import arcanine.rokaplay.R;
import arcanine.rokaplay.service.MusicService;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends BottomSheetDialogFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    View rootView;
    private MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;
    ViewPager currentSongPager;
    LinearLayout topMiniBar;
    ImageView miniBarPlaybackAction, mediaNextAction, mediaPreviousAction, mediaPlayPauseAcion;

    public NowPlayingFragment() {
        // Required empty public constructor
    }
    /*private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_now_playing, container, false);
        initViews();
        initListners();

        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.media_previous_action: {
                Toast.makeText(getContext(), "Previous", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.media_pause_or_play_action: {
                Toast.makeText(getContext(), "play or pause", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.media_next_action: {
                Toast.makeText(getContext(), "next", Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    public void initViews() {
        currentSongPager = (ViewPager) rootView.findViewById(R.id.song_art_viewpager);
        topMiniBar = (LinearLayout) rootView.findViewById(R.id.media_control_minibar);
        mediaPreviousAction = (ImageView) rootView.findViewById(R.id.media_previous_action);
        mediaPlayPauseAcion = (ImageView) rootView.findViewById(R.id.media_pause_or_play_action);
        mediaNextAction = (ImageView) rootView.findViewById(R.id.media_next_action);
        registerForContextMenu(mediaPlayPauseAcion);

    }

    public void initListners() {
        currentSongPager.addOnPageChangeListener(this);
        topMiniBar.setOnClickListener(this);
        mediaPreviousAction.setOnClickListener(this);
        //mediaPlayPauseAcion.setOnClickListener(this);
        mediaNextAction.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    /*@Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_now_playing, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.activity_library_drawer, menu);
    }
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list
            //musicSrv.setList(songList);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };
}


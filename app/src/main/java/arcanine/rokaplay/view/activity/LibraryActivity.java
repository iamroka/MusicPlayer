package arcanine.rokaplay.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import arcanine.rokaplay.adapter.LibraryPagerAdapter;
import arcanine.rokaplay.R;

public class LibraryActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    //The layout behind the bottomsheet
    RelativeLayout libraryLayout;
    //This is for our botomsheet
    RelativeLayout bottomsheet;
    LinearLayout topMiniBar;
    ImageView miniBarPlaybackAction;
    private BottomSheetBehavior mBottomSheetBehavior;
    //This is our viewpager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        bindActivity();

    }

    private void bindActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        libraryLayout = (RelativeLayout) findViewById(R.id.library_layout);
        //Initializing the tablayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
/*        tabLayout.addTab(tabLayout.newTab().setText("Songs"));
        tabLayout.addTab(tabLayout.newTab().setText("Albums"));
        tabLayout.addTab(tabLayout.newTab().setText("Artists"));
        tabLayout.addTab(tabLayout.newTab().setText("Playlists"));
        tabLayout.addTab(tabLayout.newTab().setText("Generes"));*/
        //// TODO: 6/24/2016 replace setText() with setIcon()
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Initializing the viewpager
        viewPager = (ViewPager) findViewById(R.id.pager);
        LibraryPagerAdapter adapter = new LibraryPagerAdapter(getSupportFragmentManager());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);

        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initializing the bottomsheet
        bottomsheet = (RelativeLayout) findViewById(R.id.bottomsheet_layout);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);
        mBottomSheetBehavior.setPeekHeight((int) getCodeSnippet().convertDpToPixel(72, getActivity()));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        //May be required To disable the layout behind the bottomsheet

        /*mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(mBottomSheetBehavior.getState()==BottomSheetBehavior.STATE_COLLAPSED){
                    libraryLayout.setClickable(true);
                }else {
                    libraryLayout.setClickable(false);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });*/
        topMiniBar = (LinearLayout) bottomsheet.findViewById(R.id.media_control_minibar);
        topMiniBar.setOnClickListener(this);
        miniBarPlaybackAction = (ImageView) bottomsheet.findViewById(R.id.current_media_pause_or_play);
        miniBarPlaybackAction.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.library, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {
            Toast.makeText(LibraryActivity.this, "Search", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_library) {
            // Handle the camera action
        } else if (id == R.id.nav_equalizer) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(LibraryActivity.this, AppSettings.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {

        }

        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.media_control_minibar: {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            }
            case R.id.current_media_pause_or_play: {
                Toast.makeText(this, "minibar play", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

}

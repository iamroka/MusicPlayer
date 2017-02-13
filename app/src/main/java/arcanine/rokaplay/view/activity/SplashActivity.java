package arcanine.rokaplay.view.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import arcanine.rokaplay.R;
import arcanine.rokaplay.common.Constants;
import arcanine.rokaplay.model.SongData;

public class SplashActivity extends AppCompatActivity {
    // to get the list of songs
    private ArrayList<SongData> songDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        songDataList = new ArrayList<SongData>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                checkPerissions();
                /*getSongList();
                Collections.sort(songDataList, new Comparator<SongData>(){
                    public int compare(SongData a, SongData b){
                        return a.getTitle().compareTo(b.getTitle());
                    }
                });
                Toast.makeText(SplashActivity.this, "The songDataList size is"+songDataList.size(), Toast.LENGTH_SHORT).show();*/
                //// TODO: 6/25/2016 convert the song model into parsalble and then add the songlist into a bundle and pass it to the next activity
                startActivity(new Intent(SplashActivity.this, LibraryActivity.class));
                finish();
            }
        });
    }

    public void getSongList() {
        //retrieve song info
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, MediaStore.Audio.Media.IS_MUSIC, null, null);
        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int durationColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
        /*    int songArtURIColumn = musicCursor.getColumnIndex
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
            Collections.sort(songDataList, new Comparator<SongData>() {
                public int compare(SongData a, SongData b) {
                    return a.getTitle().compareTo(b.getTitle());
                }
            });
            Toast.makeText(SplashActivity.this, "The songDataList size is"+songDataList.size(), Toast.LENGTH_SHORT).show();
        }

    }

    private void checkPerissions() {
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.PermissionRequestCodes.EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } /*else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } */ else {
                //just request the permission
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.PermissionRequestCodes.EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }

         /*   SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,true);
            editor.commit();*/


        } else {
            //You already have the permission, just go ahead.
            getSongList();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.PermissionRequestCodes.EXTERNAL_STORAGE_PERMISSION_CONSTANT && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getSongList();
        }
    }
}

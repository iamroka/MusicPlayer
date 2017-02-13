package arcanine.rokaplay.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;


import com.belladati.httpclientandroidlib.entity.mime.content.ByteArrayBody;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import arcanine.library.ExceptionTracker;
import arcanine.library.Log;
import arcanine.rokaplay.R;
import arcanine.rokaplay.common.RokaPlay;


/**
 * Created by Rohan on 06/12/16.
 */
public class CodeSnippet {

    //Time AM or PM
    private String PM = "PM";
    private String AM = "AM";
    private String TAG = getClass().getSimpleName();
    private Context mContext;

    public CodeSnippet(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Checking the internet connectivity
     *
     * @return true if the connection is available otherwise false
     */
    public boolean hasNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    public ByteArrayBody getCompressedImage(String path) {

        Bitmap imageBitmap = getBitmap(path);

        if (imageBitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteArrayBody bab;
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] data = bos.toByteArray();
            bab = new ByteArrayBody(data, "" + System.currentTimeMillis() + "displayPicture.jpg");
            return bab;
        }
        return null;
    }

    public ByteArrayBody getCompressedImage(File file) {
        Bitmap imageBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (imageBitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteArrayBody bab;
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] data = bos.toByteArray();
            bab = new ByteArrayBody(data, "" + System.currentTimeMillis() + " displayPicture.jpg");
            return bab;
        }
        return null;
    }

    private Bitmap getBitmap(final String imagePath) {

        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext).load("file://" + imagePath).asBitmap().into(500, 500).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Check which type of connection the device is connected to
     */
    public String whichNetworkConnection() {

        ConnectivityManager cm = RokaPlay.getInstance().getConnectivityManager();

        NetworkInfo wifiNetwork = cm.getActiveNetworkInfo();
        if (wifiNetwork != null && wifiNetwork.isConnectedOrConnecting()) {
            return mContext.getString(R.string.network_wifi);
        }

        NetworkInfo mobileNetwork = cm.getActiveNetworkInfo();
        if (mobileNetwork != null && mobileNetwork.isConnectedOrConnecting()) {
            return mContext.getString(R.string.network_mobile);
        }

        return null;
    }

    public String getNetworkType() {
        ConnectivityManager cm = RokaPlay.getInstance().getConnectivityManager();
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork && activeNetwork.isConnectedOrConnecting()) {
            return activeNetwork.getTypeName();
        }
        return null;
    }

    public boolean isTodayLieInBetween(String str1, String str2) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String todayStr = formatter.format(Calendar.getInstance().getTime());
            Date todayDate = formatter.parse(todayStr);
            Date date1 = formatter.parse(str1);
            Date date2 = formatter.parse(str2);

            return date1.compareTo(todayDate) <= 0 && date2.compareTo(todayDate) >= 0;
        } catch (Exception e) {
            ExceptionTracker.track(e);
        }
        return false;
    }

    public Calendar getCalendarTime(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(time));
            return calendar;
        } catch (Exception e) {
            ExceptionTracker.track(e);
        }
        return null;
    }

    public String getCalendarTime(Calendar time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            return formatter.format(time);
        } catch (Exception e) {
            ExceptionTracker.track(e);
        }
        return null;
    }

    public Calendar getCalendarForYear(String time) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(time));
            return calendar;
        } catch (Exception e) {
            ExceptionTracker.track(e);
        }
        return null;
    }

    public Calendar getCalendarToStandard(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(time));
            return calendar;
        } catch (Exception e) {
            ExceptionTracker.track(e);
        }
        return null;
    }

    public Calendar getCalendarWithTimeOnly(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(time));
            return calendar;
        } catch (Exception e) {
            ExceptionTracker.track(e);
            SimpleDateFormat formatter = new SimpleDateFormat("hh : mm aa", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(formatter.parse(time));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            return calendar;
        }
    }

    public String getDayOfMonthMonthAndYear(Calendar calendar) {
        //TODO mention
        String dateString = "";
        dateString = calendar.get(Calendar.DAY_OF_MONTH) + " " + monthNameFromInt(calendar.get(Calendar.MONTH)) + ", " + calendar.get(Calendar.YEAR);
        return dateString;
    }

    public String getDayOfMonthMonthAndYearStd(Calendar calendar) {
        String dateString = "";
        int month = calendar.get(Calendar.MONTH) + 1;
        DecimalFormat formatter = new DecimalFormat("00");
        dateString = formatter.format(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + formatter.format(month) + "-" + calendar.get(Calendar.YEAR);
        return dateString;
    }

    private String monthNameFromInt(int monthInt) {
        String month = "";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (monthInt >= 0 && monthInt <= 11) {
            month = months[monthInt];
        }
        return month.substring(0, 4);
    }

    public String getPastTimerString(Calendar calendar) {
        long time = System.currentTimeMillis() - calendar.getTimeInMillis();
        long mins = time / 60000;
        if (mins > 59L) {
            long hours = mins / 60;
            if (hours > 24) {
                long days = hours / 24;
                if (days > 1) {
                    return days + " days";
                } else {
                    return days + " day";
                }
            } else {
                return hours + " hours ago";
            }
        } else {
            return "less than a minute";
        }
    }

    public Date getTimeFromMilisecond(long timeStamp) {
        return new Date(timeStamp * 1000);
    }

    public String getOrdinaryTime(Time time) {
        if (time.hour > 12) {
            return formatTime(time.hour - 12) + ":" + formatTime(time.minute)
                    + " " + getAMorPM(time);
        }

        return (time.hour == 0 ? String.valueOf(12) : formatTime(time.hour)) + ":"
                + formatTime(time.minute) + " " + getAMorPM(time);
    }

    public String getOrdinaryDate(Calendar calendar) {

        int month = calendar.get(Calendar.MONTH) + 1;
        DecimalFormat formatter = new DecimalFormat("00");
        Log.d(TAG, "getOrdinaryDate : " + formatter.format(month));
        return formatter.format(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + formatter.format(month) + "/" + calendar.get(Calendar.YEAR);
    }

    public String getOrdinaryTime(Calendar calendar) {

        int min = calendar.get(Calendar.MINUTE);
        //Log.d(TAG,"hours :"+calendar.get(Calendar.HOUR_OF_DAY));
        String meridian = "AM";
        if (calendar.get(Calendar.HOUR_OF_DAY) > 11) {
            meridian = "PM";
        }
        DecimalFormat formatter = new DecimalFormat("00");
        return formatter.format(calendar.get(Calendar.HOUR)) + ":" + formatter.format(min) + " " + meridian;
    }

    public String getOrdinaryDateWithFipe(Time date) {

        return date.monthDay + " | " + date.month + " | " + date.year;
    }

    private String formatTime(int time) {
        if (String.valueOf(time).length() < 2)
            return "0" + time;
        else
            return String.valueOf(time);
    }

    private String getAMorPM(Time time) {
        if (time.hour > 11) {
            return PM;
        } else
            return AM;
    }

    public boolean checkPlayServices(Context context, OnGooglePlayServiceListener googlePlayServiceListener) {
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (status != ConnectionResult.SUCCESS) {
            if (GoogleApiAvailability.getInstance().isUserResolvableError(status)) {
                showGooglePlayDialog(context, googlePlayServiceListener);
            } else {
                googlePlayServiceListener.onCancelServiceInstallation();
            }
            return false;
        }
        return true;
    }

    private void showGooglePlayDialog(final Context context, final OnGooglePlayServiceListener googlePlayServiceListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Get Google Play Service");
        builder.setMessage("This app won't run without Google Play Services, which are missing from your phone");
        builder.setPositiveButton("Get Google Play Service",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        googlePlayServiceListener.onInstallingService();
                        context.startActivity(new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?"
                                        + "id=com.google.android.gms")));
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                googlePlayServiceListener.onCancelServiceInstallation();
            }
        });
        builder.setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private Intent getSettingsIntent(String settings) {
        return new Intent(settings);
    }

    private void startActivityBySettings(Context context, String settings) {
        context.startActivity(getSettingsIntent(settings));
    }

    private void startActivityBySettings(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public void showGpsSettings(Context context) {
        startActivityBySettings(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    public void showNetworkSettings() {
        Intent chooserIntent = Intent.createChooser(getSettingsIntent(Settings.ACTION_DATA_ROAMING_SETTINGS),
                "Complete action using");
        List<Intent> networkIntents = new ArrayList<>();
        networkIntents.add(getSettingsIntent(Settings.ACTION_WIFI_SETTINGS));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, networkIntents.toArray(new Parcelable[]{}));
        startActivityBySettings(mContext, chooserIntent);
    }

    public boolean isSpecifiedDelay(long exisingTime, long specifiedDelay) {
        return specifiedDelay >= (Calendar.getInstance().getTimeInMillis() - exisingTime);
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    public void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.showSoftInputFromInputMethod(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    public boolean isNull(Object object) {
        return null == object || object.toString().compareTo("null") == 0;
    }

    public final boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean isAboveMarshmallow() {
        int currentapiVersion = Build.VERSION.SDK_INT;
        // Do something for marshmallow and above versions
// do something for phones running an SDK before marshmallow
        return currentapiVersion >= Build.VERSION_CODES.M;
    }

    public boolean isAboveLollipop() {
        int currentapiVersion = Build.VERSION.SDK_INT;
        // Do something for marshmallow and above versions
// do something for phones running an SDK before marshmallow
        return currentapiVersion >= Build.VERSION_CODES.LOLLIPOP;
    }


    private interface OnGooglePlayServiceListener {
        void onInstallingService();

        void onCancelServiceInstallation();
    }
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public  float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public  float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
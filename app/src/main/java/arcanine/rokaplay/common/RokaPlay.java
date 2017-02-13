package arcanine.rokaplay.common;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Guru karthik on 05-12-2016.
 */

public class RokaPlay extends Application {

    private static RokaPlay mAppController;

    public static RokaPlay getInstance() {
        return mAppController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppController = this;
    }

    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}

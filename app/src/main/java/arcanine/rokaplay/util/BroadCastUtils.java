package arcanine.rokaplay.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import arcanine.rokaplay.common.Constants;


public class BroadCastUtils {

    private Context mContext;

    public BroadCastUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void doBroadcast(Bundle result) {
        Intent intent = new Intent(Constants.BundleKey.BROADCAST_MESSAGE);
        intent.putExtras(result);
        mContext.sendBroadcast(intent);
    }
}

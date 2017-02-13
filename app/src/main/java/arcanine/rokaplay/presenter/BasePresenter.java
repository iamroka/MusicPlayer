package arcanine.rokaplay.presenter;

import android.content.Intent;

import arcanine.rokaplay.presenter.ipresenter.IPresenter;
import arcanine.rokaplay.view.iview.IView;


/**
 * Created by Guru karthik on 06-12-2016.
 */

abstract class BasePresenter implements IPresenter {

    protected String TAG = getClass().getSimpleName();

    private IView iView;

    BasePresenter(IView iView) {
        this.iView = iView;
        iView.bindPresenter(this);
    }

    @Override
    public void onStartPresenter() {

    }

    @Override
    public void onStopPresenter() {

    }

    @Override
    public void onPausePresenter() {

    }

    @Override
    public void onResumePresenter() {

    }

    @Override
    public void onDestroyPresenter() {

    }

    @Override
    public void onActivityResultPresenter(int requestCode, int resultCode, Intent data) {

    }
}

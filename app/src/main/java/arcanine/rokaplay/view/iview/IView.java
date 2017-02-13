package arcanine.rokaplay.view.iview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import arcanine.library.CustomException;
import arcanine.rokaplay.presenter.ipresenter.IPresenter;
import arcanine.rokaplay.util.CodeSnippet;


/**
 * Created by guru on 6/29/2016.
 */
public interface IView {

    void showMessage(String message);

    void showMessage(int resId);

    void showMessage(CustomException e);

    void showProgressbar();

    void dismissProgressbar();

    FragmentActivity getActivity();

    void showSnackBar(String message);

    void showSnackBar(@NonNull View view, String message);

    void showNetworkMessage();

    CodeSnippet getCodeSnippet();

    void bindPresenter(IPresenter iPresenter);


}

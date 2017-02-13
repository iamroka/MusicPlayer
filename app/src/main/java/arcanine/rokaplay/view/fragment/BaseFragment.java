package arcanine.rokaplay.view.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import arcanine.library.CustomException;
import arcanine.rokaplay.presenter.ipresenter.IPresenter;
import arcanine.rokaplay.util.CodeSnippet;
import arcanine.rokaplay.view.iview.IView;


/**
 * Created by guru on 6/29/2016.
 */
public abstract class BaseFragment extends Fragment implements IView {

    protected String TAG = getClass().getSimpleName();

    @Override
    public void bindPresenter(IPresenter iPresenter) {
        // nothing to implement here!
    }

    @Override
    public void showMessage(String message) {
        ((IView)getActivity()).showMessage(message);
    }

    @Override
    public void showMessage(int resId) {
        ((IView)getActivity()).showMessage(resId);
    }

    @Override
    public void showMessage(CustomException e) {
        ((IView)getActivity()).showMessage(e);
    }

    @Override
    public void showProgressbar() {
        ((IView)getActivity()).showProgressbar();
    }

    @Override
    public void dismissProgressbar() {
        ((IView)getActivity()).dismissProgressbar();
    }
    @Override
    public void showSnackBar(String message) {
        ((IView)getActivity()).showSnackBar(message);
    }

    @Override
    public void showNetworkMessage() {
        ((IView)getActivity()).showNetworkMessage();
    }

    @Override
    public CodeSnippet getCodeSnippet() {
        return ((IView)getActivity()).getCodeSnippet();
    }

    @Override
    public void showSnackBar(@NonNull View view, String message) {
        ((IView)getActivity()).showSnackBar(view,message);
    }
}

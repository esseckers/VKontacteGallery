package com.vkontactegallery.view.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.fsm.transit.core.ITransitManager;
import com.vkontactegallery.tranzit.FragmentAction;
import com.vkontactegallery.view.annotation.Layout;

import butterknife.ButterKnife;

public abstract class AbstractFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayout(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    protected void initView(View view) {

    }

    protected int getViewLayout() {
        Layout layout = this.getClass().getAnnotation(Layout.class);
        return layout != null ? layout.value() : 0;
    }

    /**
     * special fragment manager, do all switch use this object.
     *
     * @return instance of {@link ITransitManager}
     */
    public ITransitManager<FragmentAction> getTransitManager() {
        if (getActivity() != null) {
            return ((AbstractActivity) getActivity()).getTransitManager();
        } else {
            return null;
        }
    }

    /**
     * get Error Handler for handling error
     */
    public ErrorHandler getErrorHandler() {
        if (getActivity() != null) {
            return ((AbstractActivity) getActivity()).getErrorHandler();
        } else {
            return null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity().getCurrentFocus() != null) {
            Object inputMethodManager = getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                ((InputMethodManager) inputMethodManager).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void hideVirtualKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public abstract class SimpleRemoteServiceCallback<T> implements IRemoteServiceCallback<T> {

        @Override
        public void onStartTask() {
            errorHandler = getErrorHandler();
            if (errorHandler != null) {
                errorHandler.manageLoadDialog(true);
            }
        }

        @Override
        public void onFailure(Fail fail) {
            if (errorHandler != null) {
                errorHandler.handleFail(fail);
            }
        }

        @Override
        public void onSuccess(T result) {
        }

        @Override
        public void onSuccess() {
        }

        @Override
        public void onServerError(String error) {
            if (errorHandler != null) {
                errorHandler.handleError(error);
            }
        }

        @Override
        public void onFinishTask() {
            if (errorHandler != null) {
                errorHandler.manageLoadDialog(false);
            }
        }
    }
}

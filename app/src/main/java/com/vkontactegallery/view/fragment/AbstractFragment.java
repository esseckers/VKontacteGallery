package com.vkontactegallery.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fsm.transit.core.ITransitManager;
import com.redmadrobot.chronos.gui.fragment.ChronosFragment;
import com.vkontactegallery.database.DatabaseGateway;
import com.vkontactegallery.model.Album;
import com.vkontactegallery.remote.operations.AlbumOperation;
import com.vkontactegallery.transit.FragmentAction;
import com.vkontactegallery.view.activity.AbstractActivity;
import com.vkontactegallery.view.annotation.Layout;

import java.util.List;

import butterknife.ButterKnife;

public abstract class AbstractFragment extends ChronosFragment {

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
}

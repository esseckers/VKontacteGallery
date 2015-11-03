package com.vkontactegallery.tranzit;

import android.app.Activity;

import com.fsm.transit.core.AbstractTransitManager;
import com.fsm.transit.core.TransitData;
import com.fsm.transit.core.TransitResultData;
import com.vkontactegallery.view.fragment.AlbumsFragment;
import com.vkontactegallery.view.fragment.PhotosFragment;

public class MainTransitManager extends AbstractTransitManager<FragmentAction> {

    /**
     * Pass {@link Activity} in args
     *
     * @param activity {@link Activity}
     */
    public MainTransitManager(Activity activity) {
        super(activity);
    }

    {
        transitionsMap.put(new TransitData<FragmentAction>(AlbumsFragment.class, FragmentAction.PHOTOS), new TransitResultData<FragmentAction>(PhotosFragment.class, true));
    }
}

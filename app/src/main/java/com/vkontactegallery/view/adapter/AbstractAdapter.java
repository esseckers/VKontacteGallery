package com.vkontactegallery.view.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

public abstract class AbstractAdapter<T> extends ArrayAdapter<T> {

    protected int resource;

    public AbstractAdapter(Context context, int resource) {
        super(context, resource);
        this.resource = resource;
    }
}

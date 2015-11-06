package com.vkontactegallery.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vkontactegallery.R;
import com.vkontactegallery.model.Album;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AlbumsAdapter extends AbstractAdapter<Album> {

    public AlbumsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Album album = getItem(position);
        ImageLoader.getInstance().displayImage(album.getImgThumbLink(), holder.imgThumb);
        holder.tvCount.setText("" + album.getSize());
        return convertView;
    }

    static class Holder {

        @Bind(R.id.img_thumb)
        ImageView imgThumb;

        @Bind(R.id.tv_count)
        TextView tvCount;

        public Holder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}

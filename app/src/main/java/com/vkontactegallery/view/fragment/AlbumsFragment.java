package com.vkontactegallery.view.fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.vkontactegallery.R;
import com.vkontactegallery.TheApplication;
import com.vkontactegallery.Utils;
import com.vkontactegallery.database.DatabaseGateway;
import com.vkontactegallery.model.Album;
import com.vkontactegallery.remote.operations.AlbumOperation;
import com.vkontactegallery.view.adapter.AlbumsAdapter;
import com.vkontactegallery.view.annotation.Layout;

import java.util.List;

import butterknife.Bind;
import io.realm.Realm;

@Layout(R.layout.fragm_albums)
public class AlbumsFragment extends AbstractFragment {

    @Bind(R.id.img_grid)
    GridView lvAlbums;
    private String userId;
    private Realm realm;

    private AlbumsAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void initView(View view) {
        userId = getArguments().getString(Utils.USER_ID_BUNDLE_KEY, "");
        adapter = new AlbumsAdapter(getActivity(), R.layout.item_album);
        if (!userId.isEmpty()) {
            runOperation(new AlbumOperation(userId));
        }
    }

    public void onOperationFinished(AlbumOperation.Result result) {
        adapter.clear();
        lvAlbums.setAdapter(adapter);
        if (result.isSuccessful()) {
            List<Album> albums = result.getOutput().getData();
            adapter.addAll(albums);
        } else if (userId.equals(TheApplication.getInstance().getUserId())) {
            adapter.addAll(DatabaseGateway.getInstance().getAlbums(realm));
        } else {
            Toast.makeText(getActivity(), "Проверьте соединение с интернетом или у Вас нет доступа для просмотра его фотографий", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }
}

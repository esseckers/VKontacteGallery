package com.vkontactegallery.view.fragment;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.vkontactegallery.R;
import com.vkontactegallery.Utils;
import com.vkontactegallery.transit.FragmentAction;
import com.vkontactegallery.view.annotation.Layout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Ivan Danilov on 05.11.2015.
 */
@Layout(R.layout.fragm_login)
public class LoginFragment extends AbstractFragment {

    @Bind(R.id.et_user_id)
    EditText etUserId;


    @OnClick(R.id.btn_ok)
    public void enterClick() {
        String userId = etUserId.getText().toString();
        if (!userId.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putString(Utils.USER_ID_BUNDLE_KEY, userId);
            getTransitManager().switchFragment(LoginFragment.this, FragmentAction.ALBUMS, bundle);
        } else {
            Toast.makeText(getActivity(), "Пожалуйста, введите id пользователя", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_title)
    public void stub() {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.USER_ID_BUNDLE_KEY, "52156020");
        getTransitManager().switchFragment(LoginFragment.this, FragmentAction.ALBUMS, bundle);
    }
}

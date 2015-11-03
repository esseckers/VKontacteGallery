package com.vkontactegallery.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.servicesphere.R;
import com.servicesphere.model.Message;
import com.servicesphere.network.ISocketConnectionListener;
import com.servicesphere.network.SocketConnectionManager;
import com.servicesphere.view.adapter.ChatAdapter;
import com.servicesphere.view.annotation.Layout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Ivan Danilov on 02.11.2015.
 */
@Layout(R.layout.fragm_chat)
public class AlbumsFragment extends AbstractFragment {

    @Bind(R.id.lv_chat)
    ListView lvChat;

    @Bind(R.id.et_typed_msg)
    EditText etTypedMsg;

    private ChatAdapter adapter;
    private SocketConnectionManager manager = SocketConnectionManager.getInstance();

    @Override
    protected void initView(View view) {
        adapter = new ChatAdapter(getActivity(), R.layout.chat_item);
        lvChat.setAdapter(adapter);
    }

    @OnClick(R.id.btn_send)
    public void send() {
        Message message = new Message();
        message.setWayTypeMessage(0);
        message.setAvatarPath("");
        message.setMessage(etTypedMsg.getText().toString());
        adapter.add(message);
        lvChat.post(new Runnable() {
            @Override
            public void run() {
                lvChat.setSelection(adapter.getCount() - 1);
            }
        });
        manager.sendMessage(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SocketConnectionManager.getInstance().stopConnection();
    }

    @Override
    public void onResume() {
        super.onResume();
        manager.setListener(new ISocketConnectionListener() {
            @Override
            public void onConnectSuccess() {

            }

            @Override
            public void onConnectFailed(Throwable cause) {
                Log.e("TAG", cause.toString());
            }

            @Override
            public void onReceived(Message message) {
                message.setWayTypeMessage(1);
                message.setAvatarPath("");
                adapter.add(message);
                lvChat.post(new Runnable() {
                    @Override
                    public void run() {
                        lvChat.setSelection(adapter.getCount() - 1);
                    }
                });
            }
        });
        manager.connect();
    }
}

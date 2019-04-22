package com.achers.ascmake.slidemenu.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achers.ascmake.R;
import com.achers.ascmake.slidemenu.adapter.FriendAdapter;
import com.achers.ascmake.slidemenu.adapter.FriendBean;
import com.achers.ascmake.slidemenu.adapter.TabTwoAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 好友列表
 * Created by yangjing on 17-6-7.
 */

public class FriendFragment extends Fragment {

    public static FriendFragment newInstance() {
        Bundle args = new Bundle();
        FriendFragment fragment = new FriendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //ui
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_friend, container, false);
        List<FriendBean> list =new  ArrayList<FriendBean>();
        for (int i =0 ;i<40;i++){
            FriendBean friendBean =new FriendBean();
            friendBean.setName("对象"+i);
            friendBean.setSelected(false);
            list.add(friendBean);
        }
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_friend);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        FriendAdapter friendAdapter = new FriendAdapter(mContext,list,recyclerView);
        recyclerView.setAdapter(friendAdapter);

        friendAdapter.setPosition(39);
        recyclerView.scrollToPosition(39);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}

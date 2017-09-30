package com.kahfi.arief.belajarandroidlocalbroadcastmanager.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kahfi.arief.belajarandroidlocalbroadcastmanager.R;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.async.AsinkronSelectData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arief on 30/09/17.
 */

public class FragmentList extends Fragment {


    @BindView(R.id.recView)RecyclerView recyclerView;

    private BroadcastReceiver broadcastReceiver;

    private LocalBroadcastManager localBroadcastManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("INFO","onCreateFragmentList()");
        //Daftarkan LocalBroadCastManager disini....
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("onCreate_broadcastR","before loadDataFromDatabase()");
                loadDataFromDatabase();
                Log.e("onCreate_broadcastR","after loadDataFromDatabase()");
            }
        };
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.registerReceiver(broadcastReceiver,new IntentFilter("com.kahfi.arief.belajarandroidlocalbroadcastmanager.receivers"));

        Log.e("INFO","succesfully registerReceiver()");
    }


    private void loadDataFromDatabase(){
        new AsinkronSelectData(getActivity(),recyclerView).execute();
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.e("INFO","onStop()");
        //Log.e("INFO","after localBroadcastManager unregister");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("INFO","onDestroy()");
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        Log.e("onDestroy()","after localBroadcastManager unregister");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("INFO","onCreateViewFragmentList()");

        View v = inflater.inflate(R.layout.fragment_list,container,false);

        ButterKnife.bind(this,v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("INFO","onActivityCreatedFragmentList()");
        loadDataFromDatabase();
    }
}

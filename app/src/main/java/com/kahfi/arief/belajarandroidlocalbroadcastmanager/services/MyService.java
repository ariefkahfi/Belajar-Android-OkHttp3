package com.kahfi.arief.belajarandroidlocalbroadcastmanager.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice.HttpServiceCustomerDAO;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.model.Customer;

import java.io.IOException;

/**
 * Created by arief on 30/09/17.
 */

public class MyService extends IntentService {


    private HttpServiceCustomerDAO dao;


    public MyService(){
        super("MyService Class");
        dao = new HttpServiceCustomerDAO();
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("info","onHandleIntentMyService()");

         Bundle b = intent.getExtras();

        try{


        if(b.getString("flag").trim().equals("insert")){
            Customer c = new Customer();

            c.setId(b.getString("id"));

            Log.e("idInsertService",c.getId());

            c.setNama(b.getString("nama"));
            c.setProduk(b.getString("produk"));

                dao.save(c);

        }else if (b.getString("flag").trim().equals("delete")){
            Log.e("idDeleteService",b.getString("id"));
            dao.delete(b.getString("id"));
        }else if (b.getString("flag").trim().equals("update")){
            Customer c = new Customer();

            c.setId(b.getString("id"));
            c.setNama(b.getString("nama"));
            c.setProduk(b.getString("produk"));

            Log.e("idOldUpdateService",c.getId());


            dao.update(
                   c
            );
        }

        }catch (Exception ex){
            ex.printStackTrace();
            Log.e("ErrorService",ex.getMessage());
        }

        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(new Intent("com.kahfi.arief.belajarandroidlocalbroadcastmanager.receivers"));
    }
}

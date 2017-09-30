package com.kahfi.arief.belajarandroidlocalbroadcastmanager.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice.HttpServiceProduk;

import java.io.IOException;
import java.util.List;

/**
 * Created by arief on 30/09/17.
 */

public class AsinkronSelectProduk extends AsyncTask<String,Integer,List<String>> {


    private Context context;
    private HttpServiceProduk produkDAO;
    private Spinner spinnerProduk;


    public AsinkronSelectProduk(Context context,Spinner spinner){
        this.context=context;
        spinnerProduk = spinner;
        produkDAO = new HttpServiceProduk();
    }


    @Override
    protected List<String> doInBackground(String... strings) {
        try {
            return produkDAO.produkList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> strings) {

        if(strings == null){
           Log.e("list","NULL");
        }else{
           Log.e("list","TIDAK NULL");
        }

       for (String s : strings){
           Log.e("itemProduk",s);
       }

       spinnerProduk.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,strings));
    }
}

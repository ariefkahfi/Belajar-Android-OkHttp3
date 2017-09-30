package com.kahfi.arief.belajarandroidlocalbroadcastmanager.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kahfi.arief.belajarandroidlocalbroadcastmanager.adapters.CustomerAdapter;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice.HttpServiceCustomerDAO;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.model.Customer;

import java.io.IOException;
import java.util.List;

/**
 * Created by arief on 30/09/17.
 */

public class AsinkronSelectData extends AsyncTask<String,Integer,List<Customer>> {

    private Context context;
    private HttpServiceCustomerDAO dao;
    private RecyclerView recyclerView;

    public AsinkronSelectData(Context context,RecyclerView recyclerView){
        this.context=context;
        this.recyclerView=recyclerView;
        dao = new HttpServiceCustomerDAO();
    }

    @Override
    protected List<Customer> doInBackground(String... strings) {
        try {
            return dao.getAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Customer> customers) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CustomerAdapter(context,customers));
    }
}

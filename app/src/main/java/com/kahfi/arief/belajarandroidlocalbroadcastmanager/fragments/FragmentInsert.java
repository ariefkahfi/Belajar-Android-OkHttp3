package com.kahfi.arief.belajarandroidlocalbroadcastmanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kahfi.arief.belajarandroidlocalbroadcastmanager.R;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.async.AsinkronSelectProduk;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice.HttpServiceCustomerDAO;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.model.Customer;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.services.MyService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by arief on 30/09/17.
 */

public class FragmentInsert extends Fragment {


    @BindView(R.id.editNama)EditText nama;
    @BindView(R.id.spinProduk)Spinner spinProduk;
    @BindView(R.id.insertData)Button insertData;

    private HttpServiceCustomerDAO dao = new HttpServiceCustomerDAO();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("INFO","onCreateFragmentInsert()");
    }


    @OnClick(R.id.insertData)
    public void clickInsertData(View v){
       Customer c = new Customer(nama.getText().toString().trim(),spinProduk.getSelectedItem().toString().trim());
       startServiceInsertData(c);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("INFO","onStopInsert()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("INFO","onDestroyInsert()");
    }




    private void  startServiceInsertData(Customer c){

        if(c.getProduk().trim().equals("") || c.getProduk() == null || c.getNama().trim().equals("") || c.getNama() == null){
            Toast.makeText(getActivity(), "Masih ada data yang kosong", Toast.LENGTH_SHORT).show();
        }else{
                Intent intent = new Intent(getActivity(), MyService.class);

                Bundle b = new Bundle();

                b.putString("flag","insert");

                b.putString("id",c.getId());

                Log.e("idInsertFragment",b.getString("id"));

                b.putString("nama",c.getNama());
                b.putString("produk",c.getProduk());

                intent.putExtras(b);


                getActivity().startService(intent);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("INFO","onCreateViewFragmentInsert()");
        View v = inflater.inflate(R.layout.fragment_insert,container,false);

        ButterKnife.bind(this,v);


        return v;
    }


    private void setUpProduk(){
        new AsinkronSelectProduk(getActivity(),spinProduk).execute();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("INFO","onActivityCreatedFragmentInsert()");
        setUpProduk();
    }
}

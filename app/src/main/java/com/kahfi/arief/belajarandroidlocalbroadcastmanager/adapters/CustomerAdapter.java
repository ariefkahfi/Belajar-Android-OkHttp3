package com.kahfi.arief.belajarandroidlocalbroadcastmanager.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kahfi.arief.belajarandroidlocalbroadcastmanager.R;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.async.AsinkronSelectProduk;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.model.Customer;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.services.MyService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arief on 30/09/17.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder>  {

    private Context context;

    private List<Customer> customerList;

    //private String selectedItem = "";

    public CustomerAdapter(){}

    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @Override
    public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomerHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ui,parent,false)
        );
    }


    private void configForData(String id ,
                               String oldNama ,
                               TextView updateId ,
                               EditText updateNama ,
                               Spinner updateProduk){
        updateId.setText(id);
        updateNama.setText(oldNama);
        new AsinkronSelectProduk(context,updateProduk).execute();
    }

    private void buatAlertDialogUpdate(String id , String oldNama , String oldProduk){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);


        View dialogView = LayoutInflater.from(context).inflate(R.layout.d_update,null);


        dialog.setView(dialogView);

        final TextView updateId = dialogView.findViewById(R.id.updateId);
        final EditText updateNama =dialogView.findViewById(R.id.updateNama);
        final Spinner updateProduk = dialogView.findViewById(R.id.spinUpdate);

        configForData(id,oldNama,updateId,updateNama,updateProduk);



        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (updateNama.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Masih ada data yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Intent serviceUpdate = new Intent(context, MyService.class);

                    Bundle b =  new Bundle();

                    b.putString("flag","update");

                    b.putString("id",updateId.getText().toString().trim());
                    b.putString("nama",updateNama.getText().toString().trim());
                    b.putString("produk", updateProduk.getSelectedItem().toString().trim());

                    Log.e("idOld",b.getString("id"));

                    serviceUpdate.putExtras(b);

                    context.startService(serviceUpdate);
                }
            }
        });

        dialog.setNegativeButton("no",null);


        dialog.show();
    }

    private void buatAlertDialogDelete(final String id){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);


        dialog.setTitle("Dialog Delete ");

        dialog.setMessage(
                "Are you sure to delete this customer : " + id + " ? "
        );


        dialog.setPositiveButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent serviceDelete = new Intent(context,MyService.class);

                Bundle b = new Bundle();

                b.putString("flag","delete");

                b.putString("id",id);


                Log.e("deleteIdDialog",b.getString("id"));

                serviceDelete.putExtras(b);

                context.startService(serviceDelete);
            }
        });

        dialog.setNegativeButton("cancel",null);

        dialog.show();
    }

    @Override
    public void onBindViewHolder(final CustomerHolder holder, int position) {
        final Customer customer = customerList.get(position);

        holder.tampilNama.setText(customer.getNama());
        holder.tampilProduk.setText(customer.getProduk());


        holder.deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("deleteOnClick",customer.getId());

                buatAlertDialogDelete(customer.getId());
            }
        });

        holder.updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buatAlertDialogUpdate(customer.getId(),customer.getNama(),customer.getProduk());
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    class CustomerHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tampilNama)TextView tampilNama;
        @BindView(R.id.tampilProduk)TextView tampilProduk;
        @BindView(R.id.update)Button updateData;
        @BindView(R.id.delete)Button deleteData;

        public CustomerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

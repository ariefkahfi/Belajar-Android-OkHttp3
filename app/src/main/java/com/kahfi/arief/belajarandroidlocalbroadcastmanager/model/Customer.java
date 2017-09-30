package com.kahfi.arief.belajarandroidlocalbroadcastmanager.model;

import java.util.UUID;

/**
 * Created by arief on 30/09/17.
 */

public class Customer {
    private String id;
    private String nama;
    private String produk;

    public Customer(){}

    public Customer(String nama,String produk){
        this.id= UUID.randomUUID().toString();
        this.nama=nama;
        this.produk=produk;
    }

    public Customer(String id , String nama ,String produk){
        this.id=id;
        this.nama=nama;
        this.produk=produk;
    }

    public void setAutoId(){
        this.id=UUID.randomUUID().toString();
    }
    public void setId(String id ){this.id=id;}
    public String getId(){
        return id;
    }



    public void setNama(String nama){
        this.nama=nama;
    }
    public String getNama(){return nama;}

    public String getProduk() {
        return produk;
    }
    public void setProduk(String produk) {
        this.produk = produk;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", produk='" + produk + '\'' +
                '}';
    }
}

package com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by arief on 30/09/17.
 */

public class HttpServiceProduk extends AbstractOkHttpCreator {



    public List<String> produkList() throws IOException {

        List<String> produks = new ArrayList<>();

        Request request = new Request.Builder()
                .url("http://192.168.1.100/android-broadcast1/back-end/selectProduk.php")
                .get()
                .build();

        Response response = createOkHttpClientInstance().newCall(request).execute();

        String json = response.body().string();

        JsonElement element = new JsonParser().parse(json);

        JsonArray produk = element.getAsJsonObject().getAsJsonArray("produk");



        for(JsonElement el : produk){
           String nama = el.getAsString();

            Log.e("infoProduk",nama);

           produks.add(nama);
        }

        return produks;
    }
}

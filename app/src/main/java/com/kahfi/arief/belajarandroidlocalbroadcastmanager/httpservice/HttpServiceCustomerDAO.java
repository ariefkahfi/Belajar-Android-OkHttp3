package com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kahfi.arief.belajarandroidlocalbroadcastmanager.model.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by arief on 30/09/17.
 */

public class HttpServiceCustomerDAO extends AbstractOkHttpCreator implements HttpServiceDAO<Customer,String> {


    @Override
    public String save(Customer customer) throws IOException {
        FormBody formBody = new FormBody.Builder()
                .add("id",customer.getId())
                .add("nama",customer.getNama())
                .add("produk",customer.getProduk()).build();

        Request request = new Request.Builder()
                .url("http://192.168.1.100/android-broadcast1/back-end/insert.php")
                .post(formBody)
                .build();

        Response response = createOkHttpClientInstance().newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public List<Customer> getAll() throws IOException {

        List<Customer> data = new ArrayList<>();

        Request request = new Request.Builder()
                .url("http://192.168.1.100/android-broadcast1/back-end/select.php")
                .get()
                .build();

        Response response = createOkHttpClientInstance().newCall(request).execute();

        String json = response.body().string();

        JsonElement element = new JsonParser().parse(json);

        JsonArray person = element.getAsJsonObject().getAsJsonArray("person");

        for(JsonElement el : person){

           String id = el.getAsJsonObject().get("id").getAsString();
           String nama = el.getAsJsonObject().get("nama").getAsString();
           String produk = el.getAsJsonObject().get("produk").getAsString();


           data.add(new Customer(id,nama,produk));
        }

        return data;
    }

    @Override
    public String delete(String s) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("192.168.1.100")
                .addPathSegments("/android-broadcast1/back-end/delete.php")
                .addQueryParameter("id",s)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        Response response = createOkHttpClientInstance().newCall(request).execute();

        return response.body().string();
    }

    @Override
    public String update(Customer customer) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()

                .scheme("http")
                .host("192.168.1.100")
                .addPathSegments("/android-broadcast1/back-end/update.php")

                .addQueryParameter("id",customer.getId())
                .addQueryParameter("nama",customer.getNama())
                .addQueryParameter("produk",customer.getProduk())

                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        Response response = createOkHttpClientInstance().newCall(request).execute();

        return response.body().string();
    }
}

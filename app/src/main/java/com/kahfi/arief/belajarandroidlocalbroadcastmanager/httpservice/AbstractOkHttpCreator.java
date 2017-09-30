package com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice;

import okhttp3.OkHttpClient;

/**
 * Created by arief on 30/09/17.
 */

public class AbstractOkHttpCreator {

    protected static OkHttpClient createOkHttpClientInstance(){
        return new OkHttpClient();
    }

}

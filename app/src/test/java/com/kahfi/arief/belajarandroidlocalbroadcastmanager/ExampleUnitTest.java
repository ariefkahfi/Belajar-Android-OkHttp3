package com.kahfi.arief.belajarandroidlocalbroadcastmanager;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    //@Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testDelete() throws IOException {
        delete("d067ff1e-363b-4fee-924c-0619410e61ff");
    }

    public void delete(String s) throws IOException {
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

        Response response = new OkHttpClient().newCall(request).execute();

        String s1 = response.body().string();

        Assert.assertNotNull(s1);

        System.out.println(s1);
    }

}
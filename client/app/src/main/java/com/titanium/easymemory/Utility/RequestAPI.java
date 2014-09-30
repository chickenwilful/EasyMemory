package com.titanium.easymemory.Utility;

import android.os.AsyncTask;
import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thanhtnguyen on 1/10/14.
 */
public class RequestAPI {

    public JSONObject execute(String... objects) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String request = objects[0];
        if (request.equals("login")) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Storage.get("LOGIN_ADDRESS"));
            System.out.println("LOGIN: " + Storage.get("LOGIN_ADDRESS"));
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", objects[1]));
                nameValuePairs.add(new BasicNameValuePair("password", objects[2]));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();

                return new JSONObject(out.toString());
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            } catch (JSONException e) {
                // TODO Auto-generated catch block
            }
        }
        return null;
    }
}

package com.titanium.easymemory.Utility;

import android.os.StrictMode;
import android.util.Log;

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

    public static JSONObject execute(String... objects) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String request = objects[0];
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = null;
        List<NameValuePair> nameValuePairs = null;

        if (request.equals("login")) {
            httppost = new HttpPost(Storage.get("LOGIN_ADDRESS"));
            // Add your data
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username", objects[1]));
            nameValuePairs.add(new BasicNameValuePair("password", objects[2]));
        }
        else if (request.equals("list_friend")) {
            httppost = new HttpPost(Storage.get("FRIEND_LIST_ADDRESS"));
            // Add data
            nameValuePairs = new ArrayList<NameValuePair>(1);
            Log.i("RequestAPI", "username: " + objects[1]);
            nameValuePairs.add(new BasicNameValuePair("username", objects[1]));
        }

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            Log.i("RequestAPI", "Response: " + out.toString());
            return new JSONObject(out.toString());
        } catch (Exception e) {
            // TODO: Handle it
        }

        return null;
    }
}

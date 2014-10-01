package com.titanium.easymemory.Utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NetworkUtil {
    public static Bitmap getImageFromUrl(String url) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return image;
    }

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
        else if (request.equals("game_play")) {
            httppost = new HttpPost(Storage.get("GAME_PLAY_ADDRESS"));
            nameValuePairs = new ArrayList<NameValuePair>(1);
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

package com.titanium.easymemory;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.titanium.easymemory.Utility.RequestAPI;
import com.titanium.easymemory.Utility.Storage;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginSignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        Log.i("LoginSignupActivity", "Process login");
        EditText usernameEditText = (EditText) findViewById(R.id.txtUserName);
        EditText passwordEditText = (EditText) findViewById(R.id.txtPassword);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        JSONObject response = (new RequestAPI()).execute("login", username, password);

        try {
            String message = response.getString("message");
            Log.i("LoginSignupActivity", "mesasge = " + message);

            if (response.getInt("success") == 1) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        } catch (JSONException e) {
            // TODO: Handle this
        }
    }
}

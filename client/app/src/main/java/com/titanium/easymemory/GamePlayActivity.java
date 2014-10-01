package com.titanium.easymemory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.titanium.easymemory.Utility.NetworkUtil;
import com.titanium.easymemory.Utility.Storage;
import com.titanium.easymemory.entity.FriendList;
import com.titanium.easymemory.entity.PersonAdapter;

import org.json.JSONArray;
import org.json.JSONObject;


public class GamePlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        JSONObject res = NetworkUtil.execute("game_play", Storage.get("username"));

        try {
            int finish = res.getInt("finished");

            if (finish == 0) {
                JSONArray choices = res.getJSONArray("choices");

                PersonAdapter personAdapter = new PersonAdapter(getApplicationContext());
                for(int i = 0; i < choices.length(); ++i) {
                    JSONObject choice = choices.getJSONObject(i);
                    String name = choice.getString("name");
                    String rel = choice.getString("rel");

                    Log.i("Game Play", "Choice: " + name + " " + rel);

                    FriendList.Friend friend = new FriendList.Friend(
                            choice.getString("id"),
                            choice.getString("name"),
                            choice.getString("rel"),
                            choice.getString("img"));
                    personAdapter.addQuestion(friend);
                }

                GridView choicesGrid = (GridView) findViewById(R.id.answerGridView);
                choicesGrid.setAdapter(personAdapter);

                ImageView imageView = (ImageView) (findViewById(R.id.imageViewQuestion));
                String imageUrl = Storage.get("SERVER_ADDRESS");
                imageUrl = imageUrl.substring(0, imageUrl.length()-1);
                imageUrl += res.getString("img");
                imageView.setImageBitmap(NetworkUtil.getImageFromUrl(imageUrl));
            }
            else {
                String correctAnswer = Storage.get("CORRECT_ANSWER");
                TextView textView = (TextView) (findViewById(R.id.whoIsThisTitle));
                    textView.setText("You have answered " + correctAnswer + " questions correctly");
            }
        }
        catch (Exception e) {
            // TODO: Handle this
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_play, menu);
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
}

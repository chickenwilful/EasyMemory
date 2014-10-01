package com.titanium.easymemory.entity;

import android.util.Log;

import com.titanium.easymemory.Utility.NetworkUtil;
import com.titanium.easymemory.Utility.Storage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class FriendList {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Friend> ITEMS = new ArrayList<Friend>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Friend> ITEM_MAP = new HashMap<String, Friend>();

    public static void updateItems() {
        ITEMS.clear();
        Log.i("DummyContent", "Update items");
        JSONObject res = NetworkUtil.execute("list_friend", Storage.get("username"));
        try {
            JSONArray friends = res.getJSONArray("friends");
            for(int i = 0; i < friends.length(); ++i) {
                JSONObject friend = friends.getJSONObject(i);
                addItem(new Friend(
                        friend.getString("id"),
                        friend.getString("name"),
                        friend.getString("relation"),
                        friend.getString("img")
                ));
            }
        }
        catch (Exception ex) {
            // TODO: Handle this
        }
    }

    private static void addItem(Friend item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Friend {
        public String id;
        public String name;
        public String relation;
        public String image;

        public Friend(String id, String name, String relation, String image) {
            this.id = id;
            this.name = name;
            this.relation = relation;
            this.image = image;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

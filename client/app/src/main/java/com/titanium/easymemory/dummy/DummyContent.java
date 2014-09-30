package com.titanium.easymemory.dummy;

import android.util.Log;

import com.titanium.easymemory.Utility.RequestAPI;
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
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static void updateItems() {
        Log.i("DummyContent", "Update items");
        JSONObject res = RequestAPI.execute("list_friend", Storage.get("username"));
        try {
            JSONArray friends = res.getJSONArray("friends");
            for(int i = 0; i < friends.length(); ++i) {
                JSONObject friend = friends.getJSONObject(i);
                addItem(new DummyItem(
                        "" + i,
                        friend.getString("name"),
                        friend.getString("relation")
                ));
            }
        }
        catch (Exception ex) {
            // TODO: Handle this
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String name;
        public String relation;

        public DummyItem(String id, String name, String relation) {
            this.id = id;
            this.name = name;
            this.relation = relation;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

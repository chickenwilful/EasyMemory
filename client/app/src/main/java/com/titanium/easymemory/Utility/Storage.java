package com.titanium.easymemory.Utility;

import java.util.HashMap;

public class Storage
{
    static HashMap<String, String> keys;

    static {
        keys = new HashMap<String, String>();
        String server_address = "http://10.0.3.2:8000/";
        keys.put("SERVER_ADDRESS", server_address);
        keys.put("LOGIN_ADDRESS", server_address + "users/login");
        keys.put("FRIEND_LIST_ADDRESS", server_address + "users/list_friend");
        keys.put("GAME_PLAY_ADDRESS", server_address + "games/next_step");
        keys.put("CORRECT_ANSWER", "0");
    }

    public static String get(String key) {
        if (keys.containsKey(key)) return keys.get(key);
        else return "";
    }

    public static void put(String key, String value) {
        keys.put(key, value);
    }
}

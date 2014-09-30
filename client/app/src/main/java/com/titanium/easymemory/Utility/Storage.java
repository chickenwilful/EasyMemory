package com.titanium.easymemory.Utility;

import java.util.HashMap;

/**
 * Created by thanhtnguyen on 1/10/14.
 */
public class Storage
{
    static HashMap<String, String> keys;

    static {
        keys = new HashMap<String, String>();
        String server_address = "http://23.98.75.224/";
        keys.put("SERVER_ADDRESS", server_address);
        keys.put("LOGIN_ADDRESS", server_address + "users/login");
    }

    public static String get(String key) {
        if (keys.containsKey(key)) return keys.get(key);
        else return "";
    }

    public static void put(String key, String value) {
        keys.put(key, value);
    }
}

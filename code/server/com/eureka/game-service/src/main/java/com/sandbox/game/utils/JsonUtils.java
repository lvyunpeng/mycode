package com.sandbox.game.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by arthur on 15/11/19.
 */
public class JsonUtils {

    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(obj);
    }

    public static Object fromJson(String jsonString, Type type) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(jsonString, type);
    }

}

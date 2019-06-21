package com.ant.shop.admin.config.gson;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class DateNullAdapterFactory<T> implements TypeAdapterFactory {

    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType != Date.class) {
            return null;
        }
        return (TypeAdapter<T>) new DateNullAdapter();
    }
}

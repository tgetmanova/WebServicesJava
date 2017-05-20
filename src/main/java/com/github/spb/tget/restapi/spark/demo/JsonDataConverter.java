package com.github.spb.tget.restapi.spark.demo;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonDataConverter {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer getJsonResponseTransformer() {
        return JsonDataConverter::toJson;
    }
}

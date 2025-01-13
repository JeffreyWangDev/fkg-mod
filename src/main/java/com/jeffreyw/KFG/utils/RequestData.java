package com.jeffreyw.KFG.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;


// FROM https://github.com/TGWaffles/iTEM/blob/8b52fe19a706196d168a73b56078730aac9a5bd6/src/main/java/club/thom/tem/hypixel/request/RequestData.java


public class RequestData {
    private final Map<String, List<String>> headers;
    private final JsonElement json;
    private final int status;
    public RequestData(int status, Map<String, List<String>> headers, JsonElement jsonData) {
        this.status = status;
        this.headers = headers;
        this.json = jsonData;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public JsonObject getJsonAsObject() {
        return json.getAsJsonObject();
    }

    public JsonElement getJson() {
        return json;
    }


    public int getStatus() {
        return status;
    }
}
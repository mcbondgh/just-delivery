package com.app.api;

import com.app.interfaces.RegionsAndCapital;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;

public class ApiCalls implements RegionsAndCapital {

    public ApiCalls() {
        getAllRegions();
    }

    private String regionsEndpoint = "https://regions-and-districts-in-ghana.onrender.com/regions";
    private OkHttpClient httpClient = new OkHttpClient();
    private Response httpResponse;
    private Request httpRequest;
    private List<String> regionsData = new ArrayList<>();

//    "Ahafo Region"
//            "Ashanti Region"
//            "Bono Region"
//            "Bono East Region"
//            "Central Region"
//            "Eastern Region"
//            "Greater Accra Region"
//            "Northern Region"
//            "North East Region"
//            "Oti Region"
//            "Savannah Region"
//            "Upper East Region"
//            "Upper West Region"
//            "Volta Region"
//            "Western Region"
//            "Western North Region"

    private void getAllRegions() {
        httpRequest = new Request.Builder().url(regionsEndpoint).build();
        try {
            httpResponse = httpClient.newCall(httpRequest).execute();
            if (httpResponse.isSuccessful()) {
                String data = httpResponse.body().string();
                JsonElement element = JsonParser.parseString(data);
                JsonObject object = element.getAsJsonObject();
                JsonArray jsonArray = object.getAsJsonArray("regions");
               for (JsonElement dataElements : jsonArray.getAsJsonArray()) {
                   JsonObject dataObject = dataElements.getAsJsonObject();
                   regionsData.add(dataObject.get("label").getAsString());
               }
            }
        }catch (Exception e){
            regionsData.add(0, "Within Koforidua");
            regionsData.add(1, "Outside Koforidua");
        }
    }

    @Override
    public List<String> getRegionalNames() {
        return  regionsData;
    }


}

package org.openauto.localspeedcam.modules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AntenneBayern extends TrafficModule {

    private String formatSpeed(String speed){
        if(!speed.isEmpty()){
            return speed+"km/h";
        } else return "";
    }

    public String getFeedContent() throws IOException, JSONException {

        StringBuilder builder = new StringBuilder();

        List<String> entries = new ArrayList<>();
        String jsonurl = "https://prod-traffic.antenne.de/data/blitzerDynamisch.json";
        String out = new Scanner(new URL(jsonurl).openStream(), "UTF-8").useDelimiter("\\A").next();
        JSONObject jObject = new JSONObject(out);
        JSONArray blitzer = jObject.getJSONArray("event");
        for (int i = 0; i < blitzer.length(); i++) {
            JSONObject b = blitzer.getJSONObject(i);
            String speed = b.getString("speed").replace("{", "").replace("}", "");
            String zip = b.getJSONObject("address").getString("zip");
            String city = b.getJSONObject("address").getString("city").replace("�","ü");
            String street = b.getJSONObject("address").getString("street").replace("�","ü");
            entries.add(zip + " " + city + ", " + street + " " + formatSpeed(speed));
        }
        Collections.sort(entries);

        for(String s : entries){
            builder.append(s);
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getFeedTitle() {
        return "Antenne Bayern - Wir lieben Bayern.";
    }
}

package org.openauto.localspeedcam.modules;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Add new module here after creating their implementation
 */
public abstract class TrafficModule {

    public static List<TrafficModule> getFeedList(){
        List<TrafficModule> modules = new ArrayList<>();
        modules.add(new RadioRT1());
        modules.add(new AntenneBayern());
        return modules;
    }

    @Override
    public String toString() {
        return getFeedTitle();
    }

    public abstract String getFeedContent() throws IOException, JSONException;
    public abstract String getFeedTitle();
}

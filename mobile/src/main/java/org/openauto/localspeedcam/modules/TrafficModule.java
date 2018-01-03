package org.openauto.localspeedcam.modules;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Add new module here after creating their implementation
 */
public abstract class TrafficModule {

    public static int DEFAULT_READ_TIMEOUT = 8000;

    public static List<TrafficModule> getFeedList(){
        List<TrafficModule> modules = new ArrayList<>();
        modules.add(new RadioRT1());
        modules.add(new AntenneBayern());
        modules.add(new HitRadioFFH());
        modules.add(new RadioTon());
        modules.add(new Landeswelle());
        modules.add(new Radiosaw());
        return modules;
    }

    @Override
    public String toString() {
        return getFeedTitle();
    }

    public abstract String getFeedContent() throws IOException, JSONException;
    public abstract String getFeedTitle();
}


package org.openauto.localspeedcam.modules;

import org.json.JSONException;

import java.io.IOException;

public interface TrafficModule {

    String getFeedContent() throws IOException, JSONException;
    String getFeedTitle();
}

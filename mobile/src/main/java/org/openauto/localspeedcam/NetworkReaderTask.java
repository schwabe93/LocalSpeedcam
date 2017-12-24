package org.openauto.localspeedcam;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openauto.localspeedcam.modules.TrafficModule;

import java.net.URL;

public class NetworkReaderTask extends AsyncTask<Object, String, String> {

	AsyncResponse activity;
    TrafficModule trafficModule;

	@Override
	protected void onPostExecute(String result) {
		activity.processFinish(trafficModule.getFeedTitle(), result);
	}

	@Override
	protected String doInBackground(Object... objects) {

		try {
			activity = (AsyncResponse)objects[0];
			trafficModule = (TrafficModule) objects[1];
			return trafficModule.getFeedContent();
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

}

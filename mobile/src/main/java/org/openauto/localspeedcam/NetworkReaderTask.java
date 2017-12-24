package org.openauto.localspeedcam;

import android.os.AsyncTask;
import android.util.Log;

import org.openauto.localspeedcam.modules.TrafficModule;

public class NetworkReaderTask extends AsyncTask<Object, String, String> {

	private AsyncResponse activity;
	private TrafficModule trafficModule;

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
			Log.e("LocalSpeedcam",Log.getStackTraceString(e));
		}

		return null;
	}

}

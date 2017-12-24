package org.openauto.localspeedcam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.openauto.localspeedcam.modules.TrafficModule;
import org.openauto.localspeedcam.utils.IOHandler;
import org.openauto.localspeedcam.utils.UIUtils;

public class LocalSpeedcamActivity extends Activity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_speedcam);

        IOHandler ioHandler = new IOHandler(this);
        Spinner feedSpinner = findViewById(R.id.feed_spinner);
        Button feed_refresh_button = findViewById(R.id.feed_refresh_button);
        Button appstart_button = findViewById(R.id.appstart_button);

        feed_refresh_button.setOnClickListener(view -> {
            TrafficModule selectedModule = (TrafficModule)feedSpinner.getSelectedItem();
            NetworkReaderTask task = new NetworkReaderTask();
            task.execute(LocalSpeedcamActivity.this, selectedModule);
        });

        appstart_button.setOnClickListener(view -> {
            EditText appstart_title = findViewById(R.id.appstart_title);
            EditText appstart_package = findViewById(R.id.appstart_package);
            String appStartConf = appstart_title.getText().toString() + "|" + appstart_package.getText().toString();
            ioHandler.saveObject(appStartConf, "appstart.conf");
        });

        UIUtils.initializeSpinner(this, feedSpinner, TrafficModule.getFeedList());

    }

    @Override
    public void processFinish(String feedTitle, String output) {
        TextView infoTv = findViewById(R.id.information_textview);
        infoTv.setText(output);
    }
}

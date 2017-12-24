package org.openauto.localspeedcam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.openauto.localspeedcam.modules.TrafficModule;
import org.openauto.localspeedcam.utils.IOHandler;
import org.openauto.localspeedcam.utils.UIUtils;

public class LocalSpeedcamActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_speedcam);

        IOHandler ioHandler = new IOHandler(this);
        Spinner feedSpinner = findViewById(R.id.feed_spinner);
        Button feed_refresh_button = findViewById(R.id.feed_refresh_button);
        Button appstart_button = findViewById(R.id.appstart_button);
        EditText appstart_title = findViewById(R.id.appstart_title);
        EditText appstart_package = findViewById(R.id.appstart_package);

        feed_refresh_button.setOnClickListener(view -> {
            TrafficModule selectedModule = (TrafficModule)feedSpinner.getSelectedItem();
            NetworkReaderTask task = new NetworkReaderTask();
            task.execute(LocalSpeedcamActivity.this, selectedModule);
        });

        appstart_button.setOnClickListener(view -> {
            String appStartConf = appstart_title.getText().toString() + "|" + appstart_package.getText().toString();
            ioHandler.saveObject(appStartConf, "appstart.conf");

            UIUtils.showSnackbar(LocalSpeedcamActivity.this, "Package saved...", 500);
        });

        UIUtils.initializeSpinner(this, feedSpinner, TrafficModule.getFeedList());

        String appStartConf = (String)ioHandler.readObject("appstart.conf");
        if(appStartConf != null && !appStartConf.isEmpty()){
            appstart_title.setText(appStartConf.split("\\|")[0]);
            appstart_package.setText(appStartConf.split("\\|")[1]);
            UIUtils.showSnackbar(LocalSpeedcamActivity.this, "Package loaded...", 500);
        }

    }

    @Override
    public void processFinish(String feedTitle, String output) {
        TextView infoTv = findViewById(R.id.information_textview);
        infoTv.setText(output);
    }
}

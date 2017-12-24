package org.openauto.localspeedcam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.openauto.localspeedcam.modules.AntenneBayern;

public class LocalSpeedcamActivity extends Activity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_speedcam);

        Button btn = findViewById(R.id.feed_refresh_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkReaderTask task = new NetworkReaderTask();
                task.execute(this, new AntenneBayern());
            }
        });

    }

    @Override
    public void processFinish(String feedTitle, String output) {
        TextView infoTv = findViewById(R.id.information_textview);
        infoTv.setText(output);
    }
}

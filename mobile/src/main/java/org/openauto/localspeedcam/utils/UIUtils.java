package org.openauto.localspeedcam.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.openauto.localspeedcam.R;
import org.openauto.localspeedcam.modules.TrafficModule;

import java.util.List;

public class UIUtils {

    public static void initializeSpinner(Context context, Spinner spinner, List<TrafficModule> values){
        Object[] valuesArray = values.toArray(new Object[values.size()]);
        ArrayAdapter<Object> spinnerArrayAdapter = new ArrayAdapter<>(context, R.layout.simple_spinner_dropdown_item_aosp, valuesArray);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_aosp);
        spinner.setAdapter(spinnerArrayAdapter);
    }

}

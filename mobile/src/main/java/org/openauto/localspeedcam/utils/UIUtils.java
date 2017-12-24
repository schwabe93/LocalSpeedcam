package org.openauto.localspeedcam.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
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

    public static void showSnackbar(Activity act, String msg, int length){
        Snackbar.make(act.findViewById(android.R.id.content), msg, length).show();
    }

}

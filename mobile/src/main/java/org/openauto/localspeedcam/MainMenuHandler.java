package org.openauto.localspeedcam;

import android.content.Intent;

import org.openauto.localspeedcam.modules.TrafficModule;
import org.openauto.localspeedcam.utils.IOHandler;

public class MainMenuHandler {

    public static ListMenuAdapter.MenuCallbacks createMenuCallbacks(MainCarActivity activity){

        final ListMenuAdapter.MenuCallbacks mMenuCallbacks = new ListMenuAdapter.MenuCallbacks() {
            @Override
            public void onMenuItemClicked(String name) {

                for(TrafficModule module : TrafficModule.getFeedList()){
                    if(name.equals(module.getClass().getSimpleName())){
                        activity.setCurrentTrafficModule(module);
                        new NetworkReaderTask().execute(activity, activity.getCurrentTrafficModule());
                        return;
                    }
                }
                if("MENU_REFRESH".equals(name)){
                    new NetworkReaderTask().execute(activity, activity.getCurrentTrafficModule());
                    return;
                }
                if("MENU_HOME".equals(name)){
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(startMain);
                    return;
                }
                if("APP_START_SHORTCUT".equals(name)){
                    IOHandler ioHandler = new IOHandler(activity);
                    String appStartConf = (String)ioHandler.readObject("appstart.conf");
                    String[] apps = appStartConf.split("\\|");
                    Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(apps[1]);
                    if (launchIntent != null) {
                        activity.startActivity(launchIntent);//null pointer check in case package name was not found
                    }
                }
            }

            @Override
            public void onEnter() {
            }

            @Override
            public void onExit() {
                activity.updateStatusBarTitle();
            }
        };
        return mMenuCallbacks;
    }


}

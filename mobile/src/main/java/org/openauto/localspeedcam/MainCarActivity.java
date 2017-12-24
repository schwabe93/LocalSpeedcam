package org.openauto.localspeedcam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.apps.auto.sdk.CarActivity;
import com.google.android.apps.auto.sdk.CarUiController;
import com.google.android.apps.auto.sdk.MenuController;
import com.google.android.apps.auto.sdk.MenuItem;
import com.google.android.apps.auto.sdk.StatusBarController;

import org.openauto.localspeedcam.modules.RadioRT1;
import org.openauto.localspeedcam.modules.TrafficModule;
import org.openauto.localspeedcam.utils.IOHandler;

public class MainCarActivity extends CarActivity implements AsyncResponse {

    private static final String FRAGMENT_DEMO = "demo";
    private static final String FRAGMENT_LOG = "log";
    private static final String CURRENT_FRAGMENT_KEY = "app_current_fragment";
    private String mCurrentFragmentTag;
    private TrafficModule currentTrafficModule;

    @Override
    public void onCreate(Bundle bundle) {
        setTheme(R.style.AppTheme_Car);
        super.onCreate(bundle);
        setContentView(R.layout.activity_car_main);

        CarUiController carUiController = getCarUiController();
        carUiController.getStatusBarController().showTitle();

        FragmentManager fragmentManager = getSupportFragmentManager();
        DemoFragment demoFragment = new DemoFragment();
        LogFragment logFragment = new LogFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, demoFragment, FRAGMENT_DEMO)
                .detach(demoFragment)
                .add(R.id.fragment_container, logFragment, FRAGMENT_LOG)
                .detach(logFragment)
                .commitNow();

        String initialFragmentTag = FRAGMENT_DEMO;
        if (bundle != null && bundle.containsKey(CURRENT_FRAGMENT_KEY)) {
            initialFragmentTag = bundle.getString(CURRENT_FRAGMENT_KEY);
        }
        switchToFragment(initialFragmentTag);

        ListMenuAdapter mainMenu = new ListMenuAdapter();
        mainMenu.setCallbacks(MainMenuHandler.createMenuCallbacks(this));

        mainMenu.addMenuItem("MENU_HOME", new MenuItem.Builder()
                .setTitle(getString(R.string.menu_unlock_phone))
                .setIconResId(R.drawable.ic_lock_open_black_24dp)
                .setType(MenuItem.Type.ITEM)
                .build());
        mainMenu.addMenuItem("MENU_FEEDS", new MenuItem.Builder()
                .setTitle(getString(R.string.menu_feeds_label))
                .setIconResId(R.drawable.ic_rss_feed_black_24dp)
                .setType(MenuItem.Type.SUBMENU)
                .build());
        mainMenu.addMenuItem("MENU_REFRESH", new MenuItem.Builder()
                .setTitle(getString(R.string.menu_refresh_label))
                .setIconResId(R.drawable.ic_refresh_black_24dp)
                .setType(MenuItem.Type.ITEM)
                .build());

        addAppStartShortcut(mainMenu);

        ListMenuAdapter feedsMenu = new ListMenuAdapter();
        feedsMenu.setCallbacks(MainMenuHandler.createMenuCallbacks(this));

        for(TrafficModule module : TrafficModule.getFeedList()){
            feedsMenu.addMenuItem(module.getClass().getSimpleName(), new MenuItem.Builder()
                    .setTitle(module.getFeedTitle())
                    .setType(MenuItem.Type.ITEM)
                    .build());
        }

        mainMenu.addSubmenu("MENU_FEEDS", feedsMenu);

        MenuController menuController = carUiController.getMenuController();
        menuController.setRootMenuAdapter(mainMenu);
        menuController.showMenuButton();

        StatusBarController statusBarController = carUiController.getStatusBarController();
        statusBarController.setAppBarAlpha(0.5f);
        statusBarController.setAppBarBackgroundColor(0xffff0000);

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(mFragmentLifecycleCallbacks,
                false);

    }

    private void addAppStartShortcut(ListMenuAdapter mainMenu){
        try{
            IOHandler ioHandler = new IOHandler(this);
            String appStartConf = (String)ioHandler.readObject("appstart.conf");
            String[] apps = appStartConf.split("\\|");

            mainMenu.addMenuItem("APP_START_SHORTCUT", new MenuItem.Builder()
                    .setTitle(apps[0])
                    .setType(MenuItem.Type.ITEM)
                    .setIconResId(R.drawable.ic_launch_black_24dp)
                    .build());

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void setCurrentTrafficModule(TrafficModule trafficModule){
        this.currentTrafficModule = trafficModule;
    }

    public TrafficModule getCurrentTrafficModule(){
        if(this.currentTrafficModule == null){
            this.currentTrafficModule = new RadioRT1();
        }
        return this.currentTrafficModule;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(CURRENT_FRAGMENT_KEY, mCurrentFragmentTag);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
        switchToFragment(mCurrentFragmentTag);
    }

    private void switchToFragment(String tag) {
        if (tag.equals(mCurrentFragmentTag)) {
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        Fragment currentFragment = mCurrentFragmentTag == null ? null : manager.findFragmentByTag(mCurrentFragmentTag);
        Fragment newFragment = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null) {
            transaction.detach(currentFragment);
        }
        transaction.attach(newFragment);
        transaction.commit();
        mCurrentFragmentTag = tag;
    }

    private final FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks
            = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentStarted(FragmentManager fm, Fragment f) {
            updateStatusBarTitle();
        }
    };

    public void updateStatusBarTitle() {
        CarFragment fragment = (CarFragment) getSupportFragmentManager().findFragmentByTag(mCurrentFragmentTag);
        getCarUiController().getStatusBarController().setTitle(fragment.getTitle());
    }

    @Override
    public void processFinish(String feedTitle, String output) {
        android.widget.TextView feedid = (android.widget.TextView) findViewById(R.id.feed_id);
        feedid.setText(feedTitle);
        android.widget.TextView feedcontent = (android.widget.TextView) findViewById(R.id.feed_content);
        feedcontent.setText(output);
    }
}

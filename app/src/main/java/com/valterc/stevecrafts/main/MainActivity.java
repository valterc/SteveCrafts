package com.valterc.stevecrafts.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.valterc.external.tumblr.TumblrAPI;
import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.api.SteveCraftsApi;
import com.valterc.stevecrafts.drawer.NavigationDrawerFragment;
import com.valterc.stevecrafts.main.fragment.MainFragment;
import com.vcutils.tasks.MultipurposeAsyncTask;
import com.vcutils.tasks.MultipurposeAsyncTaskData;
import com.vcutils.utils.DebugLog;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBarToolbar();

        MultipurposeAsyncTask<Void> multipurposeAsyncTask = new MultipurposeAsyncTask<Void>(new MultipurposeAsyncTaskData<Void>() {
            @Override
            public Void runOnBackground() {
                SteveCraftsApi api = new SteveCraftsApi();
                try {
                    /*
                    DebugLog.d("BEGIN API CALL");
                    SteveCraftsData data = api.getData();
                    DebugLog.d("END API CALL -- SAVING DATA");
                    SteveCraftsApp.getDataManager().saveData(data);
                    DebugLog.d("END SAVE DATA");
                    SteveCraftsApp.getDataManager().backupDatabase();
                    DebugLog.d("END BACKUP DATABASE");
                    */

                    DebugLog.d(SteveCraftsApp.getDataManager().getBlock("69").getNameEn());
                    DebugLog.d(SteveCraftsApp.getDataManager().getItem("69").getNameEn());
                    DebugLog.d(SteveCraftsApp.getDataManager().getPotion("10").getNameEn());

                    TumblrAPI tumblrAPI = new TumblrAPI(getString(R.string.key_tumblr_api));
                    tumblrAPI.GetPostsText("mcupdate.tumblr.com");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            public void onComplete(Void o) {

            }
        });
        multipurposeAsyncTask.execute();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationDrawerFragment mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout, mActionBarToolbar);

        getFragmentManager().beginTransaction().add(R.id.container, MainFragment.newInstance()).commit();

    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }


}

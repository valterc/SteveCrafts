package com.valterc.stevecrafts.main;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Window window = getWindow();
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //window.setStatusBarColor(getResources().getColor(R.color.red));
        }

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

                    DebugLog.d(SteveCraftsApp.getDataManager().getRandomItem().getName());


                    TumblrAPI tumblrAPI = new TumblrAPI(getString(R.string.key_tumblr_api));
                    //tumblrAPI.GetPostsText("mcupdate.tumblr.com");

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

        getFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance()).commit();

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
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

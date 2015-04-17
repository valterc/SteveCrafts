package com.valterc.stevecrafts.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.valterc.stevecrafts.R;
import com.valterc.stevecrafts.SteveCraftsApp;
import com.valterc.stevecrafts.data.api.SteveCraftsApi;
import com.valterc.stevecrafts.data.api.SteveCraftsData;
import com.valterc.stevecrafts.drawer.NavigationDrawerFragment;
import com.valterc.stevecrafts.main.fragment.MainFragment;
import com.vcutils.tasks.MultipurposeAsyncTask;
import com.vcutils.tasks.MultipurposeAsyncTaskData;


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
                    Log.d("SC", "BEGIN API CALL");
                    SteveCraftsData data = api.getData();
                    Log.d("SC", "END API CALL -- SAVING DATA");
                    SteveCraftsApp.getDataManager().saveData(data);
                    Log.d("SC", "END SAVE DATA");
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

package ftcc.initech.triadareaultimate;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ftcc.initech.triadareaultimate.fragment.*;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    //Class Variables
    private static final long DRAWER_CLOSE_DELAY_MS = 250;
    private static final String NAV_ITEM_ID = "navItemId";
    private final Handler mDrawerActionHandler = new Handler();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mNavItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Load any saved state
        if (null == savedInstanceState) {
            mNavItemId = R.id.menu_item_home;
        } else {
            mNavItemId = savedInstanceState.getInt(NAV_ITEM_ID);
        }

        //prepare the nav drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(mNavItemId).setChecked(true);

        //set up menu hamburger
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        exec(mNavItemId);
    }

    //performs fragment switching
    private void exec(int itemId){
        // TODO: 3/23/2017 add fragment logic
        Fragment fragment;
        switch(itemId){
            case R.id.menu_item_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_item_feed:
                fragment = new NewsFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem){
        menuItem.setChecked(true);
        mNavItemId = menuItem.getItemId();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mDrawerActionHandler.postDelayed(new Runnable(){
            @Override
            public void run() {
                exec(menuItem.getItemId());
            }
        }, DRAWER_CLOSE_DELAY_MS);
        return true;
    }

}

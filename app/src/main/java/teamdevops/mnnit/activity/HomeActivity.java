package teamdevops.mnnit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import teamdevops.mnnit.R;
import teamdevops.mnnit.fragment.DashBoardFragment;
import teamdevops.mnnit.fragment.InfoFragment;
import teamdevops.mnnit.fragment.MenuFragment;
import teamdevops.mnnit.fragment.NavigationDrawerFragment;
import teamdevops.mnnit.helper.SessionManager;

/**
 * Home screen of the app after login
 * This class includes the action for Navigation Drawer as well as ViewPager initialisation
 *
 * @author Deepankar
 */

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    public static CharSequence mTitle;
    protected String[] mNavTitles;
    private ListView mDrawerListView;
    private ViewPager mViewPager;
    private PagerTitleStrip mPagerTitleStrip;
    private FrameLayout mMainContainer;
    private View mFragmentNavDrawerView;
    private SessionManager session;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.app_bar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        session = SessionManager.getInstance(getApplicationContext());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
        mMainContainer = (FrameLayout) findViewById(R.id.main_container);
        mFragmentNavDrawerView = findViewById(R.id.viewFrag_nav_drawer);
        mDrawerListView = (ListView) findViewById(R.id.drawerList);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.viewFrag_nav_drawer);
        drawerFragment.setup(mFragmentNavDrawerView, mDrawerLayout, toolbar);

        mTitle = toolbar.getTitle();
        if (session.isLoggedIn())
            mNavTitles = getResources().getStringArray(R.array.login_nav_array);
        else
            mNavTitles = getResources().getStringArray(R.array.not_login_nav_array);

        MyAdapter myAdapter = new MyAdapter(this);
        mDrawerListView.setAdapter(myAdapter);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        if (NavigationDrawerFragment.mUserLearnedDrawer)
            selectItem(0);
        else
            selectItem(-1);
    }

    public void selectItem(int position) {

        if (position == -1) {
            mViewPager.setAdapter(new MainPagerAdapter(
                    getSupportFragmentManager()));
            mViewPager.setCurrentItem(1);
        }

        if (position == 0 && selectedPosition == 0) {
            setTitle(mNavTitles[position]);
            mDrawerLayout.closeDrawer(mFragmentNavDrawerView);
            mDrawerListView.setItemChecked(position, true);
        }

        if (position == 0 && selectedPosition != 0) {

            selectedPosition = position;
            setTitle(mNavTitles[position]);
            mDrawerListView.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(mFragmentNavDrawerView);
            mViewPager.setAdapter(new MainPagerAdapter(
                    getSupportFragmentManager()));
            mViewPager.setCurrentItem(1);
        }

        if (position == 1 && selectedPosition != 1) {
            if (session.isLoggedIn()) {
                mDrawerLayout.closeDrawer(mFragmentNavDrawerView);
                Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(i);
            } else {
                mDrawerLayout.closeDrawer(mFragmentNavDrawerView);
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                HomeActivity.this.finish();
            }
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_us) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        int listIcons[] = {R.drawable.ic_home, R.drawable.ic_profile, R.drawable.ic_settings, R.drawable.ic_contact};

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mNavTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return mNavTitles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                item = inflater.inflate(R.layout.drawer_list_item, parent, false);
            } else {
                item = convertView;
            }
            TextView text = (TextView) item.findViewById(R.id.listText);
            ImageView image = (ImageView) item.findViewById(R.id.listimage);
            text.setText(mNavTitles[position]);
            image.setImageResource(listIcons[position]);
            return item;
        }
    }

    class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            if (position == 0)
                frag = new MenuFragment();
            if (position == 1)
                frag = new DashBoardFragment();
            if (position == 2)
                frag = new InfoFragment();
            return frag;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return "Menu";
            if (position == 1)
                return "Dashboard";
            if (position == 2)
                return "Information";
            return null;
        }
    }

}

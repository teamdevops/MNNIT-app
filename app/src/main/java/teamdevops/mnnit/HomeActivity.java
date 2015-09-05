package teamdevops.mnnit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
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

import com.flaviofaria.kenburnsview.KenBurnsView;

/**
 * @author Deepankar
 */

public class HomeActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    public static CharSequence mTitle;
    public static final CharSequence mAppTitle = "MNNIT";
    protected String[] mNavTitles;
    private ListView mdrawerListView;
    private MyAdapter myAdapter;
    private ViewPager mViewPager;
    private PagerTitleStrip mPagerTitleStrip;
    private FrameLayout mMainContainer;
    private NavigationDrawerFragment drawerFragment;
    private View fragmentView;
    private int selectedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_appbar);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
        mMainContainer = (FrameLayout) findViewById(R.id.main_container);
        fragmentView = findViewById(R.id.fragment_nav_drawer);

        drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_drawer);
        drawerFragment.setup(fragmentView, mDrawerLayout, toolbar);

        mTitle = toolbar.getTitle();
        mNavTitles = getResources().getStringArray(R.array.nav_array);
        mdrawerListView = (ListView) findViewById(R.id.drawerList);

        myAdapter = new MyAdapter(this);
        mdrawerListView.setAdapter(myAdapter);
        mdrawerListView.setOnItemClickListener(this);

        if (NavigationDrawerFragment.mUserLearnedDrawer)
            selectItem(0);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    public void selectItem(int position) {

        if (position == 0 && selectedPosition == 0) {
            setTitle(mNavTitles[position]);
            mDrawerLayout.closeDrawer(fragmentView);
            mdrawerListView.setItemChecked(position, true);
        }
        if (position == 0 && selectedPosition != 0) {

            selectedPosition = position;
            setTitle(mNavTitles[position]);
            mdrawerListView.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(fragmentView);

            mViewPager.setAdapter(new MainPagerAdapter(
                    getSupportFragmentManager()));
            mViewPager.setCurrentItem(1);

            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    if (position == 0) {
                        toolbar.setBackgroundColor(getResources().getColor(R.color.FragmentMenuPrimaryColor));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.FragmentMenuPrimaryColorDark));
                        mPagerTitleStrip.setBackgroundColor(getResources().getColor(R.color.FragmentMenuPrimaryColorDark));
                        mPagerTitleStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    }
                    if (position == 1) {
                        toolbar.setBackgroundColor(getResources().getColor(R.color.HomeActivityPrimaryColor));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.HomeActivityPrimaryColorDark));
                        mPagerTitleStrip.setBackgroundColor(getResources().getColor(R.color.HomeActivityPrimaryColorDark));
                    }
                    if (position == 2) {
                        toolbar.setBackgroundColor(getResources().getColor(R.color.FragmentInfoPrimaryColor));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.FragmentInfoPrimaryColorDark));
                        mPagerTitleStrip.setBackgroundColor(getResources().getColor(R.color.FragmentInfoPrimaryColorDark));
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });

        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
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
            View item = null;
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

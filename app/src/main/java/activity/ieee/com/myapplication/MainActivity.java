package activity.ieee.com.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static String TAG=MainActivity.class.getSimpleName();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems=new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavItems.add(new NavItem("Home", "IEEE NIEC Home", R.mipmap.ic_launcher));
        mNavItems.add(new NavItem("Blog", "IEEE NIEC Blog", R.mipmap.ic_launcher));
        mNavItems.add(new NavItem("Events","Event Schedule",R.mipmap.ic_launcher));
        mNavItems.add(new NavItem("IEEE","IEEE Home",R.mipmap.ic_launcher));
        mNavItems.add(new NavItem("About","More About us",R.mipmap.ic_launcher));
        mNavItems.add(new NavItem("Contact","Reach out to us",R.mipmap.ic_launcher));

        mDrawerLayout=(DrawerLayout) findViewById(R.id.main_app);
        mDrawerPane=(RelativeLayout) findViewById(R.id.nav_container);
        mDrawerList=(ListView) findViewById(R.id.navList);

        DrawerListAdapter adapter=new DrawerListAdapter(this,mNavItems);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id)
            {
                selectItemFromDrawer(position);
            }
        });
    }

    public void selectItemFromDrawer(int position)
    {
        Fragment fragment=new PreferencesFragment();

        FragmentManager manager=getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.home_content,fragment)
                .commit();

        mDrawerList.setItemChecked(position,true);
        setTitle(mNavItems.get(position).mTitle);

        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class NavItem{
        String mTitle;
        String mSubTitle;
        int mIcon;

        public NavItem(String title,String subtitle,int icon)
        {
            mTitle=title;
            mSubTitle=subtitle;
            mIcon=icon;
        }
    }

    class DrawerListAdapter extends BaseAdapter
    {
        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context,ArrayList<NavItem> navitems)
        {
            mContext=context;
            mNavItems=navitems;
        }

        @Override
        public int getCount()
        {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position,View convertView, ViewGroup parent)
        {
            View view;
            if(convertView==null)
            {
                LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(R.layout.drawer_item,null);
            }
            else
            {
                view=convertView;
            }
            TextView titleView=(TextView) view.findViewById(R.id.title);
            TextView subtitleView=(TextView) view.findViewById(R.id.subTitle);
            ImageView iconView=(ImageView) view.findViewById(R.id.icon);

            titleView.setText(mNavItems.get(position).mTitle);
            subtitleView.setText(mNavItems.get(position).mSubTitle);
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }
}

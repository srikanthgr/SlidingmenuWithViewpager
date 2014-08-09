package com.srikanthgr.slidingmenuwithviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.srikanthgr.slidingmenuwithviewpager.adapter.CustomPagerAdapter;

public class SlidingMenuWithViewPager extends Activity {
	private DrawerLayout drawerLayout;
	private ListView drawerListLeft, drawerListRight;
	private ActionBarDrawerToggle drawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] leftmenuTitles, rightmenuTitles;
	private SlidingMenuWithViewPager mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding_menu_viewpager);
		mContext = this;
		ViewPager vp = (ViewPager) findViewById(R.id.pager);
		CustomPagerAdapter adapter = new CustomPagerAdapter(mContext);
		vp.setAdapter(adapter);

		mTitle = mDrawerTitle = getTitle();
		leftmenuTitles = getResources().getStringArray(R.array.left_menu);
		rightmenuTitles = getResources().getStringArray(R.array.right_menu);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerListLeft = (ListView) findViewById(R.id.left_menu);
		drawerListRight = (ListView) findViewById(R.id.right_menu);
		drawerListLeft.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, leftmenuTitles));
		drawerListRight.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, rightmenuTitles));
		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

		};
		
		drawerListLeft.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Closing the drawer
				drawerLayout.closeDrawer(drawerListLeft);

			}
		});
		
		drawerListRight.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Closing the drawer
				drawerLayout.closeDrawer(drawerListRight);

			}
		});
		drawerLayout.setDrawerListener(drawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			boolean drawerOpenRight = drawerLayout
					.isDrawerOpen(drawerListRight);
			if (drawerOpenRight) {
				drawerLayout.closeDrawer(drawerListRight);
			}

			return true;
		} else {
			switch (item.getItemId()) {
			case R.id.action_settings:
				// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
				boolean drawerOpen = drawerLayout.isDrawerOpen(drawerListRight);
				if (drawerOpen) {
					// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
					drawerLayout.closeDrawer(drawerListRight);
				} else if (drawerLayout.isDrawerOpen(drawerListLeft)) {
					drawerLayout.closeDrawer(drawerListLeft);

				} else {
					// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
					drawerLayout.openDrawer(drawerListRight);
				}

			}
		}

		return super.onOptionsItemSelected(item);
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		drawerListLeft.setItemChecked(position, true);
		drawerLayout.closeDrawer(drawerListLeft);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawerToggle.syncState();
	}

}

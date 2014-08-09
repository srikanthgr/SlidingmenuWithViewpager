package com.srikanthgr.slidingmenuwithviewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srikanthgr.slidingmenuwithviewpager.R;

public class CustomPagerAdapter extends PagerAdapter {

	Context context;

	int index = 2;

	public CustomPagerAdapter(Context context) {
		this.context = context;
		this.index = 2;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater inflater = (LayoutInflater.from(container
				.getContext()));
		View view = null;
		if (position == 0) {
			view = inflater.inflate(R.layout.page_a, null);
			((ViewPager) container).addView(view);

		} else {
			
			view = inflater.inflate(R.layout.page_b, null);
			((ViewPager) container).addView(view);
		}
		return view;
	}

	@Override
	public int getCount() {
		return index;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

}

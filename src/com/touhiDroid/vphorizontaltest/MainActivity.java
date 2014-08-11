package com.touhiDroid.vphorizontaltest;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private PagerContainer mContainer;
	private SampleAdapter adapter;
	private static int NUM_PAGE;
	private ViewPager pager;

	private ArrayList<View> viewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		NUM_PAGE = new Random().nextInt(10) + 3;

		mContainer = (PagerContainer) findViewById(R.id.pager_container);
		pager = mContainer.getViewPager();
		viewList = new ArrayList<View>();

		adapter = new SampleAdapter();
		pager.setAdapter(adapter);
		// Necessary or the pager will only have one extra page to show
		// make this at least however many pages you can see
		pager.setOffscreenPageLimit(1);
		// A little space between pages
		pager.setPageMargin(15);

		// If hardware acceleration is enabled, you should also remove
		// clipping on the pager for its children.
		pager.setClipChildren(false);
	}

	private class SampleAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TextView view = new TextView(MainActivity.this);
			// view.setText("Item " + position);
			// view.setGravity(Gravity.CENTER);
			// view.setBackgroundColor(Color.argb(255, position * 50,
			// position * 10, position * 50));
			Log.d("T_instantiateItem", "Item " + position);
			final View view = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
					R.layout.sample_frag, container, false);
			TextView tv = (TextView) view.findViewById(R.id.tv1);
			tv.setText("Page: " + position);

			Button ivTop = (Button) view.findViewById(R.id.btn);
			final ViewGroup container2 = container;
			final int pos = position;
			ivTop.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					container2.removeView(view);
					NUM_PAGE--;
					if (pos > -1 && pos < viewList.size())
						viewList.remove(pos);
					notifyDataSetChanged();
				}
			});
			container.addView(view);
			viewList.add(view);
			Log.d("T_instantiateItem", "Item " + position + ", viewList.size()=" + viewList.size());
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public int getItemPosition(Object object) {
			int index = viewList.indexOf(object);
			if (index == -1)
				return POSITION_NONE;
			else
				return index;
		}

		@Override
		public int getCount() {
			return NUM_PAGE;
		}

		// @Override
		// public float getPageWidth(int position) {
		// return super.getPageWidth(position);
		// }

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return (view == object);
		}

		// public int addView(View v) {
		// return addView(v, viewList.size());
		// }
		//
		// public int addView(View v, int position) {
		// viewList.add(position, v);
		// return position;
		// }
		//
		// public int removeView(ViewPager pager, View v) {
		// return removeView(pager, viewList.indexOf(v));
		// }
		//
		// public int removeView(ViewPager pager, int position) {
		// // ViewPager doesn't have a delete method; the closest is to set the
		// // adapter
		// // again. When doing so, it deletes all its views. Then we can
		// // delete the view
		// // from from the adapter and finally set the adapter to the pager
		// // again. Note
		// // that we set the adapter to null before removing the view from
		// // "views" - that's
		// // because while ViewPager deletes all its views, it will call
		// // destroyItem which
		// // will in turn cause a null pointer ref.
		// pager.setAdapter(null);
		// viewList.remove(position);
		// pager.setAdapter(this);
		//
		// return position;
		// }

	}
}

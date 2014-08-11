package com.touhiDroid.vphorizontaltest;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.touhiDroid.vphorizontaltest.view.CompoundLinearLayout;

public class MainActivity2 extends Activity {

	private PagerContainer mContainer;
	private ViewPager pager = null;
	private MainPagerAdapter pagerAdapter = null;
	private static int NUM_PAGE;

	Button bAdd;

	// public static int positionToRemove = -1;
	public static View viewToRemove = null;

	// -----------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		NUM_PAGE = new Random().nextInt(10) + 3;

		// ... do other initialization, such as create an ActionBar ...

		pagerAdapter = new MainPagerAdapter();
		mContainer = (PagerContainer) findViewById(R.id.pager_container);
		pager = mContainer.getViewPager();
		pager.setOffscreenPageLimit(1);
		pager.setAdapter(pagerAdapter);

		bAdd = (Button) findViewById(R.id.b_add);
		bAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addView((View) new CompoundLinearLayout(MainActivity2.this, 0), 0);

			}
		});

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				Log.e(">>>>", "onPageSelected - position " + position);

			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// Log.e(">>>>",
				// "onPageScrolled - position, positionOffset, positionOffsetPixels = "
				// + position + ", "
				// + positionOffset + ", " + positionOffsetPixels);

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				Log.e(">>>>", "onPageScrollStateChanged - state, positionToRemove = " + state + ", ");
				if (state == ViewPager.SCROLL_STATE_IDLE && viewToRemove != null) {
					// View view = pagerAdapter.getView(positionToRemove);

					// Log.e("adhgfdhsmhfvsd", (view == null) ? "null view" :
					// "view id = " + view.getId());
					removeView(viewToRemove);
					viewToRemove = null;
				}

			}
		});

		// Create an initial view to display; must be a subclass of FrameLayout.
		// LayoutInflater inflater = getLayoutInflater();

		// CompoundLinearLayout view = new
		// CompoundLinearLayout(MainActivity2.this);

		addView((View) new CompoundLinearLayout(MainActivity2.this, 0));
		addView((View) new CompoundLinearLayout(MainActivity2.this, 1));
		addView((View) new CompoundLinearLayout(MainActivity2.this, 2));
		addView((View) new CompoundLinearLayout(MainActivity2.this, 3));
		addView((View) new CompoundLinearLayout(MainActivity2.this, 4));
		addView((View) new CompoundLinearLayout(MainActivity2.this, 5));

		pager.setCurrentItem(0);

		// pagerAdapter.addView(new CompoundLinearLayout(MainActivity2.this),
		// 0);
		// pagerAdapter.addView(new CompoundLinearLayout(MainActivity2.this),
		// 0);
		// pagerAdapter.addView(new CompoundLinearLayout(MainActivity2.this),
		// 0);
	}

	// -----------------------------------------------------------------------------
	// Here's what the app should do to add a view to the ViewPager.
	public void addView(View newPage) {
		int pageIndex = pagerAdapter.addView(newPage);
		pagerAdapter.notifyDataSetChanged();
		// You might want to make "newPage" the currently displayed page:
		pager.setCurrentItem(pageIndex);
	}

	public void addView(View newPage, int position) {
		int pageIndex = pagerAdapter.addView(newPage, position);
		pagerAdapter.notifyDataSetChanged();
		// You might want to make "newPage" the currently displayed page:
		pager.setCurrentItem(pageIndex);
	}

	// -----------------------------------------------------------------------------
	// Here's what the app should do to remove a view from the ViewPager.
	public void removeView(View defunctPage) {
		int pageIndex = pagerAdapter.removeView(pager, defunctPage);
		// You might want to choose what page to display, if the current page
		// was "defunctPage".
		if (pageIndex == pagerAdapter.getCount())
			pageIndex--;
		pager.setCurrentItem(pageIndex);
	}

	public void setNextPage(View pageToShow) {
		int nextPageIndex = pagerAdapter.getItemPosition(pageToShow) + 1;

		if (nextPageIndex == pagerAdapter.getCount())
			nextPageIndex -= 2;

		Log.e(">>>>>>>>", "nextPageIndex = " + nextPageIndex);
		pager.setCurrentItem(nextPageIndex);

		if (nextPageIndex < 0) {
			removeView(pageToShow);
		}
	}

	// -----------------------------------------------------------------------------
	// Here's what the app should do to get the currently displayed page.
	public View getCurrentPage() {
		return pagerAdapter.getView(pager.getCurrentItem());
	}

	// -----------------------------------------------------------------------------
	// Here's what the app should do to set the currently displayed page.
	// "pageToShow" must
	// currently be in the adapter, or this will crash.
	public void setCurrentPage(View pageToShow) {
		pager.setCurrentItem(pagerAdapter.getItemPosition(pageToShow));
	}

}

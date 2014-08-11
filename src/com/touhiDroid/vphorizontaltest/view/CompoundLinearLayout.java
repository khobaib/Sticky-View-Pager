package com.touhiDroid.vphorizontaltest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.touhiDroid.vphorizontaltest.MainActivity2;
import com.touhiDroid.vphorizontaltest.R;

public class CompoundLinearLayout extends LinearLayout {

	View view;
	int pos;
	public boolean markAsDelete = false;

	public CompoundLinearLayout(Context context, int pos) {
		super(context);
		this.pos = pos;

		init(context);
	}

	public CompoundLinearLayout(Context context, AttributeSet attrs) {
		super(context);

		init(context);
	}

	private void init(final Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = (View) inflater.inflate(R.layout.sample_frag, this, true);

		TextView tv = (TextView) view.findViewById(R.id.tv1);
		tv.setText("Page: " + pos);

		Button ivTop = (Button) view.findViewById(R.id.btn);
		ivTop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Toast.makeText(context, "hi, I am at pos = " + pos,
				// Toast.LENGTH_SHORT).show();
				// MainActivity2.positionToRemove = pos;
				MainActivity2.viewToRemove = view;
				markAsDelete = true;
				((MainActivity2) getContext()).setNextPage(view);

				// ((MainActivity2) getContext()).removeView(view);

				// Handler h = new Handler();
				// h.postDelayed(new Runnable() {
				//
				// @Override
				// public void run() {
				// // Method in Activity that removes the current object (I
				// // believe this method is working fine and yes, it calls
				// // notifyDataSetChanged())
				// // parent.removeObject(currentObject);
				// ((MainActivity2) getContext()).removeView(view);
				// }
				// }, 1000);
				// PagerContainer.mNeedsRedraw = true;
				// ((MainActivity2)view.getParent()).removeView(view);
			}
		});

	}
}

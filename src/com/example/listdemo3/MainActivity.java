package com.example.listdemo3;



import android.os.Bundle;
import android.os.Handler;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;

import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity implements
		ListView.OnScrollListener {
	private Demo2Adapter adapter=null;
	private final class RemoveWindow implements Runnable {
		public void run() {
			removeWindow();
		}
	}

	private RemoveWindow mRemoveWindow = new RemoveWindow();
	Handler mHandler = new Handler();
	private WindowManager mWindowManager;
	private TextView mDialogText;
	private boolean mShowing;
	private boolean mReady;
	private char mPrevLetter = Character.MIN_VALUE;
	char[] strA = { 'A', 'B', 'C', 'D', 'E' };
	String[] strB = { "Asef", "Bss", "Be", "Cc", "Ccc", "Dd", "De", "Ea",
			"Asef", "Bss", "Be", "Cc", "Ccc", "Dd", "De", "Ea", "Asef", "Bss",
			"Be", "Cc", "Ccc", "Dd", "De", "Ea", "Asef", "Bss", "Be", "Cc",
			"Ccc", "Dd", "De", "Ea", "Asef", "Bss", "Be", "Cc", "Ccc", "Dd",
			"De", "Ea", "Asef", "Bss", "Be", "Cc", "Ccc", "Dd", "De", "Ea",
			"Asef", "Bss", "Be", "Cc", "Ccc", "Dd", "De", "Ea", "Asef", "Bss",
			"Be", "Cc", "Ccc", "Dd", "De", "Ea", "Asef", "Bss", "Be", "Cc",
			"Ccc", "Dd", "De", "Ea", "Asef", "Bss", "Be", "Cc", "Ccc", "Dd",
			"De", "Ea", "Asef", "Bss", "Be", "Cc", "Ccc", "Dd", "De", "Ea",
			"Asef", "Bss", "Be", "Cc", "Ccc", "Dd", "De", "Ea" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		adapter=new Demo2Adapter(this);
		for (int i = 0; i < strA.length; i++) {
			 adapter.addSeparatorItem(String.valueOf(strA[i]));
			for(int j=0;j<strB.length;j++){
				
				if(strB[j].charAt(0)==strA[i]||strB[j].charAt(0)==strA[i]+26){
					Log.v("item", String.valueOf(strB[j].charAt(0)));
					 adapter.addItem(strB[j]);
				}
			}}
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

		// Use an existing ListAdapter that will map an array
		// of strings to TextViews
		setListAdapter(adapter);

		getListView().setOnScrollListener(this);

		LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mDialogText = (TextView) inflate.inflate(R.layout.list_position, null);
		mDialogText.setVisibility(View.INVISIBLE);

		mHandler.post(new Runnable() {

			public void run() {
				mReady = true;
				WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
						LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT,
						WindowManager.LayoutParams.TYPE_APPLICATION,
						WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
								| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
						PixelFormat.OPAQUE);
				lp.gravity = Gravity.TOP;
				mWindowManager.addView(mDialogText, lp);
			}
		});
		new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
			}
		};
	}

	@Override
	protected void onResume() {
		super.onResume();
		mReady = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		removeWindow();
		mReady = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWindowManager.removeView(mDialogText);
		mReady = false;
	}


	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (mReady) {
			char firstLetter = adapter.getItem(firstVisibleItem).charAt(0);

		//	if (!mShowing && firstLetter != mPrevLetter) {

		//		mShowing = true;
				mDialogText.setVisibility(View.VISIBLE);
		//	}
			mDialogText.setText(((Character) firstLetter).toString());
			mHandler.removeCallbacks(mRemoveWindow);
			// mHandler.postDelayed(mRemoveWindow, 3000);
			mPrevLetter = firstLetter;
		}
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
	
	}

	private void removeWindow() {
		if (mShowing) {
			mShowing = false;
			mDialogText.setVisibility(View.INVISIBLE);
		}
	}

	private String[] mStrings = Cheeses.sCheeseStrings;
}

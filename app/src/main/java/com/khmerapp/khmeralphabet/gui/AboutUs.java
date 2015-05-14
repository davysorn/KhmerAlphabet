package com.khmerapp.khmeralphabet.gui;

import com.khmerapp.khmeralphabet.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class AboutUs extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_about_us);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){ 
	        
	    int action = MotionEventCompat.getActionMasked(event);
	    String DEBUG_TAG = ">>>>>";
	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	            Log.d(DEBUG_TAG,"Action was DOWN");
	        	AboutUs.this.finish();
	        	return true;
	    }
	    return true;
	}
	
}

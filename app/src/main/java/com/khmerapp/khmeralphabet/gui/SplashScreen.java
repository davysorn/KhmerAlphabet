package com.khmerapp.khmeralphabet.gui;

import com.khmerapp.khmeralphabet.R;
import com.khmerapp.khmeralphabet.R.anim;
import com.khmerapp.khmeralphabet.R.id;
import com.khmerapp.khmeralphabet.R.layout;
import com.khmerapp.khmeralphabet.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_splash_screen);
		Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
		fadeIn.setAnimationListener( new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				startActivity(new Intent(getApplicationContext(), MainMenuScreen.class));
						SplashScreen.this.finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ImageView bayon = (ImageView) findViewById(R.id.imageViewBayon);
		bayon.setAnimation(fadeIn);
		
		//startActivity(new Intent(getApplicationContext(), MenuMain.class));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.splash_screen, menu);
		//return true;
		return false;
	}

}

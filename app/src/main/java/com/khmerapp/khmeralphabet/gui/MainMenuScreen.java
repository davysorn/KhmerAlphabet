package com.khmerapp.khmeralphabet.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.khmerapp.khmeralphabet.R;
import com.khmerapp.khmeralphabet.dto.KhmerAlphabetData;

public class MainMenuScreen extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main_menu_screen);
		
		ImageView btnKhmerConsonant = (ImageView) findViewById(R.id.imageViewConsonantLearning);
		//ImageView btnKhmerVowel = (ImageView) findViewById(R.id.imageViewVowel);
		ImageView btnKhmerVowel = (ImageView) findViewById(R.id.imageViewVowelLearning);
		ImageView btnExercise=(ImageView)findViewById(R.id.imageExercise);
		ImageView btnExerciseVowel=(ImageView)findViewById(R.id.imageExerciseVowel);
		
	
		btnKhmerConsonant.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), KhmerAlphabetDisplayScreen.class);
				intent.putExtra( KhmerAlphabetData.VIEW_TYPE, KhmerAlphabetData.TYPE_CONSONANT );
				startActivity(intent);
			}
		});
		
		/*btnKhmerVowel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), KhmerAlphabetDisplayScreen.class);
				intent.putExtra( KhmerAlphabetData.VIEW_TYPE, KhmerAlphabetData.TYPE_VOWEL );
				startActivity(intent);
			}
		});
		*/
		
		btnKhmerVowel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), KhmerAlphabetDisplayScreen.class);
				intent.putExtra( KhmerAlphabetData.VIEW_TYPE, KhmerAlphabetData.TYPE_VOWEL);
				startActivity(intent);
			}
		});
		btnExercise.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), khmerAlphabetExercise.class);
				intent.putExtra( KhmerAlphabetData.VIEW_TYPE, KhmerAlphabetData.TYPE_CONSONANT );
				startActivity(intent);
			}
			
			
		});
		btnExerciseVowel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), khmerAlphabetExercise.class);
				intent.putExtra( KhmerAlphabetData.VIEW_TYPE, KhmerAlphabetData.TYPE_VOWEL );
				startActivity(intent);
			}
			
			
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu_screen, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            return true;
	        case R.id.action_about_us:
	        	displayAboutUsDialog();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		moveTaskToBack(true); //Prevet Back Key Pressed
	}


	private void displayAboutUsDialog() {
		Intent intent = new Intent(getApplicationContext(), AboutUs.class);
		startActivity(intent);
	}
	
}

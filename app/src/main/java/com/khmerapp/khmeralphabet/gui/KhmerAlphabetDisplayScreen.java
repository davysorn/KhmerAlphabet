package com.khmerapp.khmeralphabet.gui;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.khmerapp.khmeralphabet.R;
import com.khmerapp.khmeralphabet.dto.KhmerAlphabetData;
import com.khmerapp.khmeralphabet.gui.adapter.ImageAdapter;

/*
 * KhmerAlphabetDisplayScreen is used to display all khmer 33 consonants, 23 vowels, or 10 numbers
 * It needs data from the outside to display in the gridview.
 * For example, if we want to display khmer consonants, we just provide khmer 33 consonants data for this class.
 */
public class KhmerAlphabetDisplayScreen extends Activity {
	int dataType = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_khmer_alphabet_display_screen);
		
		
		
		Bundle extras = getIntent().getExtras();
		if ( extras != null ) {
		    dataType = extras.getInt( KhmerAlphabetData.VIEW_TYPE );
		  
		}

		GridView gridViewKhmerAlphabet = (GridView) findViewById(R.id.gridViewKhmerAlphabet);
		gridViewKhmerAlphabet.setAdapter( new ImageAdapter( this, dataType ) );
		gridViewKhmerAlphabet.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {				
				Intent intent = new Intent(getApplicationContext(), KhmerAlphabetDisplayDetailScreen.class);
				intent.putExtra( KhmerAlphabetData.VIEW_TYPE, dataType );
				intent.putExtra( KhmerAlphabetData.VIEW_KEY, position );
				startActivity( intent );	
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

}

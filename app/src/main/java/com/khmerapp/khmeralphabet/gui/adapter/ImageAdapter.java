package com.khmerapp.khmeralphabet.gui.adapter;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.khmerapp.khmeralphabet.R;
import com.khmerapp.khmeralphabet.dto.AlphabetData;
import com.khmerapp.khmeralphabet.dto.KhmerAlphabetData;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private int dataType;
	
    /*private Integer[] mThumbIds; *//* = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };*/

	private ArrayList<Integer> imageIds = new ArrayList<Integer>();
	
    public ImageAdapter(Context c, int type) {
        mContext = c;
        dataType = type;
        //mThumbIds = (Integer []) KhmerAlphabetData.getInstance().getDataType( dataType ).keySet().toArray( new Integer[]{} );
    //    Log.i("before list>>>>>>>>>>>>>>>>>>>", "");
        ArrayList<AlphabetData> list = KhmerAlphabetData.getInstance().getDataType( dataType );
     //   Log.i("list>>>>>>>>>>>>>>>>>>>", "" + list );

        Iterator<AlphabetData> it = list.iterator();
        for( ; it.hasNext(); ) {
        	AlphabetData data = it.next();
        	if( data != null ) {        		
        		imageIds.add( data.getImageId() );
        	}
        }
    }

	@Override
	public int getCount() {
		//return mThumbIds.length;
		return imageIds.size();
	}

	@Override
	public Object getItem(int arg0) {
		 //return mThumbIds[ arg0 ];
		return imageIds.get( arg0 );
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView( mContext );
            imageView.setLayoutParams( new GridView.LayoutParams(90, 50) );// set image size 
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP);  
           
            imageView.setPadding( 2, 3, 3, 3 );
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource( mThumbIds[position] );
        imageView.setImageResource( imageIds.get( position ) );
        return imageView;
	}

}

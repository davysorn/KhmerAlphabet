package com.khmerapp.khmeralphabet.gui.custom;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.Path;

import android.graphics.PointF;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.khmerapp.khmeralphabet.R;

@SuppressLint("NewApi")
public class AlphabetDrawingCanvas extends View {
	//	private static final Context Context = null;
	//drawing path
	private Path drawPath;
	//drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	//initial color
	//private int paintColor = 0xFF0066, paintAlpha = 255;
	public int paintColor= 0xbc084c ,paintAlpha= 255;
	private float brushSize;
	//canvas
	private Canvas drawCanvas;
	//canvas bitmap
	public Bitmap canvasBitmap;	
	public Bitmap canvasBitmap2; 
	private float RADIUS = 3;
	private List<PointF[]> lsPoints;
	private PointF[] pts = null;
	private PointF pt = null;
	private PointF touchPoint=null;
	private int pointIndex = 0;
	private int listIndex = 0;
	float scale_rate = 1;
	int dpi = 0;
	public float scale;	
	AlphabetDrawingCanvas ra;
	float touchX;
	float touchY;
	public ArrayList<PointF>recordPoints;
	public ArrayList<PointF>recordDotted;
	public int cnt=0;
	public double dist;
	int n=0;
	public AlphabetDrawingCanvas(Context context, List<PointF[]> lsPoints) {
		super(context);
		setupDrawing();
		this.lsPoints = lsPoints;
		getScaleRate();
	}

	//setup drawing
	private void setupDrawing(){
		//prepare for drawing and setup paint stroke properties
		brushSize = getResources().getInteger(R.integer.small_size);
		drawPath = new Path();
		drawPaint = new Paint();
		drawPaint.setColor(paintColor);
		drawPaint.setAlpha(paintAlpha);
		drawPaint.setAntiAlias(true);
		drawPaint.setStyle(Paint.Style.STROKE);		
		//drawPaint.setShadowLayer(5, 0, 0, Color.YELLOW);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		//canvasPaint.setShadowLayer(10, 0, 0, Color.RED);
		recordPoints=new ArrayList<PointF>();

	}
	//size assigned to view
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
		getScaleRate();
	}

	private void getScaleRate() {
		Configuration config = getResources().getConfiguration();
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		dpi = (int)displayMetrics.densityDpi;
		//int dpi = config.densityDpi;

		if ((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_LARGE) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
			scale_rate = (float) 1.5;
		} else if ((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_NORMAL) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
			scale_rate = 1;
		} else if ((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_SMALL) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
			scale_rate = (float) 0.75;
		} else if ((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_XLARGE) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
			scale_rate = 2;
		}
		scale = scale_rate * ( dpi / ( 160 * scale_rate) );
	}

	//draw the view - will be called after touch event
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);

		//Drawing points on the canvas
		if( lsPoints != null ) {
			Iterator<PointF[]> it = lsPoints.iterator();
			while( it.hasNext() ) {
				PointF[] pts = (PointF[]) it.next();
				for( int i=0; i < pts.length; i++ ) {
					PointF pt = pts[i];
					// canvas.drawCircle(pt.x*scale, pt.y*scale,drawPaint);
					canvas.drawCircle(pt.x*scale, pt.y*scale,1, drawPaint);
					// recordDotted.add(new PointF(pt.x*scale,pt.y*scale));
					//canvas.drawCircle(pt.x, pt.y, RADIUS, drawPaint);
				}
			}
		}
		//Toast.makeText(getContext(), "given point"+ drawPath , Toast.LENGTH_SHORT).show();
	}

	//register user touches as drawing action
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchX = event.getX();
		touchY = event.getY();
		
		//respond to down, move and up events
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			//checking whether the touching location is inside the circle
			//if( isInCircle( pt, touchPoint ) && pointIndex > 0 ) {
			drawPath.moveTo( touchX, touchY );
			recordPoints.add(new PointF(touchX, touchY));//need to add as .add(new PointF)	
			//}
			//	Log.i(VIEW_LOG_TAG, "touchX>>" + touchX + "TouchY>>" + touchY);
			break;
		case MotionEvent.ACTION_MOVE:

			//if( isInCircle( pt, touchPoint ) && pointIndex > 0 ) {
			drawPath.lineTo( touchX, touchY);
			drawCanvas.drawPath(drawPath, drawPaint);
			recordPoints.add(new PointF(touchX, touchY));
			pointIndex++;
			//}
			//	Log.i(VIEW_LOG_TAG, "touchX>>" + touchX + "TouchY>>" + touchY);
			break;
		case MotionEvent.ACTION_UP:

				//	if( isInCircle( pt, touchPoint ) && pointIndex< pts.length ){
			pointIndex--; 
					
		default:
			return false;
		}
		
	/*	if( lsPoints != null ) {
			Iterator<PointF[]> it = lsPoints.iterator();
			n = 0;
			while( it.hasNext() ) {
				PointF[] pts = (PointF[]) it.next();
				for( int i=0; i < pts.length; i++ ) {					

					if (n == 0) {				
						drawPath.moveTo(pts[i].x*scale, pts[i].y*scale);				 

						n++;
					} 
					else if(n>0){
						
						if(n != 3) {
							drawPath.lineTo(pts[i].x*scale, pts[i].y*scale);
							Log.d("draw", n + "," + pts[i].x*scale + "," + pts[i].y*scale);
							drawCanvas.drawPath(drawPath, drawPaint);
						} else {
							drawPath.moveTo(pts[i].x*scale, pts[i].y*scale);
						}
						 
						n++;
					} 
					else{
						if (pointIndex<pts.length){
						pointIndex--;
						}
					}		 
					
					
				//	Log.i(VIEW_LOG_TAG, "touchX>>" + pts[i].x+pts[i].y);
	
				}}}
				*/
		

		invalidate(); // allow to draw
		
		
		return true;	
				

	} 
	//*******************************Give Score
	// Draw alphabet item as solution
	public void drawSolutionAsImage(String name){

		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		try {
			File file_solution = new File(path + File.separator + name + ".png");
			if (!file_solution.exists()) {
				file_solution.createNewFile();
				Log.d("create file3", "file don't exist *************");

				canvasBitmap2 = Bitmap.createBitmap(canvasBitmap.getWidth(), canvasBitmap.getHeight(), Bitmap.Config.ARGB_8888);
				drawCanvas = new Canvas(canvasBitmap2);
				drawPaint.setColor(0xffbc084c);

				if (lsPoints != null) {
					Iterator<PointF[]> it = lsPoints.iterator();
					while (it.hasNext()) {
						PointF[] pts = (PointF[]) it.next();
						for (int i = 0; i < pts.length; i++) {
							if (i == 0) {
								drawPath.moveTo(pts[i].x * scale, pts[i].y * scale);
							} else {
								drawPath.lineTo(pts[i].x * scale, pts[i].y * scale);
								Log.d("draw", n + "," + pts[i].x * scale + "," + pts[i].y * scale);
								drawCanvas.drawPath(drawPath, drawPaint);
							}
						}
					}
					invalidate();
				}

				FileOutputStream ostream3 = new FileOutputStream(file_solution);
				canvasBitmap2.compress(CompressFormat.PNG, 100, ostream3);
				ostream3.close();
			}
		}catch(Exception e){
			Log.d("create file3", "throw exception ************888");
			e.printStackTrace();
		}
	}

	//end of give score methods    
	// redraw for showing the child's action      
	public void reDraw()
	{
		Bitmap c = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		Canvas redraw = new Canvas(c);
		drawPaint.setColor(0xFF4CC417);

		// TODO Auto-generated method stub			
		for(final PointF point: recordPoints) {	
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cnt == 0) {				
				drawPath.moveTo(point.x, point.y);				 

				
			} else if(cnt==0){				
				// do something here like draw text;

				drawPath.lineTo(point.x, point.y);
				redraw.drawPath(drawPath, drawPaint);
				cnt++;
			} else{

				pointIndex--;
			}		                
		}

		invalidate();		

	}	

	private boolean isInCircle(PointF pt1, PointF pt2 ) {// allow user in specific distance for drawing
		//p1 is the fix draw of circle , pt2 it return from touch 
		if( pt1 == null ) return false;
		float distance = (float) Math.sqrt( Math.pow(pt1.x*scale - pt2.x, 2) + Math.pow(pt1.y*scale - pt2.y, 2));	
		//Log.i(VIEW_LOG_TAG, "touchX>>" + distance);
		//Toast.makeText(getContext(), ""+ (distance < RADIUS) , Toast.LENGTH_SHORT).show();	
		return distance < RADIUS;
	}
	public void setColor(int color) {
		drawPaint.setColor(color);

	}
	public void onClearDraw(View cleardraw){//clear draw
		drawPath.reset();
		postInvalidate();

	}


}

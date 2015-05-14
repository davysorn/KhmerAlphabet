package com.khmerapp.khmeralphabet.gui;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.khmerapp.khmeralphabet.R;
import com.khmerapp.khmeralphabet.R.raw;
import com.khmerapp.khmeralphabet.dto.AlphabetData;
import com.khmerapp.khmeralphabet.dto.KhmerAlphabetData;
import com.khmerapp.khmeralphabet.da.MySQLiteHelper;




public class khmerAlphabetExercise extends Activity {


	MediaPlayer mp;
	int dataType;
	int dataKey;	
	Random random;	
	ArrayList<AlphabetData> data;
	ImageView exPlaysound,ex_next;
	ArrayList<AlphabetData> quizImageList;//number of images answer in the quiz
	ArrayList<AlphabetData> listOfSounds;
	private final ArrayList<ImageView> arrayListImageViews=new ArrayList<ImageView>();	
	private int numCorrectAnswers;
	private int totalGuesses;
	private int indexNextQuiz=0;
	LinearLayout displayImages;
	ImageView iv;
	TextView ex_answer;			
	private Handler handler; // used to delay loading next quiz
	AlphabetData correctAnswer;
	MySQLiteHelper db;
	private Animation shakeAnimation; // animation for incorrect guess
	//	AlphabetData data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_khmer_alphabet_exercise);	

		Bundle extras = getIntent().getExtras();
		if ( extras != null ) {
			dataType = extras.getInt( KhmerAlphabetData.VIEW_TYPE );
		}
		data=KhmerAlphabetData.getInstance().getDataType(dataType);
		MySQLiteHelper db = new MySQLiteHelper(this);
		/*int u=10;
		double k=99.99;
		db.insertMark(u, k);*/
		/*arrayListImageViews.add((ImageView)findViewById(R.id.ex_con1));
		arrayListImageViews.add((ImageView)findViewById(R.id.ex_con2));
		arrayListImageViews.add((ImageView)findViewById(R.id.ex_con3));
		arrayListImageViews.add((ImageView)findViewById(R.id.ex_con4));*/
		// load the shake animation that's used for incorrect answers
		shakeAnimation = 
				AnimationUtils.loadAnimation(this, R.anim.incorrect_shake); 
		shakeAnimation.setRepeatCount(3); // animation repeats 3 times 
		exPlaysound=(ImageView)findViewById(R.id.exPlaysound);		
		random = new Random();
		quizImageList= new ArrayList<AlphabetData>();		
		mp=new MediaPlayer();
		handler = new Handler(); // used to perform delayed operations
		displayImages=(LinearLayout)findViewById(R.id.Answer);
		ex_answer=(TextView)findViewById(R.id.ex_answer);		
		resetQuiz();
		
	}
	// Create 10 answers for  Quiz 
	private void resetQuiz(){	 
		numCorrectAnswers=0;
		totalGuesses=0;
		quizImageList.clear();
		mp.start();
		int quiz=0;		
		while(quiz<10){
			int quizIndex=random.nextInt(data.size());
			AlphabetData image;			
			image=data.get(quizIndex);			
			if(!quizImageList.contains(image))
			{	
				quizImageList.add(image);
				quiz++;				
			}			
		}		
		nextQuiz();			
	}
	private void nextQuiz(){
		displayImages.removeAllViews();// clear view in LinearLayout		
	//	mp.start();
		ex_answer.setText("");
		correctAnswer=quizImageList.get(indexNextQuiz);//question:sound and image
		//int correctIdImage=correctAnswer.getImageId();
		AlphabetData al;
		ArrayList<AlphabetData> answers = new ArrayList<AlphabetData>();// for storing one correct image and 3 fault images
		quizImageList.remove(indexNextQuiz);// remove the index of correct answer from the list

		answers.add(correctAnswer);// add correct answer to arraylist
		int i=0;
		int cAnsIndex = data.indexOf( correctAnswer );// search indext of correct answer in data 
		while(i<3){// get only 3 wrong images
			Integer rndNextDisImage=random.nextInt(data.size());
			al=data.get(rndNextDisImage);			
			if(!answers.contains(al) ) { // add the wrong answer, make sure that not equal to correct answer
				//iv=new ImageView(getApplicationContext());
				//iv.setImageResource(al.getImageId());
				//displayImages.addView(iv);
				answers.add(al);
				i++;
			}
		}

		Collections.shuffle(answers);// shuffle the index of images 

		/*Iterator<AlphabetData> it = answers.iterator();
		while(it.hasNext() ){
			AlphabetData alpha = it.next();
		}*/

		// view the images
		for(int j=0; j<answers.size(); j++){
			ImageView iv=new ImageView(getApplicationContext());
			iv.setImageResource( answers.get(j).getImageId() );
			iv.setTag( answers.get(j).getImageId() );// useful for get imageId when user click on image by .getTag()
			displayImages.addView(iv);

			iv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					submit(v);
				}

			});
		}		

		//	arrayListImageViews.get(arrayListImageViews.size()-1).setImageResource(correctIdImage);	
		//	Collections.shuffle(arrayListImageViews);		

		//arrayListImageViews.get(correctIdImage);		
		//	Collections.shuffle(arrayListImageViews);
		// Collections.shuffle(Arrays.asList( arrayImages ));// Arrays=> used for covert arrayImages to list  
		// *** play the answer 
		exPlaysound.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final MediaPlayer mp = MediaPlayer.create(khmerAlphabetExercise.this, correctAnswer.getSoundId() );
				mp.start();
			}

		});	


	}

	public void submit(View v){		

		TextView exAnswer=(TextView)findViewById(R.id.ex_answer);
		exAnswer.setText("Image clicked"); 
		//	String disCorrectAnswer;
		//	String incorrect="incorrect";
		//ImageView item =(ImageView)v;
		//v.getTag();
		int	guess=(Integer)v.getTag();
		//	Toast.makeText(this,guess+ "showid", Toast.LENGTH_LONG);
		int answer=correctAnswer.getImageId();
		++totalGuesses; // increment the number of guesses the user has made

		if (guess==answer) 
		{
			++numCorrectAnswers; // increment the number of correct answers

			// display "Correct!" in green text
			exAnswer.setText(R.string.correct_answer);        

			// if the user has correctly identified 10 flags
			if (numCorrectAnswers == 10) 
			{
				// create a new AlertDialog Builder
			//	final AlertDialog.Builder popDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
				AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

				builder.setTitle("Quiz Result:"); // title bar string

				// set the AlertDialog's message to display game results
				builder.setMessage(String.format("%d %s, %.02f%% %s", 
						totalGuesses, getResources().getString(R.string.guesses), 
						(1000 / (double) totalGuesses), 
						getResources().getString(R.string.correct_answer)));

				builder.setCancelable(false); 

				// add "Reset Quiz" Button                              
				builder.setPositiveButton(R.string.reset_quiz,
						new DialogInterface.OnClickListener()                
				{                                                       
					public void onClick(DialogInterface dialog, int id) 
					{
						double result= (1000 / (double) totalGuesses);
						db=new MySQLiteHelper(khmerAlphabetExercise.this);
						db.insertMark(totalGuesses, result);
						resetQuiz();                                      
					} // end method onClick                              
				} // end anonymous inner class
						); // end call to setPositiveButton

				// create AlertDialog from the Builder
				AlertDialog resetDialog = builder.create();
				resetDialog.show(); // display the Dialog
			} // end if 
			else // answer is correct but quiz is not over 
			{
				// load the next flag after a 1-second delay
				handler.postDelayed(
						new Runnable()
						{ 
							@Override
							public void run()
							{
								nextQuiz();
							}
						}, 1000); // 1000 milliseconds for 1-second delay
			} // end else
		} // end if
		else // guess was incorrect  
		{    

			// display "Incorrect!" 
			exAnswer.setText(R.string.incorrect_answer);			
			//exAnswer.setTextColor(Color.RED);
			exAnswer.setAnimation(shakeAnimation);

		} // end else
	} // end method submitGuess	

}


























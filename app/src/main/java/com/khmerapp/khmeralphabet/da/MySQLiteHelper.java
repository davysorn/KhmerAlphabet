package com.khmerapp.khmeralphabet.da;


import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AlphabetKHDB";
   
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to insert result
		String Insert_Result_Quiz = "CREATE TABLE RESULTS ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"totalguesses int, "+
				"result double )";
		
		// insert result
		db.execSQL(Insert_Result_Quiz);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS RESULTS");
        
        // create fresh books table
        this.onCreate(db);
        Log.i(DATABASE_NAME,"database created");
	}
	
	
	// Books table name
    private static final String TABLE_RESULTS = "RESULTS";
    
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TotalGuesses = "totalguesses";
    private static final String KEY_Result = "result";  
    
	public void insertMark(int totalguesses,double  result){
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		 
		// 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TotalGuesses, totalguesses); // get title 
        values.put(KEY_Result, result); // get author
 
        // 3. insert
        db.insert(TABLE_RESULTS, // table
        		null, //nullColumnHack
        		values); // key/value -> keys = column names/ values = column values
        
        // 4. close
        db.close(); 
	}
	
	
}

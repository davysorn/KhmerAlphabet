package com.khmerapp.khmeralphabet.dto;

import java.util.ArrayList;
import java.util.List;
import android.graphics.PointF;

import com.khmerapp.khmeralphabet.R;

/*
 * KhmerAlphabetData is used to map all consonant, vowel and number key to the supplement data such as sounds and drawing points.
 */
public class KhmerAlphabetData {
	
	public static int TYPE_CONSONANT = 0;
	public static int TYPE_VOWEL = 1;
	public static int TYPE_NUMBER = 2;
	public static String VIEW_TYPE = "VIEW_TYPE";
	public static String VIEW_KEY = "VIEW_KEY";
	
	private static KhmerAlphabetData kad;
	/*
	private Map<Integer, AlphabetData> consonantData;
	private Map<Integer, AlphabetData> vowelData;
	private Map<Integer, AlphabetData> numberData;
	*/
	
	private ArrayList<AlphabetData> consonantData;
	private ArrayList<AlphabetData> vowelData;
	private ArrayList<AlphabetData> numberData;
	
	public KhmerAlphabetData() {
		/*
		consonantData = new Hashtable<Integer, AlphabetData>();
		vowelData = new Hashtable<Integer, AlphabetData>();
		numberData = new Hashtable<Integer, AlphabetData>();
		*/
		
		consonantData = new ArrayList<AlphabetData>();
		vowelData = new ArrayList<AlphabetData>();
		numberData = new ArrayList<AlphabetData>();
		
		// Should be List<LinkedList<Point>> ?
		List<PointF[]> con_01 = new ArrayList<PointF[]>();
		//con_01.add( new PointF[]{ new PointF(90,180)});
		
		con_01.add( new PointF[]{ new PointF(110,170),new PointF(110,130),new PointF(110,80),new PointF(150,80),new PointF(180,80),new PointF(180,130),new PointF(180,170)});
		con_01.add(new PointF[]{new PointF(110,40),new PointF(150,40),new PointF(180,40)});
		List<PointF[]> con_02 = new ArrayList<PointF[]>();
		con_02.add( new PointF[]{ new PointF(130,70), new PointF(110,70), new PointF(110,30),new PointF(150,30),new PointF(180,30),new PointF(180,80),new PointF(130,110),new PointF(110,110),new PointF(110,170),new PointF(180,170),new PointF(180,120) } );
		List<PointF[]> con_03 = new ArrayList<PointF[]>();
		con_03.add( new PointF[]{new PointF(145,130), new PointF(110,170), new PointF(110,130), new PointF(110,100),new PointF(110,70),new PointF(150,70),new PointF(180,70),new PointF(180,100),new PointF(180,130),new PointF(180,170) } );
		con_03.add(new PointF[]{new PointF(100,35), new PointF(145,35), new PointF(185,35)});
		List<PointF[]> con_04 = new ArrayList<PointF[]>();
		con_04.add(new PointF[]{new PointF(60,40), new PointF(85,40), new PointF(85,115),new PointF(85,160), new PointF(150,165), new PointF(150,115),new PointF(150,80), new PointF(125,60), new PointF(140,40),new PointF(165,40)});
		con_04.add(new PointF[]{new PointF(150,165), new PointF(190,165),new PointF(220,165),new PointF(220,115),new PointF(220,80),new PointF(190,60),new PointF(200,40),new PointF(230,40)});
		List<PointF[]> con_05 = new ArrayList<PointF[]>();
		con_05.add(new PointF[]{new PointF(100,90), new PointF(120,90), new PointF(120,170),new PointF(150,150), new PointF(190,170), new PointF(190,90),new PointF(170,60), new PointF(170,30), new PointF(200,30),new PointF(170,60), new PointF(120,60), new PointF(120,20)});
		List<PointF[]> con_06 = new ArrayList<PointF[]>();
		con_06.add(new PointF[]{new PointF(160,70), new PointF(190,70), new PointF(190,165),new PointF(110,165), new PointF(110,70), new PointF(90,53), new PointF(110,30), new PointF(200,30)});
		List<PointF[]> con_07 = new ArrayList<PointF[]>();
		con_07.add(new PointF[]{new PointF(165,75), new PointF(190,75), new PointF(190,170),new PointF(170,170), new PointF(130,140),new PointF(95,140), new PointF(120,170), new PointF(120,80), new PointF(95,55), new PointF(115,35), new PointF(190,35)});
		List<PointF[]> con_08 = new ArrayList<PointF[]>();
		con_08.add(new PointF[]{new PointF(190,70), new PointF(190,170), new PointF(150,140),new PointF(110,170), new PointF(110,70),new PointF(90,55), new PointF(110,35), new PointF(190,35), new PointF(190,15)});
		List<PointF[]> con_09 = new ArrayList<PointF[]>();
		con_09.add(new PointF[]{new PointF(80,160), new PointF(50,160), new PointF(50,30),new PointF(120,30), new PointF(120,160),new PointF(190,160),new PointF(190,70),new PointF(165,50),  new PointF(180,30),new PointF(205,30)});
		con_09.add(new PointF[]{new PointF(190,160), new PointF(260,160), new PointF(260,70),new PointF(230,50), new PointF(240,30), new PointF(270,30)});
		List<PointF[]> con_10 = new ArrayList<PointF[]>();
		con_10.add(new PointF[]{new PointF(90,160), new PointF(60,160), new PointF(60,90),new PointF(60,20), new PointF(100,50),new PointF(130,20),  new PointF(130,90),new PointF(130,160),new PointF(130,160),new PointF(130,90),new PointF(130,20),new PointF(163,20), new PointF(200,20), new PointF(200,90),new PointF(200,160)});
		con_10.add(new PointF[]{new PointF(175,190), new PointF(210,190), new PointF(210,230),new PointF(150,230), new PointF(100,210), new PointF(60,210)});
		List<PointF[]> con_11 = new ArrayList<PointF[]>();
		con_11.add(new PointF[]{new PointF(110,80), new PointF(110,170), new PointF(145,140),new PointF(180,175),new PointF(180,80),new PointF(130,30), new PointF(110,30), new PointF(110,50),new PointF(180,50),new PointF(180,20)});
		List<PointF[]> con_12 = new ArrayList<PointF[]>();
		con_12.add(new PointF[]{new PointF(100,30), new PointF(120,30), new PointF(120,100),new PointF(120,160),new PointF(190,160), new PointF(190,110), new PointF(190,70),new PointF(165,55),new PointF(175,40),new PointF(200,40),new PointF(200,16)});
		List<PointF[]> con_13 = new ArrayList<PointF[]>();
		con_13.add(new PointF[]{new PointF(130,80), new PointF(105,80), new PointF(105,30),new PointF(145,60),new PointF(190,30), new PointF(190,80), new PointF(105,115),new PointF(100,170),new PointF(130,170),new PointF(180,150),new PointF(180,120),new PointF(180,170)});
		List<PointF[]> con_14 = new ArrayList<PointF[]>();
		con_14.add(new PointF[]{new PointF(110,170), new PointF(80,170), new PointF(80,100),new PointF(80,30),new PointF(120,60), new PointF(150,30), new PointF(150,100),new PointF(150,140),new PointF(150,175),new PointF(130,140),new PointF(180,140), new PointF(220,170), new PointF(220,100),new PointF(220,70),new PointF(190,60), new PointF(210,30), new PointF(230,30)});
		List<PointF[]> con_15 = new ArrayList<PointF[]>();
		con_15.add(new PointF[]{new PointF(70,170), new PointF(40,170), new PointF(40,100),new PointF(40,30),new PointF(110,30), new PointF(110,100), new PointF(110,170),new PointF(145,140),new PointF(180,170),new PointF(180,100),new PointF(180,30), new PointF(250,30), new PointF(250,100),new PointF(250,170)});
		List<PointF[]> con_16 = new ArrayList<PointF[]>();
		con_16.add(new PointF[]{new PointF(108,155),new PointF(135,155),new PointF(135,170),new PointF(108,170), new PointF(110,80), new PointF(190,80),new PointF(190,170)});
		con_16.add(new PointF[]{new PointF(105,35), new PointF(190,35)});
		List<PointF[]> con_17 = new ArrayList<PointF[]>();
		con_17.add(new PointF[]{new PointF(158,75), new PointF(180,75),new PointF(180,170), new PointF(110,170),new PointF(110,80), new PointF(90,60),new PointF(110,30), new PointF(190,30),new PointF(190,15)});
		List<PointF[]> con_18 = new ArrayList<PointF[]>();		
		con_18.add(new PointF[]{new PointF(110,120), new PointF(110,170),new PointF(170,170), new PointF(170,110),new PointF(175,95), new PointF(195,95),new PointF(195,115), new PointF(170,110),new PointF(110,90),new PointF(110,30),new PointF(190,30),new PointF(190,70),new PointF(165,70)});
		List<PointF[]> con_19 = new ArrayList<PointF[]>();
		con_19.add(new PointF[]{new PointF(160,80), new PointF(180,80),new PointF(180,170), new PointF(145,140),new PointF(110,170), new PointF(110,80),new PointF(90,60), new PointF(110,30),new PointF(190,30)});
		List<PointF[]> con_20 = new ArrayList<PointF[]>();
		con_20.add(new PointF[]{new PointF(110,120), new PointF(110,170),new PointF(150,140),new PointF(180,170),new PointF(180,110), new PointF(110,90),new PointF(110,30), new PointF(180,30),new PointF(180,80)});
		List<PointF[]> con_21 = new ArrayList<PointF[]>();
		con_21.add(new PointF[]{new PointF(125,30),new PointF(100,30),new PointF(85,60), new PointF(110,80),new PointF(110,170), new PointF(180,170),new PointF(180,70), new PointF(155,57),new PointF(170,30),new PointF(190,30)});		
		List<PointF[]> con_22 = new ArrayList<PointF[]>();
		con_22.add(new PointF[]{new PointF(155,75), new PointF(180,75),new PointF(180,170),new PointF(145,140), new PointF(110,170),new PointF(110,80), new PointF(90,60),new PointF(110,30), new PointF(180,30),new PointF(180,15)});
		List<PointF[]> con_23 = new ArrayList<PointF[]>();
		con_23.add(new PointF[]{new PointF(135,170), new PointF(100,170),new PointF(100,100), new PointF(100,30),new PointF(145,60), new PointF(190,30),new PointF(190,100), new PointF(190,170)});
		List<PointF[]> con_24 = new ArrayList<PointF[]>();
		con_24.add(new PointF[]{new PointF(90,170), new PointF(110,170),new PointF(110,70), new PointF(185,70),new PointF(190,170), new PointF(100,30),new PointF(190,30)});
		List<PointF[]> con_25 = new ArrayList<PointF[]>();
		con_25.add(new PointF[]{new PointF(125,30), new PointF(100,30),new PointF(85,55), new PointF(110,70),new PointF(110,170), new PointF(180,170),new PointF(180,100), new PointF(140,100),new PointF(180,100),new PointF(180,70),new PointF(155,55),new PointF(168,30),new PointF(190,30)});
		List<PointF[]> con_26 = new ArrayList<PointF[]>();
		con_26.add(new PointF[]{new PointF(105,35), new PointF(80,35),new PointF(80,170), new PointF(150,170),new PointF(150,80), new PointF(130,60),new PointF(140,30), new PointF(165,30),new PointF(150,170),new PointF(220,170),new PointF(220,80),new PointF(190,60),new PointF(208,30),new PointF(232,30)});
		List<PointF[]> con_27 = new ArrayList<PointF[]>();
		con_27.add(new PointF[]{new PointF(105,155),new PointF(105,170), new PointF(130,170),new PointF(130,80), new PointF(105,55),new PointF(115,30), new PointF(140,30)});
		List<PointF[]> con_28 = new ArrayList<PointF[]>();
		con_28.add(new PointF[]{new PointF(110,170), new PointF(80,170),new PointF(80,40), new PointF(160,40),new PointF(160,40), new PointF(160,170),new PointF(220,170),new PointF(220,70),new PointF(195,60),new PointF(210,30),new PointF(230,30)});
		List<PointF[]> con_29 = new ArrayList<PointF[]>();		
		con_29.add(new PointF[]{new PointF(115,170),new PointF(140,170),new PointF(140,80),new PointF(110,60),new PointF(128,35),new PointF(150,35),new PointF(150,18)});
		List<PointF[]> con_30 = new ArrayList<PointF[]>();
		con_30.add(new PointF[]{new PointF(105,170), new PointF(80,170),new PointF(80,80), new PointF(100,70),new PointF(150,70), new PointF(150,170),new PointF(220,170),new PointF(220,80),new PointF(190,60),new PointF(205,30),new PointF(230,30)});
		con_30.add(new PointF[]{new PointF(100,70),new PointF(75,50),new PointF(85,35),new PointF(145,35)});
		List<PointF[]> con_31 = new ArrayList<PointF[]>();
		con_31.add(new PointF[]{new PointF(95,30), new PointF(73,30),new PointF(60,60), new PointF(80,80),new PointF(80,170), new PointF(150,170),new PointF(150,30), new PointF(220,30),new PointF(220,175)});
		List<PointF[]> con_32 = new ArrayList<PointF[]>();
		con_32.add(new PointF[]{new PointF(63,110), new PointF(63,150),new PointF(130,150), new PointF(130,80),new PointF(150,80), new PointF(130,100),new PointF(63,70), new PointF(63,20),new PointF(150,20),new PointF(150,60),new PointF(123,60)});
		con_32.add(new PointF[]{new PointF(123,180),new PointF(140,180),new PointF(140,230),new PointF(175,200),new PointF(210,230),new PointF(210,130),new PointF(210,60),new PointF(180,40),new PointF(195,15),new PointF(220,15)});
		List<PointF[]> con_33 = new ArrayList<PointF[]>();		
		con_33.add(new PointF[]{new PointF(100,170), new PointF(130,170),new PointF(130,80),new PointF(100,60), new PointF(115,30),new PointF(140,28)});
		con_33.add(new PointF[]{new PointF(170,170), new PointF(200,170),new PointF(200,115), new PointF(160,110),new PointF(200,115), new PointF(200,80),new PointF(170,60), new PointF(180,30),new PointF(205,30)});
		
		
		// consonant data
		consonantData.add( new AlphabetData( R.drawable.con_01, R.drawable.con_hd_01,R.raw.con_01, "con_01.gif", con_01 ) );// GridImage,Drawimage,sound,Guidedraw,point
		consonantData.add( new AlphabetData( R.drawable.con_02, R.drawable.con_hd_02, R.raw.con02, "con_02.gif", con_02 ) );
		consonantData.add( new AlphabetData( R.drawable.con_03, R.drawable.con_hd_03, R.raw.con03, "con_03.gif", con_03 ) );
		consonantData.add( new AlphabetData( R.drawable.con_04, R.drawable.con_hd_04, R.raw.con04, "con_04.gif", con_04));
		consonantData.add( new AlphabetData( R.drawable.con_05, R.drawable.con_hd_05, R.raw.con05, "con_05.gif", con_05) );
		consonantData.add( new AlphabetData( R.drawable.con_06, R.drawable.con_hd_06, R.raw.con06, "con_06.gif", con_06));
		consonantData.add( new AlphabetData( R.drawable.con_07, R.drawable.con_hd_07, R.raw.con07, "con_07.gif", con_07) );
		consonantData.add( new AlphabetData( R.drawable.con_08, R.drawable.con_hd_08,R.raw.con08, "con_08.gif", con_08) );
		consonantData.add( new AlphabetData( R.drawable.con_09, R.drawable.con_hd_09,R.raw.con09, "con_09.gif", con_09) );
		consonantData.add( new AlphabetData( R.drawable.con_10, R.drawable.con_hd_10, R.raw.con10, "con_10.gif", con_10) );
		consonantData.add( new AlphabetData( R.drawable.con_11, R.drawable.con_hd_11, R.raw.con11, "con_11.gif", con_11 ) );
		consonantData.add( new AlphabetData( R.drawable.con_12, R.drawable.con_hd_12,R.raw.con12, "con_12.gif", con_12 ) );
		consonantData.add( new AlphabetData( R.drawable.con_13, R.drawable.con_hd_13,R.raw.con13, "con_13.gif", con_13) );
		consonantData.add( new AlphabetData( R.drawable.con_14, R.drawable.con_hd_14, R.raw.con14, "con_14.gif", con_14) );
		consonantData.add( new AlphabetData( R.drawable.con_15, R.drawable.con_hd_15, R.raw.con15, "con_15.gif", con_15) );
		consonantData.add( new AlphabetData( R.drawable.con_16, R.drawable.con_hd_16, R.raw.con16, "con_16.gif", con_16) );
		consonantData.add( new AlphabetData( R.drawable.con_17, R.drawable.con_hd_17, R.raw.con17, "con_17.gif", con_17) );
		consonantData.add( new AlphabetData( R.drawable.con_18, R.drawable.con_hd_18, R.raw.con18, "con_18.gif", con_18) );
		consonantData.add( new AlphabetData( R.drawable.con_19, R.drawable.con_hd_19,R.raw.con19, "con_19.gif", con_19) );
		consonantData.add( new AlphabetData( R.drawable.con_20, R.drawable.con_hd_20, R.raw.con20, "con_20.gif", con_20 ) );
		consonantData.add( new AlphabetData( R.drawable.con_21, R.drawable.con_hd_21, R.raw.con21, "con_21.gif", con_21 ) );
		consonantData.add( new AlphabetData( R.drawable.con_22, R.drawable.con_hd_22,R.raw.con22, "con_22.gif", con_22) );
		consonantData.add( new AlphabetData( R.drawable.con_23, R.drawable.con_hd_23, R.raw.con23, "con_23.gif", con_23) );
		consonantData.add( new AlphabetData( R.drawable.con_24, R.drawable.con_hd_24,R.raw.con24, "con_24.gif", con_24) );
		consonantData.add( new AlphabetData( R.drawable.con_25, R.drawable.con_hd_25, R.raw.con25, "con_25.gif", con_25) );
		consonantData.add( new AlphabetData( R.drawable.con_26, R.drawable.con_hd_26, R.raw.con26, "con_26.gif", con_26) );
		consonantData.add( new AlphabetData( R.drawable.con_27, R.drawable.con_hd_27, R.raw.con27, "con_27.gif", con_27) );
		consonantData.add( new AlphabetData( R.drawable.con_28, R.drawable.con_hd_28,R.raw.con28, "con_28.gif", con_28) );
		consonantData.add( new AlphabetData( R.drawable.con_29, R.drawable.con_hd_29, R.raw.con29, "con_29.gif", con_29) );
		consonantData.add( new AlphabetData( R.drawable.con_30, R.drawable.con_hd_30, R.raw.con30, "con_30.gif", con_30) );
		consonantData.add( new AlphabetData( R.drawable.con_31, R.drawable.con_hd_31, R.raw.con31, "con_31.gif", con_31) );
		consonantData.add( new AlphabetData( R.drawable.con_32, R.drawable.con_hd_32, R.raw.con32, "con_32.gif", con_32 ) );
		consonantData.add( new AlphabetData( R.drawable.con_33, R.drawable.con_hd_33, R.raw.con33, "con_33.gif", con_33) );
		
		// vowels dotted point
		
		List<PointF[]> vow_01 = new ArrayList<PointF[]>();
		vow_01.add( new PointF[]{ new PointF(115,70),new PointF(155,70),new PointF(155,195)});
		List<PointF[]> vow_02 = new ArrayList<PointF[]>();
		vow_02.add( new PointF[]{ new PointF(220,90),new PointF(100,90),new PointF(105,60),new PointF(130,45),new PointF(220,90)});
		List<PointF[]> vow_03 = new ArrayList<PointF[]>();
		vow_03.add( new PointF[]{ new PointF(200,90),new PointF(100,90),new PointF(100,70),new PointF(120,50),new PointF(200,90),new PointF(200,40)});
		List<PointF[]> vow_04 = new ArrayList<PointF[]>();
		vow_04.add( new PointF[]{ new PointF(205,90),new PointF(100,90),new PointF(100,70),new PointF(120,50),new PointF(170,55),new PointF(205,90),new PointF(205,75),new PointF(215,50),new PointF(190,40),new PointF(170,55)});
		List<PointF[]> vow_05 = new ArrayList<PointF[]>();
		vow_05.add( new PointF[]{ new PointF(210,90),new PointF(103,90),new PointF(103,73),new PointF(120,52),new PointF(175,60),new PointF(210,90),new PointF(210,43),new PointF(175,60),new PointF(175,43)});
		List<PointF[]> vow_06 = new ArrayList<PointF[]>();
		vow_06.add( new PointF[]{ new PointF(170,140),new PointF(170,195)});
		List<PointF[]> vow_07 = new ArrayList<PointF[]>();
		vow_07.add( new PointF[]{ new PointF(110,103),new PointF(110,170),new PointF(170,170),new PointF(170,103)});
		List<PointF[]> vow_08 = new ArrayList<PointF[]>();
		vow_08.add( new PointF[]{ new PointF(105,103),new PointF(105,172),new PointF(133,145),new PointF(170,172),new PointF(170,103)});
		List<PointF[]> vow_09 = new ArrayList<PointF[]>();
		vow_09.add( new PointF[]{ new PointF(95,190),new PointF(70,190),new PointF(70,100),new PointF(55,95),new PointF(65,70),new PointF(90,70)});
		vow_09.add( new PointF[]{new PointF(240,68),new PointF(140,68),new PointF(140,40),new PointF(160,30),new PointF(220,40),new PointF(240,68),new PointF(240,20)});
		List<PointF[]> vow_10 = new ArrayList<PointF[]>();
		vow_10.add( new PointF[]{ new PointF(120,155),new PointF(100,155),new PointF(100,100),new PointF(90,85),new PointF(98,70),new PointF(113,70)});
		vow_10.add( new PointF[]{new PointF(150,185),new PointF(150,210),new PointF(190,210),new PointF(190,150),new PointF(190,50),new PointF(140,50),new PointF(150,30),new PointF(175,35),new PointF(190,50),new PointF(190,20),new PointF(175,35),new PointF(175,20)});
		List<PointF[]> vow_11 = new ArrayList<PointF[]>();
		vow_11.add( new PointF[]{ new PointF(120,160),new PointF(100,160),new PointF(100,100), new PointF(90,85),new PointF(100,70),new PointF(110,70)});
		vow_11.add( new PointF[]{ new PointF(150,185),new PointF(150,210),new PointF(200,210),new PointF(200,120),new PointF(200,50),new PointF(150,40),new PointF(150,20)});
		List<PointF[]> vow_12 = new ArrayList<PointF[]>();
		vow_12.add( new PointF[]{ new PointF(140,170),new PointF(110,170),new PointF(110,95),new PointF(95,80),new PointF(110,60),new PointF(130,60)});
		List<PointF[]> vow_13 = new ArrayList<PointF[]>();
		vow_13.add( new PointF[]{ new PointF(140,190),new PointF(110,190),new PointF(110,110),new PointF(97,100),new PointF(110,80),new PointF(130,80)});
		vow_13.add( new PointF[]{ new PointF(122,35),new PointF(100,35),new PointF(100,60),new PointF(133,60),new PointF(143,35)});
		List<PointF[]> vow_14 = new ArrayList<PointF[]>();
		vow_14.add( new PointF[]{ new PointF(138,195),new PointF(110,195),new PointF(110,120), new PointF(100,105),new PointF(110,85),new PointF(130,85)});
		vow_14.add( new PointF[]{new PointF(100,65),new PointF(130,65),new PointF(130,40),new PointF(100,40),new PointF(100,25)});
		List<PointF[]> vow_15 = new ArrayList<PointF[]>();
		vow_15.add( new PointF[]{ new PointF(110,170),new PointF(85,170),new PointF(85,100),new PointF(70,80),new PointF(80,60),new PointF(100,60)});
		vow_15.add( new PointF[]{ new PointF(170,60),new PointF(205,60),new PointF(205,170)});
		List<PointF[]> vow_16 = new ArrayList<PointF[]>();
		vow_16.add( new PointF[]{ new PointF(110,185),new PointF(80,185),new PointF(85,115),new PointF(70,95),new PointF(80,75),new PointF(105,75)});
		vow_16.add( new PointF[]{ new PointF(170,80),new PointF(210,80),new PointF(210,190),new PointF(210,20)});
		List<PointF[]> vow_17 = new ArrayList<PointF[]>();
		vow_17.add( new PointF[]{ new PointF(125,75),new PointF(150,55),new PointF(170,75),new PointF(150,95),new PointF(125,75),new PointF(150,152),new PointF(150,152),new PointF(150,200)});
		List<PointF[]> vow_18 = new ArrayList<PointF[]>();
		vow_18.add( new PointF[]{ new PointF(110,70),new PointF(130,50),new PointF(155,70),new PointF(130,100)});
		List<PointF[]> vow_19 = new ArrayList<PointF[]>();
		vow_19.add( new PointF[]{new PointF(110,40),new PointF(135,20),new PointF(155,40),new PointF(130,60),new PointF(110,40)});
		vow_19.add( new PointF[]{new PointF(130,80),new PointF(160,80),new PointF(160,190)});
		
		
		
		List<PointF[]> vow_20 = new ArrayList<PointF[]>();
		vow_20.add( new PointF[]{ new PointF(115,85),new PointF(135,65),new PointF(155,90),new PointF(135,105),new PointF(115,85)});
		vow_20.add( new PointF[]{ new PointF(115,150),new PointF(135,135),new PointF(155,150),new PointF(135,170),new PointF(115,150)});
		List<PointF[]> vow_21 = new ArrayList<PointF[]>();
		vow_21.add( new PointF[]{ new PointF(140,40),new PointF(165,20),new PointF(180,40),new PointF(165,55),new PointF(140,40)});
		vow_21.add( new PointF[]{ new PointF(140,105),new PointF(165,85),new PointF(180,105),new PointF(165,120),new PointF(140,105)});
		vow_21.add( new PointF[]{ new PointF(110,160),new PointF(110,200)});
		List<PointF[]> vow_22 = new ArrayList<PointF[]>();
		vow_22.add( new PointF[]{ new PointF(110,170),new PointF(80,170),new PointF(80,100),new PointF(70,80),new PointF(80,60),new PointF(105,60)});
		vow_22.add( new PointF[]{ new PointF(165,80),new PointF(185,60),new PointF(205,80),new PointF(185,105),new PointF(165,80)});
		vow_22.add( new PointF[]{ new PointF(165,155),new PointF(185,135),new PointF(205,155),new PointF(185,175),new PointF(165,155)});
		List<PointF[]> vow_23 = new ArrayList<PointF[]>();
		vow_23.add( new PointF[]{ new PointF(65,170),new PointF(40,170),new PointF(40,95),new PointF(30,80),new PointF(40,60),new PointF(60,60)});
		vow_23.add( new PointF[]{ new PointF(125,70),new PointF(160,70),new PointF(160,170)});
		vow_23.add( new PointF[]{ new PointF(195,80),new PointF(215,60),new PointF(235,80),new PointF(210,100),new PointF(195,80)});
		vow_23.add( new PointF[]{ new PointF(190,155),new PointF(215,130),new PointF(235,155),new PointF(215,175),new PointF(190,155)});
		
		// vowels data
		
		vowelData.add( new AlphabetData( R.drawable.vo_01, R.drawable.vol_hd_01, R.raw.vol_01, "vo_01.gif", vow_01) );
		vowelData.add( new AlphabetData( R.drawable.vo_02, R.drawable.vol_hd_02, R.raw.vol_02, "vo_02.gif", vow_02) );
		vowelData.add( new AlphabetData( R.drawable.vo_03, R.drawable.vol_hd_03, R.raw.vol_03, "vo_03.gif", vow_03) );
		vowelData.add( new AlphabetData( R.drawable.vo_04, R.drawable.vol_hd_04, R.raw.vol_04, "vo_04.gif", vow_04) );
		vowelData.add( new AlphabetData( R.drawable.vo_05, R.drawable.vol_hd_05, R.raw.vol_05, "vo_05.gif", vow_05) );
		vowelData.add( new AlphabetData( R.drawable.vo_06, R.drawable.vol_hd_06, R.raw.vol_06, "vo_06.gif", vow_06) );
		vowelData.add( new AlphabetData( R.drawable.vo_07, R.drawable.vol_hd_07, R.raw.vol_07, "vo_07.gif", vow_07) );
		vowelData.add( new AlphabetData( R.drawable.vo_08, R.drawable.vol_hd_08, R.raw.vol_08, "vo_08.gif", vow_08 ) );
		vowelData.add( new AlphabetData( R.drawable.vo_09, R.drawable.vol_hd_09, R.raw.vol_09, "vo_09.gif", vow_09) );
		vowelData.add( new AlphabetData( R.drawable.vo_10, R.drawable.vol_hd_10, R.raw.vol_10, "vo_10.gif", vow_10) );
		vowelData.add( new AlphabetData( R.drawable.vo_11, R.drawable.vol_hd_11, R.raw.vol_11, "vo_11.gif", vow_11 ) );
		vowelData.add( new AlphabetData( R.drawable.vo_12, R.drawable.vol_hd_12, R.raw.vol_12, "vo_12.gif", vow_12) );
		vowelData.add( new AlphabetData( R.drawable.vo_13, R.drawable.vol_hd_13, R.raw.vol_13, "vo_13.gif", vow_13) );
		vowelData.add( new AlphabetData( R.drawable.vo_14, R.drawable.vol_hd_14, R.raw.vol_14, "vo_14.gif", vow_14 ) );
		vowelData.add( new AlphabetData( R.drawable.vo_15, R.drawable.vol_hd_15, R.raw.vol_15, "vo_15.gif", vow_15) );
		vowelData.add( new AlphabetData( R.drawable.vo_16, R.drawable.vol_hd_16, R.raw.vol_16, "vo_16.gif", vow_16) );
		vowelData.add( new AlphabetData( R.drawable.vo_17, R.drawable.vol_hd_17, R.raw.vol_17, "vo_17.gif", vow_17 ) );
		vowelData.add( new AlphabetData( R.drawable.vo_18, R.drawable.vol_hd_18, R.raw.vol_18, "vo_18.gif", vow_18) );
		vowelData.add( new AlphabetData( R.drawable.vo_19, R.drawable.vol_hd_19, R.raw.vol_19, "vo_19.gif", vow_19) );
		vowelData.add( new AlphabetData( R.drawable.vo_20, R.drawable.vol_hd_20, R.raw.vol_20, "vo_20.gif", vow_20 ) );
		vowelData.add( new AlphabetData( R.drawable.vo_21, R.drawable.vol_hd_21, R.raw.vol_21, "vo_21.gif", vow_21) );
		vowelData.add( new AlphabetData( R.drawable.vo_22, R.drawable.vol_hd_22, R.raw.vol_22, "vo_22.gif", vow_22) );
		vowelData.add( new AlphabetData( R.drawable.vo_23, R.drawable.vol_hd_23, R.raw.vol_23, "vo_23.gif", vow_23 ) );
	}
	
	public static KhmerAlphabetData getInstance() {
		if( kad == null ) kad = new KhmerAlphabetData();
		return kad;
	}
	
	public ArrayList<AlphabetData> getDataType( int type ) {
		if( type == TYPE_CONSONANT ) {
			return consonantData;
		} else if ( type == TYPE_VOWEL ) {
			return vowelData;
		} else if ( type == TYPE_NUMBER ) {
			return numberData;
		}
		return null;
	}
	
}

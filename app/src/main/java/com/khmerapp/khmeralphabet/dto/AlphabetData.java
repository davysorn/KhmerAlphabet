package com.khmerapp.khmeralphabet.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.graphics.PointF;

/*
 * AlphabetData class is used to store supplement data for each Khmer alphabet such as its sound and PointFs for drawing.
 */
public class AlphabetData {
	
	private Integer imageId;
	private Integer imageHDId;
	private Integer soundId;
	private String gifFileName;
	private List<PointF[]> Points;
	
	public AlphabetData( Integer imageId, Integer imageHDId, Integer soundId, String gifFileName, List<PointF[]> Points) {
		this.imageId = imageId;
		this.imageHDId = imageHDId;
		this.soundId = soundId;
		this.gifFileName = gifFileName;
		this.Points = Points;
	}
	
	public AlphabetData( Integer imageId, Integer imageHDId, Integer soundId, String gifFileName ) {
		this( imageId, imageHDId,soundId, gifFileName, new ArrayList<PointF[]>());
	}
	
	public Integer getImageId() {
		return imageId;
	}
	
	public void setImageId( Integer imageId ) {
		this.imageId = imageId;
	}
	
	public Integer getImageHDId() {
		return imageHDId;
	}
	public Integer getSoundId() {
		return soundId;
	}
	
	public void setSoundId( Integer soundId ) {
		this.soundId = soundId;
	}
	
	public String getGifFileName() {
		return gifFileName;
	}
	
	public void setGifFileName( String gifFileName ) {
		this.gifFileName = gifFileName;
	}
	
	public List<PointF[]> getPoints() {
		return Points;
	}
	
	public void setPointFs(List<PointF[]> Points){
		this.Points = Points;
	}
	
}

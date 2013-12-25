package edu.asu.voctec.information;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import edu.asu.voctec.GUI.BasicComponent;

public class TaskData
{
	public static Image buttonImageInaccessible;
	public static Image buttonImageComplete;
	public static Image buttonImageAccessible;
	
	protected Class<?> associatedHub;
	protected Class<?> associatedTaskScreen;
	protected ArrayList<AttemptData> listOfAttempts;
	protected boolean complete;
	protected boolean accessible;
	protected BasicComponent taskIcon;
	
	public TaskData(Class<?> associatedTaskScreen)
	{
		this.associatedTaskScreen = associatedTaskScreen;
		this.listOfAttempts = new ArrayList<>();
		this.complete = false;
		this.accessible = false;
		this.taskIcon = new BasicComponent(buttonImageInaccessible, 0, 0);
	}
	
	protected void updateImage()
	{
		if (complete)
			this.taskIcon.setCurrentImage(buttonImageComplete, true);
		else if (accessible)
			this.taskIcon.setCurrentImage(buttonImageAccessible, true);
		else
			this.taskIcon.setCurrentImage(buttonImageInaccessible, true);
	}
	
	public void setComplete(boolean complete)
	{
		boolean update = (this.complete != complete);
		
		this.complete = complete;
		
		if (update)
			updateImage();
	}
	
	public void setAccessible(boolean accessible)
	{
		boolean update = (this.accessible != accessible);
		
		this.accessible = accessible;
		
		if (update)
			updateImage();
	}
}

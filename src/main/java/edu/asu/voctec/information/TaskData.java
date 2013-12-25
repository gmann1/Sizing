package edu.asu.voctec.information;

import java.util.ArrayList;

import edu.asu.voctec.menu.buttons.TransitionButton;

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
		this.taskIcon = new BasicComonent(buttonImageInaccessible, 0, 0);
	}
	
	protected void updateImage()
	{
		if (complete)
			this.taskIcon.setImage(buttonImageComplete);
		else if (accessible)
			this.taskIcon.setImage(buttonImageAccessible);
		else
			this.taskIcon.setImage(buttonImageInaccessible);
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
		boolean accessible = (this.accessible != accessible);
		
		this.accessible = accessible;
		
		if (update)
			updateImage();
	}
}

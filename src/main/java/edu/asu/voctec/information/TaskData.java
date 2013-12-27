package edu.asu.voctec.information;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.utilities.UtilFunctions;

public class TaskData
{
	public static Image DEFAULT_IMAGE;
	
	protected Image buttonImageInaccessible;
	protected Image buttonImageComplete;
	protected Image buttonImageAccessible;
	
	protected Class<?> associatedHub;
	protected Class<?> associatedTaskScreen;
	protected ArrayList<AttemptData> listOfAttempts;
	protected boolean complete;
	protected boolean accessible;
	protected Button taskIcon;
	
	public class ConditionalTransitionListener extends TransitionButtonListener
	{
		
		public ConditionalTransitionListener(Class<?> transitionScreen)
		{
			super(transitionScreen);
		}
		
		@Override
		protected void actionPerformed()
		{
			// if (accessible && !complete)
			super.actionPerformed();
			// TODO add //else if(complete)
		}
		
	}
	
	static
	{
		try
		{
			DEFAULT_IMAGE = new Image(
					GameDefaults.ImagePaths.TaskScreen.STEP_FIVE);
		}
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TaskData(Class<?> associatedTaskScreen,
			Rectangle relativeTextBounds, String name)
	{
		this.associatedTaskScreen = associatedTaskScreen;
		this.listOfAttempts = new ArrayList<>();
		this.complete = false;
		this.accessible = false;
		this.taskIcon = new Button(DEFAULT_IMAGE, 0, 0, relativeTextBounds,
				name);
		
		taskIcon.addActionListener(new ConditionalTransitionListener(
				associatedTaskScreen));
	}
	
	public TaskData(Class<?> associatedTaskScreen, String name)
	{
		this(associatedTaskScreen, UtilFunctions.getImageBounds(DEFAULT_IMAGE), name);
	}
	
	public void setImages(Image inaccessible, Image complete, Image accessible)
	{
		this.buttonImageAccessible = accessible;
		this.buttonImageComplete = complete;
		this.buttonImageInaccessible = inaccessible;
		
		updateImage();
	}
	
	public void setImages(String inaccessible, String complete,
			String accessible) throws SlickException
	{
		this.setImages(new Image(inaccessible), new Image(complete), new Image(
				accessible));
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
	
	public Button getTaskIcon()
	{
		return taskIcon;
	}
	
	public AttemptData getCurrentAttempt()
	{
		// TODO account for null
		return listOfAttempts.get(listOfAttempts.size() - 1);
	}
	
	public void setCurrentAttempt(AttemptData attemptData)
	{
		listOfAttempts.set(listOfAttempts.size() - 1, attemptData);
	}
	
	public void addAttempt(AttemptData attemptData)
	{
		listOfAttempts.add(attemptData);
	}

	public ArrayList<AttemptData> getListOfAttempts()
	{
		return listOfAttempts;
	}

	public boolean isComplete()
	{
		return complete;
	}

	public boolean isAccessible()
	{
		return accessible;
	}
	
	
	
}

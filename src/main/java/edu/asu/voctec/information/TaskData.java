package edu.asu.voctec.information;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults.Fonts;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.GameDefaults.Labels;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.Component;
import edu.asu.voctec.GUI.ProgressBar;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.UtilFunctions;

public class TaskData
{
	public static final Image DEFAULT_IMAGE = UtilFunctions
			.createImage(ImagePaths.TaskScreen.STEP_FIVE);
	
	public static MultiTaskListener activeListener;
	
	protected Image buttonImageInaccessible;
	protected Image buttonImageComplete;
	protected Image buttonImageAccessible;
	
	protected TaskScreen associatedHub;
	protected Class<?> associatedTask;
	protected ArrayList<AttemptData> listOfAttempts;
	protected boolean complete;
	protected boolean accessible;
	protected Button taskIcon;
	
	protected ArrayList<Component> informationComponents;
	protected TextAreaX inaccessibleText;
	protected ProgressBar progressBar;
	
	public class MultiTaskListener extends ButtonListener
	{
		protected boolean displayingComponents;
		
		public MultiTaskListener()
		{
			this.displayingComponents = false;
		}
		
		@Override
		protected void actionPerformed()
		{
			if (displayingComponents)
			{
				stopDisplaying();
			}
			else
			{
				updateProgressBar();
				
				if (activeListener != null)
					activeListener.stopDisplaying();
				if (TaskScreen.activeListener != null)
					TaskScreen.activeListener.stopDisplaying();
				
				activeListener = this;
				
				if (accessible)
					associatedHub.queueAddComponents(informationComponents);
				else
					associatedHub.queueAddComponents(inaccessibleText);
			}
			
			// Toggle Displaying
			displayingComponents = !displayingComponents;
			
			// TODO REMOVE
			Game.getCurrentGame().enterState(associatedTask);
		}
		
		public void stopDisplaying()
		{
			if (displayingComponents)
			{
				associatedHub.queueRemoveComponents(informationComponents);
				associatedHub.queueRemoveComponents(inaccessibleText);
			}
			
			displayingComponents = false;
			activeListener = null;
		}
		
	}
	
	public class ReplayContinueComboListener extends ButtonListener
	{
		
		@Override
		protected void actionPerformed()
		{
			// TODO Auto-generated method stub
			AttemptData currentAttempt = getCurrentAttempt();
			
			if (currentAttempt == null || currentAttempt.isComplete())
			{
				// TODO create and load new attempt
				listOfAttempts.add(null);
				
			}
			else
			{
				// TODO start using current attempt
			}
			
			Game.getCurrentGame().enterState(associatedTask);
		}
		
	}
	
	public TaskData()
	{
		this.associatedHub = null;
		this.associatedTask = null;
		this.taskIcon = null;
		
		this.listOfAttempts = new ArrayList<>();
		this.complete = false;
		this.accessible = false;
		this.informationComponents = new ArrayList<>();
		
		listOfAttempts.add(null);
	}
	
	public TaskData(Class<?> associatedTask, TaskScreen associatedHub,
			Rectangle relativeTextBounds, String name) throws SlickException
	{
		this();
		this.associatedHub = associatedHub;
		this.associatedTask = associatedTask;
		this.taskIcon = new Button(DEFAULT_IMAGE, 0, 0, relativeTextBounds,
				name);
		
		populateInformationComponents(name, informationComponents);
		taskIcon.addActionListener(new MultiTaskListener());
	}
	
	public TaskData(Class<?> associatedTaskScreen, TaskScreen taskScreen,
			float textBounds, String name) throws SlickException
	{
		this(associatedTaskScreen, taskScreen, UtilFunctions.dialateRectangle(
				DEFAULT_IMAGE, textBounds), name);
	}
	
	public void populateInformationComponents(String name,
			ArrayList<Component> informationComponents) throws SlickException
	{
		informationComponents.clear();
		// Inaccessible Text Area
		Rectangle relativeBounds = new Rectangle(0, 0, 400, 300);
		inaccessibleText = new TextAreaX(relativeBounds, 0.92f,
				Labels.TaskScreen.INACCESSIBLE_TEXT.getTranslation());
		inaccessibleText.setFontSize(Fonts.FONT_SIZE_LARGE);
		inaccessibleText.setFontColor(Color.white);
		
		// Name Label
		relativeBounds = new Rectangle(0, 0, 400, 100);
		TextField nameLabel = new TextField(relativeBounds, 0.92f, name,
				TextDisplay.FormattingOption.FIT_TEXT);
		nameLabel.center();
		nameLabel.setFontColor(Color.white);
		informationComponents.add(nameLabel);
		
		// Progress Bar
		relativeBounds = new Rectangle(0, 100, 400, 100);
		progressBar = new ProgressBar(relativeBounds);
		progressBar.setImages(ImagePaths.TaskScreen.PROGRESS_BAR_FULL,
				ImagePaths.TaskScreen.PROGRESS_BAR_EMPTY,
				ImagePaths.TaskScreen.PROGRESS_BAR_BORDER);
		informationComponents.add(progressBar);
		
		// TODO Add Ready/Replay Button
		
		// TODO Add Star Score
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
		for (AttemptData attempt : listOfAttempts)
		{
			if (attempt != null && attempt.isComplete())
			{
				complete = true;
				break;
			}
		}
		
		return complete;
	}
	
	public boolean isAccessible()
	{
		return accessible;
	}
	
	public ArrayList<Component> getInformationComponents()
	{
		return informationComponents;
	}
	
	public TextArea getInaccessibleText()
	{
		return inaccessibleText;
	}
	
	public void reload()
	{
		associatedHub = (TaskScreen) Game
				.getGameState(associatedHub.getClass());
	}
	
	public void updateProgressBar()
	{
		if (this.progressBar != null && getCurrentAttempt() != null)
		{
			int percent = getCurrentAttempt().getPercentCompletion();
			progressBar.setPercentComplete(percent);
		}
	}
	
}

package edu.asu.voctec.game_states;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.Component;
import edu.asu.voctec.batter_sizing.BatteryIntro;
import edu.asu.voctec.cdmg.CDIntroScreen;
import edu.asu.voctec.controller_sizing.ControllerSizingIntroScreen;
import edu.asu.voctec.energy_assessment.EAPart1IntroScreen;
import edu.asu.voctec.information.TaskData;
import edu.asu.voctec.pv_game.PVIntro;
import edu.asu.voctec.utilities.UtilFunctions;

public class TaskScreen extends GUI
{
	private ArrayList<TaskData> tasks;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		System.out.println("\nTaskScreen: Initializing...");
		
		tasks = new ArrayList<>();
		setDefaultTasks();
		System.out.println("TaskScreen: Defaults Set.");
		
		Image background = new Image(ImagePaths.TaskHubBackground);
		setBackgroundImage(background.getScaledCopy(800, 600));
		
		System.out.println("TaskScreen: Initialization Finished.\n");
	}
	
	@Override
	public void onEnter()
	{
		// Disable task display
		TaskData.activeListener.stopDisplaying();
	}
	
	public void setDefaultTasks() throws SlickException
	{
		int buttonSpacing = 15;
		int buttonWidth = 350;
		int buttonHeight = 75;
		float borderScale = 0.9f;
		
		// Determine text and button bounds, relative to each button
		Rectangle relativeButtonBounds = new Rectangle(0, 0, buttonWidth,
				buttonHeight);
		Rectangle relativeTextBounds = new Rectangle(relativeButtonBounds);
		relativeTextBounds = UtilFunctions.dialateRectangle(relativeTextBounds,
				borderScale);
		
		// Declare Buttons
		// Task 1
		System.out.println("Initializing TaskData...");
		TaskData energyAssessment = new TaskData(EAPart1IntroScreen.class,
				this, "Energy Assessment");
		energyAssessment.setImages(ImagePaths.TaskScreen.STEP_ONE,
				ImagePaths.TaskScreen.STEP_ONE_COMPLETE,
				ImagePaths.TaskScreen.STEP_ONE_SELECTED);
		
		// Task 2
		TaskData criticalDesignMonth = new TaskData(CDIntroScreen.class, this,
				"Critical Design Month");
		criticalDesignMonth.setImages(ImagePaths.TaskScreen.STEP_TWO,
				ImagePaths.TaskScreen.STEP_TWO_COMPLETE,
				ImagePaths.TaskScreen.STEP_TWO_SELECTED);
		
		// Task 3
		TaskData batterySizing = new TaskData(BatteryIntro.class, this,
				"Size Battery");
		batterySizing.setImages(ImagePaths.TaskScreen.STEP_THREE,
				ImagePaths.TaskScreen.STEP_THREE_COMPLETE,
				ImagePaths.TaskScreen.STEP_THREE_SELECTED);
		
		// Task 4
		TaskData pvSizing = new TaskData(PVIntro.class, this, "Size PV Array");
		pvSizing.setImages(ImagePaths.TaskScreen.STEP_FOUR,
				ImagePaths.TaskScreen.STEP_FOUR_COMPLETE,
				ImagePaths.TaskScreen.STEP_FOUR_SELECTED);
		
		// Task 5
		TaskData controllerSizing = new TaskData(
				ControllerSizingIntroScreen.class, this, "Size Controllers");
		controllerSizing.setImages(ImagePaths.TaskScreen.STEP_FIVE,
				ImagePaths.TaskScreen.STEP_FIVE_COMPLETE,
				ImagePaths.TaskScreen.STEP_FIVE_SELECTED);
		System.out.println("TaskData Initialized");
		
		// Add all tasks
		tasks.add(energyAssessment);
		tasks.add(criticalDesignMonth);
		tasks.add(batterySizing);
		tasks.add(pvSizing);
		tasks.add(controllerSizing);
		
		// Reformat all task buttons, and add them to this screen
		for (TaskData task : tasks)
		{
			// Set Task States
			task.setComplete(false);
			task.setAccessible(false);
			
			// Format taskIcon
			Button taskIcon = task.getTaskIcon();
			taskIcon.setFontColor(Color.white);
			taskIcon.rescale(0.85f);
			
			this.addComponent(taskIcon);
		}
		
		// Set current task
		energyAssessment.setAccessible(true);
		
		// Define the rectangle that holds the taskButtons
		Rectangle taskButtonContainer = new Rectangle(0, 0, 200, 600);
		
		// Center the task buttons in their container
		UtilFunctions.centerComponentsStacked(taskButtonContainer,
				buttonSpacing, getComponents());
		
		// Position and Format informationComponents
		for (TaskData task : tasks)
		{
			// Define the location of the component block relative to the screen
			Point informationLocation = new Point(350, 175);
			ArrayList<Component> informationComponents = task
					.getInformationComponents();
			
			// Scale the displays
			for (Component component : informationComponents)
				component.rescale(0.92f);
			task.getInaccessibleText().rescale(0.92f);
			
			// Set the position relative to the screen
			UtilFunctions.translateAll(informationLocation,
					informationComponents);
			task.getInaccessibleText().translate(informationLocation);
		}
	}
	
	public void setNextAccessible()
	{
		for (int index = 0; index < tasks.size(); index++)
		{
			TaskData task = tasks.get(index);
			
			if (!task.isComplete())
			{
				task.setAccessible(true);
				break;
			}
		}
	}
	
}

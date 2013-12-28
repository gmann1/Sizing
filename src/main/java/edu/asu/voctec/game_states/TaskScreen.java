package edu.asu.voctec.game_states;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.batter_sizing.BatteryIntro;
import edu.asu.voctec.cdmg.CDIntroScreen;
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
		TaskData energyAssessment = new TaskData(EAPart1IntroScreen.class,
				"Energy Assessment");
		energyAssessment.setImages(ImagePaths.TaskScreen.STEP_ONE,
				ImagePaths.TaskScreen.STEP_ONE_COMPLETE,
				ImagePaths.TaskScreen.STEP_ONE_SELECTED);
		
		// Task 2
		TaskData criticalDesignMonth = new TaskData(CDIntroScreen.class,
				"Critical Design Month");
		criticalDesignMonth.setImages(ImagePaths.TaskScreen.STEP_TWO,
				ImagePaths.TaskScreen.STEP_TWO_COMPLETE,
				ImagePaths.TaskScreen.STEP_TWO_SELECTED);
		
		// Task 3
		TaskData batterySizing = new TaskData(BatteryIntro.class,
				"Size Battery");
		batterySizing.setImages(ImagePaths.TaskScreen.STEP_THREE,
				ImagePaths.TaskScreen.STEP_THREE_COMPLETE,
				ImagePaths.TaskScreen.STEP_THREE_SELECTED);
		
		// Task 4
		TaskData pvSizing = new TaskData(PVIntro.class, "Size PV Array");
		pvSizing.setImages(ImagePaths.TaskScreen.STEP_FOUR,
				ImagePaths.TaskScreen.STEP_FOUR_COMPLETE,
				ImagePaths.TaskScreen.STEP_FOUR_SELECTED);
		
		// Task 5
		TaskData controllerSizing = new TaskData(SelectorTest.class,
				"Size Controllers");
		controllerSizing.setImages(ImagePaths.TaskScreen.STEP_FIVE,
				ImagePaths.TaskScreen.STEP_FIVE_COMPLETE,
				ImagePaths.TaskScreen.STEP_FIVE_SELECTED);
		
		// Add buttons to this menu
		this.addComponent(energyAssessment.getTaskIcon());
		this.addComponent(criticalDesignMonth.getTaskIcon());
		this.addComponent(batterySizing.getTaskIcon());
		this.addComponent(pvSizing.getTaskIcon());
		this.addComponent(controllerSizing.getTaskIcon());
		
		// Add all tasks
		tasks.add(energyAssessment);
		tasks.add(criticalDesignMonth);
		tasks.add(batterySizing);
		tasks.add(pvSizing);
		tasks.add(controllerSizing);
		
		// Reformat all task buttons
		for (TaskData task : tasks)
		{
			task.setComplete(false);
			task.setAccessible(false);
			
			Button button = task.getTaskIcon();
			button.setFontColor(Color.white);
			button.rescale(0.85f);
		}
		
		// Set current task
		energyAssessment.setAccessible(true);
		
		// Define the rectangle that holds the taskButtons
		Rectangle taskButtonContainer = new Rectangle(0, 0, 200, 600);
		
		// Center the task buttons in their container
		UtilFunctions.centerComponentsStacked(taskButtonContainer,
				buttonSpacing, getComponents());
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

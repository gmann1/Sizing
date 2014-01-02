package edu.asu.voctec.information;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.Component;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.GameDefaults.TaskScreenDefaults;
import edu.asu.voctec.batter_sizing.BatteryIntro;
import edu.asu.voctec.cdmg.CDIntroScreen;
import edu.asu.voctec.controller_sizing.ControllerSizingIntroScreen;
import edu.asu.voctec.energy_assessment.EAPart1IntroScreen;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.pv_game.PVIntro;
import edu.asu.voctec.utilities.UtilFunctions;

public class UserProfile
{
	protected String name;
	protected ScenarioData[] scenarioData;
	
	public UserProfile(String name) throws SlickException
	{
		this.name = name;
		this.scenarioData = generateDefaultScenarios();
	}
	
	public ScenarioData[] generateDefaultScenarios() throws SlickException
	{
		ScenarioData[] scenarios = new ScenarioData[1];
		// TODO Declare Default Scenarios
		scenarios[0] = scenario1();
		
		return scenarios;
	}
	
	public ScenarioData getScenario(int index)
	{
		return scenarioData[index];
	}
	
	public ScenarioData scenario1() throws SlickException
	{
		ArrayList<TaskData> tasks = new ArrayList<>();
		ArrayList<Button> taskButtons = new ArrayList<>();
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
		
		// Declare Tasks
		// Task 0
		System.out.println("Initializing TaskData...");
		TaskData entryStep = new TaskData();
		
		// Task 1
		TaskData energyAssessment = new TaskData(EAPart1IntroScreen.class,
				new TaskScreen(), "Energy Assessment");
		energyAssessment.setImages(ImagePaths.TaskScreen.STEP_ONE,
				ImagePaths.TaskScreen.STEP_ONE_COMPLETE,
				ImagePaths.TaskScreen.STEP_ONE_SELECTED);
		
		// Task 2
		TaskData criticalDesignMonth = new TaskData(CDIntroScreen.class,
				new TaskScreen(), "Critical Design Month");
		criticalDesignMonth.setImages(ImagePaths.TaskScreen.STEP_TWO,
				ImagePaths.TaskScreen.STEP_TWO_COMPLETE,
				ImagePaths.TaskScreen.STEP_TWO_SELECTED);
		
		// Task 3
		TaskData batterySizing = new TaskData(BatteryIntro.class,
				new TaskScreen(), "Size Battery");
		batterySizing.setImages(ImagePaths.TaskScreen.STEP_THREE,
				ImagePaths.TaskScreen.STEP_THREE_COMPLETE,
				ImagePaths.TaskScreen.STEP_THREE_SELECTED);
		
		// Task 4
		TaskData pvSizing = new TaskData(PVIntro.class, new TaskScreen(),
				"Size PV Array");
		pvSizing.setImages(ImagePaths.TaskScreen.STEP_FOUR,
				ImagePaths.TaskScreen.STEP_FOUR_COMPLETE,
				ImagePaths.TaskScreen.STEP_FOUR_SELECTED);
		
		// Task 5
		TaskData controllerSizing = new TaskData(
				ControllerSizingIntroScreen.class, new TaskScreen(),
				"Size Controllers");
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
			
			taskButtons.add(taskIcon);
		}
		
		// Set current task
		energyAssessment.setAccessible(true);
		
		// Define the rectangle that holds the taskButtons
		Rectangle taskButtonContainer = new Rectangle(0, 0, 200, 600);
		
		// Center the task buttons in their container
		UtilFunctions.centerComponentsStacked(taskButtonContainer,
				buttonSpacing,
				taskButtons.toArray(new Button[taskButtons.size()]));
		UtilFunctions.translateAll(0, 30, taskButtons);
		
		// Position and Format informationComponents
		for (TaskData task : tasks)
		{
			// Define the location of the component block relative to the screen
			Point informationLocation = TaskScreenDefaults.INFORMATION_OFFSET;
			ArrayList<Component> informationComponents = task
					.getInformationComponents();
			
			// Scale the displays
			float scale = TaskScreenDefaults.INFORMATION_SCALE;
			for (Component component : informationComponents)
				component.rescale(scale);
			task.getInaccessibleText().rescale(scale);
			
			// Set the position relative to the screen
			UtilFunctions.translateAll(informationLocation,
					informationComponents);
			task.getInaccessibleText().translate(informationLocation);
		}
		
		TaskData[] taskArray = tasks.toArray(new TaskData[tasks.size()]);
		return new ScenarioData(entryStep, taskArray, null);
	}
}

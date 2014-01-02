package edu.asu.voctec.game_states;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.Component;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.batter_sizing.BatteryIntro;
import edu.asu.voctec.cdmg.CDIntroScreen;
import edu.asu.voctec.controller_sizing.ControllerSizingIntroScreen;
import edu.asu.voctec.energy_assessment.EAPart1IntroScreen;
import edu.asu.voctec.information.ScenarioData;
import edu.asu.voctec.information.TaskData;
import edu.asu.voctec.pv_game.PVIntro;
import edu.asu.voctec.utilities.UtilFunctions;

public class TaskScreen extends GUI
{
	private ArrayList<TaskData> tasks;
	private ArrayList<Component> confirmationComponents;
	private boolean scenarioLoaded;
	
	public class BackButtonListener extends ButtonListener
	{
		boolean displaying;
		
		@Override
		protected void actionPerformed()
		{
			// TODO Auto-generated method stub
			if (displaying)
				Game.getCurrentGame().enterState(MainMenu.class);
			else
			{
				queueAddComponents(confirmationComponents);
				displaying = true;
			}
		}
		
	}
	
	public class ReplayButtonListener extends ButtonListener
	{

		@Override
		protected void actionPerformed()
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		System.out.println("\nTaskScreen: Initializing...");
		
		confirmationComponents = new ArrayList<>();
		tasks = new ArrayList<>();
		setDefaultTasks();
		System.out.println("TaskScreen: Defaults Set.");
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 0, 0,
				new Rectangle(0, 0, 50, 25), "Exit");
		backButton.addActionListener(new BackButtonListener());
		backButton.setFontColor(Color.darkGray);
		
		// Confirmation Components
		// Define the location of the component block relative to the screen
		Point informationLocation = TaskScreenDefaults.INFORMATION_OFFSET;
		float scale = TaskScreenDefaults.INFORMATION_SCALE;
		Rectangle relativeBounds = new Rectangle(0, 0, 400, 100);
		TextField nameLabel = new TextField(relativeBounds, 0.92f,
				"Replay Step 0?", TextDisplay.FormattingOption.FIT_TEXT);
		nameLabel.center();
		nameLabel.setFontColor(Color.white);
		
		// Exit Button
		Image exitButtonImage = new Image(ImagePaths.Buttons.BASE);
		Rectangle textBounds = UtilFunctions.getImageBounds(exitButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.80f);
		Button exitButton = new Button(exitButtonImage, 0, 100, textBounds,
				"Exit");
		exitButton.addActionListener(new TransitionButtonListener(
				MainMenu.class));
		exitButton.setFontColor(Color.black);
		
		// Replay Button
		Image replayButtonImage = new Image(ImagePaths.Buttons.BASE);
		textBounds = UtilFunctions.getImageBounds(replayButtonImage);
		int x = 400 - textBounds.width;
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.80f);
		Button replayButton = new Button(replayButtonImage, x, 100, textBounds,
				"Replay");
		replayButton.addActionListener(new ReplayButtonListener());
		replayButton.setFontColor(Color.black);
		
		confirmationComponents.add(nameLabel);
		confirmationComponents.add(exitButton);
		confirmationComponents.add(replayButton);
		this.addComponent(backButton);

		// Scale Components
		for (Component component : confirmationComponents)
			component.rescale(scale);
		
		// Set the position relative to the screen
		UtilFunctions.translateAll(informationLocation, confirmationComponents);
		
		// Setup Background
		Image background = new Image(ImagePaths.TaskHubBackground);
		setBackgroundImage(background.getScaledCopy(800, 600));
		
		System.out.println("TaskScreen: Initialization Finished.\n");
	}
	
	@Override
	public void onEnter()
	{
		// Disable task display
		if (TaskData.activeListener != null)
			TaskData.activeListener.stopDisplaying();
		queueRemoveComponents(confirmationComponents);
		setNextAccessible();
	}
	
	public void load()
	{
		if (!scenarioLoaded)
		{
			ScenarioData scenario = Game.getCurrentScenario();
			tasks = new ArrayList<>();
			
			for (TaskData task : scenario.getTasks())
			{
				task.reload();
				tasks.add(task);
			}
			
			scenarioLoaded = true;
		}
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

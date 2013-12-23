package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.batter_sizing.BatteryIntro;
import edu.asu.voctec.cdmg.CDIntroScreen;
import edu.asu.voctec.pv_game.PVIntro;
import edu.asu.voctec.utilities.UtilFunctions;

public class TaskScreen extends GUI
{
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		int buttonSpacing = 15;
		int buttonWidth = 350;
		int buttonHeight = 75;
		float borderScale = 0.9f;
		
		// Determine text and button bounds, relative to each button
		Rectangle relativeButtonBounds = new Rectangle(0, 0, buttonWidth,
				buttonHeight);
		Rectangle relativeTextBounds = new Rectangle(0, 0, buttonWidth
				- buttonHeight, buttonHeight);
		relativeTextBounds = UtilFunctions.dialateRectangle(relativeTextBounds,
				borderScale);
		
		// Declare Buttons
		// Task 1
		Button energyAssessment = new Button(ImagePaths.NEW_GAME_BUTTON,
				relativeButtonBounds, relativeTextBounds, null);
		Button.addTransitionListener(energyAssessment, MinigameA.class);
		
		// Task 2
		Button criticalDesignMonth = new Button(ImagePaths.NEW_GAME_BUTTON,
				relativeButtonBounds, relativeTextBounds, null);
		Button.addTransitionListener(criticalDesignMonth, CDIntroScreen.class);
		
		// Task 3
		Button batterySizing = new Button(ImagePaths.NEW_GAME_BUTTON,
				relativeButtonBounds, relativeTextBounds, null);
		Button.addTransitionListener(batterySizing, BatteryIntro.class);
		
		// Task 4
		Button pvSizing = new Button(ImagePaths.NEW_GAME_BUTTON,
				relativeButtonBounds, relativeTextBounds, null);
		Button.addTransitionListener(pvSizing, PVIntro.class);
		
		// Task 5
		Button controllerSizing = new Button(ImagePaths.NEW_GAME_BUTTON,
				relativeButtonBounds, relativeTextBounds, null);
		Button.addTransitionListener(controllerSizing, MenuTest.class);
		
		// Add buttons to this menu
		this.addComponent(energyAssessment);
		this.addComponent(criticalDesignMonth);
		this.addComponent(batterySizing);
		this.addComponent(pvSizing);
		this.addComponent(controllerSizing);
		
		this.centerComponentsStacked(buttonSpacing, getComponents());
	}
	
}

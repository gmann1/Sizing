package edu.asu.voctec;

import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.menu.MainMenu;
import edu.asu.voctec.menu.Menu;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;
import edu.asu.voctec.menu.buttons.TransitionButton;
import edu.asu.voctec.menu.energy_assessment.IntroScreen;
import edu.asu.voctec.minigames.EnergyAssesmentGame;

public class ScenarioHub extends Menu
{
	public static final int			ID					= 1;
	public static final Dimension	DesignResolution	= new Dimension(1280,
																720);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		Image buttonImage = new Image(ImagePaths.BASE_BUTTON).getScaledCopy(
				100, 100);
		// TODO declare & instantiate all buttons
		Button backButton = new TransitionButton(ImagePaths.BACK_BUTTON, 10,
				10, MainMenu.ID, LayoutOption.TOP_LEFT_ALIGN);
		Button task1Button = new TransitionButton(buttonImage, new Point(200,
				150), IntroScreen.ID);
		Button task2Button = new TransitionButton(buttonImage, new Point(400,
				300), EnergyAssesmentGame.ID);
		Button task3Button = new TransitionButton(buttonImage, new Point(600,
				450), ScenarioHub.ID);
		
		// TODO add all buttons
		this.addButton(backButton);
		this.addButton(task1Button);
		this.addButton(task2Button);
		this.addButton(task3Button);
		
		// TODO initialize all resources
		
		// TODO initialize background image
		Image background = new Image(ImagePaths.ScenarioHubBackground);
		super.initializeBackgroundImage(background);
		
		// ensure the appropriate scale is being used
		resize();
		
		// ensure the appropriate language is being used
		updateTranslation();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
	
	@Override
	public Dimension getDesignResolution()
	{
		return DesignResolution;
	}
}

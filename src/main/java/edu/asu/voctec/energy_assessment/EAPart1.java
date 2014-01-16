package edu.asu.voctec.energy_assessment;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.Fonts;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.energy_assessment.EAPart2.ReadyButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Position;

public class EAPart1 extends GUI
{
	private static final String BACKGROUND = "resources/default/img/scenarioHub/Region.png";
	private static final String HOUSE = "resources/default/img/scenarioHub/House.png";
	private static final String HOUSE_GRAY = "resources/default/img/scenarioHub/HouseGray.png";
	
	public static boolean scenerio1On = true;
	public static boolean scenerio2On = false;
	public static boolean scenerio3On = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.backgroundImage = new Image(BACKGROUND);
		
		////Buttons////
		Button scenario1Button = new Button(new Image(HOUSE_GRAY), 132, 355, new Rectangle(0, 90, 90, 40), "Scenario 1");
		Button scenario2Button = new Button(new Image(HOUSE_GRAY), 354, 155, new Rectangle(0, 90, 90, 40), "Scenario 2");
		Button scenario3Button = new Button(new Image(HOUSE_GRAY), 576, 305, new Rectangle(0, 90, 90, 40), "Scenario 3");
		/*if(scenerio1On == true)
		{
			scenario1Button = Button();
		}*/
		scenario1Button.setFontColor(Color.white);
		scenario2Button.setFontColor(Color.white);
		scenario3Button.setFontColor(Color.white);
		
		scenario1Button.addActionListener(new Scenario1ButtonListener());
		scenario2Button.addActionListener(new Scenario2ButtonListener());
		scenario3Button.addActionListener(new Scenario3ButtonListener());
		
		this.addComponent(scenario1Button);
		this.addComponent(scenario2Button);
		this.addComponent(scenario3Button);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5, new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(TaskScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		this.addComponent(backButton);
	}
	public class Scenario1ButtonListener extends ButtonListener 
	{
		@Override
		protected void actionPerformed()
		{
			if(scenerio1On == true)
			{
				Game.getCurrentGame().enterState(TaskScreen.class);
			}
		}
	}
	
	public class Scenario2ButtonListener extends ButtonListener 
	{
		@Override
		protected void actionPerformed()
		{
			if(scenerio2On == true)
			{
				//Game.getCurrentGame().enterState(TaskScreen.class);
			}
		}
	}
	
	public class Scenario3ButtonListener extends ButtonListener 
	{
		@Override
		protected void actionPerformed()
		{
			if(scenerio3On == true)
			{
				//Game.getCurrentGame().enterState(TaskScreen.class);
			}
		}
	}
}

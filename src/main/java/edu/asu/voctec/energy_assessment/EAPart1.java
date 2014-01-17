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
import edu.asu.voctec.GUI.TextAreaX;
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
	private static final String HOUSE      = "resources/default/img/scenarioHub/House.png";
	private static final String HOUSE_GRAY = "resources/default/img/scenarioHub/HouseGray.png";
	
	public static boolean scenerio1On = true;
	public static boolean scenerio2On = false;
	public static boolean scenerio3On = false;
	
	TextAreaX descriptionText;
	Button scenario1Button, scenario2Button, scenario3Button;
	
	private int[][] scenarioPosition = 
		{
			{132,355},
			{354,155},
			{576,305}
		};
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.backgroundImage = new Image(BACKGROUND);
		
		//Scenario Buttons
		scenario1Button = new Button(new Image(HOUSE_GRAY), scenarioPosition[0][0], scenarioPosition[0][1], new Rectangle(0, 90, 90, 40), "");
		scenario2Button = new Button(new Image(HOUSE_GRAY), scenarioPosition[1][0], scenarioPosition[1][1], new Rectangle(0, 90, 90, 40), "");
		scenario3Button = new Button(new Image(HOUSE_GRAY), scenarioPosition[2][0], scenarioPosition[2][1], new Rectangle(0, 90, 90, 40), "");
		scenario1Button.setFontColor(Color.white);
		scenario2Button.setFontColor(Color.white);
		scenario3Button.setFontColor(Color.white);
		scenario1Button.addActionListener(new Scenario1ButtonListener());
		scenario2Button.addActionListener(new Scenario2ButtonListener());
		scenario3Button.addActionListener(new Scenario3ButtonListener());
		this.addComponent(scenario1Button);
		this.addComponent(scenario2Button);
		this.addComponent(scenario3Button);
		
		//add Text
		initializeText();
		
		//update values
		updateStart();

		//Back Button
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
	
	public void updateStart() throws SlickException
	{
		if(scenerio1On == true)
			scenario1Button = new Button(new Image(HOUSE), 132, 355, new Rectangle(0, 90, 90, 40), "");
		if(scenerio2On == true)
			scenario2Button = new Button(new Image(HOUSE), 354, 155, new Rectangle(0, 90, 90, 40), "");
		if(scenerio3On == true)
			scenario3Button = new Button(new Image(HOUSE), 576, 305, new Rectangle(0, 90, 90, 40), "");
	}
	
	private void initializeText()
	{
		//Scenario Names
		TextAreaX scenario1Text = new TextAreaX(new Rectangle(scenarioPosition[0][0], scenarioPosition[0][1]+90,200,100),0.95f,"Scenario 1");
		TextAreaX scenario2Text = new TextAreaX(new Rectangle(scenarioPosition[1][0], scenarioPosition[1][1]+90,200,100),0.95f,"Scenario 2");
		TextAreaX scenario3Text = new TextAreaX(new Rectangle(scenarioPosition[2][0], scenarioPosition[2][1]+90,200,100),0.95f,"Scenario 3");
		scenario1Text.setFontSize(16);
		scenario2Text.setFontSize(16);
		scenario3Text.setFontSize(16);
		scenario1Text.setFontColor(Color.white);
		scenario2Text.setFontColor(Color.white);
		scenario3Text.setFontColor(Color.white);
		this.addComponent(scenario1Text);
		this.addComponent(scenario2Text);
		this.addComponent(scenario3Text);
		
		//description Area Text
		descriptionText = new TextAreaX(new Rectangle(100,20,600,300),0.95f,"Description: ...");
		descriptionText.setFontSize(16);
		descriptionText.setFontColor(Color.white);
		this.addComponent(descriptionText);
	}
}

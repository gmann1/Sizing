package edu.asu.voctec.batter_sizing;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.*;
import edu.asu.voctec.game_states.GUI;

public class BatteryIntro extends GUI
{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image("resources/default/img/minigames/BatterySizing/WhiteBackground.jpg");
		
		Rectangle textLocation = new Rectangle(25, 15, 700, 500);
		TextField requiredCapacity = new TextField(textLocation, 0.95f,
				"Required Battery-Bank Output: 200 Ah \n Required Battery-Bank Output: 200 Ah \n ",
				TextDisplay.FormattingOption.FIT_TEXT);
		this.addComponent(requiredCapacity);
		
		Button Start = new Button(new Image("resources/default/img/arrow-right.png"), 750, 550,
			    new Rectangle(50, 50, 300, 50), "");

			  Start.addActionListener(new TransitionButtonListener(BatteryGameScreen.class));
			  
	  this.addComponent(Start);
	}
	
}
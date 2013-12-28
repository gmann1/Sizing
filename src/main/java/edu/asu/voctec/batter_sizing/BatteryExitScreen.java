package edu.asu.voctec.batter_sizing;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class BatteryExitScreen extends GUI
{

	//private static TextField congratulation;
	private static TextArea congratulation, congratulation2, congratulation3;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image("resources/default/img/minigames/BatterySizing/WhiteBackground.jpg");
			
		Rectangle textLocation = new Rectangle(25, 25, 600, 600);
		congratulation = new TextArea(textLocation, 0.95f,
				"");
		congratulation.setFontSize(40);
		congratulation.setFontColor(Color.black);
		this.addComponent(congratulation);
		
		Rectangle textLocation2 = new Rectangle(25, 120, 500, 500);
		congratulation2 = new TextArea(textLocation2, 0.95f,
				"");
		congratulation2.setFontSize(25);
		congratulation2.setFontColor(Color.black);
		this.addComponent(congratulation2);
		
		Rectangle textLocation3 = new Rectangle(25, 280, 700, 700);
		congratulation3 = new TextArea(textLocation3, 0.95f,
				"");
		congratulation3.setFontSize(30);
		congratulation3.setFontColor(Color.black);
		this.addComponent(congratulation3);
		
		Button Start = new Button(new Image("resources/default/img/minigames/BatterySizing/PlayAgainButton.png"), 465, 535,
			    new Rectangle(750, 550, 44, 44), "");

			  Start.addActionListener(new PlayAgainListener());
			  
	  this.addComponent(Start);
	  
	  Button exitButton = new Button(new Image("resources/default/img/minigames/BatterySizing/ExitButton.png"), 630, 535,
			    new Rectangle(750, 550, 44, 44), "");

		exitButton.addActionListener(new ExitListener());
			  
	  this.addComponent(exitButton);
		
	}
	
	public class ExitListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			BatteryGameScreen.reset();
			Game.getCurrentGame().enterState(TaskScreen.class);
		}
	}
	
	public class PlayAgainListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			BatteryGameScreen.reset();
			Game.getCurrentGame().enterState(BatteryGameScreen.class);
		}
	}
	
	public static void passEndGameMessage(String gameStatusHeadline, String gameStatusMessage, String personalizedMessage, Color personalizedMessageColor)
	{
		congratulation.setText(gameStatusHeadline);
		congratulation2.setText(gameStatusMessage);
		congratulation3.setText(personalizedMessage);
		congratulation3.setFontColor(personalizedMessageColor);
	}
	
}

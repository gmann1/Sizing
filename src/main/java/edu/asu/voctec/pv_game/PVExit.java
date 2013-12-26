package edu.asu.voctec.pv_game;

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
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class PVExit extends GUI
{

	private static TextArea congratulation3;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image("resources/default/img/minigames/BatterySizing/WhiteBackground.jpg");
			
		Rectangle textLocation = new Rectangle(25, 25, 400, 400);
		TextField congratulation = new TextField(textLocation, 0.95f,
				"Congratulations...",
				TextDisplay.FormattingOption.FIT_TEXT);
		congratulation.setFontColor(Color.black);
		this.addComponent(congratulation);
		
		Rectangle textLocation2 = new Rectangle(25, 120, 500, 500);
		TextArea congratulation2 = new TextArea(textLocation2, 0.95f,
				"You have successfully completed the PV panels Sizing Game.");
		congratulation2.setFontSize(40);
		congratulation2.setFontColor(Color.black);
		this.addComponent(congratulation2);
		
		Rectangle textLocation3 = new Rectangle(25, 320, 700, 700);
		congratulation3 = new TextArea(textLocation3, 0.95f,
				"");
		congratulation3.setFontSize(40);
		congratulation3.setFontColor(Color.red);
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
			PVGame.reset();
			Game.getCurrentGame().enterState(TaskScreen.class);
		}
	}
	
	public class PlayAgainListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			PVGame.reset();
			Game.getCurrentGame().enterState(PVGame.class);
		}
	}
	
	public static void passNumberOfBatteriesUsed()
	{
		if(Battery.getNumberOfBatteries() > 8)
		{
			congratulation3.setText("However, you could have used fewer number of PV panels to solve the game.");
			congratulation3.setFontColor(Color.red);
		}
		else
		{
			congratulation3.setText("You were able to achieve the best possible combination of PV panels.");
			congratulation3.setFontColor(Color.blue);
		}
	}
	
}

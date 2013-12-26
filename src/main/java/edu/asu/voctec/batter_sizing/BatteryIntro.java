package edu.asu.voctec.batter_sizing;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.*;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class BatteryIntro extends GUI
{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image("resources/default/img/minigames/BatterySizing/WhiteBackground.jpg");
		
		Rectangle textLocation = new Rectangle(25, 15, 625, 1500);
		TextArea instructions = new TextArea(textLocation, 0.95f,
				"In this game you need to figure out the best combination of batteries and how to connect them"
				+ " in order to achieve the required Battery-Bank output and system voltage."
				+ "                                                                       "
				+ "                                                                                            "
				+ "Game Instructions:                                                                                     "
				+ "1. To connect two batteries in parallel, place one of the batteries on top of the other."
				+ "                                                                                           "
				+ "2. To connect battaries in series, just drag the battery and drop it anywhere in the gray area."
				+ "                                                                        "
				+ "3. To remove a battery from the array, just drag and drop it anywhere outside the gray area."
				+ "                                                               "
				+ "4. Parallel batteries should share the same voltage but can have different capacities."
				+ "                                                                                   "
				+ "5. Batteries that are connected in series should have the same capacity but can have different voltage values."
				+ "                                      "
				+ "6. The current total Amp Hours and voltage for the array created will be updated automatically in the top right corner."
				+ "                                  "
				+ "7. Pressing the hint button will display the next available hint and let you browse between them."
				+ "                                                                      "
				+ "8. When the requirements are met, a \"DONE\" button will be available.");
		instructions.setFontSize(16);
		instructions.setFontColor(new Color(27,29,121));
		this.addComponent(instructions);
		
		Button Start = new Button(new Image("resources/default/img/minigames/BatterySizing/Continue.png"), 465, 535,
			    new Rectangle(750, 550, 44, 44), "");
			  Start.addActionListener(new TransitionButtonListener(BatteryGameScreen.class));
	  this.addComponent(Start);
	  
	  
	  Button exitButton = new Button(new Image("resources/default/img/minigames/BatterySizing/ExitButton.png"), 630, 535,
			    new Rectangle(750, 550, 44, 44), "");
		exitButton.addActionListener(new TransitionButtonListener(TaskScreen.class));
	  this.addComponent(exitButton);
	  
	  BasicComponent introImage1 = new BasicComponent("resources/default/img/minigames/BatterySizing/Intro1.png", 650, 100);
	  BasicComponent introImage2 = new BasicComponent("resources/default/img/minigames/BatterySizing/Intro2.png", 650, 220, 136, 65);
	  BasicComponent introImage3 = new BasicComponent("resources/default/img/minigames/BatterySizing/Intro3.png", 650, 320, 136, 89);
	  
	  this.addComponent(introImage1);
	  this.addComponent(introImage2);
	  this.addComponent(introImage3);
	}
	
}
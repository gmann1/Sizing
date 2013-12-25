package edu.asu.voctec.pv_game;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class PVIntro extends GUI
{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image("resources/default/img/minigames/BatterySizing/WhiteBackground.jpg");
		
		Rectangle textLocation = new Rectangle(25, 15, 625, 1500);
		TextArea instructions = new TextArea(textLocation, 0.95f,
				"In this game you need to figure out the best combination of PV panels and how to connect them"
				+ " in order to achieve the required charging power and system voltage."
				+ "                                                                       "
				+ "                                                                                                  "
				+ "Game Instructions:                                                                                     "
				+ "1. To connect two PV panels in parallel, place one of the panel on top of the other."
				+ "                                                                                               "
				+ "2. To connect PV panels in series, just drag the panel and drop it anywhere in the gray area."
				+ "                                                                        "
				+ "3. To remove a PV panel from the array, just drag and drop it anywhere outside the gray area."
				+ "                                                               "
				+ "4. Parallel PV panels should share the same voltage but can have different power."
				+ "                                                                                        "
				+ "5. PV panels that are connected in series should have the same power but can have different voltage values."
				+ "                                           "
				+ "6. The current total watts and voltage for the array created will be updated automatically in the top right corner."
				+ "                                       "
				+ "7. Pressing the hint button will display the next available hint and let you browse between them."
				+ "                                                                       "
				+ "8. When the requirements are met, a \"DONE\" button will be available.");
		instructions.setFontSize(16);
		instructions.setFontColor(new Color(27,29,121));
		this.addComponent(instructions);
		
		Button Start = new Button(new Image("resources/default/img/minigames/BatterySizing/Continue.png"), 465, 535,
			    new Rectangle(750, 550, 44, 44), "");
			  Start.addActionListener(new TransitionButtonListener(PVGame.class));
	  this.addComponent(Start);
	  
	  
	  Button exitButton = new Button(new Image("resources/default/img/minigames/BatterySizing/ExitButton.png"), 630, 535,
			    new Rectangle(750, 550, 44, 44), "");
		exitButton.addActionListener(new TransitionButtonListener(TaskScreen.class));
	  this.addComponent(exitButton);
	  
	  BasicComponent introImage1 = new BasicComponent("resources/default/img/minigames/BatterySizing/PVintro1.png", 650, 100);
	  BasicComponent introImage2 = new BasicComponent("resources/default/img/minigames/BatterySizing/PVintro2.png", 650, 220);
	  BasicComponent introImage3 = new BasicComponent("resources/default/img/minigames/BatterySizing/PVintro3.png", 650, 320);
	  
	  this.addComponent(introImage1);
	  this.addComponent(introImage2);
	  this.addComponent(introImage3);
	}
	
}

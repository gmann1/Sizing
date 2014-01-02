package edu.asu.voctec.energy_assessment;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.*;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class EAPart1IntroScreen extends GUI
	{
	private static final String CONTINUE = "resources/default/img/minigames/energyAssessment/continueButton.png";
	private static final String EXIT = "resources/default/img/minigames/energyAssessment/exitButton.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	
	
	private static final String ea1IntroText = "In this game you need will need to put in order the different processes needed to determine the correct daily energy requirements."
			+ "Instructions:"
			+ "1. You are given 7 different processes on the bottom of the screen, use the arrows to cycle through all the different ones."
			+ "2. At the top of the screen is a diagram that represents the processes flow, your job is to fill in the diagram. "
			+ "3. To place a process click the big box center box with the process you want placed."
			+ "4. If you find you do not want the diagram as you have placed the processes you can click a diagram box with a process to have it returned."
			+ "5. Once you have placed something in every diagram box a ready button will appear which you can click. If the diagram is placed correctly you will move onto the next screen. If it is not correct it will highlight the ones wrong in red and will give you a hint."
			+ "6. The less hints used the higher your score will be. ";

	
		@Override
		public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
			//load basic things
			
			this.backgroundImage = new Image(BACKGROUND);
			
			////Text////
			//top welcome text
			Rectangle topTextLocation = new Rectangle(50, 50, 300, 50);
			TextField topTextField = new TextField(topTextLocation, 0.95f, "Welcome!", TextDisplay.FormattingOption.FIT_TEXT);
			topTextField.setFontColor(Color.blue);
			this.addComponent(topTextField);
			
			//intro text
			Rectangle introTextLocation = new Rectangle(50, 100, 700, 400);
			TextArea introTextArea = new TextArea(introTextLocation, 0.90f, ea1IntroText);
			introTextArea.setFontColor(Color.black);
			introTextArea.setFillColor(Color.white);
			introTextArea.setFontSize(16);
			this.addComponent(introTextArea);
			
			//Next Button
			Button Next = new Button(new Image(CONTINUE), 575, 500, new Rectangle(50,50,300,50), "");
			Next.addActionListener(new TransitionButtonListener(EAPart1.class));
			this.addComponent(Next);
			
			//Exit Button
			Button exit = new Button(new Image(EXIT), 50, 500, new Rectangle(50,50,300,50), "");
			exit.addActionListener(new TransitionButtonListener(TaskScreen.class));
			this.addComponent(exit);

		}
		
		
		@Override
		public Dimension getDesignResolution()
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}

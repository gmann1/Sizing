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
	
	
	private static final String ea1IntroText = "In this game you need will need to put in order the different processes needed to determine the correct daily energy requirements.";

	
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

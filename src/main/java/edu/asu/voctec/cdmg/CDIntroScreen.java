package edu.asu.voctec.cdmg;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;

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
import edu.asu.voctec.game_states.GUI;

public class CDIntroScreen extends GUI
	{
		@Override
		public void init(GameContainer container, StateBasedGame game)
				throws SlickException
		{
			this.backgroundImage = new Image("resources/default/img/minigames/criticalDesign/space.jpg");
			Rectangle textLocation = new Rectangle(50, 50, 300, 50);
			//TextField textField = new TextField(textLocation, 0.95f,
					//"Cliped Text Field ... CLIP CLIP CLIP",
					//TextDisplay.FormattingOption.CLIP_TEXT);
			
			//textLocation.setLocation(50, 150);
			TextField textField2 = new TextField(textLocation, 0.95f,
					"Welcome!",
					TextDisplay.FormattingOption.FIT_TEXT);
			
			textLocation.setLocation(50, 250);
			TextField textField3 = new TextField(textLocation, 0.95f,
					"Introduction stuff...",
					TextDisplay.FormattingOption.FIT_TEXT);
			
			Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 500, 500, new Rectangle(50,50,300,50), "Start!");
			
			
			Start.addActionListener(new TransitionButtonListener(CDPart1.class));
			
			textField2.center();
			textField3.center();
			
		
			this.addComponent(Start);
			this.addComponent(textField2);
			this.addComponent(textField3);
			//this.addComponent(new Selector<SelectorIcon>(100, 100));
			
			System.out
					.println("Listeners: " + Arrays.toString(this.getListeners()));
		}
		
		
		@Override
		public Dimension getDesignResolution()
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}

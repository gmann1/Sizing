package edu.asu.voctec.GUI;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.game_states.GUI;

public class MenuTest extends GUI
{
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		Rectangle textLocation = new Rectangle(50, 50, 300, 50);
		TextField textField = new TextField(textLocation, 0.95f,
				"Cliped Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		
		textLocation.setLocation(50, 150);
		TextField textField2 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.FIT_TEXT);
		
		textLocation.setLocation(50, 250);
		TextField textField3 = new TextField(textLocation, 0.95f,
				"Vert Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.FIT_TEXT_VERTICALLY);
		
		textField.enableBorder();
		textField2.enableBorder();
		textField3.enableBorder();
		textField.center();
		textField2.center();
		textField3.center();
		
		this.addComponent(textField);
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

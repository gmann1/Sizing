package edu.asu.voctec.GUI;

import java.awt.Dimension;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuTest extends GUI
{
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub
		this.addComponent(new Selector<SelectorIcon>(100, 100));

		System.out.println("Listeners: " + Arrays.toString(this.getListeners()));
	}

	@Override
	public Dimension getDesignResolution()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}

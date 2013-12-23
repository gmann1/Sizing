package edu.asu.voctec.GUI;

import edu.asu.voctec.Game;

public class TransitionButtonListener extends ButtonListener
{
	private Class<?> transitionScreen;
	
	public TransitionButtonListener(Class<?> transitionScreen)
	{
		this.transitionScreen = transitionScreen;
	}
	
	@Override
	protected void actionPerformed()
	{
		Game.getCurrentGame().enterState(transitionScreen);
	}
	
}

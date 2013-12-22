package edu.asu.voctec.GUI;

import edu.asu.voctec.Game;

public class TransitionButtonListener extends ButtonListener
{
	private int transitionScreenID;
	
	public TransitionButtonListener(int transitionScreenID)
	{
		this.transitionScreenID = transitionScreenID;
	}
	
	@Override
	protected void actionPerformed()
	{
		Game.getCurrentGame().enterState(transitionScreenID);
	}
	
}

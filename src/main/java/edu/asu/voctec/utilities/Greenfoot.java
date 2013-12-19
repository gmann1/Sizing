package edu.asu.voctec.utilities;

import edu.asu.voctec.minigames.Actor;

public class Greenfoot
{
	public static class MouseInformation
	{
		public int	buttonType;
		public int	x;
		public int	y;
		
		public MouseInformation(int buttonType, int x, int y)
		{
			this.buttonType = buttonType;
			this.x = x;
			this.y = y;
		}
	}
	
	public static MouseInformation	currentMouseEvent;
	
	public static void mousePressed(int buttonType, int x, int y)
	{
		currentMouseEvent = new MouseInformation(buttonType, x, y);
	}
	
	public static boolean mouseClicked(Actor actor)
	{
		boolean outOfBounds;
		
		if (currentMouseEvent != null)
		{
			int minimumX = actor.getxLocation();
			int maximumX = minimumX + actor.getImage().getWidth();
			int minimumY = actor.getyLocation();
			int maximumY = minimumY + actor.getImage().getHeight();
			
			// Determine if mouse location is outside of this button object
			outOfBounds = (currentMouseEvent.x <= minimumX
					|| currentMouseEvent.x >= maximumX
					|| currentMouseEvent.y <= minimumY || currentMouseEvent.y >= maximumY);
		}
		else
		{
			outOfBounds = true;
		}
		return !outOfBounds;
	}
}

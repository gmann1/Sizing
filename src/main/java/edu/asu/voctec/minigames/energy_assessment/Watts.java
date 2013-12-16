package edu.asu.voctec.minigames.energy_assessment;

import java.awt.Point;

import org.newdawn.slick.Graphics;

import edu.asu.voctec.minigames.Actor;

public class Watts extends Actor
{
    public static Watts watt1 = new Watts("10 Watts");
    public static Watts watt2 = new Watts("20 Watts");
    public static Watts watt3 = new Watts("30 Watts"); 
    public static Watts watt4 = new Watts("40 Watts");
    
    public static Watts[] watts = {watt1, watt2, watt3, watt4};

    //TODO add support for custom text locations
    static Point relativeTextLocation = new Point(45, 50);
    boolean connected;
    String text;
    
    //TODO cache wattage images
    
    //setup
    public Watts(String text)
    {
        this.text = text;
        this.connected = false;
        this.setImage(ImagePaths.WATTAGE_BAD);
    }
    
    //set if it is connected
    public void setConnected(boolean value)
    {
        this.connected = value;
    }
    
    //run state
    public void act() 
    {
        if(connected)
            this.setImage(ImagePaths.WATTAGE_GOOD);
        else
            this.setImage(ImagePaths.WATTAGE_BAD);
    }
	
    @Override
	public void draw(Graphics graphics)
	{
    	super.draw(graphics);
    	graphics.setColor(textColor);
		graphics.drawString(text, 
				(float) (this.xLocation + relativeTextLocation.x), 
				(float) (this.yLocation + relativeTextLocation.y));
	}

	@Override
	public void actOnMouseClick()
	{
	}
	
	public boolean isConnected()
	{
		return this.connected;
	}
    
}

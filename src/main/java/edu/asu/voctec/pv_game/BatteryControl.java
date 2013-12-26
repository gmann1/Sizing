package edu.asu.voctec.pv_game;

import org.newdawn.slick.Image;

import edu.asu.voctec.GUI.BasicComponent;

import org.lwjgl.input.Mouse;

public abstract class BatteryControl extends BasicComponent {

	protected boolean mouseIsPressed = false;
	protected static BatteryControl objectSelected;
	
	public BatteryControl(Image image, int x, int y) {
		super(image, x, y);
	}
	
	public abstract void update();
	
	protected int mouseX()
	{
		return (Mouse.getX());
	}
	
	protected int mouseY()
	{
		return (600-Mouse.getY());
	}
	
	protected boolean withinArrayCreationArea()
    {
		return withinArrayCreationArea( getX(), getY());
    }
	
	protected boolean withinArrayCreationArea(int x, int y)
    {
        return (y >= 75 && y <= 500 && x >= 25 && x <= 775);
    }
    
    protected boolean thisObjectSelected(int x, int y)
    {
        return (x >= getX() && x <= getX()+60 && y >= getY() && y <= getY()+60);
    }
    
    protected boolean mouseIsBeingDragged()
    {
    	if(mouseDragEnded())
    	{
    		mouseIsPressed = false;
    		objectSelected = null;
    	}
    	if(Mouse.isButtonDown(0)&& thisObjectSelected(mouseX(), mouseY()) && objectSelected == null)
    		objectSelected = this;
    	if (objectSelected == this && !mouseDragEnded() && Mouse.isButtonDown(0) && (thisObjectSelected(mouseX(), mouseY())||mouseIsPressed)) {
    		mouseIsPressed = true;
    		objectSelected = this;
    		return true;
    	}
    	else
    		return false;
    }
    
    
    protected boolean mouseDragEnded()
    {
    	if (!Mouse.isButtonDown(0) && mouseIsPressed) {
    		mouseIsPressed = false;
    		objectSelected = null;
    		return true;
    	}
    	else
			return false;
    }
    
    protected void setLocation(int x, int y)
    {
    	this.setX(x);
		this.setY(y);
    }
}

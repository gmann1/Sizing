package edu.asu.voctec.batter_sizing;

import org.newdawn.slick.Image;

import edu.asu.voctec.GUI.BasicComponent;

public class InitialBattery extends BatteryControl {
	
	private int voltage, capacity, initialX, initialY;
	private  static BatteryGameScreen gameWorld;
	private Image image;
	private static BasicComponent horizontalLine;
	private static boolean firstBattery = true;
	
    public InitialBattery(int voltage, int capacity, int initialX, int initialY, Image image, int x, int y, BatteryGameScreen batteryGameWorld)
    {
        super(image, x, y);
    	this.voltage = voltage;
        this.capacity = capacity;
        this.initialX = initialX;
        this.initialY = initialY;
        this.image = image;
        gameWorld = batteryGameWorld;
    }
    
    @Override
    public void update() 
    {
        
        if(mouseDragEnded() && withinArrayCreationArea())
        {
        	if(firstBattery)
        		addHorizontalLine();
        	Battery newBattery = new Battery(image, mouseX(), mouseY(), gameWorld);
            gameWorld.addObject(newBattery);
            newBattery.initiate(voltage, capacity);
            setLocation(initialX, initialY);
        }
        if(mouseIsBeingDragged())
        {
            setLocation(mouseX()-30, mouseY()-30);
        }
        else
        {
            setLocation(initialX, initialY);
        }
    }
    
    public static void addHorizontalLine()
    {
    	horizontalLine = new BasicComponent(BatteryGameScreen.getHorizontalLineImage(),25,120);
    	gameWorld.addComponent(horizontalLine);
    	firstBattery = false;
    }
    
    public static void removeHorizontalLine()
    {
    	gameWorld.removeComponent(horizontalLine);
    	firstBattery = true;
    }
}

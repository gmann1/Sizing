package edu.asu.voctec.batter_sizing;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.game_states.GUI;

public class BatteryGameScreen extends GUI
{

	public static final String GameBackground = "resources/default/img/minigames/BatterySizing/GameBackground.jpg";
	public static final String BlueBattery = "resources/default/img/minigames/BatterySizing/BlueBattery.png";
	public static final String YellowBattery = "resources/default/img/minigames/BatterySizing/YellowBattery.png";
	public static final String RedBattery = "resources/default/img/minigames/BatterySizing/RedBattery.png";
	public static final String GreenBattery = "resources/default/img/minigames/BatterySizing/GreenBattery.png";
	
	public static final String HorizontalLine = "resources/default/img/minigames/BatterySizing/Line.png";
	private static Image horizontalLineImage;
	
	public static final String VerticalLines = "resources/default/img/minigames/BatterySizing/TwoLines.png";
	private static Image verticalLinesImage;
	
	private  TextField currentVoltage, currentCapacity;
	public static List<BatteryControl> objectsArray = new ArrayList<BatteryControl>();
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(GameBackground);
		verticalLinesImage = new Image(VerticalLines);
		horizontalLineImage = new Image(HorizontalLine);
		
		Rectangle textLocation = new Rectangle(25, 15, 400, 30);
		TextField requiredCapacity = new TextField(textLocation, 0.95f,
				"Required Battery-Bank Output: 200 Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		this.addComponent(requiredCapacity);
		
		Rectangle textLocation2 = new Rectangle(25, 45, 400, 30);
		TextField requiredVoltage = new TextField(textLocation2, 0.95f,
				"DC System Voltage: 12 V",
				TextDisplay.FormattingOption.FIT_TEXT);
		this.addComponent(requiredVoltage);
		
		Rectangle textLocation3 = new Rectangle(525, 15, 275, 30);
		currentCapacity = new TextField(textLocation3, 0.95f,
				"Total Amp Hours: 0 Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		this.addComponent(currentCapacity);
		
		Rectangle textLocation4 = new Rectangle(525, 45, 275, 30);
		currentVoltage = new TextField(textLocation4, 0.95f,
				"Total System Voltage: 0 V",
				TextDisplay.FormattingOption.FIT_TEXT);
		this.addComponent(currentVoltage);
		
		InitialBattery battery1 = new InitialBattery(6, 130, 20, 520, new Image(BlueBattery), 20, 520, this);
		InitialBattery battery2 = new InitialBattery(6, 260, 170, 520, new Image(YellowBattery), 170, 520, this);
		InitialBattery battery3 = new InitialBattery(12, 130, 320, 520, new Image(RedBattery), 320, 520, this);
		InitialBattery battery4 = new InitialBattery(12, 260, 470, 520, new Image(GreenBattery), 470, 520, this);
		
		addObject(battery1);
		addObject(battery2);
		addObject(battery3);
		addObject(battery4);
	}
	
	public void addObject(BatteryControl object)
	{
		objectsArray.add(object);
		this.addComponent(object);
	}
	
	public void removeObject(BatteryControl object)
	{
		objectsArray.remove(object);
		this.removeComponent(object);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		for(int index =0; index<objectsArray.size(); index++)
		{
			BatteryControl invokedObject = objectsArray.get(index);
			invokedObject.update();
		}
	}

	public static Image getVerticalLinesImage() {
		return verticalLinesImage;
	}
	
	public static Image getHorizontalLineImage() {
		return horizontalLineImage;
	}
	
	public void changeCurrentCapacity(int capacity)
	{
		currentCapacity.setText("Total Amp Hours: "+capacity+" Ah");
	}
	
	public void changeCurrentCapacity(String capacity)
	{
		currentCapacity.setText("Total Amp Hours: "+capacity+" Ah");
	}
	
	public void changeCurrentVoltage(int voltage)
	{
		currentVoltage.setText("Total System Voltage: "+voltage+" V");
	}
	
	public void changeCurrentVoltage(String voltage)
	{
		currentVoltage.setText("Total System Voltage: "+voltage+" V");
	}
	
}

package edu.asu.voctec.batter_sizing;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
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
	
	private static final String[] hintsTextArray = {"Parallel batteries should share the same voltage but can have different capacities.",
													"Sets of batteries connected in series should have the same capacity but can have different voltage values",
													"The array can be solved using 1 or 2 batteries.",
													"Two batteries could be connected in series to solve the game",
													"Connecting two batteries in parallel is not recommended but could solve the game"};
	
	private  TextField currentVoltage, currentCapacity;
	private static TextArea hintsText;
	private static int currentHintText = 0;
	public static List<BatteryControl> objectsArray = new ArrayList<BatteryControl>();
	private InitialBattery battery1, battery2, battery3, battery4;
	private static final int RequiredCapacity = 174, RequiredVoltage = 12;
	private Button doneButton;
	private boolean doneButtonAvailable = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(GameBackground);
		verticalLinesImage = new Image(VerticalLines);
		horizontalLineImage = new Image(HorizontalLine);
		
		battery1 = new InitialBattery(12, 90, 20, 520, new Image(BlueBattery), 20, 520, this);
		battery2 = new InitialBattery(12, 200, 170, 520, new Image(YellowBattery), 170, 520, this);
		battery3 = new InitialBattery(6, 200, 320, 520, new Image(RedBattery), 320, 520, this);
		//battery4 = new InitialBattery(12, 260, 470, 520, new Image(GreenBattery), 470, 520, this);
		
		initializeText();
		
		addObject(battery1);
		addObject(battery2);
		addObject(battery3);
		//addObject(battery4);
		
		Button backButton = new Button(new Image("resources/default/img/buttons/backButton.png"), 1, 1,
			    new Rectangle(1, 1, 40, 40), "Back");
		backButton.setFontColor(Color.blue);
		backButton.addActionListener(new TransitionButtonListener(BatteryIntro.class));	  
		this.addComponent(backButton);
		
		doneButton = new Button(new Image("resources/default/img/minigames/BatterySizing/DoneButton.png"), 660, 350,
			    new Rectangle(660, 350, 83, 30), "");
		doneButton.addActionListener(new TransitionButtonListener(BatteryExitScreen.class));
		
		Button hintButton = new Button(new Image("resources/default/img/minigames/BatterySizing/HintButton.png"), 660, 403,
			    new Rectangle(660, 403, 83, 30), "");
		hintButton.addActionListener(new HintsButtonListener());
		this.addComponent(hintButton);
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
		super.update(container,game,delta);
		
		for(int index =0; index<objectsArray.size(); index++)
		{
			BatteryControl invokedObject = objectsArray.get(index);
			invokedObject.update();
		}
		
		if(Battery.isGameOver() && !doneButtonAvailable)
		{
			this.addComponent(doneButton);
			doneButtonAvailable = true;
			BatteryExitScreen.passNumberOfBatteriesUsed();
		}
		else if(!Battery.isGameOver() && doneButtonAvailable)
		{
			this.removeComponent(doneButton);
			doneButtonAvailable = false;
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
	
	public static int getRequiredCapacity()
	{
		return RequiredCapacity;
	}
	
	public static int getRequiredVoltage()
	{
		return RequiredVoltage;
	}
	
	private void showNextHintText()
	{
		hintsText.setText(hintsTextArray[currentHintText]);
		if(currentHintText == 4)
			currentHintText = 0;
		else
			currentHintText++;
	}
	
	private void initializeText()
	{
		Rectangle textLocation = new Rectangle(60, 15, 400, 30);
		TextField requiredCapacityText = new TextField(textLocation, 0.95f,
				"Required Battery-Bank Output: "+RequiredCapacity+" Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		requiredCapacityText.setFontColor(Color.black);
		this.addComponent(requiredCapacityText);
		
		Rectangle textLocation2 = new Rectangle(60, 45, 400, 30);
		TextField requiredVoltageText = new TextField(textLocation2, 0.95f,
				"DC System Voltage: "+RequiredVoltage+" V",
				TextDisplay.FormattingOption.FIT_TEXT);
		requiredVoltageText.setFontColor(Color.black);
		this.addComponent(requiredVoltageText);
		
		Rectangle textLocation3 = new Rectangle(525, 15, 275, 30);
		currentCapacity = new TextField(textLocation3, 0.95f,
				"Total Amp Hours: 0 Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		currentCapacity.setFontColor(Color.black);
		this.addComponent(currentCapacity);
		
		Rectangle textLocation4 = new Rectangle(525, 45, 275, 30);
		currentVoltage = new TextField(textLocation4, 0.95f,
				"Total System Voltage: 0 V",
				TextDisplay.FormattingOption.FIT_TEXT);
		currentVoltage.setFontColor(Color.black);
		this.addComponent(currentVoltage);
		
		Rectangle textLocation5 = new Rectangle(85, 525, 80, 30);
		TextField battery1Capacity = new TextField(textLocation5, 0.95f,
				battery1.getCapacity()+" Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery1Capacity.setFontColor(Color.black);
		this.addComponent(battery1Capacity);
		
		Rectangle textLocation6 = new Rectangle(85, 550, 80, 30);
		TextField battery1Voltage = new TextField(textLocation6, 0.95f,
				battery1.getVoltage()+" V",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery1Voltage.setFontColor(Color.black);
		this.addComponent(battery1Voltage);
		
		Rectangle textLocation7 = new Rectangle(235, 525, 80, 30);
		TextField battery2Capacity = new TextField(textLocation7, 0.95f,
				battery2.getCapacity()+" Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery2Capacity.setFontColor(Color.black);
		this.addComponent(battery2Capacity);
		
		Rectangle textLocation8 = new Rectangle(235, 550, 80, 30);
		TextField battery2Voltage = new TextField(textLocation8, 0.95f,
				battery2.getVoltage()+" V",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery2Voltage.setFontColor(Color.black);
		this.addComponent(battery2Voltage);
		
		Rectangle textLocation9 = new Rectangle(385, 525, 80, 30);
		TextField battery3Capacity = new TextField(textLocation9, 0.95f,
				battery3.getCapacity()+" Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery3Capacity.setFontColor(Color.black);
		this.addComponent(battery3Capacity);
		
		Rectangle textLocation10 = new Rectangle(385, 550, 80, 30);
		TextField battery3Voltage = new TextField(textLocation10, 0.95f,
				battery3.getVoltage()+" V",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery3Voltage.setFontColor(Color.black);
		this.addComponent(battery3Voltage);
		
		/*Rectangle textLocation11 = new Rectangle(535, 525, 80, 30);
		TextField battery4Capacity = new TextField(textLocation11, 0.95f,
				battery4.getCapacity()+" Ah",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery4Capacity.setFontColor(Color.black);
		this.addComponent(battery4Capacity);
		
		Rectangle textLocation12 = new Rectangle(535, 550, 80, 30);
		TextField battery4Voltage = new TextField(textLocation12, 0.95f,
				battery4.getVoltage()+" V",
				TextDisplay.FormattingOption.FIT_TEXT);
		battery4Voltage.setFontColor(Color.black);
		this.addComponent(battery4Voltage);*/
		
		Rectangle textLocation13 = new Rectangle(609, 433, 191, 300);
		hintsText = new TextArea(textLocation13, 0.95f,
				"");
		hintsText.setFontColor(Color.black);
		hintsText.setFontSize(14);
		this.addComponent(hintsText);
	}
	
	public class HintsButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			showNextHintText();
		}
	}
	
	public static void reset()
	{
		hintsText.setText("");
		currentHintText = 0;
		Battery.reset();
	}
}

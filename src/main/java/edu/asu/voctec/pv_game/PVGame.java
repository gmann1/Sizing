package edu.asu.voctec.pv_game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;

public class PVGame extends GUI
{

	public static final String GameBackground = "resources/default/img/minigames/BatterySizing/GameBackground.jpg";
	public static final String BlueBattery = "resources/default/img/minigames/BatterySizing/BluePV.png";
	public static final String YellowBattery = "resources/default/img/minigames/BatterySizing/YellowPV.png";
	public static final String RedBattery = "resources/default/img/minigames/BatterySizing/RedPV.png";
	public static final String GreenBattery = "resources/default/img/minigames/BatterySizing/GreenPV.png";
	
	public static final String HorizontalLine = "resources/default/img/minigames/BatterySizing/Line.png";
	private static Image horizontalLineImage;
	
	public static final String VerticalLines = "resources/default/img/minigames/BatterySizing/TwoLines.png";
	private static Image verticalLinesImage;
	
	private static final String[] hintsTextArray = {"Parallel PV panels should share the same voltage but can have different power.",
													"Sets of PV panels connected in series should have the same power but can have different voltage values",
													"The array can be solved using 1 or 2 PV panels.",
													"Two panels could be connected in parallel to solve the game."};
	
	private  TextField currentVoltage, currentCapacity;
	private static TextArea hintsText;
	private static int currentHintText = 0;
	public static List<BatteryControl> objectsArray = new ArrayList<BatteryControl>();
	private List<InitialBattery> initialBatteries = new ArrayList<InitialBattery>();
	private static final int RequiredCapacity = 127, RequiredVoltage = 12;
	private boolean firstRoundOfHints = true;
	public static int totalNumberOfHintsUsed = 0;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(GameBackground);
		verticalLinesImage = new Image(VerticalLines);
		horizontalLineImage = new Image(HorizontalLine);
		
		initialBatteries.add(new InitialBattery(12, 65, 20, 520, new Image(BlueBattery), 20, 520, this));
		initialBatteries.add(new InitialBattery(12, 130, 170, 520, new Image(YellowBattery), 170, 520, this));
		//initialBatteries.add(new InitialBattery(12, 320, 320, 520, new Image(RedBattery), 320, 520, this));
		//initialBatteries.add(new InitialBattery(12, 640, 470, 520, new Image(GreenBattery), 470, 520, this));
		
		initializeText();
		
		for(InitialBattery addInitialBattery :initialBatteries)
		{
			addObject(addInitialBattery);
		}
		
		Button backButton = new Button(new Image("resources/default/img/buttons/backButton.png"), 1, 1,
			    new Rectangle(1, 1, 40, 40), "Back");
		backButton.setFontColor(Color.blue);
		backButton.addActionListener(new TransitionButtonListener(PVIntro.class));	  
		this.addComponent(backButton);
		
		Button doneButton = new Button(new Image("resources/default/img/minigames/BatterySizing/DoneButton.png"), 660, 350,
			    new Rectangle(660, 350, 83, 30), "");
		doneButton.addActionListener(new DoneButtonListener());
		this.addComponent(doneButton);
		
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
	}

	public static Image getVerticalLinesImage() {
		return verticalLinesImage;
	}
	
	public static Image getHorizontalLineImage() {
		return horizontalLineImage;
	}
	
	public void changeCurrentCapacity(int capacity)
	{
		currentCapacity.setText("Total Watts: "+capacity+" Watts");
	}
	
	public void changeCurrentCapacity(String capacity)
	{
		currentCapacity.setText("Total Watts: "+capacity+" Watts");
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
		if(firstRoundOfHints)
			totalNumberOfHintsUsed++;
		if(currentHintText == (hintsTextArray.length-1))
		{
			currentHintText = 0;
			firstRoundOfHints = false;
		}
		else
			currentHintText++;
	}
	
	private void initializeText()
	{
		Rectangle textLocation = new Rectangle(60, 15, 400, 30);
		TextField requiredCapacityText = new TextField(textLocation, 0.95f,
				"Required Charging Power: "+RequiredCapacity+" Watts",
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
				"Total Watts: 0 Watts",
				TextDisplay.FormattingOption.FIT_TEXT);
		currentCapacity.setFontColor(Color.black);
		this.addComponent(currentCapacity);
		
		Rectangle textLocation4 = new Rectangle(525, 45, 275, 30);
		currentVoltage = new TextField(textLocation4, 0.95f,
				"Total System Voltage: 0 V",
				TextDisplay.FormattingOption.FIT_TEXT);
		currentVoltage.setFontColor(Color.black);
		this.addComponent(currentVoltage);
		
		for(int index = 0; index<initialBatteries.size(); index++)
		{
			InitialBattery addInitialBattery = initialBatteries.get(index);
			Rectangle textLocation5 = new Rectangle((85+(index*150)), 525, 80, 30);
			TextField battery1Capacity = new TextField(textLocation5, 0.95f,
					addInitialBattery.getCapacity()+" Watts",
					TextDisplay.FormattingOption.FIT_TEXT);
			battery1Capacity.setFontColor(Color.black);
			this.addComponent(battery1Capacity);
			
			Rectangle textLocation6 = new Rectangle((85+(index*150)), 550, 80, 30);
			TextField battery1Voltage = new TextField(textLocation6, 0.95f,
					addInitialBattery.getVoltage()+" V",
					TextDisplay.FormattingOption.FIT_TEXT);
			battery1Voltage.setFontColor(Color.black);
			this.addComponent(battery1Voltage);
		}
		
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
	
	public class DoneButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(Battery.isGameOver())
			{
				if(Battery.getNumberOfBatteries() > 2)
				{
					PVExit.passEndGameMessage("Sorry...",
							"You were not successful in completing this Game.",
							"You could have used fewer number of PV panels to solve the game.", Color.red);
				}
				else if(Battery.getNumberOfBatteries() <= 2)
				{
					if(Battery.batteryArray.size() == 2)
					{
						PVExit.passEndGameMessage("Sorry...",
								"You were not successful in completing this Game.",
								"You could have used fewer number of PV panels to solve the game.", Color.red);
					}
					else
					{
						PVExit.passEndGameMessage("Well Done...",
								"You have successfully completed the PV Array Sizing Game.",
								"You were able to solve the game in an optimal combination.", Color.blue);
					}
				}	
				Game.getCurrentGame().enterState(PVExit.class);
			}
			else
			{
				hintsText.setText("Sorry, You were not able to solve the PV Array Sizing Game correctly. Try Again.");
			}

		}
	}

	
	public static void reset()
	{
		hintsText.setText("");
		currentHintText = 0;
		Battery.reset();
	}
}

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
import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.Position;

public class PVGame extends GUI
{

	public static final String GameBackground = "resources/default/img/minigames/BatterySizing/GameBackground.jpg";
	public static final String GraySquare = "resources/default/img/minigames/BatterySizing/PVBlankGraySquare.png";
	public static final String HintBox = "resources/default/img/minigames/BatterySizing/hintBox.png";
	public static final String AvailableBatteriesAreaImage = "resources/default/img/minigames/BatterySizing/AvailableBatteriesArea.png";
	public static final String BlankButton = "resources/default/img/minigames/BatterySizing/blankReadyButton.png";
	public static final String BlankHintButton = "resources/default/img/minigames/BatterySizing/BlankHintButton.png";
	public static final String BlueBattery = "resources/default/img/minigames/BatterySizing/BluePV.png";
	public static final String YellowBattery = "resources/default/img/minigames/BatterySizing/YellowPV.png";
	public static final String RedBattery = "resources/default/img/minigames/BatterySizing/RedPV.png";
	public static final String GreenBattery = "resources/default/img/minigames/BatterySizing/GreenPV.png";
	public static final String Trash = "resources/default/img/minigames/BatterySizing/GarbageBin.png";
	
	public static final String FirstImage = "resources/default/img/minigames/BatterySizing/FirstImage.png";
	public static final String SecondImage = "resources/default/img/minigames/BatterySizing/SecondImagePV.png";
	private static Image PVImage;
	
	public static final String HorizontalLine = "resources/default/img/minigames/BatterySizing/pvMainLine.png";
	private static Image horizontalLineImage;
	
	public static final String VerticalLines = "resources/default/img/minigames/BatterySizing/TwoLines_PVGame.png";
	private static Image verticalLinesImage;
	
	public static final String TransparentPVPanel = "resources/default/img/minigames/BatterySizing/TransparentPVPanel.png";
	private static Image TransparentPVPanelImage;
	
	private static final String[] hintsTextArray = {"The array can be solved using 1 or 2 PV panels.",
													"Two panels could be connected in parallel to solve the game.",
													"The game could be solved using only one PV panel."};
	
	public static final String BatteryBankStartLabel = "Drag a panel here to place it in the PV Panels Array";
	public static final String AvailableBatteriesLabel = "Available PV Panels:";
	public static final String LEFT_ARROW_TEXT = "Back";
	public static final String DoneButtonText = "Done";
	public static final String HintButtonText = "Hint";
	public static final String CapacityUnit = " Watts";
	public static final String VoltageUnit = " V";
	public static final String UncalculatableVoltageHint = "Parallel PV panels should share the same voltage but can have different power.";
	public static final String UncalculatableCapacityHint = "PV panels connected in series should have the same power but can have different voltage values.";
	public static final String RequiredOutputLabel = "Required Charging Power: ";
	public static final String RequiredVoltageLabel = "DC System Voltage: ";
	public static final String CurrentOutputLabel = "Total Watts: ";
	public static final String CurrentVoltageLabel = "Total System Voltage: ";
	public static final String BatteryBankLabel = "PV Panels Array";
	public static final String ExtraObjectsUsedMessage = "You were not able to solve the PV Array Sizing Game correctly. You need to use fewer number of PV panels to solve the game.";
	public static final String CompletingGameMessage = "You have successfully completed the PV Array Sizing Game.";
	public static final String CorrectAnswerCongratulation = "Well Done...";
	public static final String CorrectAnswerMessage = "You were able to solve the game in an optimal combination.";
	public static final String IncorrectAnswerMessage = "You didn't solve the Game correctly. Remember: ";
	public static final String GameAnswer = "Using a 130Watts and 12V PV panel will solve the game.";
	
	public static final int [] Capacities = {65,130,130};
	public static final int [] Voltages = {12,12,12};
	
	private  TextField currentVoltage, currentCapacity;
	private static TextField batteryBankText;
	private static TextArea hintsText;
	private static int currentHintText = 0;
	public static List<BatteryControl> objectsArray = new ArrayList<BatteryControl>();
	private List<InitialBattery> initialBatteries = new ArrayList<InitialBattery>();
	private static final int RequiredCapacity = 127, RequiredVoltage = 12;
	private boolean firstRoundOfHints = true, parallelHintNOtDisplayed = true, seriesHintNOtDisplayed = true;
	public static int totalNumberOfHintsUsed = 0, doneButtonCounter = 0;
	private BasicComponent AnimationImage;
	private static boolean playingAnimation = false;
	private static int AnimationTimeCounter = 0;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(GameBackground);
		
	    BasicComponent batteryBankArea = new BasicComponent(new Image(GraySquare),25,85);
	    this.addComponent(batteryBankArea);
	    
	    BasicComponent hintBoxArea = new BasicComponent(HintBox,604,400,196,200);
	    this.addComponent(hintBoxArea);
	    
	    BasicComponent AvailableBatteriesArea = new BasicComponent(AvailableBatteriesAreaImage,25,514);
	    this.addComponent(AvailableBatteriesArea);
	    
	    Rectangle textLocation = new Rectangle(25, 250, 750, 60);
	    batteryBankText = new TextField(textLocation, 0.95f,
	    		BatteryBankStartLabel,
	        TextDisplay.FormattingOption.FIT_TEXT);
	    batteryBankText.setFontColor(Color.white);
	    batteryBankText.center();
	    this.addComponent(batteryBankText);
	    
	    Rectangle textLocation2 = new Rectangle(21, 514, 270, 20);
	    TextField AvailableBatteriesText = new TextField(textLocation2, 0.95f,
	    		AvailableBatteriesLabel,
	        TextDisplay.FormattingOption.FIT_TEXT);
	    AvailableBatteriesText.setFontColor(Color.white);
	    //AvailableBatteriesText.center();
	    this.addComponent(AvailableBatteriesText);

		verticalLinesImage = new Image(VerticalLines);
		horizontalLineImage = new Image(HorizontalLine);
		TransparentPVPanelImage = new Image(TransparentPVPanel);
		PVImage = new Image(SecondImage);
		
		initialBatteries.add(new InitialBattery(Voltages[0], Capacities[0], 50, 535, new Image(BlueBattery), 50, 535, this));
		initialBatteries.add(new InitialBattery(Voltages[1], Capacities[1], 200, 535, new Image(YellowBattery), 200, 535, this));
		initialBatteries.add(new InitialBattery(Voltages[2], Capacities[2], 350, 535, new Image(RedBattery), 350, 535, this));
		//initialBatteries.add(new InitialBattery(12, 640, 500, 535, new Image(GreenBattery), 500, 535, this));
		
		BasicComponent GarbageBin = new BasicComponent(new Image(Trash),540,525);
	    this.addComponent(GarbageBin);
		
		initializeText();
		
		BasicComponent TransparentBattery = new BasicComponent(TransparentPVPanelImage,60+(0*90),90+(0*90));
	    Battery.addToTransparentBatteriesArray(TransparentBattery);
	    this.addComponent(TransparentBattery);
		
		for(InitialBattery addInitialBattery :initialBatteries)
		{
			addObject(addInitialBattery);
		}
		
		// Back Button
	    Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
	        new Rectangle(0, 0, 50, 25), LEFT_ARROW_TEXT);
	    backButton.addActionListener(new TransitionButtonListener(PVIntro.class));
	    backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
	    backButton.positionText(Position.BOTTOM);
	  
		this.addComponent(backButton);
		
		Button doneButton = new Button(BlankButton, 660, 360, 88, 35,
			    new Rectangle(1, 1, 86, 33), DoneButtonText);
		doneButton.addActionListener(new DoneButtonListener());
		doneButton.setFontColor(Color.black);
		this.addComponent(doneButton);
		
		Button hintButton = new Button(BlankHintButton, 660, 403, 88, 45,
			    new Rectangle(2, 6, 84, 33), HintButtonText);
		hintButton.addActionListener(new HintsButtonListener());
		hintButton.setFontColor(Color.black);
		this.addComponent(hintButton);
		
		AnimationImage = new BasicComponent(new Image(FirstImage),25,85);
		this.addComponent(AnimationImage);
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
		if(playingAnimation)
		{
			if(AnimationImage == null)
			{
				playingAnimation = false;
			}
			if(AnimationTimeCounter == 30)
			{
				AnimationImage.setCurrentImage(PVImage, true);
			}
			if(AnimationTimeCounter == 60)
			{
				this.removeComponent(AnimationImage);
				AnimationImage = null;
				playingAnimation = false;
			}
			AnimationTimeCounter++;
		}
		else
		{
			super.update(container,game,delta);
			
			for(int index =0; index<objectsArray.size(); index++)
			{
				BatteryControl invokedObject = objectsArray.get(index);
				invokedObject.update();
			}
		}
	}

	public static Image getVerticalLinesImage() {
		return verticalLinesImage;
	}
	
	public static Image getHorizontalLineImage() {
		return horizontalLineImage;
	}
	
	public static Image getTransparentPVPanelImage() {
	    return TransparentPVPanelImage;
	  }

	
	public void changeCurrentCapacity(int capacity)
	{
		currentCapacity.setText(capacity+CapacityUnit);
	}
	
	public void changeCurrentCapacity(String capacity)
	{
		currentCapacity.setText(capacity+CapacityUnit);
	}
	
	public void changeCurrentVoltage(int voltage)
	{
		currentVoltage.setText(voltage+VoltageUnit);
	}
	
	public void changeCurrentVoltage(String voltage)
	{
		currentVoltage.setText(voltage+VoltageUnit);
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
		if(!Battery.allParallelsHaveSameVoltage())
		{
			hintsText.setText(UncalculatableVoltageHint);
			hintsText.setFontColor(Color.white);
			if(parallelHintNOtDisplayed)
			{
				totalNumberOfHintsUsed++;
				parallelHintNOtDisplayed = false;
			}
		}
		else if(!Battery.allSeriesHaveSameCapacity())
		{
			hintsText.setText(UncalculatableCapacityHint);
			hintsText.setFontColor(Color.white);
			if(seriesHintNOtDisplayed)
			{
				totalNumberOfHintsUsed++;
				seriesHintNOtDisplayed = false;
			}
		}
		else
		{
			hintsText.setText(hintsTextArray[currentHintText]);
			hintsText.setFontColor(Color.white);
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
	}
	
	private void showNextHintText(String doneButtonMessage)
	{
		if(!Battery.allParallelsHaveSameVoltage())
		{
			hintsText.setText(doneButtonMessage+UncalculatableVoltageHint);
			hintsText.setFontColor(Color.white);
			if(parallelHintNOtDisplayed)
			{
				totalNumberOfHintsUsed++;
				parallelHintNOtDisplayed = false;
			}
		}
		else if(!Battery.allSeriesHaveSameCapacity())
		{
			hintsText.setText(doneButtonMessage+UncalculatableCapacityHint);
			hintsText.setFontColor(Color.white);
			if(seriesHintNOtDisplayed)
			{
				totalNumberOfHintsUsed++;
				seriesHintNOtDisplayed = false;
			}
		}
		else
		{
			hintsText.setText(doneButtonMessage+hintsTextArray[currentHintText]);
			hintsText.setFontColor(Color.white);
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
	}
	
	private void initializeText()
	{
		Rectangle textLocation = new Rectangle(60, 15, 400, 30);
		TextField requiredCapacityText = new TextField(textLocation, 0.95f,
				RequiredOutputLabel+RequiredCapacity+CapacityUnit,
				TextDisplay.FormattingOption.FIT_TEXT);
		requiredCapacityText.setFontColor(Color.white);
		this.addComponent(requiredCapacityText);
		
		Rectangle textLocation2 = new Rectangle(60, 45, 400, 30);
		TextField requiredVoltageText = new TextField(textLocation2, 0.95f,
				RequiredVoltageLabel+RequiredVoltage+VoltageUnit,
				TextDisplay.FormattingOption.FIT_TEXT);
		requiredVoltageText.setFontColor(Color.white);
		this.addComponent(requiredVoltageText);
		
		Rectangle textLocation3 = new Rectangle(525, 15, 275, 30);
		TextField currentCapacityText = new TextField(textLocation3, 0.95f,
				CurrentOutputLabel,
				TextDisplay.FormattingOption.FIT_TEXT);
		currentCapacityText.setFontColor(Color.darkGray);
		this.addComponent(currentCapacityText);
		
		Rectangle textLocation4 = new Rectangle(525, 45, 275, 30);
		TextField currentVoltageText = new TextField(textLocation4, 0.95f,
				CurrentVoltageLabel,
				TextDisplay.FormattingOption.FIT_TEXT);
		currentVoltageText.setFontColor(Color.darkGray);
		this.addComponent(currentVoltageText);
		
		Rectangle textLocation7 = new Rectangle(648, 15, 275, 30);
	    currentCapacity = new TextField(textLocation7, 0.95f,
	        0+CapacityUnit,
	        TextDisplay.FormattingOption.FIT_TEXT);
	    currentCapacity.setFontColor(Color.red);
	    this.addComponent(currentCapacity);
	    
	    Rectangle textLocation8 = new Rectangle(730, 45, 275, 30);
	    currentVoltage = new TextField(textLocation8, 0.95f,
	        0+VoltageUnit,
	        TextDisplay.FormattingOption.FIT_TEXT);
	    currentVoltage.setFontColor(Color.red);
	    this.addComponent(currentVoltage);

		
		for(int index = 0; index<initialBatteries.size(); index++)
		{
			InitialBattery addInitialBattery = initialBatteries.get(index);
			Rectangle textLocation5 = new Rectangle((115+(index*150)), 540, 80, 30);
			TextField battery1Capacity = new TextField(textLocation5, 0.95f,
					addInitialBattery.getCapacity()+CapacityUnit,
					TextDisplay.FormattingOption.FIT_TEXT);
			battery1Capacity.setFontColor(Color.white);
			this.addComponent(battery1Capacity);
			
			Rectangle textLocation6 = new Rectangle((115+(index*150)), 565, 80, 30);
			TextField battery1Voltage = new TextField(textLocation6, 0.95f,
					addInitialBattery.getVoltage()+VoltageUnit,
					TextDisplay.FormattingOption.FIT_TEXT);
			battery1Voltage.setFontColor(Color.white);
			this.addComponent(battery1Voltage);
		}
		
		Rectangle textLocation13 = new Rectangle(609, 433, 191, 300);
		hintsText = new TextArea(textLocation13, 0.95f,
				"");
		hintsText.setFontColor(Color.white);
		hintsText.setFontSize(14);
		this.addComponent(hintsText);
	}
	
	public static void changeBatteryBankText()
	  {
	    batteryBankText.setText(BatteryBankLabel);
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
					hintsText.setText(ExtraObjectsUsedMessage);
					hintsText.setFontColor(Color.red);
				}
				else if(Battery.getNumberOfBatteries() <= 2)
				{
					if(Battery.batteryArray.size() == 2)
					{
						hintsText.setText(ExtraObjectsUsedMessage);
						hintsText.setFontColor(Color.red);
					}
					else
					{
						PVExit.passEndGameMessage(CorrectAnswerCongratulation,
								CompletingGameMessage,
								CorrectAnswerMessage, Color.black);	
						Game.getCurrentGame().enterState(PVExit.class);
					}
				}
			}
			else
			{
				showNextHintText(IncorrectAnswerMessage);
			}
			
			if(doneButtonCounter >= 5)
			{
				hintsText.setText(GameAnswer);
				hintsText.setFontColor(Color.red);
			}
			
			doneButtonCounter++;
		}
	}
	
	public static void playAnimation()
	{
		playingAnimation = true;
		AnimationTimeCounter = 0;
	}
	
	public static void reset()
	{
		hintsText.setText("");
		batteryBankText.setText(BatteryBankStartLabel);
		currentHintText = 0;
		doneButtonCounter = 0;
		Battery.reset();
	}
}

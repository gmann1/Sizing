package edu.asu.voctec.batter_sizing;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

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

public class BatteryGameScreen extends GUI
{

	public static final String GameBackground = "resources/default/img/minigames/BatterySizing/GameBackground.jpg";
	public static final String GraySquare = "resources/default/img/minigames/BatterySizing/BatteryBlankGraySquare.png";
	public static final String HintBox = "resources/default/img/minigames/BatterySizing/hintBox.png";
	public static final String AvailableBatteriesAreaImage = "resources/default/img/minigames/BatterySizing/AvailableBatteriesArea.png";
	public static final String BlankButton = "resources/default/img/minigames/BatterySizing/blankReadyButton.png";
	public static final String BlankHintButton = "resources/default/img/minigames/BatterySizing/BlankHintButton.png";
	public static final String BlueBattery = "resources/default/img/minigames/BatterySizing/BlueBattery.png";
	public static final String YellowBattery = "resources/default/img/minigames/BatterySizing/YellowBattery.png";
	public static final String RedBattery = "resources/default/img/minigames/BatterySizing/RedBattery.png";
	public static final String GreenBattery = "resources/default/img/minigames/BatterySizing/GreenBattery.png";
	public static final String Trash = "resources/default/img/minigames/BatterySizing/GarbageBin.png";
	
	public static final String FirstImage = "resources/default/img/minigames/BatterySizing/FirstImage.png";
	public static final String SecondImage = "resources/default/img/minigames/BatterySizing/SecondImageBattery.png";
	private static Image BatteryImage;
	
	public static final String HorizontalLine = "resources/default/img/minigames/BatterySizing/BatteryMainLine.png";
	private static Image horizontalLineImage;
	
	public static final String VerticalLines = "resources/default/img/minigames/BatterySizing/TwoLines.png";
	private static Image verticalLinesImage;
	
	public static final String TransparentBattery = "resources/default/img/minigames/BatterySizing/TransparentBattery.png";
	private static Image TransparentBatteryImage;
	
	private static final String[] hintsTextArray = {"The array can be solved using 1 or 2 batteries.",
													"Two batteries could be connected in series to solve the game.",
													"Connecting two batteries in parallel is not recommended but could solve the game."};
	
	public static final String BatteryBankStartLabel = "Drag a battery here to place it in the Battery Bank";
	public static final String AvailableBatteriesLabel = "Available Batteries:";
	public static final String LEFT_ARROW_TEXT = "Back";
	public static final String DoneButtonText = "Done";
	public static final String HintButtonText = "Hint";
	public static final String CapacityUnit = " Ah";
	public static final String VoltageUnit = " V";
	public static final String UncalculatableVoltageHint = "Parallel batteries should share the same voltage but can have different capacities.";
	public static final String UncalculatableCapacityHint = "Batteries connected in series should share the same capacity but can have different voltages.";
	public static final String RequiredOutputLabel = "Required Battery-Bank Output: ";
	public static final String RequiredVoltageLabel = "DC System Voltage: ";
	public static final String CurrentOutputLabel = "Total Amp Hours: ";
	public static final String CurrentVoltageLabel = "Total System Voltage: ";
	public static final String BatteryBankLabel = "Battery Bank";
	public static final String ExtraObjectsUsedMessage = "You were not able to solve the Battery Sizing Game correctly. You need to use fewer number of batteries.";
	public static final String UnfavourableAnswerCongratulation = "Nice Job...";
	public static final String CompletingGameMessage = "You have successfully completed the Battery Sizing Game.";
	public static final String UnfavourableAnswerMessage = "However, This solution is not recommended.";
	public static final String CorrectAnswerCongratulation = "Well Done...";
	public static final String CorrectAnswerMessage = "You were able to solve the game in an optimal combination.";
	public static final String IncorrectAnswerMessage = "You didn't solve the Game correctly. Remember: ";
	public static final String GameAnswer = "Using a 200Ah and 12V battery will solve the game.";
	
	public static final int [] Capacities = {90,200,200};
	public static final int [] Voltages = {12,12,6};
	
	private  TextField currentVoltage, currentCapacity;
	private static TextField batteryBankText;
	private static TextArea hintsText;
	private static int currentHintText = 0;
	public static List<BatteryControl> objectsArray = new ArrayList<BatteryControl>();
	private List<InitialBattery> initialBatteries = new ArrayList<InitialBattery>();
	private static final int RequiredCapacity = 174, RequiredVoltage = 12;
	private boolean firstRoundOfHints = true, parallelHintNOtDisplayed = true, seriesHintNOtDisplayed = true;;
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
		
		Rectangle textLocation2 = new Rectangle(25, 514, 270, 20);
		TextField AvailableBatteriesText = new TextField(textLocation2, 0.95f,
				AvailableBatteriesLabel,
				TextDisplay.FormattingOption.FIT_TEXT);
		AvailableBatteriesText.setFontColor(Color.white);
		//AvailableBatteriesText.center();
		this.addComponent(AvailableBatteriesText);
		
		verticalLinesImage = new Image(VerticalLines);
		horizontalLineImage = new Image(HorizontalLine);
		TransparentBatteryImage = new Image(TransparentBattery);
		BatteryImage = new Image(SecondImage);
		
		initialBatteries.add(new InitialBattery(Voltages[0], Capacities[0], 50, 535, new Image(BlueBattery), 50, 535, this));
		initialBatteries.add(new InitialBattery(Voltages[1], Capacities[1], 200, 535, new Image(YellowBattery), 200, 535, this));
		initialBatteries.add(new InitialBattery(Voltages[2], Capacities[2], 350, 535, new Image(RedBattery), 350, 535, this));
		//initialBatteries.add(new InitialBattery(12, 260, 500, 520, new Image(GreenBattery), 500, 520, this));
		
		BasicComponent GarbageBin = new BasicComponent(new Image(Trash),540,525);
		this.addComponent(GarbageBin);
		
		initializeText();
		
		BasicComponent TransparentBattery = new BasicComponent(TransparentBatteryImage,60+(0*90),90+(0*90));
		Battery.addToTransparentBatteriesArray(TransparentBattery);
		this.addComponent(TransparentBattery);
		
		for(InitialBattery addInitialBattery :initialBatteries)
		{
			addObject(addInitialBattery);
		}
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), LEFT_ARROW_TEXT);
		backButton.addActionListener(new TransitionButtonListener(BatteryIntro.class));
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
				AnimationImage.setCurrentImage(BatteryImage, true);
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
	
	public static Image getTransparentBatteryImage() {
		return TransparentBatteryImage;
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
		
		Rectangle textLocation7 = new Rectangle(688, 15, 275, 30);
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
					if(Battery.batteryArray.get(0).size() == 2)
					{
						BatteryExitScreen.passEndGameMessage(UnfavourableAnswerCongratulation,
								CompletingGameMessage,
								UnfavourableAnswerMessage, Color.red);
						Game.getCurrentGame().enterState(BatteryExitScreen.class);
					}
					else if(Battery.getTotalVoltage()>12)
					{
						hintsText.setText(ExtraObjectsUsedMessage);
						hintsText.setFontColor(Color.red);
					}
					else
					{
						BatteryExitScreen.passEndGameMessage(CorrectAnswerCongratulation,
								CompletingGameMessage,
								CorrectAnswerMessage, Color.black);
						Game.getCurrentGame().enterState(BatteryExitScreen.class);
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

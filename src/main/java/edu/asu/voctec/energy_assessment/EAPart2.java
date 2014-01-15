package edu.asu.voctec.energy_assessment;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.ActionListener;
import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.Fonts;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.batter_sizing.Battery;
import edu.asu.voctec.batter_sizing.BatteryControl;
import edu.asu.voctec.batter_sizing.InitialBattery;
import edu.asu.voctec.cdmg.CDIntroScreen;
import edu.asu.voctec.cdmg.CDPart1.CDContinueListener;
import edu.asu.voctec.cdmg.CDPart1.CDHintListener;
import edu.asu.voctec.cdmg.CDPart1.CDReadyListener;
import edu.asu.voctec.energy_assessment.EAPart1.ReadyButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.UtilFunctions;
import edu.asu.voctec.utilities.gameTemplate;

public class EAPart2 extends gameTemplate
{
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/New/Game1Background.png";
	private static final String APPBACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	private static final String DRAGBACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	private static final String SQUARE = "resources/default/img/minigames/energyAssessment/New/AppBoxTransparent.png";
	private static final String STARTSQUARE = "resources/default/img/minigames/energyAssessment/New/AppBox.png";
	
	private static final String CFL = "resources/default/img/minigames/energyAssessment/New/CFL.png";
	private static final String LED = "resources/default/img/minigames/energyAssessment/New/LED.png";
	private static final String RADIO = "resources/default/img/minigames/energyAssessment/New/Radio.png";
	private static final String TELIVISION = "resources/default/img/minigames/energyAssessment/New/TV.png";
	private static final String PHONE = "resources/default/img/minigames/energyAssessment/New/Cellphone.png";

	static PowerBar powerBar;
	
	private static int hintNumber = 2;
	private String[] hintArray = 
		{
			"There is at least 2 CFLs",
			"There is at least 1 LED",
			"There is at least 1 Radio"
		};
	
	private boolean continueGood = false;
	protected static int targetPowerRating = 81;
	public static int totalPowerRating = 0;
	public static int[] applianceArray = {0,0,0,0,0};
	public static int[] powerRatings = {14,9,30,60,2};
	public static String[] applianceNames = {"CFL","LED","Radio","TV","Cellphone"};
	public static List<ObjectMove> objectsArray = new ArrayList<ObjectMove>();
	private List<InitialObjects> initialObjects = new ArrayList<InitialObjects>();
	public static int locationArray[][] = 
	{
		{145,175},
		{255,175},
		{365,175},
		{200,285},
		{310,285}
	};
	
	public static int initialArray[][] = 
	{
		{25,475},
		{135,475},
		{245,475},
		{355,475},
		{465,475}
	};
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		super.init(container,  game );
		this.backgroundImage = new Image(BACKGROUND);	
		
		//Appliance Background
		//BasicComponent appliancesBackground = new BasicComponent(APPBACKGROUND,0,430);
		//this.addComponent(appliancesBackground);
		
		//Appliance square1
		BasicComponent square1 = new BasicComponent(SQUARE,locationArray[0][0],locationArray[0][1]);
		this.addComponent(square1);
		//Appliance square2
		BasicComponent square2 = new BasicComponent(SQUARE,locationArray[1][0],locationArray[1][1]);
		this.addComponent(square2);
		//Appliance square3
		BasicComponent square3 = new BasicComponent(SQUARE,locationArray[2][0],locationArray[2][1]);
		this.addComponent(square3);
		//Appliance square4
		BasicComponent square4 = new BasicComponent(SQUARE,locationArray[3][0],locationArray[3][1]);
		this.addComponent(square4);
		//Appliance square5
		BasicComponent square5 = new BasicComponent(SQUARE,locationArray[4][0],locationArray[4][1]);
		this.addComponent(square5);
		
		//appliances Plate
		BasicComponent cfl = new BasicComponent(SQUARE,initialArray[0][0],initialArray[0][1]);
		this.addComponent(cfl);
		BasicComponent led = new BasicComponent(SQUARE,initialArray[1][0],initialArray[1][1]);
		this.addComponent(led);
		BasicComponent radio = new BasicComponent(SQUARE,initialArray[2][0],initialArray[2][1]);
		this.addComponent(radio);
		BasicComponent telivision = new BasicComponent(SQUARE,initialArray[3][0],initialArray[3][1]);
		this.addComponent(telivision);
		BasicComponent laptop = new BasicComponent(SQUARE,initialArray[4][0],initialArray[4][1]);
		this.addComponent(laptop);
		
		initializeWatts();
		initializeNames();
		initializeText();
		
		powerBar = new PowerBar(500,50,.8,81,81,100);
		this.addComponent(powerBar);
		powerBar.addPowerBarComponents(this);
		powerBar.updatePowerBar(totalPowerRating);
		
		instructionBox.setText("Drag the diffrent appliances to the boxs to meet the target power rating.");
		
		initialObjects.add(new InitialObjects(new Image(CFL), 25, 475, this,1,14));
		initialObjects.add(new InitialObjects(new Image(LED), 135, 475, this,2,9));
		initialObjects.add(new InitialObjects(new Image(RADIO), 245, 475, this,3,30));
		initialObjects.add(new InitialObjects(new Image(TELIVISION), 355, 475, this,4,60));
		initialObjects.add(new InitialObjects(new Image(PHONE), 465, 475, this,5,40));
		
		for(InitialObjects addInitialObjects :initialObjects)
		{
			addObject(addInitialObjects);
		}
		
		readyButton.addActionListener(new ReadyButtonListener());
		continueButton.addActionListener(new ContinueButtonListener());
		hintButton.addActionListener(new HintButtonListener());
		backButton.addActionListener(new TransitionButtonListener(EAPart1IntroScreen.class));
		
		////Testing Stuff can be deleted later////
		//Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		//Start.addActionListener(new TransitionButtonListener(EAPart2ScoreScreen.class));
		//this.addComponent(Start);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		super.update(container,game,delta);
		
		for(int index =0; index<objectsArray.size(); index++)
		{
			ObjectMove invokedObject = objectsArray.get(index);
			invokedObject.update();
		}
	}
	
	public static void updatePowerRating()
	{
		totalPowerRating = 0;
		for(int v = 0;v<5;v++)
		{
			if(applianceArray[v] == 1)
				totalPowerRating += powerRatings[0];
			else if(applianceArray[v] == 2)
				totalPowerRating += powerRatings[1];
			else if(applianceArray[v] == 3)
				totalPowerRating += powerRatings[2];
			else if(applianceArray[v] == 4)
				totalPowerRating += powerRatings[3];
			else if(applianceArray[v] == 5)
				totalPowerRating += powerRatings[4];
		}
		powerBar.updatePowerBar(totalPowerRating);
		System.out.println("Total Power Rating: "+totalPowerRating);
	}
	
	public void addObject(ObjectMove object)
	{
		objectsArray.add(object);
		this.addComponent(object);
	}
	
	public void removeObject(ObjectMove thisObject)
	{
		objectsArray.remove(thisObject);
		this.removeComponent(thisObject);
	}
	
	private boolean allFilled()
	{
		for(int v=0;v<5;v++)
		{
			if(applianceArray[v] == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,Graphics graphics) throws SlickException 
	{
		super.render(container, game, graphics);
		//graphics.drawImage(new Image(applianceImage[v][0]),applianceArray[v][1],applianceArray[v][2]);
	}
		
 	public class ReadyButtonListener extends ButtonListener 
	{
		@Override
		protected void actionPerformed()
		{
			if(totalPowerRating == targetPowerRating && allFilled() == true)
			{
				try {
					continueButtonOn();
					continueGood = true;
					hintBox.setText("Good Job! you have the correct combination of items.");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				resetContinue();
		}
	}
 	
	public class ContinueButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(continueGood == true)
			{
				reset();
				Game.getCurrentGame().enterState(EAPart2ScoreScreen.class);
			}
		}
	}
	
	public class HintButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(hintNumber < 2)
				hintNumber++;
			else
				hintNumber = 0;
			hintBox.setText(hintArray[hintNumber]);
		}
	}
	
	private void initializeText()
	{
		TextAreaX targetPower = new TextAreaX(new Rectangle(425,400, 200, 100), 0.95f, "Target: "+targetPowerRating + " Watts");
		targetPower.setFontSize(16);
		targetPower.setFontColor(Color.white);
		this.addComponent(targetPower);
	}
	
	private void initializeWatts()
	{
		TextAreaX watt1 = new TextAreaX(new Rectangle(initialArray[0][0],initialArray[0][1]+90, 100, 50), 0.95f, powerRatings[0]+" Watt");
		watt1.setFontSize(16);
		watt1.setFontColor(Color.white);
		this.addComponent(watt1);
		
		TextAreaX watt2 = new TextAreaX(new Rectangle(initialArray[1][0],initialArray[1][1]+90, 100, 50), 0.95f, powerRatings[1]+" Watt");
		watt2.setFontSize(16);
		watt2.setFontColor(Color.white);
		this.addComponent(watt2);
		
		TextAreaX watt3 = new TextAreaX(new Rectangle(initialArray[2][0],initialArray[2][1]+90, 100, 50), 0.95f, powerRatings[2]+" Watt");
		watt3.setFontSize(16);
		watt3.setFontColor(Color.white);
		this.addComponent(watt3);
		
		TextAreaX watt4 = new TextAreaX(new Rectangle(initialArray[3][0],initialArray[3][1]+90, 100, 50), 0.95f, powerRatings[3]+" Watt");
		watt4.setFontSize(16);
		watt4.setFontColor(Color.white);
		this.addComponent(watt4);
		
		TextAreaX watt5 = new TextAreaX(new Rectangle(initialArray[4][0],initialArray[4][1]+90, 100, 50), 0.95f, powerRatings[4]+" Watt");
		watt5.setFontSize(16);
		watt5.setFontColor(Color.white);
		this.addComponent(watt5);
	}
	
	private void initializeNames()
	{
		TextAreaX name1 = new TextAreaX(new Rectangle(initialArray[0][0],initialArray[0][1]-30, 200, 50), 0.95f, applianceNames[0]);
		name1.setFontSize(16);
		name1.setFontColor(Color.white);
		this.addComponent(name1);
		
		TextAreaX name2 = new TextAreaX(new Rectangle(initialArray[1][0],initialArray[1][1]-30, 200, 50), 0.95f, applianceNames[1]);
		name2.setFontSize(16);
		name2.setFontColor(Color.white);
		this.addComponent(name2);
		
		TextAreaX name3 = new TextAreaX(new Rectangle(initialArray[2][0],initialArray[2][1]-30, 200, 50), 0.95f, applianceNames[2]);
		name3.setFontSize(16);
		name3.setFontColor(Color.white);
		this.addComponent(name3);
		
		TextAreaX name4 = new TextAreaX(new Rectangle(initialArray[3][0],initialArray[3][1]-30, 200, 50), 0.95f, applianceNames[3]);
		name4.setFontSize(16);
		name4.setFontColor(Color.white);
		this.addComponent(name4);
		
		TextAreaX name5 = new TextAreaX(new Rectangle(initialArray[4][0],initialArray[4][1]-30, 200, 50), 0.95f, applianceNames[4]);
		name5.setFontSize(16);
		name5.setFontColor(Color.white);
		this.addComponent(name5);
	}
	
	public void resetContinue()
	{
		continueGood = false;
		continueButton.setFontColor(Fonts.BUTTON_FONT_COLOR);
		try {
			continueButton.setCurrentImage(new Image(ImagePaths.CONTINUE_BUTTON_OFF), true);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reset()
	{
		
		hintNumber = 0;
		hintBox.setText("");
		Object.reset();
		totalPowerRating = 0;
		powerBar.updatePowerBar(totalPowerRating);
		
		resetContinue();
		for(int v = 0; v<5;v++)
		{
			applianceArray[v] = 0;
		}
		System.out.println("eaPart2 Reset");
		
	}
}

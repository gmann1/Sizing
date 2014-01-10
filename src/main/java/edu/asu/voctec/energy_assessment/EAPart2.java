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
import edu.asu.voctec.GameDefaults.ImagePaths;
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
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	private static final String APPBACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	private static final String DRAGBACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	private static final String SQUARE = "resources/default/img/minigames/energyAssessment/New/AppBoxTransparent.png";
	private static final String STARTSQUARE = "resources/default/img/minigames/energyAssessment/New/AppBox.png";
	
	private static final String CFL = "resources/default/img/minigames/energyAssessment/New/CFL.png";
	private static final String LED = "resources/default/img/minigames/energyAssessment/New/LED.png";
	private static final String RADIO = "resources/default/img/minigames/energyAssessment/New/Radio.png";
	private static final String TELIVISION = "resources/default/img/minigames/energyAssessment/New/TV.png";
	private static final String LAPTOP = "resources/default/img/minigames/energyAssessment/New/Laptop.png";
	
	private TextAreaX watt1, watt2, watt3, watt4, watt5;
	static PowerBar powerBar;
	
	private boolean continueGood = false;
	public static int totalPowerRating = 0;
	public static int[] applianceArray = {0,0,0,0,0};
	public static List<ObjectMove> objectsArray = new ArrayList<ObjectMove>();
	private List<InitialObjects> initialObjects = new ArrayList<InitialObjects>();
	public static int locationArray[][] = 
	{
		{25,210},
		{135,210},
		{245,210},
		{355,210},
		{465,210}
	};
	public static int powerRatings[] = {14,9,30,60,40};
	
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
		BasicComponent cfl = new BasicComponent(SQUARE,25,475);
		this.addComponent(cfl);
		
		BasicComponent led = new BasicComponent(SQUARE,135,475);
		this.addComponent(led);
		
		BasicComponent radio = new BasicComponent(SQUARE,245,475);
		this.addComponent(radio);
		
		BasicComponent telivision = new BasicComponent(SQUARE,355,475);
		this.addComponent(telivision);
		
		BasicComponent laptop = new BasicComponent(SQUARE,465,475);
		this.addComponent(laptop);
		
		//initializeWatts();
		powerBar = new PowerBar(20,20,1,81,81);
		
		this.addComponent(powerBar);
		this.addComponent(powerBar.powerBarIndicator);
		
		powerBar.updatePowerBar(totalPowerRating);
		
		this.addComponent(topText);
		topText.setText("Drag the diffrent appliances to the diffrent boxs to meet the total power rating.");
		
		initialObjects.add(new InitialObjects(new Image(CFL), 25, 475, this,1,14));
		initialObjects.add(new InitialObjects(new Image(LED), 135, 475, this,2,9));
		initialObjects.add(new InitialObjects(new Image(RADIO), 245, 475, this,3,30));
		initialObjects.add(new InitialObjects(new Image(TELIVISION), 355, 475, this,4,60));
		initialObjects.add(new InitialObjects(new Image(LAPTOP), 465, 475, this,5,40));
		
		for(InitialObjects addInitialObjects :initialObjects)
		{
			addObject(addInitialObjects);
		}
		
		readyButton.addActionListener(new ReadyButtonListener());
		continueButton.addActionListener(new ContinueButtonListener());
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
			if(totalPowerRating == 81 && allFilled() == true)
			{
				try {
					continueButtonOn();
					continueGood = true;
					topText.setText("Good Job! you have have the correct combination of items.");
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
 	
	public class ContinueButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(continueGood == true)
				Game.getCurrentGame().enterState(EAPart2ScoreScreen.class);
		}
	}
	
	private void initializeText()
	{

	}
	
	private void initializeWatts()
	{
		watt1 = new TextAreaX(new Rectangle(25, 570, 90, 20), 0.95f, "14 Watt");
		//watt1.setFontSize(16);
		//watt1.setFontColor(Color.white);
		this.addComponent(watt1);
		
		watt2 = new TextAreaX(new Rectangle(25, 570, 90, 20), 0.95f, "9 Watt");
		//watt2.setFontSize(16);
		//watt2.setFontColor(Color.white);
		this.addComponent(watt2);
		
		watt3 = new TextAreaX(new Rectangle(25, 570, 90, 20), 0.95f, "30 Watt");
		//watt3.setFontSize(16);
		//watt3.setFontColor(Color.white);
		this.addComponent(watt3);
		
		watt4 = new TextAreaX(new Rectangle(25, 570, 90, 20), 0.95f, "60 Watt");
		//watt4.setFontSize(16);
		//watt4.setFontColor(Color.white);
		this.addComponent(watt4);
		
		watt5 = new TextAreaX(new Rectangle(25, 570, 90, 20), 0.95f, "40 Watt");
		//watt5.setFontSize(16);
		//watt5.setFontColor(Color.white);
		this.addComponent(watt5);
	}
	
	public static void reset()
	{
		System.out.println("eaPart2 Reset");
	}
	
}

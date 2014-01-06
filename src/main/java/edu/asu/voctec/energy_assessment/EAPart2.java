package edu.asu.voctec.energy_assessment;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

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
import edu.asu.voctec.energy_assessment.EAPart1.ReadyButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.UtilFunctions;

public class EAPart2 extends GUI
{
	private static final String READY = "resources/default/img/minigames/energyAssessment/readyButton.png";
	private static final String CONTINUE = "resources/default/img/minigames/energyAssessment/continueButton.png";
	private static final String NOTREADY = "resources/default/img/minigames/energyAssessment/readyButtonGray.png";
	private static final String BACK = "resources/default/img/minigames/energyAssessment/backButton.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/part2/HouseBackground.png";
	private static final String HOUSE = "resources/default/img/minigames/energyAssessment/part2/House.png";
	private static final String OPTION = "resources/default/img/minigames/energyAssessment/part2/OptionBox.png";
	private static final String OPTIONBLANK = "resources/default/img/minigames/energyAssessment/part2/OptionBoxBlank.png";

	private static final String CFL = "resources/default/img/minigames/energyAssessment/part2/CFL.png";
	private static final String CFLCLICKED = "resources/default/img/minigames/energyAssessment/part2/CFLClicked.png";
	private static final String LED = "resources/default/img/minigames/energyAssessment/part2/LED.png";
	private static final String LEDCLICKED = "resources/default/img/minigames/energyAssessment/part2/LEDClicked.png";
	private static final String RADIO = "resources/default/img/minigames/energyAssessment/part2/Radio.png";
	private static final String RADIOCLICKED = "resources/default/img/minigames/energyAssessment/part2/RadioClicked.png";
	private static final String TRICK0 = "resources/default/img/minigames/energyAssessment/part2/Trick0.png";
	private static final String TRICK1 = "resources/default/img/minigames/energyAssessment/part2/Trick1.png";
	
	private static int step = 0;
	
	Button ready;
	Button continueButton;
	Button option0;
	Button option1;
	Button option2;
	
	private static TextField appliance0Watt;
	private static TextField appliance1Watt;
	private static TextField appliance2Watt;
	private static TextField appliance3Watt;
	private static TextField appliance4Watt;
	
	private TextArea hintText;
	private TextField instructionsText;

	private static int[][] applianceArray = 
		{
			{0,150,150},
			{0,270,150},
			{0,520,220},
			{0,20,120},
			{0,160,50},
			{0,10,250},//trick0
			{0,200,220},//trick1
		};
	private String[][] applianceImage = 
		{
			{CFL,CFLCLICKED},
			{CFL,CFLCLICKED},
			{CFL,CFLCLICKED},
			{LED,LEDCLICKED},
			{RADIO,RADIOCLICKED},
			{TRICK0,TRICK0},
			{TRICK1,TRICK1},
		};
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.backgroundImage = new Image(BACKGROUND);
		
		////Ready Button////
		ready = new Button(new Image(READY), 575, 500, new Rectangle(50,50,300,50), "");
		ready.addActionListener(new ReadyButtonListener());
		this.addComponent(ready);
		
		////Continue Button////
		continueButton = new Button(new Image(CONTINUE), 575, -500, new Rectangle(50,50,300,50), "");
		continueButton.addActionListener(new ContinueButtonListener());
		this.addComponent(continueButton);
		
		//Back Button
		Button back = new Button(new Image(BACK), 50, 500, new Rectangle(50,50,300,50), "");
		back.addActionListener(new TransitionButtonListener(EAPart2IntroScreen.class));
		this.addComponent(back);
		
		////Appliances////
		//0
		Button appliance0 = new Button(new Image(CFL), applianceArray[0][1], applianceArray[0][2], new Rectangle(50,50,300,50), "");
		appliance0.addActionListener(new ApplianceClick0());
		this.addComponent(appliance0);
		//1
		Button appliance1 = new Button(new Image(CFL), applianceArray[1][1], applianceArray[1][2], new Rectangle(50,50,300,50), "");
		appliance1.addActionListener(new ApplianceClick1());
		this.addComponent(appliance1);
		//2
		Button appliance2 = new Button(new Image(CFL), applianceArray[2][1], applianceArray[2][2], new Rectangle(50,50,300,50), "");
		appliance2.addActionListener(new ApplianceClick2());
		this.addComponent(appliance2);
		//3
		Button appliance3 = new Button(new Image(LED), applianceArray[3][1], applianceArray[3][2], new Rectangle(50,50,300,50), "");
		appliance3.addActionListener(new ApplianceClick3());
		this.addComponent(appliance3);
		//4
		Button appliance4 = new Button(new Image(RADIO), applianceArray[4][1], applianceArray[4][2], new Rectangle(50,50,300,50), "");
		appliance4.addActionListener(new ApplianceClick4());
		this.addComponent(appliance4);
		//trick0
		Button trick0 = new Button(new Image(TRICK0), applianceArray[5][1], applianceArray[5][2], new Rectangle(50,50,300,50), "");
		trick0.addActionListener(new ApplianceClickWrong0());
		this.addComponent(trick0);
		//trick1
		Button trick1 = new Button(new Image(TRICK1), applianceArray[6][1], applianceArray[6][2], new Rectangle(50,50,300,50), "");
		trick1.addActionListener(new ApplianceClickWrong1());
		this.addComponent(trick1);
		
		option0 = new Button(new Image(OPTION), 10, -400, new Rectangle(5,5,240,70), "Device's Positioning");
		option0.addActionListener(new Option0Click());
		option0.setFontColor(Color.black);
		this.addComponent(option0);
		
		option1 = new Button(new Image(OPTION), 270, -400, new Rectangle(5,5,240,70), "Critical Design Month");
		option1.addActionListener(new Option1Click());
		option1.setFontColor(Color.black);
		this.addComponent(option1);
		
		option2 = new Button(new Image(OPTION), 530, -400, new Rectangle(5,5,240,70), "Daily Device Usage");
		option2.addActionListener(new Option2Click());
		option2.setFontColor(Color.black);
		this.addComponent(option2);
		
		initializeText();
		initializeHintBox();
		
		////Testing Stuff can be deleted later////
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart2ScoreScreen.class));
		this.addComponent(Start);
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,Graphics graphics) throws SlickException 
	{
		super.render(container, game, graphics);
		
		//draw appliances
		for(int v=0;v<7;v++)
		{
			if(applianceArray[v][0] == 0)
			{
				graphics.drawImage(new Image(applianceImage[v][0]),applianceArray[v][1],applianceArray[v][2]);
			}
			else
			{
				graphics.drawImage(new Image(applianceImage[v][1]),applianceArray[v][1],applianceArray[v][2]);
				//graphics.drawString("14 W", applianceArray[v][1]+20, applianceArray[v][2]+20);
			}
		}
		if(step == 1)
		{
			
				
		}
	}
		
	public class ReadyButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(step == 0)
			{
				boolean allSelected = true;
				for(int v=0;v<5;v++)
				{
					if(applianceArray[v][0] == 0)
					{
						allSelected = false;
						break;
					}
				}
				if(allSelected == true)
				{
					step = 1;
					System.out.println("Part 2, Step 0 done");
					appliance0Watt.setText("14 W");
					appliance1Watt.setText("14 W");
					appliance2Watt.setText("14 W");
					appliance3Watt.setText("9 W");
					appliance4Watt.setText("30 W");
					
					appliance0Watt.setFillColor(Color.white);
					appliance1Watt.setFillColor(Color.white);
					appliance2Watt.setFillColor(Color.white);
					appliance3Watt.setFillColor(Color.white);
					appliance4Watt.setFillColor(Color.white);
					
					appliance0Watt.setBorderColor(Color.green);
					appliance1Watt.setBorderColor(Color.green);
					appliance2Watt.setBorderColor(Color.green);
					appliance3Watt.setBorderColor(Color.green);
					appliance4Watt.setBorderColor(Color.green);
					instructionsText.setText("What information is still needed to find the Daily Energy Consumption?");
					
					//option0.setCurrentImage(new Image(OPTION), true);
					ready.setY(-500);
					option0.setY(400);
					option1.setY(400);
					option2.setY(400);
				}
				else 
					System.out.println("Not Ready");
			}
		}
		
	}
	
	public class ContinueButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			Game.getCurrentGame().enterState(EAPart2ScoreScreen.class);
		}
	}
	
	public void initializeHintBox()
	{
		hintText = new TextArea(new Rectangle(575, 70, 200, 225), 0.95f, "Hint Box");
		hintText.setFontSize(16);
		hintText.setFontColor(Color.lightGray);
		hintText.setFillColor(Color.darkGray);
		this.addComponent(hintText);
	}
	
	private void initializeText()
	{
		//0
		appliance0Watt = new TextField(new Rectangle(applianceArray[0][1]-10, applianceArray[0][2]-30, 50, 75), 0.95f, "", TextDisplay.FormattingOption.FIT_TEXT);
		appliance0Watt.setFontColor(Color.black);
		this.addComponent(appliance0Watt);
		//1
		appliance1Watt = new TextField(new Rectangle(applianceArray[1][1]-10, applianceArray[1][2]-30, 50, 75), 0.95f, "", TextDisplay.FormattingOption.FIT_TEXT);
		appliance1Watt.setFontColor(Color.black);
		this.addComponent(appliance1Watt);
		//2
		appliance2Watt = new TextField(new Rectangle(applianceArray[2][1]-10, applianceArray[2][2]-30, 50, 75), 0.95f, "", TextDisplay.FormattingOption.FIT_TEXT);
		appliance2Watt.setFontColor(Color.black);
		this.addComponent(appliance2Watt);
		//3
		appliance3Watt = new TextField(new Rectangle(applianceArray[3][1]-10, applianceArray[3][2]-30, 50, 75), 0.95f, "", TextDisplay.FormattingOption.FIT_TEXT);
		appliance3Watt.setFontColor(Color.black);
		this.addComponent(appliance3Watt);
		//4
		appliance4Watt = new TextField(new Rectangle(applianceArray[4][1]-3, applianceArray[4][2]-30, 50, 75), 0.95f, "", TextDisplay.FormattingOption.FIT_TEXT);
		appliance4Watt.setFontColor(Color.black);
		this.addComponent(appliance4Watt);
		//Instructions
		instructionsText = new TextField(new Rectangle(0, 300, 800, 100), 0.95f, "Click the electrical appliances in the house that would affect the PV System", TextDisplay.FormattingOption.FIT_TEXT);
		instructionsText.center();
		instructionsText.setFontColor(Color.white);
		this.addComponent(instructionsText);
	}
	
	/*public class Appliance0MouseOver extends ButtonListener
	{
		@Override
		protected boolean verify(Input input)
		{
			return verify2(input, associatedComponent.getBounds());
		}
		protected boolean verify2(Input input, Rectangle bounds)
		{
			Point mouseLocation = new Point(input.getMouseX(),input.getMouseY());
			return bounds.contains(mouseLocation);
		}

		@Override
		protected void actionPerformed() 
		{
			int v = 0;
			if(step == 0)
			{
					applianceArray[v][0] = 3;
			}		
		}
		
	}*/
	
	public class ApplianceClick0 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 0)
			{
				if(applianceArray[0][0] == 0)
					applianceArray[0][0] = 1;
				else
					applianceArray[0][0] = 0;
				System.out.println("Clicked0");
			}
		}
		
	}
	public class ApplianceClick1 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 0)
			{
				if(applianceArray[1][0] == 0)
					applianceArray[1][0] = 1;
				else
					applianceArray[1][0] = 0;
				System.out.println("Clicked1");
			}
		}
		
	}
	public class ApplianceClick2 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 0)
			{
				if(applianceArray[2][0] == 0)
					applianceArray[2][0] = 1;
				else
					applianceArray[2][0] = 0;
				System.out.println("Clicked2");
			}
		}
		
	}
	public class ApplianceClick3 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 0)
			{
				if(applianceArray[3][0] == 0)
					applianceArray[3][0] = 1;
				else
					applianceArray[3][0] = 0;
				System.out.println("Clicked3");
			}
		}
		
	}
	public class ApplianceClick4 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 0)
			{
				if(applianceArray[4][0] == 0)
					applianceArray[4][0] = 1;
				else
					applianceArray[4][0] = 0;
				System.out.println("Clicked4");
			}
		}
		
	}
	public class ApplianceClickWrong0 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 0)
			{
				System.out.println("ClickedWrong");
			}
		}
		
	}
	public class ApplianceClickWrong1 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 0)
			{
				System.out.println("ClickedWrong");
			}
		}
		
	}
	
	
	public class Option0Click extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 1)
			{
				hintText.setText("Incorrect1: (Display a hint)");
			}
		}
		
	}
	public class Option1Click extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 1)
			{
				hintText.setText("Incorrect2: (Display a hint)");
			}
		}
		
	}
	public class Option2Click extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			if(step == 1)
			{
				hintText.setText("Correct: Finding the daily device usage is still needed to complete the Daily Energy Consumption Calculations.");
				continueButton.setY(500);
			}
		}
		
	}
	public static void reset()
	{
		for(int v=0;v<5;v++)
		{
			applianceArray[v][0] = 0; 
		}
		appliance0Watt.setText("");
		appliance1Watt.setText("");
		appliance2Watt.setText("");
		appliance3Watt.setText("");
		appliance4Watt.setText("");
		
		appliance0Watt.setFillColor(null);
		appliance1Watt.setFillColor(null);
		appliance2Watt.setFillColor(null);
		appliance3Watt.setFillColor(null);
		appliance4Watt.setFillColor(null);
		
		appliance0Watt.setBorderColor(null);
		appliance1Watt.setBorderColor(null);
		appliance2Watt.setBorderColor(null);
		appliance3Watt.setBorderColor(null);
		appliance4Watt.setBorderColor(null);
		
		step = 0;
		System.out.println("eaPart2 Reset");
	}
	
}

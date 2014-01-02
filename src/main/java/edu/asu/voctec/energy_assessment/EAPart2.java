package edu.asu.voctec.energy_assessment;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.energy_assessment.EAPart1.ReadyButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.UtilFunctions;

public class EAPart2 extends GUI
{
	private static final String READY = "resources/default/img/minigames/energyAssessment/readyButton.png";
	private static final String NOTREADY = "resources/default/img/minigames/energyAssessment/readyButtonGray.png";
	private static final String BACK = "resources/default/img/minigames/energyAssessment/backButton.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/part2/House-02.png";
	
	private static final String CFL = "resources/default/img/minigames/energyAssessment/part2/CFL.png";
	private static final String CFLCLICKED = "resources/default/img/minigames/energyAssessment/part2/CFLClicked.png";
	private static final String LED = "resources/default/img/minigames/energyAssessment/part2/LED.png";
	private static final String LEDCLICKED = "resources/default/img/minigames/energyAssessment/part2/LEDClicked.png";
	private static final String RADIO = "resources/default/img/minigames/energyAssessment/part2/Radio.png";
	private static final String RADIOCLICKED = "resources/default/img/minigames/energyAssessment/part2/RadioClicked.png";
	
	Button ready;
	
	private static TextArea appliance0Text;
	private static TextArea appliance1Text;
	private static TextArea appliance2Text;
	private static TextArea appliance3Text;
	private static TextArea appliance4Text;

	private int[][] applianceArray = 
		{
			{0,325,220},
			{0,550,230},
			{0,680,230},
			{0,200,200},
			{0,430,325},
		};
	private String[][] applianceImage = 
		{
			{CFL,CFLCLICKED},
			{CFL,CFLCLICKED},
			{CFL,CFLCLICKED},
			{LED,LEDCLICKED},
			{RADIO,RADIOCLICKED},
		};
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.backgroundImage = new Image(BACKGROUND);
		
		////Ready Button////
		ready = new Button(new Image(READY), 575, 500, new Rectangle(50,50,300,50), "");
		ready.addActionListener(new ReadyButtonListener());
		this.addComponent(ready);
		
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
		
		////Text////
		//0
		Rectangle appliance0TextLocation = new Rectangle(300, 300, 300, 50);
		TextField appliance0TextField = new TextField(new Rectangle(applianceArray[0][1]-25, applianceArray[0][2]+80, 300, 50), 0.95f, "14 W", TextDisplay.FormattingOption.FIT_TEXT);
		appliance0TextField.setFontColor(Color.black);
		this.addComponent(appliance0TextField);
		//1
		Rectangle appliance1TextLocation = new Rectangle(525, 310, 300, 50);
		TextField appliance1TextField = new TextField(new Rectangle(applianceArray[1][1]-25, applianceArray[1][2]+80, 300, 50), 0.95f, "14 W", TextDisplay.FormattingOption.FIT_TEXT);
		appliance1TextField.setFontColor(Color.black);
		this.addComponent(appliance1TextField);
		//2
		Rectangle appliance2TextLocation = new Rectangle(655, 310, 300, 50);
		TextField appliance2TextField = new TextField(new Rectangle(applianceArray[2][1]-25, applianceArray[2][2]+80, 300, 50), 0.95f, "14 W", TextDisplay.FormattingOption.FIT_TEXT);
		appliance2TextField.setFontColor(Color.black);
		this.addComponent(appliance2TextField);
		//3
		Rectangle appliance3TextLocation = new Rectangle(175, 280, 300, 50);
		TextField appliance3TextField = new TextField(new Rectangle(applianceArray[3][1]-25, applianceArray[3][2]+80, 300, 50), 0.95f, "9 W", TextDisplay.FormattingOption.FIT_TEXT);
		appliance3TextField.setFontColor(Color.black);
		this.addComponent(appliance3TextField);
		//4
		Rectangle appliance4TextLocation = new Rectangle(375, 400, 300, 50);
		TextField appliance4TextField = new TextField(new Rectangle(applianceArray[4][1]-100, applianceArray[4][2]+100, 300, 50), 0.95f, "30 W", TextDisplay.FormattingOption.FIT_TEXT);
		appliance4TextField.setFontColor(Color.black);
		this.addComponent(appliance4TextField);
		
		////Testing Stuff can be deleted later////
		System.out.println("EAPart1IntroScreen");
					
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart2ScoreScreen.class));
		this.addComponent(Start);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,Graphics graphics) throws SlickException 
	{
		super.render(container, game, graphics);
		for(int v=0;v<5;v++)
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
	}
		
	public class ReadyButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			boolean good = true;
			for(int v=0;v<5;v++)
			{
				if(applianceArray[v][0] == 0)
				{
					good = false;
					break;
				}
			}
			if(good == true)
			{
				System.out.println("Ready!");
				Game.getCurrentGame().enterState(EAPart2ScoreScreen.class);
			}
			else
				System.out.println("NotReady");
		}
		
	}
	
	public class ApplianceClick0 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			applianceArray[0][0] = 1;
			System.out.println("Clicked0");
			
		}
		
	}
	public class ApplianceClick1 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			applianceArray[1][0] = 1;
			System.out.println("Clicked1");
		}
		
	}
	public class ApplianceClick2 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			applianceArray[2][0] = 1;
			System.out.println("Clicked2");
		}
		
	}
	public class ApplianceClick3 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			applianceArray[3][0] = 1;
			System.out.println("Clicked3");
		}
		
	}
	public class ApplianceClick4 extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			applianceArray[4][0] = 1;
			System.out.println("Clicked4");
		}
		
	}
	public class ApplianceClickWrong extends ButtonListener
	{
		@Override
		protected void actionPerformed() 
		{
			System.out.println("ClickedWrong");
		}
		
	}
	
}

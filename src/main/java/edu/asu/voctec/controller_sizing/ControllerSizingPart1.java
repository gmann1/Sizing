package edu.asu.voctec.controller_sizing;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.GUI.TextDisplay;

public class ControllerSizingPart1 extends GUI {

	private double counter = -15;
	private double starCounter;
	private int houseTransition = 0;
	private boolean day = true;
	private boolean stars_on = true;
	private boolean houseHalf = false;
	private boolean houseFull = false;
	private double batteryPercent = 80;

	private boolean panelOn = false;
	private boolean houseOn = false;
	private boolean wait = false;
	private boolean houseNeed = false;
	private boolean batteryWithinRange = true;
	private boolean charging = false;
	private boolean discharging = false;
	

	private static final int BATTERY_MIN = 15;
	private static final int BATTERY_MAX = 90;
	private static final int RECHARGE_LEVEL  = 39;

	public static final float LARGE_FONT_SIZE = 18f;
	public static final float MEDIUM_FONT_SIZE = 11f;

	public static final String BACKGROUND_DAY = "resources/default/img/minigames/ControllerSizing/backgroundday.png";
	public static final String BACKGROUND_NIGHT = "resources/default/img/minigames/ControllerSizing/backgroundnight.png";
	public static final String GROUND = "resources/default/img/minigames/ControllerSizing/hills.png";
	public static final String HOUSE_OFF = "resources/default/img/minigames/ControllerSizing/house1.png";
	public static final String HOUSE_HALF = "resources/default/img/minigames/ControllerSizing/house2.png";
	public static final String HOUSE_FULL = "resources/default/img/minigames/ControllerSizing/house3.png";
	public static final String SUN = "resources/default/img/minigames/ControllerSizing/sun.png";
	public static final String MOON = "resources/default/img/minigames/ControllerSizing/moon.png";
	public static final String TRANS_0 = "resources/default/img/minigames/ControllerSizing/backgroundnight0.png";
	public static final String TRANS_1 = "resources/default/img/minigames/ControllerSizing/backgroundnight1.png";
	public static final String TRANS_2 = "resources/default/img/minigames/ControllerSizing/backgroundnight2.png";
	public static final String TRANS_4 = "resources/default/img/minigames/ControllerSizing/backgroundnight3.png";
	public static final String TRANS_5 = "resources/default/img/minigames/ControllerSizing/backgroundnight4.png";
	public static final String TRANS_3 = "resources/default/img/minigames/ControllerSizing/backgroundnight5.png";

	public static final String LAYOUT_PANEL_OFF = "resources/default/img/minigames/ControllerSizing/layoutoff.png";
	public static final String LAYOUT_PANEL_OFF1 = "resources/default/img/minigames/ControllerSizing/layoutoff1.png";
	public static final String LAYOUT_PANEL_OFF2 = "resources/default/img/minigames/ControllerSizing/layoutoff2.png";
	public static final String LAYOUT_PANEL_ON = "resources/default/img/minigames/ControllerSizing/layoutPanel.png";
	public static final String LAYOUT_PANEL_ON1 = "resources/default/img/minigames/ControllerSizing/layoutPanel1.png";
	public static final String LAYOUT_PANEL_ON2 = "resources/default/img/minigames/ControllerSizing/layoutPanel2.png";

	public static final String BATTERY_ONE = "resources/default/img/minigames/ControllerSizing/battery1.png";
	public static final String BATTERY_TWO = "resources/default/img/minigames/ControllerSizing/battery2.png";
	public static final String BATTERY_THREE = "resources/default/img/minigames/ControllerSizing/battery3.png";
	public static final String BATTERY_FOUR = "resources/default/img/minigames/ControllerSizing/battery4.png";
	public static final String BATTERY_FIVE = "resources/default/img/minigames/ControllerSizing/battery5.png";
	public static final String TRANS_6 = "resources/default/img/minigames/ControllerSizing/backgroundday6.png";

	public static final String PANEL = "resources/default/img/minigames/ControllerSizing/solarpanel.png";
	public static final String STAR = "resources/default/img/minigames/ControllerSizing/star.png";
	Image sunImage;
	Image starImage;
	TextField batteryLife;
	TextAreaX batteryPrompt;
	private String s;
	BasicComponent house;
	BasicComponent sun;
	BasicComponent battery;
	BasicComponent layout;
	ArrayList<BasicComponent> stars = new ArrayList<>();
	ArrayList<Integer> houseIncrements = new ArrayList<>();

	private int currentHouseIndex;

	private int currentBattery = 0;
	private int previousBattery = 0;
	int starsx[] = new int[15];
	int starsy[] = new int[15];
	int starsOffSet[] = new int[15];
	public static final String batteryStrings[] = new String[5];
	
	Random generator = new Random();

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(TRANS_3);

		batteryStrings[0] = BATTERY_ONE;
		batteryStrings[1] = BATTERY_TWO;
		batteryStrings[2] = BATTERY_THREE;
		batteryStrings[3] = BATTERY_FOUR;
		batteryStrings[4] = BATTERY_FIVE;
		
		houseIncrements.add(42);
		houseIncrements.add(42);
		houseIncrements.add(42);
		houseIncrements.add(81);
		houseIncrements.add(81);
		houseIncrements.add(123);
		Collections.shuffle(houseIncrements);
		// layout
		layout = new BasicComponent(new Image(LAYOUT_PANEL_OFF), 75, 390);

		// ground
		BasicComponent ground = new BasicComponent(new Image(GROUND), 0, 0);

		// house

		house = new BasicComponent(new Image(HOUSE_OFF), 480, 300);

		// sun
		sunImage = new Image(SUN);
		sunImage.setCenterOfRotation(35, 435);
		sun = new BasicComponent(sunImage, 365, 25);

		// solarpanel
		BasicComponent pole = new BasicComponent(new Image(PANEL), 475, 325);

		// battery
		battery = new BasicComponent(new Image(BATTERY_ONE), 330, 510);
		// battery life
		s = String.format("%d", (int) batteryPercent);
		Rectangle textLocation = new Rectangle(360, 540, 300, 50);
		batteryLife = new TextField(textLocation, 0.95f, s + "%",
				TextDisplay.FormattingOption.CLIP_TEXT);
		batteryLife.setFontSize(LARGE_FONT_SIZE);
		batteryLife.setFontColor(Color.black);
		
		//battery prompts
		textLocation = new Rectangle(80, 490, 145, 80);
		batteryPrompt = new TextAreaX(textLocation, 0.95f, "");
		batteryPrompt.setFontSize(MEDIUM_FONT_SIZE);
		batteryPrompt.setFontColor(Color.black);
		batteryPrompt.setText("Battery low. Turning off power to house to prevent battery damage. Recharging to 40%");
		
		// stars

		starImage = new Image(STAR);
		starsx[0] = 175;
		starsy[0] = 150;
		starsx[1] = 70 + 4 * 7;
		starsy[1] = 60 + 4 * 6;
		starsx[2] = 175 + 8 * 7;
		starsy[2] = 150 + 8 * 6;
		starsx[3] = 175 + 26 * 7;
		starsy[3] = 150 + 26 * 6;
		starsx[4] = 175 + 18 * 7;
		starsy[4] = 150 + 18 * 6;

		starsx[5] = 175 + 6 * 7;
		starsy[5] = 150 + 6 * 6;
		starsx[6] = 70 + 8 * 7;
		starsy[6] = 60 + 8 * 6;
		starsx[7] = 175 + 11 * 7;
		starsy[7] = 150 + 11 * 6;
		starsx[8] = 175 + 32 * 7;
		starsy[8] = 150 + 32 * 6;
		starsx[9] = 175 + 23 * 7;
		starsy[9] = 150 + 23 * 6;

		starsx[10] = 175 - 3 * 7;
		starsy[10] = 150 - 3 * 6;
		starsx[11] = 175 + 29 * 7;
		starsy[11] = 150 + 29 * 6;
		starsx[12] = 175 + 15 * 7;
		starsy[12] = 150 + 15 * 6;
		starsx[13] = 175 + 20 * 7;
		starsy[13] = 150 + 20 * 6;
		starsx[14] = 175 + 35 * 7;
		starsy[14] = 150 + 35 * 6;

		for (int i = 0; i < starsx.length; i++) {
			stars.add(new BasicComponent(starImage, 800, 600));
			starsOffSet[i] = generator.nextInt(361);

		}
		for (BasicComponent star : stars) {

			addComponent(star);
		}
		addComponent(sun);
		addComponent(ground);
		addComponent(pole);
		addComponent(house);

		addComponent(layout);
		addComponent(battery);
		addComponent(batteryLife);
		addComponent(batteryPrompt);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		super.update(container, game, delta);
		
		previousBattery = currentBattery;
		if (batteryPercent <= ((BATTERY_MAX - BATTERY_MIN)/5) + BATTERY_MIN){
			currentBattery = 1;
		}
		else if (batteryPercent <= (2*(BATTERY_MAX - BATTERY_MIN)/5) + BATTERY_MIN){
			currentBattery = 2;
		}
		else if (batteryPercent <= (3*(BATTERY_MAX - BATTERY_MIN)/5) + BATTERY_MIN){
			currentBattery = 3;
		}
		else if (batteryPercent <= (4*(BATTERY_MAX - BATTERY_MIN)/5) + BATTERY_MIN){
			currentBattery = 4;
		}
		else{
			currentBattery = 5;
		}
		if (previousBattery != currentBattery){
			battery.setCurrentImage(new Image(batteryStrings[currentBattery-1]), true);
		}
		if (!day) {
			panelOn = false;

		} 
		if (houseHalf || houseFull) {
			if (batteryPercent > BATTERY_MIN && !wait){
			houseOn = true;
			houseNeed = false;
			}
			else{
				houseNeed = true;
				wait = true;
				
				houseOn = false;
			}
		}
		else{
			houseOn = false;
			houseNeed = false;
		}
		if (panelOn && batteryPercent < BATTERY_MAX) {
			
			batteryPercent += .2;
		
			charging = true;
		} else {
			charging = false;
		}
		if(batteryPercent >= RECHARGE_LEVEL)
		{
			
			wait = false;
		}
		if (houseOn && !wait) {
			
			discharging = true;
			if (houseHalf) {
				batteryPercent -= .1;
			
			} else {
				batteryPercent -= .2;
				
			}
		} else {
			discharging = false;
		}
		
		if (batteryPercent > BATTERY_MIN && batteryPercent < BATTERY_MAX){
			batteryWithinRange = true;
			if(batteryPercent >= RECHARGE_LEVEL)
			{
				batteryPrompt.setText("");
				
			}
			
			
		}
		if(batteryPercent >BATTERY_MAX){
			batteryPercent = BATTERY_MAX;
		}
		if((panelOn || houseOn) && batteryWithinRange){
			s = String.format("%d", (int) Math.ceil(batteryPercent));
			batteryLife.setText(s + "%");
		}
		if (houseNeed) {
			if (batteryWithinRange) {
				
				batteryPrompt.setText("Battery too low. Turning off power to house to prevent battery damage. Recharging to 40%");
			}
			batteryWithinRange = false;
		}
		if (panelOn && !charging) {
			if (batteryWithinRange) {
				batteryPrompt.setText("Battery at capacity. Cannot overcharge.");
				batteryWithinRange = false;
			}
		}
		if (!panelOn && !houseOn && !houseNeed) {
			layout.setCurrentImage(new Image(LAYOUT_PANEL_OFF), true);
		}
		if (!panelOn && houseOn && !houseNeed) {
			layout.setCurrentImage(new Image(LAYOUT_PANEL_OFF1), true);
		}
		if (!panelOn && !houseOn && houseNeed) {
			layout.setCurrentImage(new Image(LAYOUT_PANEL_OFF2), true);
		}
		if (panelOn && !houseOn && !houseNeed) {
			layout.setCurrentImage(new Image(LAYOUT_PANEL_ON), true);
		}
		if (panelOn && houseOn && !houseNeed) {
			layout.setCurrentImage(new Image(LAYOUT_PANEL_ON1), true);
		}
		if (panelOn && !houseOn && houseNeed) {
			layout.setCurrentImage(new Image(LAYOUT_PANEL_ON2), true);
		}
		if ((houseTransition) % (houseIncrements.get(currentHouseIndex) * 10) == 0) {
			houseTransition = 0;
			if (generator.nextInt(3) == 0) {
				houseHalf = false;
				houseFull = false;
				house.setCurrentImage(new Image(HOUSE_OFF), true);

			} else if (generator.nextInt(3) == 1) {
				houseHalf = true;
				houseFull = false;
				
				house.setCurrentImage(new Image(HOUSE_HALF), true);
				
			} else {
				houseHalf = false;
				houseFull = true;
				
				house.setCurrentImage(new Image(HOUSE_FULL), true);
				
			}
			++currentHouseIndex;
			if (currentHouseIndex == houseIncrements.size()) {
				currentHouseIndex = 0;
				Collections.shuffle(houseIncrements);
			}
		}

		houseTransition = houseTransition + 3;
		sun.setX((int) (350 * Math.sin(Math.toRadians(counter - 90))) + 365);
		sun.setY((int) (300 * Math.cos(Math.toRadians(counter + 90))) + 300);
		if (stars_on) {
			for (int i = 0; i < starsx.length; i++) {

				stars.get(i).setX(
						(int) (starsx[i] * Math.sin(Math.toRadians(starCounter
								- starsOffSet[i]))) + 394);
				stars.get(i).setY(
						(int) (starsy[i] * Math.cos(Math.toRadians(starCounter
								+ (180 - starsOffSet[i])))) + 340);
			}
		}
		counter = counter + .3;
		starCounter = starCounter + .3;
		if ((int) counter == 182) {
			if (day) {
				this.backgroundImage = new Image(TRANS_6);
			} else {

			}
		}
		if ((int) counter == 186) {
			if (day) {
				this.backgroundImage = new Image(TRANS_5);
			} else {

			}
		}

		if ((int) counter == 190) {
			if (day) {
				this.backgroundImage = new Image(TRANS_4);
			} else {
				this.backgroundImage = new Image(TRANS_0);
			}
		}
		if ((int) counter == -2) {
			if (day) {

				this.backgroundImage = new Image(TRANS_3);
			} else {
				stars_on = true;
				starCounter = counter;
			}

		}
		if ((int) counter == 2) {
			if (day) {
				panelOn= true;
				stars_on = false;
				for (int i = 0; i < starsx.length; i++) {

					stars.get(i).setX(800);
					stars.get(i).setY(600);
				}
				this.backgroundImage = new Image(TRANS_5);
			} else {
				this.backgroundImage = new Image(TRANS_2);
			}
		}
		if ((int) counter == 6) {

			if (day) {

				this.backgroundImage = new Image(TRANS_6);
			} else {

				this.backgroundImage = new Image(TRANS_1);
			}
		}
		if ((int) counter == 10) {
			if (day) {
				this.backgroundImage = new Image(BACKGROUND_DAY);
			} else {
				this.backgroundImage = new Image(BACKGROUND_NIGHT);
			}
		}

		if (counter >= 190) {
			if (day) {
				sunImage = new Image(MOON);
				sun.setCurrentImage(sunImage, true);

				day = false;
			} else {
				sunImage = new Image(SUN);
				sun.setCurrentImage(sunImage, true);

				day = true;
			}
			counter = -15;
		}

	}

}

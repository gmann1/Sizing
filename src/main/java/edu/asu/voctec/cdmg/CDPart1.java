package edu.asu.voctec.cdmg;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.utilities.Position;
import edu.asu.voctec.utilities.UtilFunctions;
import edu.asu.voctec.utilities.gameTemplate;

/**
 * 
 * @author Gabriel Mann
 * 
 */

public class CDPart1 extends gameTemplate {

	private static int index = 0;
	public static int hints = 0;
	private int hintCount = 0;
	private boolean correctAnswer = false;

	private static final Color FONT_COLOR = Color.darkGray;
	private static final Color FONT_COLOR1 = Color.white;

	public static final String APRIL = "resources/default/img/minigames/criticalDesign/April.png";
	public static final String FEBRUARY = "resources/default/img/minigames/criticalDesign/February.png";
	public static final String DECEMBER = "resources/default/img/minigames/criticalDesign/December.png";
	public static final String OCTOBER = "resources/default/img/minigames/criticalDesign/October.png";
	public static final String SEPTEMBER = "resources/default/img/minigames/criticalDesign/September.png";
	public static final String JUNE = "resources/default/img/minigames/criticalDesign/June.png";
	public static final String BACKGROUND = "resources/default/img/minigames/criticalDesign/space.jpg";
	public static final String BOX = "resources/default/img/selector/display/EmptyBox.png";
	public static final String MOUSE_OVER_BOX = "resources/default/img/minigames/criticalDesign/MouseOverBox.png";
	public static final String CORRECT_BOX = "resources/default/img/minigames/criticalDesign/CorrectBox.png";
	public static final String SELECTED_BOX = "resources/default/img/minigames/criticalDesign/SelectedBox.png";
	public static final String INCORRECT_BOX = "resources/default/img/minigames/criticalDesign/IncorrectBox.png";

	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;

	public static final int READY_BUTTON_X = 600;

	private static boolean ap = false;
	private static boolean fe = false;
	private static boolean oc = false;
	private static boolean se = false;
	private static boolean ju = false;

	static ArrayList<String> Earths = new ArrayList<>();
	static ArrayList<String> April = new ArrayList<>();
	static ArrayList<String> February = new ArrayList<>();
	static ArrayList<String> December = new ArrayList<>();
	static ArrayList<String> October = new ArrayList<>();
	static ArrayList<String> September = new ArrayList<>();
	static ArrayList<String> June = new ArrayList<>();
	static ArrayList<ArrayList<String>> months = new ArrayList<>();

	static TextField main1;
	static TextField main2;
	static TextField main3;
	static TextField right1;
	static TextField right2;
	static TextField right3;
	static TextField left1;
	static TextField left2;
	static TextField left3;

	BasicComponent box1;
	BasicComponent box2;
	BasicComponent box3;
	BasicComponent box4;

	static BasicComponent earths;
	ArrayList<String> monthlyHints = new ArrayList<>();
	ArrayList<String> genericHints = new ArrayList<>();
	private boolean box1Selected = false;
	private boolean box1Hover = false;
	private boolean box2Selected = false;
	private boolean box2Hover = false;
	private boolean box3Selected = false;
	private boolean box3Hover = false;
	private boolean box4Selected = false;
	private boolean box4Hover = false;

	public class CDReadyListener extends ButtonListener {

		@Override
		protected void actionPerformed() {

			try {
				userAnswer(index);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public class CDContinueListener extends ButtonListener {

		@Override
		protected void actionPerformed() {
			if (correctAnswer) {
				Game.getCurrentGame().enterState(CDPart2.class);
			}

		}

	}

	public class CDHintListener extends ButtonListener {

		@Override
		protected void actionPerformed() {
			displayHint(hintCount);

		}

	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		int spacing = 0;
		this.backgroundImage = new Image(BACKGROUND);
		Rectangle boxBounds = new Rectangle();
		box1 = new BasicComponent(new Image(BOX), 50, 500);
		box1.rescale(1.2f);
		box2 = new BasicComponent(new Image(BOX), 50, 500);
		box2.rescale(1.2f);
		box3 = new BasicComponent(new Image(BOX), 50, 500);
		box3.rescale(1.2f);
		box4 = new BasicComponent(new Image(BOX), 50, 500);
		box4.rescale(1.2f);

		boxBounds = box1.getBounds();
		UtilFunctions.centerRectangle(control.getBounds(), boxBounds);
		box1.setBounds(boxBounds);
		spacing = (control.getBounds().width - 4 * box1.getBounds().width) / 7;
		System.out.println("spacing = " + spacing);

		box2.setX(box1.getX());
		box2.setY(box1.getY());
		box3.setX(box1.getX());
		box3.setY(box1.getY());
		box4.setX(box1.getX());
		box4.setY(box1.getY());

		box1.setX(2 * spacing);
		box2.setX(box1.getBounds().width + box1.getX() + spacing);
		box3.setX(box2.getBounds().width + box2.getX() + spacing);
		box4.setX(box3.getBounds().width + box3.getX() + spacing);

		instructionBox.setText("Select the Critical Design Month.");
		topText.setText("Location: Niger, Niamey" + "\n"
				+ "Latitude: 13° 31 N, Longitude: 2° 6 E");
		// add initial things to the arraylists
		Earths.add(APRIL);
		Earths.add(FEBRUARY);
		Earths.add(DECEMBER);
		Earths.add(OCTOBER);
		Earths.add(SEPTEMBER);
		Earths.add(JUNE);

		months.add(April);
		months.add(February);
		months.add(December);
		months.add(October);
		months.add(September);
		months.add(June);

		April.add("April");
		April.add("\tSpring");
		April.add("\t7.02 PSH/Day");

		February.add("February");
		February.add("\tWinter");
		February.add("\t6.38 PSH/Day");

		December.add("December");
		December.add("Winter");
		December.add("5.24 PSH/Day");

		October.add("October");
		October.add("\tAutumn");
		October.add("\t6.05 PSH/Day");

		September.add("September");
		September.add("\tAutumn");
		September.add("\t5.96 PSH/Day");

		June.add("June");
		June.add("\tSummer");
		June.add("\t6.64 PSH/Day");

		monthlyHints
				.add("Sorry, April is not the critical design month. Try again.");
		monthlyHints
				.add("Sorry, February is not the critical design month. Try again.");
		monthlyHints
				.add("Good Job! The critical design month is the month with the highest ratio of load to solar insolation.");
		monthlyHints
				.add("Sorry, October is not the critical design month. Try again.");
		monthlyHints
				.add("Sorry, September is not the critical design month. Try again.");
		monthlyHints
				.add("Sorry, June is not the critical design month. Try again.");

		genericHints
				.add("The Earth has a tilt of 23.5 degrees. Because of this, different parts of the Earth are tilted closer to the Sun during different times of the year. This is why we have seasons.");
		genericHints
				.add("PSH or peak sun-hours is a measure of the amount of solar insolation being received. ");
		genericHints
				.add("The critical design month is the month with the lowest solar insolation.");

		// earth
		Image Earth = new Image(Earths.get(index));
		earths = new BasicComponent(Earth, sidePanel.getX() / 2
				- Earth.getWidth() / 2, 57);

		// whole selector

		Image readyButtonImage = new Image(ImagePaths.READY_BUTTON);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		int readyButtonOffSet = container.getWidth() - READY_BUTTON_X
				- textBounds.width;

		// Main Selector
		Rectangle textLocation = new Rectangle(0, 0, 50, 50);

		// UtilFunctions.centerRectangle(sel.getMainBounds(), textLocation);
		textLocation.x = textLocation.x + 10;
		main2 = new TextField(textLocation, 0.95f, "",
				TextDisplay.FormattingOption.CLIP_TEXT);
		main2.setFontSize(LARGE_FONT_SIZE);
		main2.setFontColor(FONT_COLOR);
		textLocation.y = textLocation.y - textLocation.height;
		main1 = new TextField(textLocation, 0.95f, "",
				TextDisplay.FormattingOption.CLIP_TEXT);
		textLocation.y = textLocation.y + textLocation.height;
		textLocation.y = textLocation.y + textLocation.height;
		main1.setFontSize(LARGE_FONT_SIZE);
		main1.setFontColor(FONT_COLOR);
		main3 = new TextField(textLocation, 0.95f, "",
				TextDisplay.FormattingOption.CLIP_TEXT);
		main3.setFontSize(MEDIUM_FONT_SIZE);
		main3.setFontColor(FONT_COLOR);
		main1.setText(months.get(index).get(0));
		main2.setText(months.get(index).get(1));
		main3.setText(months.get(index).get(2));

		// Right
		textLocation = new Rectangle(0, 0, 50, 50);

		// UtilFunctions.centerRectangle(sel.getRightBounds(), textLocation);
		textLocation.x = textLocation.x + 5;
		right2 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		right2.setFontSize(MEDIUM_FONT_SIZE);
		right2.setFontColor(FONT_COLOR);
		textLocation.y = textLocation.y - textLocation.height;
		right1 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		textLocation.y = textLocation.y + textLocation.height;
		textLocation.y = textLocation.y + textLocation.height;
		right1.setFontSize(MEDIUM_FONT_SIZE);
		right1.setFontColor(FONT_COLOR);
		right3 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		right3.setFontSize(SMALL_FONT_SIZE);
		right3.setFontColor(FONT_COLOR);
		right1.setText(months.get(index + 1).get(0));
		right2.setText(months.get(index + 1).get(1));
		right3.setText(months.get(index + 1).get(2));

		// Left
		textLocation = new Rectangle(0, 0, 50, 50);

		// UtilFunctions.centerRectangle(sel.getLeftBounds(), textLocation);
		textLocation.x = textLocation.x + 5;
		left2 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		left2.setFontSize(MEDIUM_FONT_SIZE);
		left2.setFontColor(FONT_COLOR);
		textLocation.y = textLocation.y - textLocation.height;
		left1 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		textLocation.y = textLocation.y + textLocation.height;
		textLocation.y = textLocation.y + textLocation.height;
		left1.setFontSize(MEDIUM_FONT_SIZE);
		left1.setFontColor(FONT_COLOR);
		left3 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		left3.setFontSize(SMALL_FONT_SIZE);
		left3.setFontColor(FONT_COLOR);
		left1.setText(months.get(5).get(0));
		left2.setText(months.get(5).get(1));
		left3.setText(months.get(5).get(2));

		this.addComponent(box1);
		this.addComponent(box2);
		this.addComponent(box3);
		this.addComponent(box4);
		this.addComponent(main1);
		this.addComponent(main2);
		this.addComponent(main3);
		this.addComponent(right1);
		this.addComponent(right2);
		this.addComponent(right3);
		this.addComponent(left1);
		this.addComponent(left2);
		this.addComponent(left3);
		this.addComponent(earths);
		this.addComponent(topText);

		hintButton.addActionListener(new CDHintListener());
		readyButton.addActionListener(new CDReadyListener());
		continueButton.addActionListener(new CDContinueListener());
		backButton.addActionListener(new TransitionButtonListener(
				CDIntroScreen.class));

		System.out
				.println("Listeners: " + Arrays.toString(this.getListeners()));
	}

	private void userAnswer(int i) throws SlickException {
		if (i == 0) {
			hintBox.setText(monthlyHints.get(i));
			if (!ap && !correctAnswer) {
				++hints;
				ap = true;
			}
			System.out.println("April Hint shown, total hints: " + hints);
		}
		if (i == 1) {
			hintBox.setText(monthlyHints.get(i));
			if (!fe && !correctAnswer) {
				++hints;
				fe = true;
			}
			System.out.println("February Hint shown, total hints: " + hints);
		}
		if (i == 2) {
			hintBox.setText(monthlyHints.get(i));
			continueButtonOn();
			correctAnswer = true;
			System.out.println("Correct answer gotten after " + hints
					+ " hints.");
		}
		if (i == 3) {
			hintBox.setText(monthlyHints.get(i));
			if (!oc && !correctAnswer) {
				++hints;
				oc = true;
			}
			System.out.println("October Hint shown, total hints: " + hints);
		}
		if (i == 4) {
			hintBox.setText(monthlyHints.get(i));
			if (!se && !correctAnswer) {
				++hints;
				se = true;
			}
			System.out.println("September Hint shown, total hints: " + hints);
		}
		if (i == 5) {
			hintBox.setText(monthlyHints.get(i));
			if (!ju && !correctAnswer) {
				++hints;
				ju = true;
			}
			System.out.println("June Hint shown, total hints: " + hints);
		}

	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);
		int MouseX = container.getInput().getMouseX();
		int MouseY = container.getInput().getMouseY();
		if ((MouseX >= box1.getX() && MouseX <= (box1.getX() + box1.getBounds().width))																// range
				&& (MouseY >= box1.getY() && MouseY <= (box1.getY() + box1
						.getBounds().height))) {
				if(!box1Selected){
					if(!box1Hover){
						box1.setCurrentImage(new Image(MOUSE_OVER_BOX), true);
						box1Hover = true;
					}
					if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
						box1.setCurrentImage(new Image(SELECTED_BOX), true);
						box2.setCurrentImage(new Image(BOX), true);
						box3.setCurrentImage(new Image(BOX), true);
						box4.setCurrentImage(new Image(BOX), true);
						box1Selected = true;
						box2Selected = false;
						box3Selected = false;
						box4Selected = false;
						
						box1Hover = false;
					}
				}
		}
		else{
			if (box1Hover){
				box1.setCurrentImage(new Image(BOX), true);
				box1Hover = false;
			}
		}
		if ((MouseX >= box2.getX() && MouseX <= (box2.getX() + box2.getBounds().width))																// range
				&& (MouseY >= box2.getY() && MouseY <= (box2.getY() + box2
						.getBounds().height))) {
				if(!box2Selected){
					if(!box2Hover){
						box2.setCurrentImage(new Image(MOUSE_OVER_BOX), true);
						box2Hover = true;
					}
					if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
						box1.setCurrentImage(new Image(BOX), true);
						box2.setCurrentImage(new Image(SELECTED_BOX), true);
						box3.setCurrentImage(new Image(BOX), true);
						box4.setCurrentImage(new Image(BOX), true);
						box2Selected = true;
						box1Selected = false;
						box3Selected = false;
						box4Selected = false;
						box2Hover = false;
					}
				}
		}
		else{
			if (box2Hover){
				box2.setCurrentImage(new Image(BOX), true);
				box2Hover = false;
			}
		}
		if ((MouseX >= box3.getX() && MouseX <= (box3.getX() + box3.getBounds().width))																// range
				&& (MouseY >= box3.getY() && MouseY <= (box3.getY() + box3
						.getBounds().height))) {
				if(!box3Selected){
					if(!box3Hover){
						box3.setCurrentImage(new Image(MOUSE_OVER_BOX), true);
						box3Hover = true;
					}
					if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
						box1.setCurrentImage(new Image(BOX), true);
						box2.setCurrentImage(new Image(BOX), true);
						box3.setCurrentImage(new Image(SELECTED_BOX), true);
						box4.setCurrentImage(new Image(BOX), true);
						box3Selected = true;
						box1Selected = false;
						box2Selected = false;
						box4Selected = false;
						box3Hover = false;
					}
				}
		}
		else{
			if (box3Hover){
				box3.setCurrentImage(new Image(BOX), true);
				box3Hover = false;
			}
		}
		if ((MouseX >= box4.getX() && MouseX <= (box4.getX() + box4.getBounds().width))																// range
				&& (MouseY >= box4.getY() && MouseY <= (box4.getY() + box4
						.getBounds().height))) {
				if(!box4Selected){
					if(!box4Hover){
						box4.setCurrentImage(new Image(MOUSE_OVER_BOX), true);
						box4Hover = true;
					}
					if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
						box1.setCurrentImage(new Image(BOX), true);
						box2.setCurrentImage(new Image(BOX), true);
						box3.setCurrentImage(new Image(BOX), true);
						box4.setCurrentImage(new Image(SELECTED_BOX), true);
						box4Selected = true;
						box2Selected = false;
						box3Selected = false;
						box1Selected = false;
						box4Hover = false;
					}
				}
		}
		else{
			if (box4Hover){
				box4.setCurrentImage(new Image(BOX), true);
				box4Hover = false;
			}
		}
	}

	private void displayHint(int hCount) {
		if (hCount == 0) {
			hintBox.setText(genericHints.get(0));
			++hintCount;
			if (!correctAnswer) {
				++hints;
			}
			System.out.println("Generic Hint1 shown, total hints: " + hints);
		}
		if (hCount == 1) {
			hintBox.setText(genericHints.get(1));
			++hintCount;
			if (!correctAnswer) {
				++hints;
			}
			System.out.println("Generic Hint2 shown, total hints: " + hints);
		}
		if (hCount == 2) {
			hintBox.setText(genericHints.get(2));
			++hintCount;
			if (!correctAnswer) {
				++hints;
			}
			System.out.println("Generic Hint2 shown, total hints: " + hints);
		}
		if (hCount > 2) {

			if (hCount == 3) {
				hintBox.setText(genericHints.get(0));
			} else if (hCount == 4) {
				hintBox.setText(genericHints.get(1));
			} else {
				hintBox.setText(genericHints.get(2));
			}
			++hintCount;
			if (hintCount == 6) {
				hintCount = 3;
			}
			System.out
					.println("User requested more hints, none to give. total hints: "
							+ hints);

		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException {
		super.render(container, game, graphics);

	}

	public Dimension getDesignResolution() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void changeImage(boolean right) throws SlickException {
		if (right) {
			index++;
			if (index == Earths.size()) {
				index = 0;

			}

		} else {
			index--;
			if (index == -1) {
				index = Earths.size() - 1;
			}
		}
		if (index == 5) {
			changeRight(0);
		} else {
			changeRight(index + 1);
		}
		if (index == 0) {
			changeLeft(5);
		} else {
			changeLeft(index - 1);
		}
		changeMain(index);
		earths.setCurrentImage(new Image(Earths.get(index)), true);

	}

	public static void changeMain(int ind) {
		main1.setText(months.get(ind).get(0));
		main2.setText(months.get(ind).get(1));
		main3.setText(months.get(ind).get(2));

	}

	public static void changeRight(int ind) {
		right1.setText(months.get(ind).get(0));
		right2.setText(months.get(ind).get(1));
		right3.setText(months.get(ind).get(2));

	}

	public static void changeLeft(int ind) {
		left1.setText(months.get(ind).get(0));
		left2.setText(months.get(ind).get(1));
		left3.setText(months.get(ind).get(2));

	}

}

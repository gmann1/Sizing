package edu.asu.voctec.cdmg;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.Position;
import edu.asu.voctec.utilities.UtilFunctions;

/**
 * 
 * @author Gabriel Mann
 * 
 */

public class CDPart1 extends GUI {
	

	private static int index = 0;
	public static int hints = 0;
	private int hintCount = 0;
	private boolean correctAnswer = false;

	private static final Color FONT_COLOR = Color.darkGray;
	private static final Color FONT_COLOR1 = Color.white;

	public static final String APRIL = "resources/default/img/minigames/criticalDesign/april.png";
	public static final String FEBRUARY = "resources/default/img/minigames/criticalDesign/february.png";
	public static final String DECEMBER = "resources/default/img/minigames/criticalDesign/december.png";
	public static final String OCTOBER = "resources/default/img/minigames/criticalDesign/october.png";
	public static final String SEPTEMBER = "resources/default/img/minigames/criticalDesign/september.png";
	public static final String JUNE = "resources/default/img/minigames/criticalDesign/june.png";
	public static final String BACKGROUND = "resources/default/img/minigames/criticalDesign/space.jpg";

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

	static CDSelector<SelectorIcon> sel;
	static TextField main1;
	static TextField main2;
	static TextField main3;
	static TextField right1;
	static TextField right2;
	static TextField right3;
	static TextField left1;
	static TextField left2;
	static TextField left3;

	static BasicComponent earths;
	ArrayList<String> monthlyHints = new ArrayList<>();
	ArrayList<String> genericHints = new ArrayList<>();

	public TextArea theHints;

	private TextField instructionsLabel;

	public class CDReadyListener extends ButtonListener {

		@Override
		protected void actionPerformed() {
			try {
				if (correctAnswer) {
					Game.getCurrentGame().enterState(CDPart2.class);
				} else {
					userAnswer(index);
				}

			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
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
		
		//earth
		Image Earth = new Image(Earths.get(index));
		earths = new BasicComponent(Earth, 30, 57);
		//whole selector
		sel = new CDSelector<SelectorIcon>(50, 520);
		sel.rescale(.75f, .75f);
		Image readyButtonImage = new Image(ImagePaths.READY_BUTTON);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		int readyButtonOffSet = container.getWidth() - READY_BUTTON_X
				- textBounds.width;

		sel.setX(READY_BUTTON_X - readyButtonOffSet - sel.getBounds().width);
		sel.setY(sel.getY() +5);
		// Main Selector
		Rectangle textLocation = new Rectangle(0, 0, sel.getMainBounds().width,
				sel.getMainBounds().height / 4);

		UtilFunctions.centerRectangle(sel.getMainBounds(), textLocation);
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
		textLocation = new Rectangle(0, 0, sel.getRightBounds().width,
				sel.getRightBounds().height / 4);

		UtilFunctions.centerRectangle(sel.getRightBounds(), textLocation);
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
		textLocation = new Rectangle(0, 0, sel.getLeftBounds().width,
				sel.getLeftBounds().height / 4);

		UtilFunctions.centerRectangle(sel.getLeftBounds(), textLocation);
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

		this.addComponent(sel);
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

		intializeDefaults();
		// Hints
		theHints = new TextArea(new Rectangle(413, 77, 340, 450), .95f, "");
		theHints.setFontSize(MEDIUM_FONT_SIZE);
		theHints.setFontColor(FONT_COLOR1);
		// instructionLabel
		Rectangle instructionBounds = new Rectangle(398, 0, 370, 62);

		instructionsLabel = new TextField(instructionBounds, 0.95f,
				"Select the Critical Design Month.",
				TextDisplay.FormattingOption.FIT_TEXT);

		instructionsLabel.center();

		instructionsLabel.setFontColor(Fonts.FONT_COLOR);

		// initialize

		this.addComponent(theHints);
		this.addComponent(instructionsLabel);

		System.out
				.println("Listeners: " + Arrays.toString(this.getListeners()));
	}

	private void userAnswer(int i) throws SlickException {
		if (i == 0) {
			theHints.setText(monthlyHints.get(i));
			if (!ap && !correctAnswer) {
				++hints;
				ap = true;
			}
			System.out.println("April Hint shown, total hints: " + hints);
		}
		if (i == 1) {
			theHints.setText(monthlyHints.get(i));
			if (!fe && !correctAnswer) {
				++hints;
				fe = true;
			}
			System.out.println("February Hint shown, total hints: " + hints);
		}
		if (i == 2) {
			theHints.setText(monthlyHints.get(i));
			instructionsLabel.setText("Correct! Press ready to continue.");
			correctAnswer = true;
			System.out.println("Correct answer gotten after " + hints
					+ " hints.");
		}
		if (i == 3) {
			theHints.setText(monthlyHints.get(i));
			if (!oc && !correctAnswer) {
				++hints;
				oc = true;
			}
			System.out.println("October Hint shown, total hints: " + hints);
		}
		if (i == 4) {
			theHints.setText(monthlyHints.get(i));
			if (!se && !correctAnswer) {
				++hints;
				se = true;
			}
			System.out.println("September Hint shown, total hints: " + hints);
		}
		if (i == 5) {
			theHints.setText(monthlyHints.get(i));
			if (!ju && !correctAnswer) {
				++hints;
				ju = true;
			}
			System.out.println("June Hint shown, total hints: " + hints);
		}

	}

	private void displayHint(int hCount) {
		if (hCount == 0) {
			theHints.setText(genericHints.get(0));
			++hintCount;
			if (!correctAnswer) {
				++hints;
			}
			System.out.println("Generic Hint1 shown, total hints: " + hints);
		}
		if (hCount == 1) {
			theHints.setText(genericHints.get(1));
			++hintCount;
			if (!correctAnswer) {
				++hints;
			}
			System.out.println("Generic Hint2 shown, total hints: " + hints);
		}
		if (hCount == 2) {
			theHints.setText(genericHints.get(2));
			++hintCount;
			if (!correctAnswer) {
				++hints;
			}
			System.out.println("Generic Hint2 shown, total hints: " + hints);
		}
		if (hCount > 2) {

			if (hCount == 3) {
				theHints.setText(genericHints.get(0));
			} else if (hCount == 4) {
				theHints.setText(genericHints.get(1));
			} else {
				theHints.setText(genericHints.get(2));
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

	public void intializeDefaults() throws SlickException {
		// Initialize Header
		Rectangle headerLocation = new Rectangle(32, 52, 350, 500);
		TextAreaX header = new TextAreaX(headerLocation, .95f,
				null);
		header.setFontSize(MEDIUM_FONT_SIZE);
		header.setFontColor(FONT_COLOR1);
		header.setText("Location: Niger, Niamey" + "\n" + "Latitude: 13° 31 N, Longitude: 2° 6 E");
		
		// Hint Box Initialization
		// Hint Bounds
		Rectangle hintBounds = new Rectangle(398, 57, 370, 160);
		Rectangle relativeHintTextBounds = UtilFunctions.dialateRectangle(
				new Rectangle(0, 0, 370, 160), 0.92f);
		TextAreaX hintBox = new TextAreaX(hintBounds, relativeHintTextBounds,
				null);

		Image hintBoxBackground = new Image(
				ImagePaths.Selector.HINT_BOX_BACKGROUND);
		hintBox.setCurrentImage(hintBoxBackground, true);

		// Format hint box
		hintBox.setFontSize(Fonts.FONT_SIZE_MEDIUM);

		hintBox.setFontColor(Fonts.FONT_COLOR);

		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(
				CDIntroScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		// Ready Button
		Image readyButtonImage = new Image(ImagePaths.READY_BUTTON);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.80f);
		Button readyButton = new Button(readyButtonImage, 600, 500, textBounds,
				null);
		readyButton.addActionListener(new CDReadyListener());

		// Hint Button
		Image hintButtonImage = (new Image(ImagePaths.HINT_BUTTON));
		textBounds = UtilFunctions.getImageBounds(hintButtonImage);
		Button Hint = new Button(hintButtonImage, readyButton.getX() + 18,
				readyButton.getY() - textBounds.height - 5, textBounds,
				"HINT");
		Hint.addActionListener(new CDHintListener());
		Hint.rescale(.8f, 1f);
		Hint.setX(readyButton.getX() + 28);
		Hint.getTextField().center();
		Hint.setFontColor(FONT_COLOR);

		this.addComponent(hintBox);
		this.addComponent(header);
		this.addComponent(backButton);
		this.addComponent(readyButton);

		this.addComponent(Hint);

	}

}

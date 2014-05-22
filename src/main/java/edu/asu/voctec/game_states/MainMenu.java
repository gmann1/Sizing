package edu.asu.voctec.game_states;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.information.UserProfile;
import edu.asu.voctec.minigames.step_selection.ScenarioIntroductionScreen;
import edu.asu.voctec.utilities.UtilFunctions;

public class MainMenu extends GUI implements GameDefaults, KeyListener

{
	private Button userNameField;
	private Button readyButton;
	private String userName = "";
	
	public static ArrayList<String> UserData = new ArrayList<>();
	
	public class UserNameFieldListener extends ButtonListener
	{
		private static final long serialVersionUID = -325855184018136406L;
		
		@Override
		protected void actionPerformed()
		{
			userNameField.getTextField().setText(userName);
		}
	}
	
	public class ReadyButtonListener extends ButtonListener
	{
		private static final long serialVersionUID = -325855184018136406L;
		
		@Override
		protected void actionPerformed()
		{
			if (UserData.isEmpty())
			{
				UserData.add(userName);
			}
			else
			{
				UserData.remove(0);
				UserData.add(0, userName);
			}
			
			Game.getCurrentGame().enterState(ScenarioIntroductionScreen.class);
		}
	}
	
	/**
	 * Notification that a key was pressed. If the key is a letter or space, its value
	 * will be appended to the current userName. If the key is a backspace, one character
	 * will be removed from the current userName. All other keys will be ignored.
	 * 
	 * @see org.newdawn.slick.state.BasicGameState#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(int key, char c)
	{
		if ((isLetter(c) || isSpace(c)) && (userName.length() < 20))
			userName += c;
		else if (isBackspace(c) && (userName.length() > 0))
			userName = userName.substring(0, userName.length() - 1);
		
		userNameField.getTextField().setText(userName);
	}
	
	/**
	 * Returns true if the given character is a letter (a-z || A-Z)
	 * 
	 * @param c
	 *            The character to check
	 * @return True if c is a letter
	 */
	private static boolean isLetter(char c)
	{
		return ((c >= 65 && c <= 90) || (c >= 97 && c <= 122));
	}
	
	/**
	 * Returns true if the given character is a backspace character (ASCII 8)
	 * 
	 * @param c
	 *            The character to check
	 * @return True if c is a backspace character
	 */
	private static boolean isBackspace(char c)
	{
		return (c == 8);
	}
	
	/**
	 * Returns true if the given character is a space (ASCII 32)
	 * 
	 * @param c
	 *            The character to check
	 * @return True if c is a space
	 */
	private static boolean isSpace(char c)
	{
		return (c == 32);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		System.out.println("\nMainMenu: Initializing...");
		
		Image readyButtonImage = new Image(ImagePaths.READY_BUTTON);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.80f);
		readyButton = new Button(readyButtonImage, 0, 0, textBounds, "Ready");
		readyButton.setFontColor(Fonts.BUTTON_FONT_COLOR);
		readyButton.addActionListener(new ReadyButtonListener());
		
		// TODO Add support for loading profiles
		UserProfile profile = new UserProfile("Default User");
		profile.activate();
		
		int buttonSpacing = 15;
		int buttonWidth = 350;
		int buttonHeight = 75;
		// Account for square icon at the far right
		int textWidth = buttonWidth - buttonHeight;
		float borderScale = 0.9f;
		
		// Determine text and button bounds, relative to each button
		Rectangle buttonBounds = new Rectangle(0, 0, buttonWidth, buttonHeight);
		Rectangle relativeTextBounds = new Rectangle(0, 0, textWidth, buttonHeight);
		relativeTextBounds = UtilFunctions.dialateRectangle(relativeTextBounds,
				borderScale);
		
		// Declare Components
		// UserName Field
		userNameField = new Button(ImagePaths.NEW_GAME_BUTTON, buttonBounds,
				relativeTextBounds, "Click to enter username");
		userNameField.addActionListener(new UserNameFieldListener());
		
		// Color text
		userNameField.setFontColor(MainMenuDefaults.BUTTON_FONT_COLOR);
		
		// Game Title
		Rectangle textLocation = new Rectangle(0, 0, 2 * buttonWidth, buttonHeight);
		TextField gameTitle = new TextField(textLocation, 0.95f, "PV System Sizing Game",
				TextDisplay.FormattingOption.FIT_TEXT);
		gameTitle.setFontColor(Color.white);
		gameTitle.center();
		
		TextField padding = new TextField(textLocation, 0.95f, "",
				TextDisplay.FormattingOption.FIT_TEXT);
		
		// Add buttons to this menu
		this.addComponent(gameTitle);
		this.addComponent(padding);
		this.addComponent(userNameField);
		this.addComponent(readyButton);
		
		this.centerComponentsStacked(buttonSpacing);
		gameTitle.setY(gameTitle.getY() - 100);
		userNameField.setY(userNameField.getY() - 50);
		readyButton.setY(readyButton.getY() - 35);
		Image background = new Image(ImagePaths.MainMenuBackground);
		setBackgroundImage(background.getScaledCopy(800, 600));
		
		System.out.println("MainMenu: Initialization Complete.\n");
	}
	
	@Override
	public void initiateDeffered()
	{
		System.out.println("Loading...");
		
		try
		{
			GameState newState = new ScenarioIntroductionScreen();
			Game.getCurrentGame()
					.addState(newState, Game.getCurrentGame().getContainer());
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Loaded!");
	}
}

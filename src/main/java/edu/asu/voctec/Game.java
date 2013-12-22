package edu.asu.voctec;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.game_states.MainMenu;
import edu.asu.voctec.game_states.MenuTest;
import edu.asu.voctec.game_states.ModifiedGameState;
import edu.asu.voctec.utilities.Singleton;

/**
 * Singleton class representing the currently running game.
 * 
 * @author Zach Moore
 */
public class Game extends StateBasedGame implements Singleton
{
	// Replace with hashMap of classes and IDs
	public static int MainMenuID;
	public static int TaskScreenID;
	public static int LanguageMenuID;
	public static int InstructorControlPanelID;
	
	private static Game currentGame;
	
	/**
	 * List of all GameState IDs that have been added. @see #addState(GameState)
	 */
	public static final ArrayList<Integer> GAME_STATES = new ArrayList<>();
	
	/** GameState to enter upon launching the application */
	public static final int DEFAULT_GAME_STATE = 0;
	
	// TODO: Class loading
	
	/**
	 * Private constructor to enforce Singleton.
	 * 
	 * @param gameTitle
	 *            the name of the window this game will run in.
	 * @throws DuplicateInstantiationException
	 *             if an attempt is made to create more than one instance of
	 *             this class.
	 */
	private Game(String gameTitle) throws DuplicateInstantiationException
	{
		super(gameTitle);
		
		if (Game.currentGame == null)
			Game.currentGame = this;
		else
			throw new DuplicateInstantiationException();
		
		System.out.println("Game constructed successfully.");
	}
	
	/**
	 * Constructs a game using the title defined in Main.
	 * 
	 * @return the current Game object.
	 * @see #constructGame(String)
	 * @see edu.asu.voctec.Main
	 */
	public static Game constructGame()
	{
		return constructGame(Main.GAME_TITLE);
	}
	
	/**
	 * Pseudo-Constructor. If a game has already been instantiated, this method
	 * will return that Game object. Otherwise, a new Game object will be
	 * constructed and returned.
	 * 
	 * @param the
	 *            name of the window this game will run in.
	 * @return the current Game object.
	 * @see #Game(String)
	 */
	protected static Game constructGame(String gameTitle)
	{
		if (Game.currentGame == null)
		{
			try
			{
				return new Game(gameTitle);
			}
			catch (DuplicateInstantiationException exception)
			{
				return Game.currentGame;
			}
		}
		else
		{
			return Game.currentGame;
		}
	}
	
	/**
	 * @return the Game object representing the currently running game.
	 */
	public static Game getCurrentGame()
	{
		return currentGame;
	}
	
	/*
	 * (non-Javadoc) Create, add, and initialize all states associated with this
	 * game
	 * 
	 * @see
	 * org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick
	 * .GameContainer)
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		// Declare & Initialize all game states
		ModifiedGameState mainMenu = new MainMenu();
		Game.MainMenuID = mainMenu.getID();
		
		ModifiedGameState menuTest = new MenuTest();
		Game.TaskScreenID = menuTest.getID();
		
		Game.InstructorControlPanelID = 0;
		Game.LanguageMenuID = 0;
		
		// Add all GameStates
		this.addState(mainMenu);
		this.addState(menuTest);
		// TODO add all other states
		
		// Move to the default game state
		this.enterState(Game.DEFAULT_GAME_STATE);
	}
	
	/*
	 * (non-Javadoc) adds the provided GameState to the list of gameStates, and
	 * adds its id to the list of gameStateIDs
	 * 
	 * @see
	 * org.newdawn.slick.state.StateBasedGame#addState(org.newdawn.slick.state
	 * .GameState)
	 */
	@Override
	public void addState(GameState state)
	{
		super.addState(state);
		GAME_STATES.add(state.getID());
	}
	
	@Override
	public void enterState(int id)
	{
		System.out.println("Switching States...");
		super.enterState(id);
		System.out.println("Switch Successful. Current State: "
				+ this.getCurrentStateID());
	}
}

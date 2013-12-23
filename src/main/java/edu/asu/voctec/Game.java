package edu.asu.voctec;

import java.util.Collection;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.game_states.InstructorControlPanel;
import edu.asu.voctec.game_states.LanguageMenu;
import edu.asu.voctec.game_states.MainMenu;
import edu.asu.voctec.game_states.MenuTest;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Singleton;

/**
 * Singleton class representing the currently running game.
 * 
 * @author Zach Moore
 */
public class Game extends StateBasedGame implements Singleton
{
	/**
	 * Map of all GameState IDs that have been added. @see #addState(GameState)
	 */
	private static HashMap<Class<?>, Integer> gameStates = new HashMap<>();
	private static Game currentGame;
	
	/** GameState to enter upon launching the application */
	public static final Class<?> DEFAULT_GAME_STATE = MainMenu.class;
	
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
	
	public static Collection<Integer> getGameStates()
	{
		return Game.gameStates.values();
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
		// Initialize & Add all GameStates
		this.addState(new MainMenu());
		this.addState(new MenuTest());
		this.addState(new InstructorControlPanel());
		this.addState(new LanguageMenu());
		this.addState(new TaskScreen());
		
		// Move to the default game state
		this.enterState(MainMenu.class);
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
		gameStates.put(state.getClass(), state.getID());
	}
	
	@Override
	public void enterState(int id)
	{
		System.out.println("Switching States...");
		super.enterState(id);
		System.out.println("Switch Successful. Current State: "
				+ this.getCurrentStateID());
	}
	
	public void enterState(Class<?> state)
	{
		this.enterState(gameStates.get(state));
	}
}

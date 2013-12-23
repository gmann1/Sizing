package edu.asu.voctec;

import java.util.Collection;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.batter_sizing.BatteryExitScreen;
import edu.asu.voctec.batter_sizing.BatteryGameScreen;
import edu.asu.voctec.batter_sizing.BatteryIntro;
import edu.asu.voctec.cdmg.CDExtra;
import edu.asu.voctec.cdmg.CDIntroScreen;
import edu.asu.voctec.cdmg.CDPart1;
import edu.asu.voctec.cdmg.CDPart2;
import edu.asu.voctec.cdmg.CDPart3;
import edu.asu.voctec.game_states.InstructorControlPanel;
import edu.asu.voctec.game_states.LanguageMenu;
import edu.asu.voctec.game_states.MainMenu;
import edu.asu.voctec.game_states.MenuTest;
import edu.asu.voctec.game_states.MinigameA;
import edu.asu.voctec.game_states.ModifiedGameState;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.pv_game.PVExit;
import edu.asu.voctec.pv_game.PVGame;
import edu.asu.voctec.pv_game.PVIntro;
import edu.asu.voctec.utilities.Singleton;

/**
 * Singleton class representing the currently running game. The singleton Game
 * object can be accessed statically via {@link #getCurrentGame()}. Using this
 * object, the game state can be changed using a Class object to reference a
 * Singleton gameState (i.e. any extension of ModifiedGameState).
 * 
 * @author Zach Moore
 * @see #enterState(Class)
 * @see ModifiedGameState
 * @see Singleton
 * @see #getCurrentGame()
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
		this.addState(new CDPart1());
		this.addState(new CDPart2());
		this.addState(new CDPart3());
		this.addState(new CDExtra());
		this.addState(new CDIntroScreen());
		this.addState(new BatteryExitScreen());
		this.addState(new BatteryIntro());
		this.addState(new BatteryGameScreen());
		this.addState(new MinigameA());
		this.addState(new PVIntro());
		this.addState(new PVGame());
		this.addState(new PVExit());
		
		// Move to the default game state
		this.enterState(Game.DEFAULT_GAME_STATE);
	}
	
	/*
	 * (non-Javadoc) adds the provided GameState to the list of gameStates, and
	 * maps the state, so it can be accessed statically.
	 * 
	 * @see #enterstate(Class<?>)
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

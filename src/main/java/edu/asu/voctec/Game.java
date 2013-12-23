package edu.asu.voctec;

import java.util.Collection;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

<<<<<<< HEAD
import edu.asu.voctec.cdmg.CDIntroScreen;
import edu.asu.voctec.cdmg.CDPart1;
=======
import edu.asu.voctec.game_states.InstructorControlPanel;
import edu.asu.voctec.game_states.LanguageMenu;
>>>>>>> branch 'master' of https://github.com/gmann1/voctec_sizing.git
import edu.asu.voctec.game_states.MainMenu;
import edu.asu.voctec.game_states.MenuTest;
import edu.asu.voctec.game_states.TaskScreen;
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
<<<<<<< HEAD
	// Replace with hashMap of classes and IDs
	public static int MainMenuID;
	public static int TaskScreenID;
	public static int LanguageMenuID;
	public static int InstructorControlPanelID;
	public static int CDPart1ID;
	
=======
	/**
	 * Map of all GameState IDs that have been added. @see #addState(GameState)
	 */
	private static HashMap<Class<?>, Integer> gameStates = new HashMap<>();
>>>>>>> branch 'master' of https://github.com/gmann1/voctec_sizing.git
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
<<<<<<< HEAD
		// Declare & Initialize all game states
		ModifiedGameState mainMenu = new MainMenu();
		Game.MainMenuID = mainMenu.getID();
		
		ModifiedGameState menuTest = new MenuTest();
		Game.TaskScreenID = menuTest.getID();
		
		Game.InstructorControlPanelID = 0;
		Game.LanguageMenuID = 0;
		
		CDPart1 p1 = new CDPart1();
		Game.CDPart1ID = p1.getID();
		this.addState(p1);
		// Add all GameStates
		this.addState(mainMenu);
		this.addState(menuTest);
		// TODO add all other states
=======
		// Initialize & Add all GameStates
		this.addState(new MainMenu());
		this.addState(new MenuTest());
		this.addState(new InstructorControlPanel());
		this.addState(new LanguageMenu());
		this.addState(new TaskScreen());
>>>>>>> branch 'master' of https://github.com/gmann1/voctec_sizing.git
		
		// Move to the default game state
<<<<<<< HEAD
		//this.enterState(Game.DEFAULT_GAME_STATE);
		CDIntroScreen cd = new CDIntroScreen();
		this.addState(cd);
		this.enterState(cd.getID());
=======
		this.enterState(MainMenu.class);
>>>>>>> branch 'master' of https://github.com/gmann1/voctec_sizing.git
	}
	
	/*
	 * (non-Javadoc) adds the provided GameState to the list of gameStates, and
	 * maps the state, so it can be accessed statically.
	 * 
	 * @see #enterstate(Class<?>)
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

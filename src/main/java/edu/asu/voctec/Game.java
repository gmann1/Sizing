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
import edu.asu.voctec.controller_sizing.ControllerSizingExit;
import edu.asu.voctec.controller_sizing.ControllerSizingIntroScreen;
import edu.asu.voctec.controller_sizing.ControllerSizingPart1;
import edu.asu.voctec.controller_sizing.ControllerSizingPart2;
import edu.asu.voctec.energy_assessment.EAPart1;
import edu.asu.voctec.energy_assessment.EAPart1IntroScreen;
import edu.asu.voctec.energy_assessment.EAPart1ScoreScreen;
import edu.asu.voctec.energy_assessment.EAPart2;
import edu.asu.voctec.energy_assessment.EAPart2IntroScreen;
import edu.asu.voctec.energy_assessment.EAPart2ScoreScreen;
import edu.asu.voctec.game_states.InstructorControlPanel;
import edu.asu.voctec.game_states.LanguageMenu;
import edu.asu.voctec.game_states.MainMenu;
import edu.asu.voctec.game_states.MenuTest;
import edu.asu.voctec.game_states.ModifiedGameState;
import edu.asu.voctec.game_states.SelectorTest;
import edu.asu.voctec.game_states.Task;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.information.ScenarioData;
import edu.asu.voctec.information.TaskData;
import edu.asu.voctec.information.UserProfile;
import edu.asu.voctec.pv_game.PVExit;
import edu.asu.voctec.pv_game.PVGame;
import edu.asu.voctec.pv_game.PVIntro;
import edu.asu.voctec.step_selection.ScenarioIntroductionScreen;
import edu.asu.voctec.step_selection.StepSelectionExitScreen;
import edu.asu.voctec.utilities.Singleton;

/**
 * Singleton class representing the currently running game. The singleton Game
 * object can be accessed statically via {@link #getCurrentGame()}. Using this
 * object, the game state can be changed using a Class object to reference a
 * Singleton gameState (i.e. any extension of ModifiedGameState).
 * 
 * All states added to this game should implement Singleton, and extend
 * ModifiedGameState. States that do not meet these requirements will not be
 * added to the state list of this game.
 * 
 * @author Zach Moore
 * @see ModifiedGameState
 * @see Singleton
 * @see #getCurrentGame()
 * @see #enterState(Class)
 */
public class Game extends StateBasedGame implements Singleton
{
	/**
	 * Map of all GameState IDs that have been added. @see #addState(GameState)
	 */
	private static HashMap<Class<?>, Integer> gameStates = new HashMap<>();
	private static Game currentGame;
	private static ScenarioData currentScenario;
	private static TaskData currentTask;
	private static UserProfile currentUser;
	
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
		this.addState(new ScenarioIntroductionScreen());
		this.addState(new SelectorTest());
		this.addState(new StepSelectionExitScreen());
		this.addState(new CDPart1());
		this.addState(new CDPart2());
		this.addState(new CDPart3());
		this.addState(new CDExtra());
		this.addState(new CDIntroScreen());
		this.addState(new BatteryExitScreen());
		this.addState(new BatteryIntro());
		this.addState(new BatteryGameScreen());
		this.addState(new PVIntro());
		this.addState(new PVGame());
		this.addState(new PVExit());
		this.addState(new EAPart1IntroScreen());
		this.addState(new EAPart1());
		this.addState(new EAPart1ScoreScreen());
		this.addState(new EAPart2());
		this.addState(new EAPart2IntroScreen());
		this.addState(new EAPart2ScoreScreen());
		this.addState(new ControllerSizingIntroScreen());
		this.addState(new ControllerSizingExit());
		this.addState(new ControllerSizingPart1());
		this.addState(new ControllerSizingPart2());
		
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
		if (state instanceof ModifiedGameState)
		{
			super.addState(state);
			gameStates.put(state.getClass(), state.getID());
		}
		else
		{
			throw new IllegalArgumentException(
					"Game only accepts ModifiedGameStates.");
		}
	}
	
	@Override
	public void enterState(int id)
	{
		System.out.println("Switching States...");
		
		GameState state = getState(id);
		if (state instanceof Task)
			((Task) state).load();
		if (state instanceof ModifiedGameState)
			((ModifiedGameState) state).onEnter();
		
		super.enterState(id);
		System.out.println("Switch Successful. Current State: "
				+ this.getCurrentStateID());
	}
	
	public void enterState(Class<?> state)
	{
		this.enterState(getStateID(state));
	}
	
	public static TaskData getCurrentTask()
	{
		return currentTask;
	}
	
	public static int getStateID(Class<?> state)
	{
		return gameStates.get(state);
	}
	
	public static void setCurrentTask(TaskData currentTask)
	{
		Game.currentTask = currentTask;
	}
	
	public static GameState getGameState(Class<?> state)
	{
		return currentGame.getState(getStateID(state));
	}

	public static UserProfile getCurrentUser()
	{
		return currentUser;
	}

	public static void setCurrentUser(UserProfile currentUser)
	{
		Game.currentUser = currentUser;
	}

	public static ScenarioData getCurrentScenario()
	{
		return currentScenario;
	}

	public static void setCurrentScenario(ScenarioData currentScenario)
	{
		Game.currentScenario = currentScenario;
	}
	
	
	
}

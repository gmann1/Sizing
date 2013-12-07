package voctec;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * 
 * @author Zach Moore
 *
 */
public class Game extends StateBasedGame implements Singleton
{
	private static Game currentGame;
	
	/**List of all GameState IDs that have been added. @see #addState(GameState)*/
	public static final ArrayList<Integer> GAME_STATES = new ArrayList<>();
	/**GameState to enter upon launching the application*/
	public static final int DEFAULT_GAME_STATE = MainMenu.ID;
	//TODO: Class loading
	
	private Game(String gameTitle) throws DuplicateInstantiationException
	{
		super(gameTitle);
		
		if (Game.currentGame == null)
			Game.currentGame = this;
		else
			throw new DuplicateInstantiationException();
			
		
		System.out.println("Constructor finished.");
	}
	
	public static Game constructGame()
	{
		return constructGame(Main.GAME_TITLE);
	}
	
	private static Game constructGame(String gameTitle)
	{
		if (Game.currentGame == null)
		{
			try
			{
				return new Game(gameTitle);
			} 
			catch(DuplicateInstantiationException exception) 
			{
				return Game.currentGame;
			}
		}
		else
		{
			return Game.currentGame;
		}
	}

	public static Game getCurrentGame()
	{
		return currentGame;
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		//Add all GameStates
		this.addState(new MainMenu());
		
		//Initialize all the game states
		for (int stateID : Game.GAME_STATES)
		{
			GameState g = this.getState(stateID);
			g.init(container, this);
		}
		
		//Move to the default game state
		this.enterState(Game.DEFAULT_GAME_STATE);
	}
	
	@Override
	public void addState(GameState state)
	{
		super.addState(state);
		GAME_STATES.add(state.getID());
	}
}

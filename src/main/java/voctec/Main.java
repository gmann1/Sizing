package voctec;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main
{
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final String GAME_TITLE = "Sizing";
	public static final boolean SHOW_FPS = true;
	
	public static void main(String[] args) throws SlickException
	{
		AppGameContainer game = new AppGameContainer(Game.constructGame());
		
		//adjust settings
		game.setShowFPS(SHOW_FPS);
		game.setTargetFrameRate(59);
		game.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		game.setForceExit(false);
		//TODO load settings from file
		
		//launch game
		game.start();
	}
}

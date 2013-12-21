package rubbish.deprecate.misc;

import java.awt.Dimension;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import rubbish.deprecate.voctec.menu.MainMenu;
import rubbish.deprecate.voctec.menu.Menu;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;
import edu.asu.voctec.menu.buttons.TransitionButton;

public class ScenarioHub extends Menu
{
	public static final int			ID					= 1;
	public static final Dimension	DesignResolution	= new Dimension(1280,
																720);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		/*Image buttonImage = new Image(ImagePaths.BASE_BUTTON).getScaledCopy(
				100, 100);*/
		// TODO declare & instantiate all buttons
		Button backButton = new TransitionButton(ImagePaths.BACK_BUTTON, 10,
				10, MainMenu.ID, LayoutOption.TOP_LEFT_ALIGN);
		
		// TODO add all buttons
		this.addButton(backButton);
		
		// TODO initialize all resources
		
		// TODO initialize background image
		Image background = new Image(ImagePaths.ScenarioHubBackground);
		super.initializeBackgroundImage(background);
		
		// ensure the appropriate scale is being used
		resize();
		
		// ensure the appropriate language is being used
		updateTranslation();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
	
	@Override
	public Dimension getDesignResolution()
	{
		return DesignResolution;
	}
}

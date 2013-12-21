package rubbish.deprecate;

import java.awt.Dimension;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults.Fonts;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.GameDefaults.Labels;
import edu.asu.voctec.ScenarioHub;
import edu.asu.voctec.menu.Menu;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;
import edu.asu.voctec.menu.buttons.Dictionary;
import edu.asu.voctec.menu.buttons.LabelName;
import edu.asu.voctec.menu.buttons.TransitionButton;
import edu.asu.voctec.menu.buttons.TranslatableLabel;

public class IntroScreen extends Menu
{
	public static final int			ID					= 1500;
	public static final Dimension	DesignResolution	= new Dimension(800,
																600);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// Label positions
		int textLeft = 300;
		int yStart = 50;
		int yDelta = 30;
		int width = 400;
		int height = 35;
		TrueTypeFont font = new TrueTypeFont(
				TranslatableLabel.DEFAULT_AWT_FONT.deriveFont(20f),
				Fonts.ANTI_ALLIAS, Dictionary.getExtraCharacters());
		
		// Add Labels
		addLabel(new TranslatableLabel(
				Labels.CriticalDesignMonth.instructionsLine1, font, textLeft,
				yStart, width, height));
		addLabel(new TranslatableLabel(
				Labels.CriticalDesignMonth.instructionsLine2, font, textLeft,
				yStart + 2 * yDelta, width, height));
		addLabel(new TranslatableLabel(
				Labels.CriticalDesignMonth.instructionsLine3, font, textLeft,
				yStart + 3 * yDelta, width, height));
		addLabel(new TranslatableLabel(
				Labels.CriticalDesignMonth.instructionsLine4, font, textLeft,
				yStart + 4 * yDelta, width, height));
		addLabel(new TranslatableLabel(
				Labels.CriticalDesignMonth.instructionsLine5, font, textLeft,
				yStart + 5 * yDelta, width, height));
		addLabel(new TranslatableLabel(
				Labels.CriticalDesignMonth.instructionsLine6, font, textLeft,
				yStart + 6 * yDelta, width, height));
		
		// Add buttons
		Button startButton = new TransitionButton(ImagePaths.NEW_GAME_BUTTON,
				textLeft, yStart + 8 * yDelta, CriticalDesignMonthGameScreen.ID);
		startButton.setLabel(LabelName.startButton);
		addButton(new TransitionButton(ImagePaths.BACK_BUTTON, 10, -10,
				ScenarioHub.ID, LayoutOption.BOTTOM_LEFT_ALIGN));
		addButton(startButton);
		
		// initialize background image
		Image background = (new Image(ImagePaths.INTRO_BACKGROUND))
				.getScaledCopy(DesignResolution.width, DesignResolution.height);
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Dimension getDesignResolution()
	{
		// TODO Auto-generated method stub
		return DesignResolution;
	}
	
	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return ID;
	}
	
	@Override
	public boolean resize()
	{
		// TODO implement resize code
		return false;
	}
	
	@Override
	public void rescale()
	{
		// TODO implement rescale code
	}
}

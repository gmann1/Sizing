package edu.asu.voctec.menu;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.TranslatableLabel;
import edu.asu.voctec.utilities.AspectRatio;
import edu.asu.voctec.utilities.Resizable;
import edu.asu.voctec.utilities.ScreenResolution;
import edu.asu.voctec.utilities.Translatable;
import edu.asu.voctec.utilities.AspectRatio.ResolutionNotSupportedException;
import edu.asu.voctec.Main;

public abstract class Menu extends BasicGameState implements Resizable, Translatable
{
	private ScreenResolution baseResolution;
	private Image baseBackgroundImage;
	private Image backgroundImage;
	//TODO combine to GUIElements
	protected final ArrayList<Button> buttons = new ArrayList<>();
	protected final ArrayList<TranslatableLabel> labels = new ArrayList<>();
	protected float scale;
	
	public abstract Dimension getDesignResolution();
	
	protected void initializeBackgroundImage(Image backgroundImage)
	{
		try
		{
			if (backgroundImage != null)
				this.baseResolution = new ScreenResolution(backgroundImage);
		}
		catch (ResolutionNotSupportedException e)
		{
			e.printStackTrace();
			//TODO resize image to design resolution
		}
		
		this.backgroundImage = backgroundImage;
		this.baseBackgroundImage = backgroundImage;
	}
	
	/**
	 * Adds a button to this menu, and ensures that no duplicate buttons are
	 * added. More specifically, If this menu already contains a button e
	 * such that e.equals(button), then the provided button will not be added
	 * and this method will return false.
	 * 
	 * @param button	the button to add to this menu
	 * @return			whether or not the provided button was added successfully.
	 * @see 			ArrayList#add(Object)
	 */
	public boolean addButton(Button button)
	{
		if (buttons.contains(button))
			return false;
		else
			return buttons.add(button);
	}
	
	public boolean addLabel(TranslatableLabel label)
	{
		if (labels.contains(label))
			return false;
		else
			return labels.add(label);
	}
	
	/** 
	 * This method contains actions to take during a mousePressed event. Should
	 * only be called to notify this object of a mousePressed event.
	 * 
	 * @param button 	The index of the button (starting at 0)
	 * @param x 		The x position of the mouse when the button was pressed
	 * @param y 		The y position of the mouse when the button was pressed
	 */
	@Override
	public void mousePressed(int buttonType, int x, int y)
	{
		boolean leftButtonPressed = (buttonType == Input.MOUSE_LEFT_BUTTON);
		
		if (leftButtonPressed)
		{
			//check buttons
			for (Button button : buttons)
			{
				if (button.checkClicked(new Point(x,y)))
				{
					button.actOnMouseClick();
					break;
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics)
			throws SlickException
	{
		//TODO account for different aspect ratios
		//draw background
		if (this.backgroundImage == null)
			System.out.println("Menu background failed to load: BackgroundImageNull");
		else
			graphics.drawImage(this.backgroundImage, 0, 0);
		
		//TODO implement change tracking
		//TODO draw only buttons that have been changed
		//draw all buttons
		for (Button button : buttons)
		{
			button.draw(graphics);
		}
		
		//TODO implement change tracking
		//TODO draw only labels that have been changed
		//draw all labels
		for (TranslatableLabel label : labels)
		{
			label.draw(graphics);
		}
	}
	
	public boolean resize()
	{
		// Define dimension to transition to
		ScreenResolution newDimension = Main.getCurrentScreenDimension();
		
		if (this.baseResolution.equals(newDimension))
			this.backgroundImage = this.baseBackgroundImage;
		else if (this.backgroundImage != null)
		{
			try
			{
				// Define rectangle to crop image to new aspectRatio
				//TODO fix getSubSection algorithm
				Rectangle croppedSubSection = AspectRatio.getSubSection(
						this.baseResolution, newDimension.getAspectRatio());
				// Crop image to new aspectRatio
				Image croppedImage = this.baseBackgroundImage.getSubImage(
						croppedSubSection.x, croppedSubSection.y, 
						croppedSubSection.width, croppedSubSection.height);
				
				// Scale cropped section to new dimension
				Image scaledImage = croppedImage.getScaledCopy(
						newDimension.width, newDimension.height);
				
				// Update the current image
				this.backgroundImage = scaledImage;
			}
			catch (ResolutionNotSupportedException e)
			{
				// TODO handle exception
				e.printStackTrace();
			}
		}
		
		// Update metadata of this map (regarding size) and all buttons in this menu
		this.rescale();
		
		//if no errors occurred, return true
		return true;
	}
	
	public void rescale()
	{
		this.scale = (float) (Main.getCurrentScreenDimension().getHeight() / getDesignResolution().getHeight());
		
		for (Button button : buttons)
		{
			button.scale(scale);
		}
		
		//TODO scale labels
	}

	public Image getBackgroundImage()
	{
		return backgroundImage;
	}
	
	public void updateTranslation()
	{
		//TODO only translate if current language != new language
		for (Button button : buttons)
		{
			button.updateTranslation();
		}
		
		//TODO update labels
		for (TranslatableLabel label : labels)
		{
			label.updateTranslation();
		}
	}
}

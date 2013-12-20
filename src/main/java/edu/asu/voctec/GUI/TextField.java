package edu.asu.voctec.GUI;

import java.awt.Font;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.utilities.UtilFunctions;

/**
 * Contains a single line of text that can be displayed to the screen using
 * LWJGL. A TextField object can have other options specified, such as a visible
 * border or background, font size and style, etc. Text can be automatically
 * sized or clipped, depending on the constructor used.
 * 
 * @author Moore, Zachary
 * @see #TextField(Rectangle, String, Color, boolean, boolean)
 * @see #TextField(Rectangle, String, Color, int, boolean, boolean)
 * @see #TextField(Rectangle, String, Font, Color, boolean, boolean)
 * @see #TextField(Rectangle, String, Font, int, Color, boolean, boolean)
 * 
 */
public class TextField implements Displayable
{
	public static final double BORDER_SCALE = 0.95;
	
	protected Rectangle bounds;
	protected Rectangle textBounds;
	protected String text;
	protected Font awtFont;
	protected TrueTypeFont font;
	protected Color fontColor;
	protected Color borderColor;
	protected Color backgroundColor;
	
	protected boolean center;
	
	public TextField(Rectangle bounds, String text, Font unsizedFont,
			int forcedFontSize, Color fontColor, boolean antiAlias,
			boolean center)
	{
		this.bounds = bounds;
		this.textBounds = bounds;
		this.text = text;
		this.fontColor = fontColor;
		this.center = center;
		this.backgroundColor = Color.blue;
		this.borderColor = Color.white;
		
		if (forcedFontSize > 0)
			this.awtFont = unsizedFont.deriveFont((float) forcedFontSize);
		else
			this.awtFont = unsizedFont;
		
		this.font = new TrueTypeFont(awtFont, antiAlias);
		
		this.text = TextSupport.clipString(font, text,
				(int) (bounds.width * BORDER_SCALE))[0];
		
		if (center)
			centerText();
	}
	
	public TextField(Rectangle bounds, String text, Font awtFont,
			Color fontColor, boolean antiAlias, boolean center)
	{
		this(bounds, text, TextSupport.getMaxScaledFont(awtFont, text, bounds,
				BORDER_SCALE), 0, fontColor, antiAlias, center);
	}
	
	public TextField(Rectangle bounds, String text, Color fontColor,
			boolean antiAlias, boolean center)
	{
		this(bounds, text, TextSupport
				.getMaxScaledFont(GameDefaults.Fonts.DEFAULT_AWT_FONT, text,
						bounds, BORDER_SCALE), 0, fontColor, antiAlias, center);
	}
	
	public TextField(Rectangle bounds, String text, Color fontColor,
			int forcedFontSize, boolean antiAlias, boolean center)
	{
		this(bounds, text, GameDefaults.Fonts.DEFAULT_AWT_FONT, forcedFontSize,
				fontColor, antiAlias, center);
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.voctec.GUI.Displayable#draw(org.newdawn.slick.Graphics)
	 */
	@Override
	public void draw(Graphics graphics)
	{
		Color color = graphics.getColor();
		
		// Draw background
		if (backgroundColor != null)
		{
			graphics.setColor(backgroundColor);
			graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		}
		
		// Draw border
		if (borderColor != null)
		{
			graphics.setColor(borderColor);
			graphics.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		}
		
		// Draw text
		graphics.setFont(font);
		graphics.setColor(fontColor);
		graphics.drawString(text, textBounds.x, textBounds.y);
		
		graphics.setColor(color);
	}
	
	public void centerText()
	{
		Rectangle trueTextRectangle = new Rectangle(textBounds.x, textBounds.y,
				font.getWidth(text), font.getHeight(text));
		
		UtilFunctions.centerRectangleHorizontally(bounds, trueTextRectangle);
		UtilFunctions.centerRectangleVertically(bounds, trueTextRectangle);
		
		this.textBounds = trueTextRectangle;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.voctec.GUI.Displayable#getX()
	 */
	@Override
	public int getX()
	{
		return bounds.x;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.voctec.GUI.Displayable#getY()
	 */
	@Override
	public int getY()
	{
		return bounds.y;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.voctec.GUI.Displayable#getBounds()
	 */
	@Override
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	@Override
	public void setBounds(Rectangle bounds)
	{
		// TODO Resize text
		// TODO Add image support
		this.bounds = bounds;
	}
	
	/**
	 * Display a border around this text field, using the provided color. If the
	 * borderColor parameter is null, this object's font color will be used as
	 * the border color.
	 * 
	 * @param borderColor
	 *            the desired border color.
	 */
	public void enableBorder(Color borderColor)
	{
		if (borderColor != null)
			this.borderColor = borderColor;
		else
			this.borderColor = this.fontColor;
	}
	
	/**
	 * Display a border around this text field, using the current font color.
	 */
	public void enableBorder()
	{
		enableBorder(fontColor);
	}
	
	/**
	 * Display a solid-color background behind this text field, using the
	 * backgroundColor parameter. Passing a null value as backgroundColor will
	 * have no impact on this object's current background.
	 * 
	 * @param backgroundColor the desired background color.
	 */
	public void enableBackground(Color backgroundColor)
	{
		if (backgroundColor != null)
			this.backgroundColor = backgroundColor;
	}
	
	/**
	 * Display a solid-color background behind this text field, using WHITE as
	 * the background color.
	 */
	public void enableBackground()
	{
		enableBackground(Color.white);
	}
	
	/**
	 * Stop displaying the background of this text field.
	 */
	public void disableBackground()
	{
		this.backgroundColor = null;
	}
	
	/**
	 * Stop displaying the border of this text field.
	 */
	public void disableBorder()
	{
		this.borderColor = null;
	}
}

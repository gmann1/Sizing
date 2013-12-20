package edu.asu.voctec.GUI;

import java.awt.Font;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.utilities.UtilFunctions;

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
			int forcedFontSize, Color fontColor, boolean antiAlias, boolean center)
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
		
		this.text = TextSupport.clipString(font, text, (int) (bounds.width*BORDER_SCALE))[0];
		
		if (center)
			centerText();
	}
	
	public TextField(Rectangle bounds, String text, Font awtFont,
			Color fontColor, boolean antiAlias, boolean center)
	{
		this(bounds, text,
				TextSupport.getMaxScaledFont(awtFont, text, bounds, BORDER_SCALE), 0,
				fontColor, antiAlias, center);
	}
	
	public TextField(Rectangle bounds, String text, Color fontColor,
			boolean antiAlias, boolean center)
	{
		this(bounds, text,
				TextSupport.getMaxScaledFont(GameDefaults.Fonts.DEFAULT_AWT_FONT, text,
						bounds, BORDER_SCALE), 0, fontColor, antiAlias, center);
	}
	
	public TextField(Rectangle bounds, String text, Color fontColor, int forcedFontSize,
			boolean antiAlias, boolean center)
	{
		this(bounds, text, GameDefaults.Fonts.DEFAULT_AWT_FONT, forcedFontSize, fontColor, antiAlias, center);
	}
	
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
	
	@Override
	public int getX()
	{
		return bounds.x;
	}
	
	@Override
	public int getY()
	{
		return bounds.y;
	}
	
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
	
	public void enableBorder(Color borderColor)
	{
		if (borderColor != null)
			this.borderColor = borderColor;
		else
			this.borderColor = this.fontColor;
	}
	
	public void enableBorder()
	{
		enableBorder(fontColor);
	}
	
	public void enableBackground(Color borderColor)
	{
		this.backgroundColor = borderColor;
	}
	
	public void enableBackground()
	{
		enableBackground(fontColor);
	}
	
	public void disableBackground()
	{
		this.backgroundColor = null;
	}
	
	public void disableBorder()
	{
		this.borderColor = null;
	}
}

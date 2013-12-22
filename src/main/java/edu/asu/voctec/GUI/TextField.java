package edu.asu.voctec.GUI;

import java.awt.Font;
import java.awt.Rectangle;

import org.newdawn.slick.Graphics;

import edu.asu.voctec.GameDefaults.Fonts;
import edu.asu.voctec.utilities.UtilFunctions;

/**
 * Contains a single line of text that can be displayed to the screen using
 * LWJGL. A TextField object can have other options specified, such as a visible
 * border or background, font size and style, etc. Text can be automatically
 * sized or clipped, depending on the constructor input.
 * 
 * @author Moore, Zachary
 * @see #TextField(Rectangle, float, Font, boolean, String, FormattingOption)
 * @see #TextField(Rectangle, Rectangle, Font, boolean, String,
 *      FormattingOption)
 * 
 */
public class TextField extends TextDisplay
{
	public static final FormattingOption DEFAULT_FORMAT = FormattingOption.CLIP_TEXT;
	protected String text;
	// TODO add support for centering text
	protected FormattingOption formatting;
	
	public TextField(Rectangle bounds, Rectangle textBounds, Font awtFont,
			boolean antiAlias, String text, FormattingOption option)
	{
		super(bounds, textBounds, awtFont, antiAlias);
		
		this.formatting = (option == null) ? DEFAULT_FORMAT : option;
		this.text = text;

		// Set font size based on provided formating option.
		formatText();
	}
	
	public TextField(Rectangle bounds, float textBounds, Font awtFont,
			boolean antiAlias, String text, FormattingOption option)
	{
		this(bounds,
				UtilFunctions.dialateRelativeRectangle(bounds, textBounds),
				awtFont, antiAlias, text, option);
	}
	
	public TextField(Rectangle bounds, float textBounds, String text,
			FormattingOption option)
	{
		this(bounds,
				UtilFunctions.dialateRelativeRectangle(bounds, textBounds),
				Defaults.AWT_FONT, Fonts.ANTI_ALLIAS, text, option);
	}
	
	protected void formatText()
	{

		if (formatting == FormattingOption.FIT_TEXT)
		{
			this.setFontSize(TextSupport.getMaxScaledFontSize(awtFont, text,
					textBounds));
		}
		else if (formatting == FormattingOption.FIT_TEXT_VERTICALLY)
		{
			this.setFontSize(TextSupport.getMaxVerticalScaledFontSize(awtFont,
					textBounds));
		}
		
		this.text = TextSupport.clipString(font, text, textBounds.width)[0];
	}
	
	protected void drawText(Graphics graphics)
	{
		graphics.setFont(font);
		graphics.setColor(fontColor);
		graphics.drawString(text, textBounds.x + bounds.x, textBounds.y
				+ bounds.y);
	}
	
	public void setText(String text)
	{
		this.text = text;
		formatText();
	}
	
	public void center()
	{
		center(true, true);
	}
	
	public void center(boolean vertical, boolean horizontal)
	{
		int textWidth = textBounds.width;
		int textHeight = textBounds.height;
		int relativeTextX = textBounds.x;
		int relativeTextY = textBounds.y;
		
		if (horizontal)
		{
			textWidth = font.getWidth(text);
			relativeTextX = (bounds.width / 2) - (textWidth / 2);
		}
		
		if (vertical)
		{
			textHeight = font.getHeight(text);
			relativeTextY = (bounds.height / 2) - (textHeight / 2);
		}
		
		this.textBounds =  new Rectangle(relativeTextX, relativeTextY,
				textWidth, textHeight);
	}
}

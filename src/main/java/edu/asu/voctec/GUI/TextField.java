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
 * @see #TextField(Rectangle, Rectangle, Font, boolean, String, FormattingOption)
 * 
 */
public class TextField extends TextDisplay
{
	protected String text;
	// TODO add support for centering text
	protected boolean center;
	
	public TextField(Rectangle bounds, Rectangle textBounds, Font awtFont,
			boolean antiAlias, String text, FormattingOption option)
	{
		super(bounds, textBounds, awtFont, antiAlias);
		
		// Set font size based on provided formating option. 
		// Default is the provided font size (awtFont.getSize())
		if (option == FormattingOption.FIT_TEXT)
			this.setFontSize(TextSupport.getMaxScaledFontSize(awtFont, text, textBounds));
		else if (option == FormattingOption.FIT_TEXT_VERTICALLY)
			this.setFontSize(TextSupport.getMaxVerticalScaledFontSize(awtFont, textBounds));

		this.text = TextSupport.clipString(font, text, textBounds.width)[0];
	}
	
	public TextField(Rectangle bounds, float textBounds, Font awtFont,
			boolean antiAlias, String text, FormattingOption option)
	{
		this(bounds, UtilFunctions.dialateRelativeRectangle(bounds, textBounds),
				awtFont, antiAlias, text, option);
	}
	
	public TextField(Rectangle bounds, float textBounds, String text, FormattingOption option)
	{
		this(bounds, UtilFunctions.dialateRelativeRectangle(bounds, textBounds),
				Defaults.AWT_FONT, Fonts.ANTI_ALLIAS, text, option);
	}
	
	protected void drawText(Graphics graphics)
	{
		graphics.setFont(font);
		graphics.setColor(fontColor);
		graphics.drawString(text, textBounds.x + bounds.x, textBounds.y + bounds.y);
	}
}

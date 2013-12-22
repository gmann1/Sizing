package edu.asu.voctec.GUI;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import edu.asu.voctec.utilities.UtilFunctions;

public class TextArea extends TextDisplay
{
	protected ArrayList<String> lines = new ArrayList<>();
	protected int maximumDisplayLines;
	
	public TextArea(Rectangle bounds, Rectangle textBounds, Font awtFont,
			boolean antiAlias, String text)
	{
		super(bounds, textBounds, awtFont, antiAlias);
		this.lines = TextSupport.wrapText(font, text, textBounds.width);
		this.maximumDisplayLines = calculateMaxDisplayLines();
		this.clipedText = determineClipedText();
	}
	
	public TextArea(Rectangle bounds, float textBounds, Font awtFont,
			boolean antiAlias, String text)
	{
		this(bounds, UtilFunctions.dialateRelativeRectangle(bounds, textBounds),
				awtFont, antiAlias, text);
	}
	
	protected String determineClipedText()
	{
		if (lines.size() <= this.maximumDisplayLines)
			return "";
		else
		{
			//TODO test
			StringBuilder clip = new StringBuilder();
			int startingIndex = 2*lines.size() - maximumDisplayLines;
			for (int lineIndex = startingIndex; lineIndex < lines.size(); lineIndex++)
			{
				clip.append(lines.get(lineIndex) + " ");
			}
			
			return clip.toString();
		}
	}
	
	protected int calculateMaxDisplayLines()
	{
		int lineHeight = font.getHeight();
		
		return textBounds.height / lineHeight;
	}
	
	@Override
	protected void drawText(Graphics graphics)
	{
		graphics.setFont(font);
		graphics.setColor(fontColor);
		
		int locationIncrement = font.getHeight();
		int maxLine = (maximumDisplayLines < lines.size()) ? maximumDisplayLines
				: lines.size();
		
		for (int lineIndex = 0; lineIndex < maxLine; lineIndex++)
		{
			String lineText = lines.get(lineIndex);
			int x = textBounds.x + bounds.x;
			int y = textBounds.y  + bounds.y + (locationIncrement * lineIndex);
			
			graphics.drawString(lineText, x, y);
		}
		
	}
	
}

package edu.asu.voctec.GUI;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Graphics;

import edu.asu.voctec.GameDefaults.Fonts;
import edu.asu.voctec.utilities.UtilFunctions;

public class TextAreaX extends TextArea
{
	public TextAreaX(Rectangle bounds, Rectangle textBounds, Font awtFont,
			boolean antiAlias, String text)
	{
		super(bounds, textBounds, awtFont, antiAlias, null);
		setText(text);
		System.out.println("Lines: " + Arrays.toString(lines.toArray(new String[lines.size()])));
	}

	public TextAreaX(Rectangle bounds, float textBounds, Font awtFont,
			boolean antiAlias, String text)
	{
		this(bounds, UtilFunctions.dialateRelativeRectangle(bounds, textBounds),
				awtFont, antiAlias, text);
	}

	public TextAreaX(Rectangle bounds, float textBounds, String text)
	{
		this(bounds,
				UtilFunctions.dialateRelativeRectangle(bounds, textBounds),
				Defaults.AWT_FONT, Fonts.ANTI_ALLIAS, text);
	}
	
	public void setFontSize(float size)
	{
		super.setFontSize(size);
		formatText();
	}
	
	public void wrapText()
	{
		ArrayList<String> wrappedText = new ArrayList<>();
		
		// Handle all lines between null entries as a block
		int startIndex = 0;
		while (startIndex < lines.size())
		{
			// Determine where this block ends
			int blockEndIndex = lines.indexOf(null);
			if (blockEndIndex < 0) blockEndIndex = lines.size();
			StringBuilder blockText = new StringBuilder();
			
			// Combine all lines in this block with a space
			for (int index = startIndex; index < blockEndIndex; index++)
			{
				blockText.append(lines.get(index));
				blockText.append(" ");
			}
			
			// Wrap this block across multiple lines
			ArrayList<String> wrappedBlock = TextSupport.wrapText(font, blockText.toString(), textBounds.width);
			
			// Add this block to the new line array
			wrappedText.addAll(wrappedBlock);
			
			// Account for new line character
			wrappedText.add(null);
			
			// Determine the bounds of the next block
			startIndex = blockEndIndex;
			if (blockEndIndex < lines.size())
				lines.remove(blockEndIndex);
		}
		
		// One extra "null" will have been added; Remove it
		wrappedText.remove(wrappedText.size() - 1);
		
		lines.clear();
		lines.addAll(wrappedText);
		this.clipedText = determineClipedText();
	}
	
	public void setText(String text)
	{
		this.lines.clear();
		this.clipedText = "";
		lines.addAll(getTextBlocks(text));
		
		formatText();
	}
	
	protected ArrayList<String> getTextBlocks(String text)
	{
		String[] brokenString = text.split("\n");
		ArrayList<String> blocks = new ArrayList<>();
		
		for (String block : brokenString)
		{
			blocks.add(block);
			
			// Account for new line character
			blocks.add(null);
		}
		
		// One extra "null" will have been added; Remove it
		blocks.remove(blocks.size() - 1);
		
		return blocks;
	}
	
	protected String determineClipedText()
	{
		if (lines.size() <= this.maximumDisplayLines)
			return "";
		else
		{
			//TODO test
			StringBuilder clip = new StringBuilder();
			
			int startingIndex = maximumDisplayLines;
			for (int lineIndex = startingIndex; lineIndex < lines.size(); lineIndex++)
			{
				String line = lines.get(lineIndex);
				
				if (line == null)
					clip.append("\n");
				else
					clip.append(line + " ");
			}
			
			return clip.toString();
		}
	}
	
	@Override
	protected void drawText(Graphics graphics)
	{
		graphics.setFont(font);
		graphics.setColor(fontColor);
		
		int locationIncrement = font.getHeight();
		int maxLine = (maximumDisplayLines < lines.size()) ? maximumDisplayLines
				: lines.size();
		
		int x = textBounds.x + bounds.x;
		int y = textBounds.y  + bounds.y;
		
		for (int lineIndex = 0; lineIndex < maxLine; lineIndex++)
		{
			String lineText = lines.get(lineIndex);
			
			// Disregard newLine marks
			if (lineText != null)
			{
				graphics.drawString(lineText, x, y);
				y += locationIncrement;
			}
		}
		
	}

	@Override
	protected void formatText()
	{
		this.maximumDisplayLines = calculateMaxDisplayLines();
		wrapText();
	}
	
	@Override
	protected int calculateMaxDisplayLines()
	{
		int lineHeight = font.getHeight();
		
		return textBounds.height / lineHeight;
	}
	
}

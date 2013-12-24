package edu.asu.voctec.GUI;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.newdawn.slick.TrueTypeFont;

/**
 * Support class for displaying text using OpenGL. Includes methods to scale,
 * and auto-size fonts, as well as methods for text wrapping and clipping.
 * 
 * @author Moore, Zachary
 * 
 */
public abstract class TextSupport
{
	/**
	 * Default value to search for the desired font size.
	 * 
	 * @see #getMaxScaledFontSize(Font, Rectangle, double, int)
	 * @see #getMaxScaledFontSize(Font, String, Rectangle, double, int)
	 */
	private static final int DEFAULT_SEARCH_INCREMENT = 5;
	
	/**
	 * Removes words from a string that exceed the maximum width of the string.
	 * The clipped string will, then, be able to be displayed in a box
	 * "maxWidth" pixels wide, when displayed using the provided font. If the
	 * given string is null or empty, then an array of two empty strings will be
	 * returned. This ensures that the returned text can always be drawn to the
	 * screen without null pointer exceptions.
	 * 
	 * @param font
	 *            font to consider when calculating the size of the string
	 * @param string
	 *            string to clip
	 * @param maxWidth
	 *            the clipped string will be a maximum of this many pixels wide,
	 *            when displayed using the provided font.
	 * @return an array of 2 strings. The first element is the clipped string.
	 *         The second element is the remainder of the original string (i.e.
	 *         the portion that was clipped).
	 */
	public static String[] clipString(TrueTypeFont font, String string,
			int maxWidth)
	{
		// TODO add support for words that are longer than the max width
		
		String empty = "";
		
		// Array of two empty strings. Return this as the default value.
		String[] blanks = { empty, empty };
		
		// Split string into individual words.
		String[] allWords = string.split(" ");
		ArrayList<String> clipedWords = new ArrayList<>();
		
		// Account for empty strings
		if (allWords.length < 1)
			return blanks;
		
		// Determine the number of words that can fit in the desired width.
		int wordIndex;
		int lineWidth = 0;
		int spaceWidth = font.getWidth(" ");
		for (wordIndex = 0; wordIndex < allWords.length; wordIndex++)
		{
			// Get current word, and determine its width.
			String word = allWords[wordIndex];
			int wordWidth = font.getWidth(word);
			
			// If the word will not fit within the desired width, clip the
			// string here. Otherwise, continue adding words to the line.
			if (lineWidth + wordWidth > maxWidth)
				break;
			else
			{
				// Add the new word to the list of words that fit on this line.
				clipedWords.add(word);
				lineWidth += wordWidth;
				
				// Account for white space
				lineWidth += spaceWidth;
			}
		}
		
		// Join the words that fit into a single string.
		String clippedString = StringUtils.join(clipedWords, " ");
		
		// Join all leftover words into a single string
		String leftoverString;
		if (wordIndex < allWords.length)
			leftoverString = StringUtils.join(
					Arrays.copyOfRange(allWords, wordIndex, allWords.length),
					" ");
		else
			// If there are no leftover words, make the leftovers an empty
			// string.
			leftoverString = empty;
		
		// Return a 2-element array.
		// The first element is the clipped string. The second element is the
		// leftover string (the portion that was clipped).
		return new String[] { clippedString, leftoverString };
	}
	
	/**
	 * Separates the provided string into a number of lines, each no longer than
	 * the provided line width. This method is intended to take a long body of
	 * text, and wrap it in a text display. The returned array will be in order
	 * of how the original text appears.
	 * 
	 * @param font
	 *            The font to consider while sizing the text.
	 * @param text
	 *            The text to wrap.
	 * @param lineWidth
	 *            The maximum length (in pixels) that each line can be.
	 * @return An array of strings, representing all lines of the wrapped text.
	 */
	public static ArrayList<String> wrapText(TrueTypeFont font, String text,
			int lineWidth)
	{
		// TODO test
		// TODO add support for newline characters
		ArrayList<String> lines = new ArrayList<>();
		String remainder = text;
		
		do
		{
			// Clip the string to obtain a line no longer than the given
			// lineWidth.
			String[] clipResults = clipString(font, remainder, lineWidth);
			lines.add(clipResults[0]);
			remainder = clipResults[1];
		} while (!remainder.isEmpty());
		
		return lines;
	}
	
	public static Font getMaxScaledFont(Font font, String text, Rectangle bounds)
	{
		return getMaxScaledFont(font, text, bounds, DEFAULT_SEARCH_INCREMENT);
	}
	
	public static Font getMaxScaledFont(Font font, String text,
			Rectangle bounds, int searchIncrement)
	{
		return font.deriveFont((float) getMaxScaledFontSize(font, text, bounds,
				searchIncrement));
	}
	
	public static Font getMaxVerticalScaledFont(Font font, Rectangle bounds)
	{
		return getMaxVerticalScaledFont(font, bounds, DEFAULT_SEARCH_INCREMENT);
	}
	
	public static Font getMaxVerticalScaledFont(Font font, Rectangle bounds,
			int searchIncrement)
	{
		return font.deriveFont((float) getMaxVerticalScaledFontSize(font,
				bounds, searchIncrement));
	}
	
	public static int getMaxScaledFontSize(Font font, String text,
			Rectangle bounds, int searchIncrement)
	{
		// TODO optimize
		System.out.println("Scaling font...");
		double maxWidth = bounds.width;
		double maxHeight = bounds.height;
		
		// Scale the font to fit the current bounds
		Font scaledFont = font;
		int currentSize = font.getSize();
		TrueTypeFont fontContainer = new TrueTypeFont(scaledFont, false);
		System.out.println("Size: " + currentSize);
		
		double textWidth = fontContainer.getWidth(text);
		double textHeight = fontContainer.getHeight(text);
		
		double horizontalScale = maxWidth / textWidth;
		double verticalScale = maxHeight / textHeight;
		
		double trueScale = (horizontalScale < verticalScale) ? horizontalScale
				: verticalScale;
		
		trueScale = trueScale * 0.98;
		currentSize = (int) (currentSize * trueScale);
		
		System.out.println("Size: " + currentSize);
		System.out.println("Scaling Complete.");
		return currentSize;
	}
	
	// TODO REMOVE
	/*
	 * public static int getMaxScaledFontSize(Font font, String text, Rectangle
	 * bounds, int searchIncrement) { // TODO remove borderScale double
	 * borderScale = 1.0;
	 * 
	 * // TODO optimize System.out.println("Scaling font..."); int maxWidth =
	 * (int) (bounds.width * borderScale); int maxHeight = (int) (bounds.height
	 * * borderScale);
	 * 
	 * // Scale the font to fit the current bounds Font scaledFont = font; int
	 * currentSize = font.getSize(); TrueTypeFont fontContainer = new
	 * TrueTypeFont(scaledFont, false); int textWidth =
	 * fontContainer.getWidth(text); int textHeight =
	 * fontContainer.getHeight(text);
	 * 
	 * // Increase the size of the font until either width or height is larger
	 * // than the max bounds while (!(textWidth >= maxWidth || textHeight >=
	 * maxHeight)) { // Increase size currentSize += searchIncrement; scaledFont
	 * = font.deriveFont((float) currentSize); fontContainer = new
	 * TrueTypeFont(scaledFont, false);
	 * 
	 * // Recalculate text dimensions textWidth = fontContainer.getWidth(text);
	 * textHeight = fontContainer.getHeight(text);
	 * 
	 * System.out.println("Size: " + currentSize); }
	 * 
	 * // Shrink the size of the font until both width and height fit within //
	 * the max bounds searchIncrement = -1; while (textWidth >= maxWidth ||
	 * textHeight >= maxHeight) { // Decrease size currentSize +=
	 * searchIncrement; scaledFont = font.deriveFont((float) currentSize);
	 * fontContainer = new TrueTypeFont(scaledFont, false);
	 * 
	 * // Recalculate text dimensions textWidth = fontContainer.getWidth(text);
	 * textHeight = fontContainer.getHeight(text); System.out.println("Size: " +
	 * currentSize); }
	 * 
	 * System.out.println("Scaling Complete."); return currentSize; }
	 */
	
	public static int getMaxScaledFontSize(Font font, String text,
			Rectangle bounds)
	{
		return getMaxScaledFontSize(font, text, bounds,
				DEFAULT_SEARCH_INCREMENT);
	}
	
	public static int getMaxVerticalScaledFontSize(Font font, Rectangle bounds)
	{
		return getMaxVerticalScaledFontSize(font, bounds,
				DEFAULT_SEARCH_INCREMENT);
	}
	
	public static int getMaxVerticalScaledFontSize(Font font, Rectangle bounds,
			int searchIncrement)
	{
		// TODO remove borderScale
		double borderScale = 1.0;
		
		// TODO test
		System.out.println("Scaling font (vertical)...");
		int maxHeight = (int) (bounds.height * borderScale);
		
		// Scale the font to fit the current bounds
		Font scaledFont = font;
		int currentSize = font.getSize();
		TrueTypeFont fontContainer = new TrueTypeFont(scaledFont, false);
		int textHeight = fontContainer.getHeight();
		
		// Increase the size of the font until either width or height is larger
		// than the max bounds
		while (textHeight < maxHeight)
		{
			// Increase size
			currentSize += searchIncrement;
			scaledFont = font.deriveFont((float) currentSize);
			fontContainer = new TrueTypeFont(scaledFont, false);
			
			// Recalculate text height
			textHeight = fontContainer.getHeight();
			
			System.out.println("Size: " + currentSize);
		}
		
		// Shrink the size of the font until both width and height fit within
		// the max bounds
		while (textHeight >= maxHeight)
		{
			// Decrease size
			currentSize--;
			scaledFont = font.deriveFont((float) currentSize);
			fontContainer = new TrueTypeFont(scaledFont, false);
			
			// Recalculate text dimensions
			textHeight = fontContainer.getHeight();
			System.out.println("Size: " + currentSize);
		}
		
		System.out.println("Scaling Complete.");
		return currentSize;
	}
}

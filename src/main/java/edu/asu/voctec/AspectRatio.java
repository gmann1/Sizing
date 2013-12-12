package edu.asu.voctec;

import java.awt.Dimension;

public enum AspectRatio
{
	x16_9 (16, 9),
	x4_3 (4, 3);
	
	/**
	 * If ASPECT_RATIO_MARGIN > 0, then each AspectRatio can be used to
	 * represent dimensions that are *close* to the desired AspectRatio, 
	 * using this constant as a MOE. For instance, if ASPECT_RATIO_MARGIN
	 * is 5, then an aspect ratio of 4:3 can be used to represent the dimension
	 * 800x600, as well as 805x605 and 795x600, etc. This constant should 
	 * generally be 0, unless imprecise image resizing is being done outside 
	 * of this class.
	 */
	public static final int ASPECT_RATIO_MARGIN = 0;
	
	public final int width;
	public final int height;
	
	private AspectRatio(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public static AspectRatio getAspectRatio(int width, int height) 
			throws ResolutionNotSupportedException
	{
		AspectRatio matchingAspectRatio = null;
		
		//TODO optimize search
		for(AspectRatio aspectRatio : AspectRatio.values())
		{
			int scale;
			
			// Ensure width corresponds to aspect ratio
			if ((width % aspectRatio.width) <= ASPECT_RATIO_MARGIN)
			{
				//TODO account for case in which height = ratio - MARGIN and 
				//TODO_CONT		width = ratio + MARGIN
				// Determine the constant that is modified by the aspect ratio
				// to obtain the desired resolution
				scale = width / aspectRatio.width;
				
				// Ensure height corresponds to aspect ratio and scale
				if ((height % aspectRatio.height) <= ASPECT_RATIO_MARGIN
						&& (height / aspectRatio.height) == scale)
				{
					// Found matching aspect ratio; stop searching
					matchingAspectRatio = aspectRatio;
					break;
				}
			}
		}
		
		// If a matchingAspectRatio was not found, this resolution is not supported
		if(matchingAspectRatio == null)
			throw new ResolutionNotSupportedException(width, height);
		else
			return matchingAspectRatio;
		
	}
	
	public static AspectRatio getAspectRatio(Dimension dimension) 
			throws ResolutionNotSupportedException
	{
		return getAspectRatio(dimension.width, dimension.height);
	}
	
	//TODO move to exceptions package
	public static class ResolutionNotSupportedException extends Exception
	{
		private static final long serialVersionUID = 1548486820456150649L;
		
		public ResolutionNotSupportedException(int width, int height)
		{
			super("This resolution is not supported: " + width + "x" + height);
		}
	}
}

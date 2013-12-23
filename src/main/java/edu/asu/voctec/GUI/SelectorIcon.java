package edu.asu.voctec.GUI;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SelectorIcon extends BasicComponent
{
	protected final String	name;
	protected final String description;
	protected final int id;
	
	public SelectorIcon(Image image, int x, int y, String name, String description, int id)
	{
		super(image, x, y);
		this.name = name;
		this.description = description;
		this.id = id;
	}
	
	public SelectorIcon(Image image, int x, int y, String name, int id)
	{
		this(image, x, y, name, null, id);
	}
	
	public SelectorIcon(String imagePath, int x, int y, String name, int id) throws SlickException
	{
		this(new Image(imagePath), x, y, name, null, id);
	}
	
	public SelectorIcon(String imagePath, String name, int id) throws SlickException
	{
		this(new Image(imagePath), 0, 0, name, null, id);
	}
	
	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public int getId()
	{
		return id;
	}
	
}

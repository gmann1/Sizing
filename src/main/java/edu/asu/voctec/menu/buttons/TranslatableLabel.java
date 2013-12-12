package edu.asu.voctec.menu.buttons;

import java.awt.Font;
import java.awt.Rectangle;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

import edu.asu.voctec.Main;

public class TranslatableLabel extends TextField
{
	public static final Font DEFAULT_AWT_FONT = new Font("Times New Roman", Font.BOLD, 24);
	protected final LabelName label;
	
	public TranslatableLabel(LabelName label, GUIContext container, org.newdawn.slick.Font font, 
			int x, int y, int width, int height)
	{
		super(container, font, x, y, width, height);
		super.setCursorVisible(false);
		this.label = label;
		this.setText(label.getTranslation());
	}
	
	public TranslatableLabel(LabelName label, GUIContext container, org.newdawn.slick.Font font, 
			Rectangle labelBounds)
	{
		this(label, container, font, labelBounds.x, labelBounds.y, labelBounds.width, labelBounds.height);
	}
	
	public TranslatableLabel(LabelName label, GUIContext container, int x, int y,
			int width, int height)
	{
		this(label, container, new TrueTypeFont(DEFAULT_AWT_FONT, false), x, y, width, height);
	}
	
	public TranslatableLabel(LabelName label, GUIContext container, Rectangle labelBounds)
	{
		this(label, container, labelBounds.x, labelBounds.y, labelBounds.width, labelBounds.height);
	}
	
	public TranslatableLabel(LabelName label, Rectangle labelBounds)
	{
		this(label, Main.getGameContainer(), labelBounds.x, labelBounds.y, labelBounds.width, labelBounds.height);
		
		// TODO Test that gameContainer is consistent
	}
	
	public void updateText()
	{
		//TODO change text based on current dictionary
	}
	
}

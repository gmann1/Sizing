package edu.asu.voctec.menu.buttons;

import java.awt.Font;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.Main;
import edu.asu.voctec.Translatable;

public class TranslatableLabel extends TextField implements Translatable, GameDefaults
{
	public static final Font DEFAULT_AWT_FONT = new Font("Meiryo", Font.BOLD, Fonts.FONT_SIZE);
	public static final TrueTypeFont DEFAULT_TRUE_TYPE_FONT = 
			new TrueTypeFont(DEFAULT_AWT_FONT, Fonts.ANTI_ALLIAS, Dictionary.getExtraCharacters());
	
	protected final LabelName label;
	
	public TranslatableLabel(LabelName label, GUIContext container, org.newdawn.slick.Font font, 
			int x, int y, int width, int height)
	{
		super(container, font, x, y, width, height);
		super.setCursorVisible(false);
		super.deactivate();
		this.label = label;
		this.setBackgroundColor(null);
		this.setBorderColor(null);
		this.setText(label.getTranslation());
		this.setTextColor(Color.magenta);
	}
	
	public TranslatableLabel(LabelName label, GUIContext container, org.newdawn.slick.Font font, 
			Rectangle labelBounds)
	{
		this(label, container, font, labelBounds.x, labelBounds.y, labelBounds.width, labelBounds.height);
	}
	
	public TranslatableLabel(LabelName label, GUIContext container, int x, int y,
			int width, int height)
	{
		this(label, container, DEFAULT_TRUE_TYPE_FONT, x, y, width, height);
	}
	
	public TranslatableLabel(LabelName label, GUIContext container, Rectangle labelBounds)
	{
		this(label, container, labelBounds.x, labelBounds.y, labelBounds.width, labelBounds.height);
	}
	
	public TranslatableLabel(LabelName label, TrueTypeFont font, Rectangle labelBounds)
	{
		this(label, Main.getGameContainer(), font, labelBounds.x, labelBounds.y, labelBounds.width, labelBounds.height);
	}
	
	public void updateTranslation()
	{
		this.setText(label.getTranslation());
	}
	
}

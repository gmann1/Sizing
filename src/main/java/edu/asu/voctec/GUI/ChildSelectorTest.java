package edu.asu.voctec.GUI;

import org.newdawn.slick.SlickException;

public class ChildSelectorTest<T extends SelectorIcon> extends Selector<T>
{
	public class NewListener extends RightArrowListener
	{
		@Override
		protected void actionPerformed()
		{
			super.actionPerformed();
		}
	}

	public ChildSelectorTest(int x, int y)
			throws SlickException
	{
		super(x, y, false);
		this.addActionListener(new NewListener());
	}
	
}

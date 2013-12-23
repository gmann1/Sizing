package edu.asu.voctec.cdmg;

import org.newdawn.slick.SlickException;

import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.cdmg.CDPart1;
public class ChildSelectorTest<T extends SelectorIcon> extends Selector<T>
{
	 public class RightListener extends RightArrowListener
	 {
  @Override
		  protected void actionPerformed()
		  {
			   super.actionPerformed();
			   try {
			   CDPart1.changeImage(true);
			   } catch (SlickException e) {
				
				e.printStackTrace();
			}
		  }
	 }
	 public class LeftListener extends LeftArrowListener
	 {
  @Override
		  protected void actionPerformed()
		  {
			   super.actionPerformed();
			   try {
			   CDPart1.changeImage(false);
			   } catch (SlickException e) {
				
				e.printStackTrace();
			}
		  }
	 }

	 public ChildSelectorTest(int x, int y)
	   throws SlickException
		 {
			  super(x, y, false);
			  this.addActionListener(new RightListener());
			  this.addActionListener(new LeftListener());
		 }
		 
}
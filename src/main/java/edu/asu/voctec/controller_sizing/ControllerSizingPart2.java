package edu.asu.voctec.controller_sizing;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;

public class ControllerSizingPart2 extends GUI
{
	public static final String GameBackground = "resources/default/img/minigames/ControllerSizing/Step1Background.png";
	public static final String OriginalControllerPath = "resources/default/img/minigames/ControllerSizing/NormalController.png";
	public static final String ChosenControllerPath = "resources/default/img/minigames/ControllerSizing/ChosenController.png";
	public static final String IncorrectControllerPath = "resources/default/img/minigames/ControllerSizing/IncorrectController.png";
	public static final String CorrectControllerPath = "resources/default/img/minigames/ControllerSizing/CorrectController.png";
	public static final String DoneButtonPath = "resources/default/img/minigames/ControllerSizing/LargeDoneButton.png";
	public static final String ContinueButtonPath = "resources/default/img/minigames/ControllerSizing/ContinueButton.png";
	public static final String InvisibleContinueButtonPath = "resources/default/img/minigames/ControllerSizing/invisibleContinueButton.png";
	
	public static final String instructionMessage = "Select the most cost-effective controller that best satisfy the minimum power current.";
	public static final String correctSolutionMessage = "This is the correct answer.\nPress the Continue Button to advance to the next step.";
	public static final String smallerControllerMessage = "This is not the correct choice.\nThis controller does not satisfy the minimum power current.";
	public static final String largerControllerMessage = "This is not the correct choice.\nThere is a more cost-effective controller.";
	
	private int[] controllersValues = {13,15,20};
	private int solution = 15;
	private double minimumPowerCurrent = 13.5;
	
	private List<BasicComponent> controllers = new ArrayList<BasicComponent>();
	private static BasicComponent chosenController;
	private BasicComponent continueButton;
	private Image OriginalControllerImage, ChosenControllerImage, IncorrectControllerImage, CorrectControllerImage, ContinueButtonImage;
	private boolean stepCompleted = false;
	private TextAreaX instructions;
	private int doneButtonCounter = 0;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(GameBackground);
		
		OriginalControllerImage = new Image(OriginalControllerPath);
		ChosenControllerImage = new Image(ChosenControllerPath);
		IncorrectControllerImage = new Image(IncorrectControllerPath);
		CorrectControllerImage = new Image(CorrectControllerPath);
		ContinueButtonImage = new Image(ContinueButtonPath);
		
		
		
		controllers.add(new BasicComponent(OriginalControllerPath, 100, 250, 110, 100));
		controllers.add(new BasicComponent(OriginalControllerPath, 350, 250, 110, 100));
		controllers.add(new BasicComponent(OriginalControllerPath, 600, 250, 110, 100));
		
		for(BasicComponent controller :controllers)
		{
			controller.addActionListener(new ControllerListener());
			this.addComponent(controller);
		}
		
		initializeText();
		
		Button backButton = new Button(new Image("resources/default/img/buttons/backButton.png"), 2, 2,
			    new Rectangle(1, 1, 40, 40), "Back");
		backButton.setFontColor(Color.blue);
		backButton.addActionListener(new TransitionButtonListener(ControllerSizingIntroScreen.class));
		
		Button doneButton = new Button(new Image(DoneButtonPath), 630, 535,
			    new Rectangle(660, 350, 83, 30), "");
		doneButton.addActionListener(new DoneButtonListener());
		
		continueButton = new BasicComponent(new Image(InvisibleContinueButtonPath), 630, 475);
		continueButton.addActionListener(new ContinueButtonListener());
		
		this.addComponent(backButton);
		this.addComponent(doneButton);
		this.addComponent(continueButton);
	}
	
	private void initializeText()
	{
		Rectangle textLocation = new Rectangle( 50, 100, 500, 500);
		TextField minimumPowerCurrentText = new TextField(textLocation, 0.95f,
				"Minimum Power Current: "+minimumPowerCurrent+" A",
				TextDisplay.FormattingOption.FIT_TEXT);
		minimumPowerCurrentText.setFontColor(Color.white);
		this.addComponent(minimumPowerCurrentText);
		
		for(int index = 0; index<controllers.size(); index++)
		{
			BasicComponent controller = controllers.get(index);
			
			Rectangle textLocation2 = new Rectangle( controller.getX()+20, controller.getY()+110, 60, 60);
			TextField controllerValue = new TextField(textLocation2, 0.95f,
					controllersValues[index]+" A",
					TextDisplay.FormattingOption.FIT_TEXT);
			controllerValue.setFontColor(Color.white);
			this.addComponent(controllerValue);
		}
		
		Rectangle textLocation3 = new Rectangle(20, 480, 600, 600);
		instructions = new TextAreaX(textLocation3, 0.95f,
				instructionMessage);
		instructions.setFontColor(Color.white);
		instructions.setFontSize(20);
		this.addComponent(instructions);
	}
	
	public class ControllerListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(chosenController == associatedComponent)
			{
				chosenController.setCurrentImage(OriginalControllerImage, true);
				chosenController = null;
			}
			else
			{
				if(chosenController != null)
				{
					chosenController.setCurrentImage(OriginalControllerImage, true);
					chosenController = null;
				}
				chosenController = (BasicComponent) associatedComponent;
				chosenController.setCurrentImage(ChosenControllerImage, true);
			}
		}
	}
	
	public class DoneButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(chosenController != null)
			{
				if(controllersValues[controllers.indexOf(chosenController)] == solution)
				{
					chosenController.setCurrentImage(CorrectControllerImage, true);
					instructions.setText(correctSolutionMessage);
					instructions.setFontColor(new Color(140,198,63));
					stepCompleted = true;
					continueButton.setCurrentImage(ContinueButtonImage, true);
				}
				else
				{
					chosenController.setCurrentImage(IncorrectControllerImage, true);
					if(controllersValues[controllers.indexOf(chosenController)] > solution)
					{
						instructions.setText(largerControllerMessage);
						instructions.setFontColor(new Color(237,28,36));
					}
					else
					{
						instructions.setText(smallerControllerMessage);
						instructions.setFontColor(new Color(237,28,36));
					}
					doneButtonCounter++;
					System.out.println("Number of tries: "+doneButtonCounter);
				}
			}
		}
	}
	
	public class ContinueButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			if(stepCompleted)
				Game.getCurrentGame().enterState(ControllerSizingPart1.class);
		}
	}

}
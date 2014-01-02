package edu.asu.voctec.information;

import edu.asu.voctec.menu.buttons.TransitionButton;

public class ScenarioData
{
	protected TaskData entryStep;
	protected TaskData[] tasks;
	protected TransitionButton scenarioIcon;
	
	public ScenarioData(TaskData entryStep, TaskData[] tasks,
			TransitionButton scenarioIcon)
	{
		super();
		this.entryStep = entryStep;
		this.tasks = tasks;
		this.scenarioIcon = scenarioIcon;
	}

	public TaskData getEntryStep()
	{
		return entryStep;
	}

	public void setEntryStep(TaskData entryStep)
	{
		this.entryStep = entryStep;
	}

	public TaskData[] getTasks()
	{
		return tasks;
	}

	public void setTasks(TaskData[] tasks)
	{
		this.tasks = tasks;
	}

	public TransitionButton getScenarioIcon()
	{
		return scenarioIcon;
	}

	public void setScenarioIcon(TransitionButton scenarioIcon)
	{
		this.scenarioIcon = scenarioIcon;
	}
	
	public boolean isEntryComplete()
	{
		return entryStep.isComplete();
	}
}

package alexpansion.manabalance.entity;

import alexpansion.manabalance.helper.LoopTask;
import alexpansion.manabalance.helper.TaskManager;

public class Generator extends Construct {

	public Generator(Mage owner, TaskManager manager) {
		super("Generator", owner, 100, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	void onActivate() {
		LoopTask task = new LoopTask("Generate", new Generate(owner), 20);
		TaskManager.addTask(task);
	}
	

	@Override
	void onDeactivate() {
		LoopTask task = new LoopTask("Generate", new Generate(owner), 20);
		TaskManager.removeTask(task);
	}
	
	private final class Generate implements Runnable{
		private Mage owner;
		
		public Generate(Mage owner){
			this.owner = owner;
		}
		
		public void run(){
			owner.addMana(1);
		}
	}

}

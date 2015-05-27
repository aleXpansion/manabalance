/**
 * 
 */
package alexpansion.manabalance.helper;

/**
 * @author buzzr
 *
 */
public class LoopTask implements Runnable {

	Runnable task;
	int delay;
	int remaining;
	private String name;

	public LoopTask(String name, Runnable task, int delay) {
		this.task = task;
		this.delay = delay;
		this.name = name;
		remaining = delay;
	}

	@Override
	public void run() {
		if (remaining <= 0) {
			task.run();
			remaining = delay;
		}
		else{
			remaining--;
		}

	}

	public int getDelay() {
		return delay;
	}
	
	public int getRemaining(){
		return remaining;
	}
	
	public String getName(){
		return name;
	}

}

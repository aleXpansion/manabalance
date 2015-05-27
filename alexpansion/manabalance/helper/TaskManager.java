package alexpansion.manabalance.helper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {

	static ArrayList<LoopTask> tasks = new ArrayList<LoopTask>();
	private static Timer timer = new Timer();
	static TickTask tick;
	static private boolean active = false;

	public static void init() {
		TaskManager inst = new TaskManager();
		tick = inst.new TickTask();
	}

	public static void start() {
		timer.scheduleAtFixedRate(tick, 0, 50);
		active = true;
	}

	public static void stop() {
		System.out.println("Stopping");
		timer.cancel();
		active = false;
	}

	public static void addTask(LoopTask task) {
		tasks.add(task);
		//DEBUG: System.out.println("Task \"" + task.getName() + "\" added.");
		if (!active) {
			start();
		}
		//printTasks();
	}

	public static void removeTask(LoopTask target) {
		String name = target.getName();
		removeTask(name);
	}

	public static void removeTask(String name) {
		for (int i = 0; i < tasks.size(); i++) {
			LoopTask task = tasks.get(i);
			if (task.getName() == name) {
				//System.out.println("Task \"" + task.getName() + "\" removed.");
				tasks.remove(i);
				if (tasks.size() == 0) {
					stop();
				}
				return;
			}
		}
		System.out.println("Task \"" + name + "\" not found.");
		//printTasks();
	}

	public static void run() {
		for (int i = 0; i < tasks.size(); i++) {
			tasks.get(i).run();
		}
	}

	private final class TickTask extends TimerTask {

		public TickTask() {
		}

		@Override
		public void run() {
			TaskManager.run();
		}
	}

	@SuppressWarnings("unused")
	private static void printTasks() {
		System.out.println("Tasks:");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println(i + ": " + tasks.get(i).getName());
		}
	}

}

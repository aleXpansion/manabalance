package alexpansion.manabalance.controllers;

import alexpansion.manabalance.frames.BattleFrame;
import alexpansion.manabalance.helper.LoopTask;
import alexpansion.manabalance.helper.TaskManager;
import java.util.ArrayList;
import alexpansion.manabalance.entity.*;
import alexpansion.manabalance.exceptions.*;
import alexpansion.manabalance.moves.AttackType;

public class BattleController {

	private BattleFrame frame;
	private Mage player;
	private Enemy enemy;
	private ArrayList<ActiveAttack> attacks;
	// final Timer timer;
	private int fieldLength = 10;

	public BattleController(Mage player) {

		// initialize variables
		this.player = player;
		enemy = new Enemy("not me", 5);
		frame = new BattleFrame(this);
		attacks = new ArrayList<ActiveAttack>(1);

		TaskManager
				.addTask(new LoopTask("BattleLoop", new BattleLoopTask(), 0));

		// set up frame
		frame.setNames(player.getName(), enemy.getName());

		// activate frame
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	public BattleController() {
		this(new Mage("me", 450, 500, 1));
	}

	public void battleLoop() {
		updateAttacks();
		enemy.move(this);
		if (enemy.getManaLevel() <= 0) {
			System.out.println("You Won!");
			endGame();
		} else if (player.getManaLevel() <= 0) {
			System.out.println("You Lost! :(");
			endGame();
		}
		player.update();
		enemy.update();
		frame.update();
	}

	public void attack(int attackNum, Mage target, Mage source) {
		AttackType type = source.getAttacks()[attackNum];
		ActiveAttack newAttack = new ActiveAttack(type, target);
		if (source.getManaLevel() > type.getCost()) {
			source.damage(type.getCost());
			attacks.add(newAttack);
		} else {
			System.out.println("Not Enough Mana!");
		}
	}

	public void updateAttacks() {
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (int i = 0; i < attacks.size(); i++) {
			ActiveAttack attack = attacks.get(i);
			int attackLoc = attack.move();
			if (attackLoc >= fieldLength) {
				attack.getTarget().damage(attack.getType().getPower() * 5);
				toRemove.add(i);
			}
		}
		int offset = 0;
		for (int i : toRemove) {
			attacks.remove(i - offset);
			offset++;
		}
	}

	public void endGame() {
		TaskManager.removeTask("BattleLoop");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		} finally {
			frame.dispose();
		}
	}

	public void activateShield(Mage mage, int shieldNum, int shieldSlot) {
		try {
			mage.shieldUp(shieldNum, shieldSlot);
		} catch (SlotFullException e) {
			System.out.println("Slot is full!");
		} catch (InsufficientManaException e) {
			System.out.println("Not enough mana!");
		}
	}

	public void deactivateShield(Mage mage, int shieldSlot) {
		mage.shieldDown(shieldSlot);
	}

	public Mage getPlayer() {
		return player;
	}

	public Mage getEnemy() {
		return enemy;
	}

	public ArrayList<ActiveAttack> getAttacks() {
		return attacks;
	}

	// </editor-fold>

	private final class BattleLoopTask implements Runnable {

		public BattleLoopTask() {
		}

		public void run() {
			battleLoop();
		}
	}
}

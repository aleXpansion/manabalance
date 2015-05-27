package alexpansion.manabalance.entity;

import alexpansion.manabalance.enums.ConstructType;
import alexpansion.manabalance.exceptions.*;
import alexpansion.manabalance.helper.TaskManager;
import alexpansion.manabalance.moves.AttackType;
import alexpansion.manabalance.moves.ShieldType;

/**
 *
 * @author Danny
 */
public class Mage {

	private String name;
	private AttackType[] attacks;
	int manaLevel;
	private int manaMax;
	private int level;
	private int manaTick;
	private ShieldType[] shields;
	private ActiveShield[] shieldSlots;
	int armor;
	TaskManager manager;

	public Mage(String name) {
		this(name, 80, 100, 5);
	}

	public Mage(String name, int level) {
		this(name, 16 * level, 20 * level, level);
	}

	public Mage(String name, int manaLevel, int manaMax, int level) {
		this.name = name;
		this.manaLevel = manaLevel;
		this.manaMax = manaMax;
		this.level = level;
		attacks = new AttackType[5];
		attacks[0] = new AttackType("basic");
		attacks[1] = new AttackType("fast", 2, 9, 9);
		attacks[2] = new AttackType("strong", 10, 5, 1);
		attacks[3] = new AttackType("basic2");
		attacks[4] = new AttackType("basic3");
		shields = new ShieldType[3];
		shields[0] = new ShieldType();
		shields[1] = new ShieldType("Strong", 20, 4);
		shields[2] = new ShieldType("Weak", 5, 3);
		manaTick = 0;
		shieldSlots = new ActiveShield[3];
		manager = new TaskManager();
	}

	public void damage(int amt) {
		if (armor > 0) {
			manaLevel -= Math.ceil(amt * ((100 - armor) / 100));
		} else {
			manaLevel -= amt;
		}
	}

	public void build(ConstructType type) {
		if (manaLevel > type.getCost()) {
			Construct construct;
			switch (type) {
			case Generator:
				construct = new Generator(this, manager);
				break;
			default:
				return;
			}
			manaLevel -= construct.getInitCost();
			construct.activate();
			System.out.println(construct.getName() + " built.");
		}
	}

	private void updateArmor() {
		int strength;
		for (ActiveShield shield : shieldSlots) {
			if (shield != null) {
				strength = shield.getType().getStrength();
				armor += strength;
			}
		}
	}

	public void addMana(int amt) {
		if (manaLevel + amt <= manaMax) {
			manaLevel += amt;
		}
	}

	public void update() {
		manaTick++;
		int manaUpLevel = 1000 / level;
		if (manaTick >= manaUpLevel) {
			addMana(1);
			manaTick -= manaUpLevel;
		}
	}

	public void shieldUp(int shieldNum, int shieldSlot)
			throws SlotFullException, InsufficientManaException {
		if (shieldSlots[shieldSlot] != null) {
			throw new SlotFullException();
		} else if (manaLevel < shields[shieldNum].getCastCost()) {
			throw new InsufficientManaException();
		} else {
			System.out.println("Activating shield");
			manaLevel -= shields[shieldNum].getCastCost();
			shieldSlots[shieldSlot] = new ActiveShield(shields[shieldNum], this);
		}
		updateArmor();
	}

	public void shieldDown(int shieldSlot) {
		if (shieldSlots[shieldSlot] != null) {
			System.out.println("Deactivating Shield");
			shieldSlots[shieldSlot] = null;
		}
		updateArmor();
	}

	// <editor-fold defaultstate="collapsed" desc="getter methods">
	public String getName() {
		return name;
	}

	public AttackType[] getAttacks() {
		return attacks;
	}

	public int getManaLevel() {
		return manaLevel;
	}

	public int getManaMax() {
		return manaMax;
	}

	public AttackType getAttack(int attackNum) {
		return attacks[attackNum];
	}

	public TaskManager getManager() {
		return manager;
	}
	// </editor-fold>
}

package alexpansion.manabalance.entity;

import alexpansion.manabalance.exceptions.*;
import alexpansion.manabalance.moves.Attack;
import alexpansion.manabalance.moves.Shield;

/**
 *
 * @author Danny
 */
public class Mage {

    private String name;
    private Attack[] attacks;
    int manaLevel;
    private int manaMax;
    private int level;
    private int manaTick;
    private Shield[] shields;
    private ActiveShield[] shieldSlots;
    int armor;

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
        attacks = new Attack[5];
        attacks[0] = new Attack("basic");
        attacks[1] = new Attack("fast", 2, 9, 9);
        attacks[2] = new Attack("strong", 10, 5, 1);
        attacks[3] = new Attack("basic2");
        attacks[4] = new Attack("basic3");
        shields = new Shield[3];
        shields[0] = new Shield();
        shields[1] = new Shield("Strong", 20, 4);
        shields[2] = new Shield("Weak", 5, 3);
        manaTick = 0;
        shieldSlots = new ActiveShield[3];
    }

    public void damage(int amt) {
        if (armor > 0) {
            manaLevel -= Math.ceil(amt * ((100 - armor)/100));
        } else {
            manaLevel -= amt;
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
        int manaUpLevel = 100 / level;
        if (manaTick >= manaUpLevel) {
            addMana(1);
            manaTick -= manaUpLevel;
        }
    }

    public void shieldUp(int shieldNum, int shieldSlot) throws SlotFullException, InsufficientManaException {
        if (shieldSlots[shieldSlot] != null) {
            throw new SlotFullException();
        } else if (manaLevel < shields[shieldNum].getCastCost()) {
            throw new InsufficientManaException();
        } else {
            manaLevel -= shields[shieldNum].getCastCost();
            shieldSlots[shieldSlot] = new ActiveShield(shields[shieldNum], this);
        }
        updateArmor();
    }

    public void shieldDown(int shieldSlot) {
        if (shieldSlots[shieldSlot] != null) {
            shieldSlots[shieldSlot] = null;
        }
        updateArmor();
    }

    //<editor-fold defaultstate="collapsed" desc="getter methods">
    public String getName() {
        return name;
    }

    public Attack[] getAttacks() {
        return attacks;
    }

    public int getManaLevel() {
        return manaLevel;
    }

    public int getManaMax() {
        return manaMax;
    }

    public Attack getAttack(int attackNum) {
        return attacks[attackNum];
    }
    //</editor-fold>
}

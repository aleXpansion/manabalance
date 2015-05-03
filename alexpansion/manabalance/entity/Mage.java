package alexpansion.manabalance.entity;

import alexpansion.manabalance.moves.Attack;

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

    public Mage(String name) {
        this(name, 100, 100, 1);
    }

    public Mage(String name, int level) {
        this(name, 80, 100, level);
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
        manaTick = 0;
    }

    public void damage(int amt) {
        manaLevel -= amt;
    }

    public void addMana(int amt) {
        if (manaLevel + amt <= manaMax) {
            manaLevel += amt;
        }
    }

    public void update() {
        manaTick++;
        int manaUpLevel = 100/level;
        if(manaTick>=manaUpLevel){
            addMana(1);
            manaTick -= manaUpLevel;
        }
    }

    //getter functions

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

}

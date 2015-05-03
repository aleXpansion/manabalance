package alexpansion.manabalance.controllers;

import alexpansion.manabalance.frames.BattleFrame;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import alexpansion.manabalance.entity.*;
import alexpansion.manabalance.moves.Attack;

public class BattleController {

    private BattleFrame frame;
    private Mage player;
    private Enemy enemy;
    private ArrayList<ActiveAttack> attacks;
    final Timer timer;
    private int fieldLength = 100;

    public BattleController() {

        //initialize variables        
        player = new Mage("me",5);
        enemy = new Enemy("not me",5);
        frame = new BattleFrame(this);
        attacks = new ArrayList(1);

        timer = new Timer();
        final TickTask add = new TickTask();
        timer.scheduleAtFixedRate(add, 0, 50);

        //set up frame
        frame.setNames(player.getName(), enemy.getName());

        //activate frame
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });
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

    /*public void clicked(JButton button){
        
     }*/
    public void attack(int attackNum, Mage target, Mage source) {
        Attack type = source.getAttacks()[attackNum];
        ActiveAttack newAttack = new ActiveAttack(type, target);
        if (source.getManaLevel() > type.getCost()) {
            source.damage(type.getCost());
            attacks.add(newAttack);
        } else {
            System.out.println("Not Enough Mana!");
        }
    }

    public void updateAttacks() {
        ArrayList<Integer> toRemove = new ArrayList();
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
        timer.cancel();
        timer.purge();
        try{
        Thread.sleep(2000);
        }
        catch(InterruptedException e){}
        finally{
            frame.dispose();
        }
    }
    
    public void activateShield(Mage mage, int ShieldNum){
        
    }
    
    public void deactivateShield(Mage mage, int ShieldNum){
        
    }

    public Mage getPlayer() {
        return player;
    }

    public Mage getEnemy() {
        return enemy;
    }

    public ArrayList getAttacks() {
        return attacks;
    }

    private final class TickTask extends TimerTask {

        public TickTask() {
        }

        @Override
        public void run() {
            battleLoop();
        }
    }
}

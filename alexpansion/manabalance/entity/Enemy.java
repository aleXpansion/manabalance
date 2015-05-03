/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexpansion.manabalance.entity;

import alexpansion.manabalance.controllers.BattleController;
import java.util.Random;

/**
 *
 * @author buzzr
 */
public class Enemy extends Mage{
    
    
    private Random rng;
    
    public Enemy(String name){
        super(name);
        init();
    }
    
    public Enemy(String name,int level){
        super(name,level);
        init();
    }
    
    private void init(){
        rng = new Random();
    }
    
    
    public void move(BattleController battle){
        if (rng.nextInt(6400) <= Math.pow(manaLevel-20,2)) {
            int randomInt = rng.nextInt(3);
            if (randomInt < 5) {
                battle.attack(randomInt, battle.getPlayer(), this);
            }
        }
    }
}

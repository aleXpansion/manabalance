package alexpansion.manabalance.entity;

import alexpansion.manabalance.moves.AttackType;

/**
 *
 * @author Danny
 */


public class ActiveAttack{
    AttackType type;
    int progress;
    int toMove;
    Mage target;
    
    public ActiveAttack(AttackType newType,Mage target){
        toMove = 0;
        type = newType;
        this.target = target;
        progress = 0;
    }
    
    public int move(){
        toMove -= type.getSpeed();
        if(toMove<=0){
            progress++;
            toMove += 50;
        }
        return progress;
    }
    
    public AttackType getType(){
        return type;
    }
    
    public Mage getTarget(){
        return target;
    }
    
    public int getProgress(){
        return progress;
    }
}

package alexpansion.manabalance.entity;

import alexpansion.manabalance.moves.Attack;

/**
 *
 * @author Danny
 */


public class ActiveAttack{
    Attack type;
    int progress;
    int toMove;
    Mage target;
    
    public ActiveAttack(Attack newType,Mage target){
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
    
    public Attack getType(){
        return type;
    }
    
    public Mage getTarget(){
        return target;
    }
    
    public int getProgress(){
        return progress;
    }
}

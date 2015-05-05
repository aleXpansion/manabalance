/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexpansion.manabalance.entity;

import alexpansion.manabalance.moves.Shield;

/**
 *
 * @author buzzr
 */
public class ActiveShield {
    
    private Shield type;
    private Mage owner;
    
    public ActiveShield(Shield type,Mage caster){
        this.type = type;
        owner = caster;
    }
    
    //getter methods
    public Shield getType(){
        return type;
    }
    
    public Mage getOwner(){
        return owner;
    }
}

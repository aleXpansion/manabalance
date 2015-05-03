/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manabalance;

import alexpansion.manabalance.entity.Mage;

/**
 *
 * @author Danny
 */
public class Bunch {
    Mage owner;
    int mana;
    
    public void Bunch(Mage owner){
        this.owner = owner;
    }
    
    public Mage getOwner(){
        return owner;
    }
    
    public void takeMana(int amount){
        mana -= amount;
    }
}

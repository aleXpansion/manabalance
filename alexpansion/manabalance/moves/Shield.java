/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexpansion.manabalance.moves;

/**
 *
 * @author buzzr
 */
public class Shield {
    
    private String name;
    private int castCost;
    private int strength;
    
    public Shield(String name,int castCost,int strength){
        this.name = name;
        this.castCost = castCost;
        this.strength = strength;
    }
    
    public Shield(){
        this("basic",10,5);
    }
    
    public String getName(){
        return name;
    }
    public int getCastCost(){
        return castCost;
    }
    public int getStrength(){
        return strength;
    }
}

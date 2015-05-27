/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manabalance;

import java.util.ArrayList;

/**
 *
 * @author Danny
 */
public class Pool {
    private ArrayList<Bunch> bunches;
    int mixed = 0;

    public Pool() {
        this.bunches = new ArrayList<Bunch>();
    }
    
    public void addBunch(Bunch newBunch){
        bunches.add(newBunch);
    }
    
    public boolean extract(int amount){
        int toExtract = amount;
        while(toExtract>0){
            if(toExtract>mixed){
                toExtract -= mixed;
                mixed = 0;
                if(!mix()){
                    return false;
                }
            }
            else{
                mixed -= toExtract;
                return true;
            }
        }
        return false;
    }
    
    private boolean mix(){
        int count = 0;
        for(Bunch current:bunches){
            current.takeMana(1);
            count++;
        }
        mixed +=count;
        return true;
    }
    
}

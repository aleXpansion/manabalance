/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manabalance;

import alexpansion.manabalance.controllers.BattleController;
import alexpansion.manabalance.entity.Mage;
import alexpansion.manabalance.helper.TaskManager;

/**
 *
 * @author Danny
 */
public class ManaBalance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	TaskManager.init();
    	Mage player = new Mage("Danny", 10);
    	//player.build(ConstructType.Generator);
        @SuppressWarnings("unused")
		BattleController battle = new BattleController(player);
    }
    
}

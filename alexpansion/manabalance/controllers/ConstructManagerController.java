
package alexpansion.manabalance.controllers;

import alexpansion.manabalance.entity.Mage;


public class ConstructManagerController {
	
	private Mage owner;
	
	public ConstructManagerController(Mage owner){
		setOwner(owner);
	}

	/**
	 * @return the owner
	 */
	public Mage getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Mage owner) {
		this.owner = owner;
	}
}

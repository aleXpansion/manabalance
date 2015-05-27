package alexpansion.manabalance.entity;

public abstract class Construct {
	
	static String name;
	static int initCost;
	static int activeCost;
	boolean active;
	Mage owner;
	
	public Construct(String name, Mage owner, int initCost,int activeCost){
		Construct.initCost = initCost;
		Construct.activeCost = activeCost;
		this.owner = owner;
	}
	
	void activate(){
		active = true;
		onActivate();
	}
	
	void deactivate(){
		active = false;
		onDeactivate();
	}
	
	abstract void onActivate();
	abstract void onDeactivate();
	
	public String getName(){
		return name;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the initCost
	 */
	public int getInitCost() {
		return initCost;
	}

	/**
	 * @return the activeCost
	 */
	public int getActiveCost() {
		return activeCost;
	}

	/**
	 * @return the owner
	 */
	public Mage getOwner() {
		return owner;
	}
	
}

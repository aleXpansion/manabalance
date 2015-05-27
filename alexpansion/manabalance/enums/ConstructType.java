package alexpansion.manabalance.enums;


public enum ConstructType {
	Generator(100);
	private int cost;
	
	private ConstructType(int cost){
		this.setCost(cost);
		
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}

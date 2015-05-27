package alexpansion.manabalance.moves;

public class ShieldType {
    
    private String name;
    private int castCost;
    private int strength;
    
    public ShieldType(String name,int castCost,int strength){
        this.name = name;
        this.castCost = castCost;
        this.strength = strength;
    }
    
    public ShieldType(){
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
    
    public boolean equals(Object obj){
    	if(this == obj){
    		return true;
    	}
    	if(this.getClass() != obj.getClass()){
    		return false;
    	}
    	ShieldType other = (ShieldType) obj;
    	if(this.getCastCost() != other.getCastCost()){
    		return false;
    	}
    	if(this.getName() != other.getName()){
    		return false;
    	}
    	if(this.getStrength() != other.getStrength()){
    		return false;
    	}
    	
    	return true;
    }
}

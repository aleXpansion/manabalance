package alexpansion.manabalance.moves;

/**
 *
 * @author Danny
 */
public class Attack {

    private String name;
    private int power;
    private int cost;
    private int speed;

    public Attack(String name, int power, int cost, int speed) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.speed = speed;
    }
    public Attack(String name) {
        this(name, 5, 10, 4);
    }

    //getter functions
    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getCost() {
        return cost;
    }

    public int getSpeed() {
        return speed;
    }

}
